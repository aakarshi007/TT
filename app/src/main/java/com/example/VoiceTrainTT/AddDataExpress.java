package com.example.VoiceTrainTT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDataExpress extends AppCompatActivity {
    EditText trainno,source,dest,time,trainame;
    Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dataexpress);
        trainno=(EditText)findViewById(R.id.trainno1);
        source=(EditText)findViewById(R.id.src1);
        dest=(EditText)findViewById(R.id.dest1);
        time=(EditText)findViewById(R.id.time1);
        trainame=(EditText)findViewById(R.id.type1);

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminControlExpress.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processInsert();
            }
        });
    }
    private void processInsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("TrainNo",trainno.getText().toString());
        map.put("Source",source.getText().toString());
        map.put("Destination",dest.getText().toString());
        map.put("Time",time.getText().toString());
        map.put("TrainName",trainame.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Express").child("ExpressDown").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        trainno.setText("");
                        source.setText("");
                        dest.setText("");
                        time.setText("");
                        trainame.setText("");

                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}