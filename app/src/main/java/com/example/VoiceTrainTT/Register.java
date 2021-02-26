package com.example.VoiceTrainTT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name, mobno, emailid, username, pwd, cpwd;
    Button register;
    TextView account;
    ProgressBar pb;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.fullname);
        mobno = (EditText) findViewById(R.id.mobno);
        emailid = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.usrname);
        pwd = (EditText) findViewById(R.id.pwd);
        cpwd = (EditText) findViewById(R.id.cpwd1);
        register = (Button) findViewById(R.id.btnRegister);
        account = (TextView) findViewById(R.id.accnt);
        pb = (ProgressBar) findViewById(R.id.progressBar);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailid.getText().toString().trim();
                String password = pwd.getText().toString().trim();
                String c_password = cpwd.getText().toString().trim();

                final String fullName = name.getText().toString();
                final String phone = mobno.getText().toString();


                if (fullName.isEmpty()) {
                    name.setError("Full name is required");
                    name.requestFocus();
                    return;
                }
                if (phone.isEmpty() || phone.length() > 10) {
                    mobno.setError("Mobile number is required !! Please Enter Valid Mobile Number");
                    mobno.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    emailid.setError("Email aaddress is required");
                    emailid.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailid.setError("Provide Valid Email ID");
                    emailid.requestFocus();
                    return;
                }
                if (password.isEmpty() && (password.length() < 8 && password.length() > 15)) {
                    pwd.setError("Minimum Password length should be 8 to 15 Characters");
                    pwd.requestFocus();
                    return;
                }
                if (c_password == password) {
                    cpwd.setError("Password and Confirm Password do not match");
                    cpwd.requestFocus();
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(fullName, phone, email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                                                pb.setVisibility(View.GONE);

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Failed !!TRY AGAIN", Toast.LENGTH_LONG).show();
                                                pb.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_LONG).show();
                                    pb.setVisibility(View.GONE);

                                }
                            }
                        });
            }
        });
    }

    public void loginPage(View v) {

        startActivity(new Intent(getApplicationContext(), Login_activity.class));
    }
}