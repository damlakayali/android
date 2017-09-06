package com.damlakayali.fbkullanimi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    String TAG="LoginActivity.java";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        etEmail= (EditText)findViewById(R.id.loginEmail);
        etPassword=(EditText)findViewById(R.id.loginPassword);
        btnLogin=(Button)findViewById(R.id.btnGiris);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                LoginClick(email,password);
            }
        });

    }


    private void LoginClick(String email,String password){

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Email adresinizi yazınız.", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Şifrenizi yazınız.", Toast.LENGTH_LONG).show();
        } else if(password.length() < 6){
            Toast.makeText(getApplicationContext(),"Şifre Çok Kısa", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(LoginActivity.this, "HATA",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
           // mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
