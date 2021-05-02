package com.example.VoiceTrainTT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ExpressMetro2 extends AppCompatActivity {

    String [] directions={"UP","DOWN"};
    Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_metro2);

        spinner1=(Spinner)findViewById(R.id.spin1);
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String >(getApplicationContext(),android.R.layout.simple_spinner_item,directions);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner2=(Spinner)findViewById(R.id.spin2);
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String >(getApplicationContext(),android.R.layout.simple_spinner_item,directions);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);

    }
    public  void goExpress(View v){
        String spin2=spinner2.getSelectedItem().toString();

        if(spin2.equalsIgnoreCase("up")){
            startActivity(new Intent(getApplicationContext(),AdminControlExpress.class));

        }
        else {
            startActivity(new Intent(getApplicationContext(),AdminControlExpress.class));

        }

    }
    public  void goMetro(View v){
        String spin1=spinner1.getSelectedItem().toString();
        if(spin1.equalsIgnoreCase("up")){
            startActivity(new Intent(getApplicationContext(),AdminControlMetroUp.class));

        }

        else{
            startActivity(new Intent(getApplicationContext(),AdminControlMetro.class));

        }
    }
}