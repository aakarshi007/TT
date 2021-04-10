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

public class MainActivityExpress extends AppCompatActivity
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
        setContentView(R.layout.activity_mainexpress);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        singleRowAct = new SingleRowAct();
        tp=(TimePicker)findViewById(R.id.tp);
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
            count=3;

        }
        else if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Ghatkopar")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").startAt("C").endAt("M\uf8ff"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=4;
        }
        else if (sourceStn.equalsIgnoreCase("Mumbai CST") && destStn.equalsIgnoreCase("Mulund")) {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Source").startAt("C").endAt("M\uf8ff"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=5;
        }
        else
        {
            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central"), model.class)
                            .build();
            adapter = new myadapter(options);
            recview.setAdapter(adapter);
            count=6;
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
        final int hour=tp.getCurrentHour();
        int mins=tp.getCurrentMinute();
        final String ti=hour+":"+mins;
        df= FirebaseDatabase.getInstance().getReference().child("Locals").child("Central");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp : dataSnapshot.getChildren()) {
                    String Source = dp.child("Source").getValue().toString();
                    String Destination = dp.child("Destination").getValue().toString();
                    String Time = dp.child("Time").getValue().toString();
                    String Type = dp.child("Type").getValue().toString();
                    if(count==1) {
                        if(hour == 6){
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....Kalyan..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else if(hour==7) {
                            textToSpeech.speak("One Train is available  at... " + "7:04" + "...Source is..." + Source + "...Destination is....Badlapur..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else if(hour==8) {
                            textToSpeech.speak("One Train is available at... " + "8:15" + "...Source is..." + Source + "...Destination is....Karjat..." + "Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if(hour==9) {
                            textToSpeech.speak("One Train is available at... " + "9" + "...Source is..." + Source + "...Destination is....Khopoli..." + "Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if(hour==10) {
                            textToSpeech.speak("Two Trains are  Available...one is  at... " + "10" + "...Source is..." + Source + "...Destination is....Badlapur..." + Type + "... and another one is at 10:30...Source is..." + Source + "...Destination is....Dombivli...Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if(hour==11) {
                            textToSpeech.speak("Two Trains are  Available...one is  at... " + "11" + "...Source is..." + Source + "...Destination is....Kalyan..." + "Fast" + "... and another one is at 11:30...Source is..." + Source + "...Destination is....Asangaon..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else if(hour==12) {
                            textToSpeech.speak("One Train is available at... " + "12:30" + "...Source is..." + Source + "...Destination is....Dombivli..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==13) {
                            textToSpeech.speak("Two Trains are  Available...one is  at... " + "13:00" + "...Source is..." + Source + "...Destination is....Kasara..." + Type + "... and another one is at 13:30...Source is..." + Source + "...Destination is....Thane..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==14) {
                            textToSpeech.speak("Two Trains are  Available...one is  at... " + "14" + "...Source is..." + Source + "...Destination is....Kalyan..." + Type+ "... and another one is at 14:30...Source is..." + Source + "...Destination is....Karjat..." + "Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==15) {
                            textToSpeech.speak("Two Trains are  Available...one is  at... " + "15:05" + "...Source is..." + Source + "...Destination is....Kalyan..." + Type + "... and another one is at 15:40...Source is..." + Source + "...Destination is....Dombivli..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==16) {
                            textToSpeech.speak("One train  is available  at... " + "4" + "...Source is..." + Source + "...Destination is....Kasara...", TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==17) {
                            textToSpeech.speak("One train  is available  at... " + "17:30" + "...Source is..." + Source + "...Destination is....Thane...", TextToSpeech.QUEUE_FLUSH, null, null);
                        }

                    }
                    else if(count==6){
                        if(hour == 6){
                            textToSpeech.speak("One Train is available at... " + "6:05" + "...Source is..." + Source + "...Destination is....Kalyan..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else if(hour==7) {
                            textToSpeech.speak("One Train is available  at... " + "7:04" + "...Source is..." + Source + "...Destination is....Badlapur..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else if(hour==8) {
                            textToSpeech.speak("One Train is available at... " + "8:15" + "...Source is..." + Source + "...Destination is....Karjat..." + "Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if(hour==9) {
                            textToSpeech.speak("One Train is available at... " + "9" + "...Source is..." + Source + "...Destination is....Khopoli..." + "Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if(hour==10) {
                            textToSpeech.speak("Three Trains are  Available...one is  at... " + "10" + "...Source is..." + Source + "...Destination is....Badlapur..." + Type + "... and another one is at 10:30...Source is..." + Source + "...Destination is....Dombivli...Fast and another one is at 10:00...Source is... Dadar + ...Destination is....Thane...SLow", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else if(hour==11) {
                            textToSpeech.speak("Two Trains are  Available...one is  at... " + "11" + "...Source is..." + Source + "...Destination is....Kalyan..." + "Fast" + "... and another one is at 11:30...Source is..." + Source + "...Destination is....Asangaon..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else if(hour==12) {
                            textToSpeech.speak("One Train is available at... " + "12:30" + "...Source is..." + Source + "...Destination is....Dombivli..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==13) {
                            textToSpeech.speak("Two Trains are  Available... one is  at... " + "13:15" + "...Source is..." + Source + "...Destination is....Kasara..." + Type + "... and another one is at 13:30...Source is..." + Source + "...Destination is....Thane..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==14) {
                            textToSpeech.speak("Two Trains are  Available... one is  at... " + "14" + "...Source is..." + Source + "...Destination is....Kalyan..." + Type+ "... and another one is at 14:30...Source is..." + Source + "...Destination is....Karjat..." + "Fast", TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==15) {
                            textToSpeech.speak("Two Trains are  Available .... one is  at... " + "15:5" + "...Source is..." + Source + "...Destination is....Kalyan..." + Type + "... and another one is at 15:40...Source is..." + Source + "...Destination is....Dombivli..." + Type, TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==16) {
                            textToSpeech.speak("One train  is available  at... " + "4" + "...Source is..." + Source + "...Destination is....Kasara...", TextToSpeech.QUEUE_FLUSH, null, null);
                        }else if(hour==17) {
                            textToSpeech.speak("One train  is available  at... " + "17:30" + "...Source is..." + Source + "...Destination is....Thane...", TextToSpeech.QUEUE_FLUSH, null, null);
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