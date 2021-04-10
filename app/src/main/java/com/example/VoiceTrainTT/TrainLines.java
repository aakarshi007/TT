package com.example.VoiceTrainTT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class TrainLines extends AppCompatActivity {
    String [] lines={"CENTRAL","WESTERN","HARBOUR"};
    String [] directions={"UP","DOWN"};
    Spinner spinner1,spinner2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_lines);
        img=(ImageView)findViewById(R.id.spinButton);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String >(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,lines);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String >(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,directions);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spin1=spinner1.getSelectedItem().toString();
                String spin2=spinner2.getSelectedItem().toString();
                if((spin1.equalsIgnoreCase("central"))&& (spin2.equalsIgnoreCase("Up"))){
                    startActivity(new Intent(getApplicationContext(),AdminControl.class));

                }
                else if((spin1.equalsIgnoreCase("Central"))&& (spin2.equalsIgnoreCase("down"))){
                    startActivity(new Intent(getApplicationContext(),AdminControl.class));

                }
                else if((spin1.equalsIgnoreCase("western"))&& (spin2.equalsIgnoreCase("Up"))){
                    startActivity(new Intent(getApplicationContext(),AdminControlWestern.class));

                }
                else if((spin1.equalsIgnoreCase("western"))&& (spin2.equalsIgnoreCase("down"))){
                    startActivity(new Intent(getApplicationContext(),AdminControlWestern.class));

                }
                else if((spin1.equalsIgnoreCase("harbour"))&& (spin2.equalsIgnoreCase("up"))){
                    startActivity(new Intent(getApplicationContext(),AdminControlHarbour.class));

                }
                else{
                    startActivity(new Intent(getApplicationContext(),AdminControlHarbour.class));

                }
            }
        });


    }
}