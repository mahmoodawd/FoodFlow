package com.example.foodflow.auth.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodflow.MainActivity;
import com.example.foodflow.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String TAG = "AUTH_RESULT";
    private Button loginBtn;
    public static final int RC_SIGN_IN = 321;
    private SignInButton btnSignInWithGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginButton);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        btnSignInWithGoogle = findViewById(R.id.btnGoogleSignIn);
        mAuth = FirebaseAuth.getInstance();
        requestGoogleSignIn();

        btnSignInWithGoogle.setOnClickListener(view -> {
            signInWithGoogle();
        });
        loginBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText())) {
                Toast.makeText(LoginActivity.this, "Email and Password can't be left blank", Toast.LENGTH_SHORT).show();
            } else logIn(email.getText().toString(), password.getText().toString());
        });
    }

    void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                    navigate(MainActivity.class);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void requestGoogleSignIn() {
        // Configure sign-in to request the user’s basic profile like name and email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
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

                            // Sign in success, navigate user to Profile Activity
                            navigate(MainActivity.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "User authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //authenticating user with firebase using received token id
                firebaseAuthWithGoogle(account.getIdToken());
                //assigning user information to variables
                String userName = account.getDisplayName();
                String userEmail = account.getEmail();
                String userPhoto = account.getPhotoUrl().toString();
                userPhoto = userPhoto + "?type=large";
                //create sharedPreference to store user data when user signs in successfully
                SharedPreferences.Editor editor = getApplicationContext()
                        .getSharedPreferences("MyPrefs", MODE_PRIVATE)
                        .edit();
                editor.putString("username", userName);
                editor.putString("useremail", userEmail);
                editor.putString("userPhoto", userPhoto);
                editor.apply();
                Log.i(TAG, "onActivityResult: Success");
            } catch (ApiException e) {
                Log.e(TAG, "onActivityResult: " + e.getMessage());
            }
        }
    }

    void navigate(Class<?> cls) {
        Intent intent = new Intent(LoginActivity.this, cls);
        startActivity(intent);
    }
}