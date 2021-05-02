package com.example.VoiceTrainTT;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    TimePicker tp;
    String sourceStn;
    String destStn;
    RecyclerView recview;
    myadapter adapter;
    DatabaseReference df;
    SingleRowAct singleRowAct;
    TextToSpeech textToSpeech;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        singleRowAct = new SingleRowAct();
        tp = (TimePicker) findViewById(R.id.tp);
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
                //speakDetails();

            }
        }.start();

        Intent intent2 = getIntent();
        sourceStn = intent2.getStringExtra("SOURCE");
        destStn = intent2.getStringExtra("DEST");
        if (sourceStn.equalsIgnoreCase("Virar")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Western").orderByChild("Source").equalTo("Virar"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count = 1;
        } else if (sourceStn.equalsIgnoreCase("Vasai Road")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Western").orderByChild("Source").equalTo("Virar"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count = 1;
        } else if (sourceStn.equalsIgnoreCase("Bhayander")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Western").orderByChild("Source").equalTo("Virar"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count = 1;
        } else if (sourceStn.equalsIgnoreCase("Churchgate")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Western").orderByChild("Source").equalTo("Churchgate"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count = 2;
        } else if (sourceStn.equalsIgnoreCase("Mumbai Central")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Western").orderByChild("Source").equalTo("Churchgate"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count = 2;
        } else {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Metro").child("MetroUp"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count = 1;
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

    public void speakDetails() {
        final int hour = tp.getCurrentHour();
        int mins = tp.getCurrentMinute();
        final String ti = hour + ":" + mins;
        df = FirebaseDatabase.getInstance().getReference().child("Locals").child("Western");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp : dataSnapshot.getChildren()) {
                    String Source = dp.child("Source").getValue().toString();
                    String Destination = dp.child("Destination").getValue().toString();
                    String Time = dp.child("Time").getValue().toString();
                    String Type = dp.child("Type").getValue().toString();
                    if (count == 1) {
                        if (hour == 8) {
                            textToSpeech.speak("One Train is available at... " + "8:35" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 9) {
                            textToSpeech.speak("One Train is available at... " + "9:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 10) {
                            textToSpeech.speak("One Train is available at... " + "10:10" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 11) {
                            textToSpeech.speak("One Train is available at... " + "11" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 12) {
                            textToSpeech.speak("One Train is available at... " + "12:30" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 13) {
                            textToSpeech.speak("One Train is available at... " + "13:00" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 13) {
                            textToSpeech.speak("One Train is available at... " + "13:30" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 14) {
                            textToSpeech.speak("One Train is available at... " + "14:00" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 14) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 15) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 16) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 17) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        }

                    } else if (count == 2) {
                        if (hour == 8) {
                            textToSpeech.speak("One Train is available at... " + "8:35" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 9) {
                            textToSpeech.speak("One Train is available at... " + "9:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 10) {
                            textToSpeech.speak("One Train is available at... " + "10:10" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 11) {
                            textToSpeech.speak("One Train is available at... " + "11" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 12) {
                            textToSpeech.speak("One Train is available at... " + "12:30" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 13) {
                            textToSpeech.speak("One Train is available at... " + "13:00" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 13) {
                            textToSpeech.speak("One Train is available at... " + "13:30" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 14) {
                            textToSpeech.speak("One Train is available at... " + "14:00" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 14) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 15) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 16) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if (hour == 17) {
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....K" + Destination, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}