package com.example.VoiceTrainTT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {
    EditText Email,Number,Feedback;
    String email,number,feed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Email = (EditText) findViewById(R.id.email);
        Number = (EditText) findViewById(R.id.number);
        Feedback = (EditText) findViewById(R.id.feedback);
        email=Email.getText().toString();
        number=Number.getText().toString();
        feed=Feedback.getText().toString();

    }
    public void submitFeedback(View v){
        Feedbackdb fdb1=new Feedbackdb(email,number,feed);
        FirebaseDatabase.getInstance().getReference("Feedback")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(fdb1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Your feedback submitted successfully!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}