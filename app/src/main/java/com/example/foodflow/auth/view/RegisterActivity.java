package com.example.foodflow.auth.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodflow.MainActivity;
import com.example.foodflow.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final String TAG = "AUTH_RESULT";
    private Button signUpBtn;
    public static final int RC_SIGN_IN = 321;
    private Button btnSignInWithGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    EditText email;
    EditText password;
    EditText confirmPassword;
    EditText userName;
    TextView navToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUpBtn = findViewById(R.id.signUpButton);
        btnSignInWithGoogle = findViewById(R.id.btnGoogleSignIn);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        confirmPassword = findViewById(R.id.confirmPasswordField);
        userName = findViewById(R.id.userNameField);
        navToLogin = findViewById(R.id.navToLoginTv);

        mAuth = FirebaseAuth.getInstance();
        requestGoogleSignIn();

        btnSignInWithGoogle.setOnClickListener(view -> signInWithGoogle());
        signUpBtn.setOnClickListener(v -> {
            if (areFieldsValid()) {
                if (isPasswordsMatch()) {

                    createAccount(userName.getText().toString(), email.getText().toString(), password.getText().toString());
                } else {

                    Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Please, Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        navToLogin.setOnClickListener(v -> {
            navigate(LoginActivity.class);
        });
    }

    private boolean areFieldsValid() {
        return !TextUtils.isEmpty(email.getText()) &&
                !TextUtils.isEmpty(password.getText()) &&
                !TextUtils.isEmpty(userName.getText());
    }

    private boolean isPasswordsMatch() {
        return password.getText().toString().equals(confirmPassword.getText().toString());
    }

    void createAccount(String username, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();

                if (user != null) {

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username).setPhotoUri(Uri.parse("https://cdn4.iconfinder.com/data/icons/green-shopper/1068/user.png"))
                            .build();
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Log.d(TAG, "User profile updated.");
                                    finish();
                                    navigate(MainActivity.class);
                                }
                            });

//                    user.sendEmailVerification().addOnCompleteListener(sendVerifyEmailTask -> {
//
//                    });
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());

                Toast.makeText(RegisterActivity.this, getUserErrorMessage(task), Toast.LENGTH_LONG).show();
            }
        });
    }

    String getUserErrorMessage(Task<AuthResult> task) {
        String errorMessage;
        if (task.getException() instanceof FirebaseAuthInvalidUserException) {
            // Invalid email address
            errorMessage = "Invalid email address. Please try again.";
        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
            // Invalid password
            errorMessage = "Invalid password. Please try again.";
        } else {
            // Other error
            errorMessage = "Authentication failed. Please try again.";
        }
        return errorMessage;
    }

    private void requestGoogleSignIn() {
        // Configure sign-in to request the user’s basic profile like name and email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("719276620910-28306ej300ssu0fmvccki1jm5a81cm30.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(String idToken) {

        //getting user credentials with the help of AuthCredential method and also passing user Token Id.
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        //trying to sign in user using signInWithCredential and passing above credentials of user.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");

                            navigate(MainActivity.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "User authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
                String userName = account.getDisplayName();
                String userEmail = account.getEmail();
                String userPhoto = account.getPhotoUrl().toString();
                storeUserCredentials(userName, userEmail, userPhoto);

                Log.i(TAG, "onActivityResult: Success");
            } catch (ApiException e) {
                Log.e(TAG, "onActivityResult: " + e.getMessage());
            }
        }
    }

    private void storeUserCredentials(String userName, String userEmail, String userPhoto) {
        userPhoto = userPhoto + "?type=large";
        //create sharedPreference to store user data when user signs in successfully
        SharedPreferences.Editor editor = getApplicationContext()
                .getSharedPreferences("MyPrefs", MODE_PRIVATE)
                .edit();
        editor.putString("username", userName);
        editor.putString("useremail", userEmail);
        editor.putString("userPhoto", userPhoto);
        editor.apply();
    }

    void navigate(Class<?> cls) {
        Intent intent = new Intent(RegisterActivity.this, cls);
        startActivity(intent);
    }


}