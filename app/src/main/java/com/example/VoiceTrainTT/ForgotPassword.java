package com.example.VoiceTrainTT;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText input_email;
    private Button button2;
    private ProgressBar progressBar3;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        input_email=(EditText)findViewById(R.id.email);
        button2=(Button)findViewById(R.id.button2);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);

        auth= FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2();
            }
        });
    }

    private void button2() {
        String email=input_email.getText().toString().trim();

        if (email.isEmpty()){
            input_email.setError("Email is required");
            input_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            input_email.setError("please provide valid email");
            input_email.requestFocus();
            return;
        }
        progressBar3.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"check your email to reset your password!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgotPassword.this,"Try again! something wrong happened", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}