package com.example.VoiceTrainTT;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailid, pwd;
    ProgressBar progressBar;
    DatabaseReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_page);


        emailid = (EditText) findViewById(R.id.input_email);
        pwd = (EditText) findViewById(R.id.input_password);
        progressBar=(ProgressBar)findViewById(R.id.progressBar4);
        mAuth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);

    }
    public void doLogin(View v){
        final String email = emailid.getText().toString().trim();
        final String password = pwd.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            emailid.setError("Email is Required.");
            return;
        }

        if(TextUtils.isEmpty(password)){
            pwd.setError("Password is Required.");
            return;
        }

        if(password.length() < 6){
            pwd.setError("Password Must be >= 6 Characters");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        df= FirebaseDatabase.getInstance().getReference("Admin");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp : dataSnapshot.getChildren()) {
                    String Fullname = dp.child("Fullname").getValue().toString();
                    String Username = dp.child("Username").getValue().toString();
                    String Password = dp.child("Password").getValue().toString();
                    if ((email.equalsIgnoreCase(Username) && password.equalsIgnoreCase(Password))) {
                        Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),AdminHomepage.class);
                        startActivity(i);                }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}