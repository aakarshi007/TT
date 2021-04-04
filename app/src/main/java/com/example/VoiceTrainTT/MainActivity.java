package com.example.VoiceTrainTT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.widget.TimePicker;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;
import java.util.Queue;

public class MainActivity extends AppCompatActivity
{
    TimePicker tp;
    String sourceStn;
    String destStn;
    RecyclerView recview;
   myadapter adapter;
   DatabaseReference df;
   SingleRowAct singleRowAct;
   TextToSpeech textToSpeech;
   int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        singleRowAct = new SingleRowAct();
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        new CountDownTimer(2000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                speakDetails();

            }
        }.start();

        Intent intent2 = getIntent();
        sourceStn = intent2.getStringExtra("SOURCE");
        destStn = intent2.getStringExtra("DEST");
        if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Byculla")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").equalTo("CSMT"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=1;
        } else if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Dadar")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").startAt("C").endAt("M\uf8ff"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=2;

        }
        else if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Kurla")){
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").startAt("C").endAt("M\uf8ff"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
        }
        else if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Ghatkopar")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").startAt("C").endAt("M\uf8ff"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=3;
        }
        else if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Mulund")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").startAt("C").endAt("M\uf8ff"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=4;
        }
        else
        {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void speakDetails(){
//        int hour=tp.getCurrentHour();
//        int mins=tp.getCurrentMinute();
        df=FirebaseDatabase.getInstance().getReference().child("Locals").child("CentralUp");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp : dataSnapshot.getChildren()) {
                    String Source = dp.child("Source").getValue().toString();
                    String Destination = dp.child("Destination").getValue().toString();
                    String Time = dp.child("Time").getValue().toString();
                    String Type = dp.child("Type").getValue().toString();
                    textToSpeech.speak("Train is Available... at... " + Time + "...Source is..." + Source + "...Destination is.... " + Destination + "..." + Type + "... Car", TextToSpeech.QUEUE_FLUSH, null, null);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}