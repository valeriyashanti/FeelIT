package com.login.valeriya.feelit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";


    @BindView(R.id.input_name)
    EditText mNameText;
    @BindView(R.id.input_email) EditText mEmailText;
    @BindView(R.id.input_mobile) EditText mMobileText;
    @BindView(R.id.input_password) EditText mPasswordText;
    @BindView(R.id.input_reEnterPassword) EditText mReEnterPasswordText;
    @BindView(R.id.btn_signup)
    Button mSignupButton;
    @BindView(R.id.link_login)
    TextView mLoginLink;

    private FirebaseAuth mAuth;
    private CollectionReference mUserRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: start");

        mAuth = FirebaseAuth.getInstance();
        mUserRef = FirebaseFirestore.getInstance().collection("users");

        mSignupButton.setOnClickListener(v -> signup());
        mLoginLink.setOnClickListener(v -> {
            // Finish the registration screen and return to the Login activity
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void signup() {
        Log.d(TAG, "signup: start");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        mSignupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String mobile = mMobileText.getText().toString();
        String password = mPasswordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        loadToDatabase(user, name, mobile);
                        progressDialog.dismiss();
                        onSignupSuccess();
                    } else {
                        progressDialog.dismiss();
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Sign up failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void loadToDatabase(FirebaseUser user, String name, String mobile) {
        Log.d(TAG, "loadToDatabase: start");
        Map<String, Object> newUser= new HashMap<>();
        newUser.put("name", name);
        newUser.put("mobile", mobile);
        newUser.put("dataIsLoaded", false);

        mUserRef.document(user.getUid())
                .set(newUser)
                .addOnFailureListener(e -> {
                    Log.d(TAG, "onFailure: fail to load to Firestore");
                    user.delete()
                            .addOnCompleteListener(task -> {
                                Toast.makeText(getApplicationContext(), "Fail: try to sign up later", Toast.LENGTH_LONG).show();
                                mSignupButton.setEnabled(true);
                            });
                })
                .addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: successfully loaded data to Firestore"));
    }

    public void onSignupSuccess() {
        Log.d(TAG, "onSignupSuccess: ");
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        Log.d(TAG, "onSignupFailed: ");
        Toast.makeText(getBaseContext(), "Please, fill the data correctly", Toast.LENGTH_LONG).show();
        mSignupButton.setEnabled(true);
    }

    public boolean validate() {
        Log.d(TAG, "validate: start");
        boolean valid = true;

        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String mobile = mMobileText.getText().toString();
        String password = mPasswordText.getText().toString();
        String reEnterPassword = mReEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            mNameText.setError("at least 3 characters");
            valid = false;
        } else {
            mNameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            mMobileText.setError("enter 9 digits");
            valid = false;
        } else {
            mMobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 10) {
            mPasswordText.setError("between 6 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            mReEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            mReEnterPasswordText.setError(null);
        }

        Log.d(TAG, "validate: end");
        return valid;
    }
}
