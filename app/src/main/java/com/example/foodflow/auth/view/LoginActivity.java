package com.example.foodflow.auth.view;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String TAG = "AUTH_RESULT";
    private Button loginBtn;
    EditText email;
    EditText password;
    TextView navToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginButton);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        navToRegister = findViewById(R.id.navToSignUp);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText())) {
                Toast.makeText(LoginActivity.this, "Email and Password can't be left blank", Toast.LENGTH_SHORT).show();
            } else logIn(email.getText().toString(), password.getText().toString());
        });

        navToRegister.setOnClickListener(v -> finish());
    }

    void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    finish();
                    navigate(MainActivity.class);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, getUserErrorMessage(task), Toast.LENGTH_LONG).show();

                }
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

    void navigate(Class<?> cls) {
        Intent intent = new Intent(LoginActivity.this, cls);
        startActivity(intent);
    }
}