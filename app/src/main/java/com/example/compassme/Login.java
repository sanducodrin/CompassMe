package com.example.compassme;

import android.content.Intent;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.*;

import java.security.*;

public class Login extends AppCompatActivity {

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TextView login_new_account = findViewById(R.id.login_new_account);
        TextView login_new_password = findViewById(R.id.login_new_password);

        TextInputLayout login_email_layout = findViewById(R.id.login_email_layout);
        TextInputLayout login_password_layout = findViewById(R.id.login_password_layout);

        TextInputEditText login_email = findViewById(R.id.login_email);
        TextInputEditText login_password = findViewById(R.id.login_password);

        Button login_btn = findViewById(R.id.login_btn);

        login_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToNewAccount();
            }
        });

        login_new_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { GoToChangePassword(); }
        });

        login_btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login_email.setError(null);
                login_email_layout.setError(null);
                login_password.setError(null);
                login_password_layout.setError(null);

                if(emailValidator(login_email, login_email_layout) && passwordValidator(login_password, login_password_layout)) {
                    email = login_email.getText().toString();
                    password = login_password.getText().toString();
                }
            }
        }));

    }

    public boolean emailValidator(EditText etMail, TextInputLayout etMailLayout) {
        String emailToText = etMail.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true;
        } else {
            etMailLayout.setError("Adresă de Email invalidă!");
            return false;
        }
    }

    public boolean passwordValidator(EditText etPassword, TextInputLayout etPasswordLayout) {
        String passwordToText = etPassword.getText().toString();
        if (!passwordToText.isEmpty()) {
            return true;
        } else {
            etPasswordLayout.setError("Parolă invalidă!");
            return false;
        }
    }

    public static String hashPassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Update the digest with the password bytes
            md.update(password.getBytes());
            // Get the hashed password bytes
            byte[] hashedBytes = md.digest();
            // Convert the hashed bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            // Return the hashed password as a hexadecimal string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException
            e.printStackTrace();
            return null;
        }
    }

    private void GoToChangePassword() {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }

    private void GoToNewAccount() {
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }

    private void GoToCandidate(){
        Intent intent = new Intent(this, Candidate.class);
        startActivity(intent);
    }

    private void GoToCompany(){
        Intent intent = new Intent(this, Company.class);
        startActivity(intent);
    }
}