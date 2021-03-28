package com.example.VoiceTrainTT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
   RecyclerView recview;
   myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        Intent intent1=getIntent();
        String message1=intent1.getStringExtra(SourceDestination.msg1);
//        Intent intent2=getIntent();
//        String message2=intent2.getStringExtra(SourceDestination.msg2);
        if (message1.equalsIgnoreCase("Mumbai CST")) {

            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("Destination").equalTo("Kalyan"), model.class)
                            .build();
            //  Query query=FirebaseDatabase.getInstance().getReference().child("Locals").child("Central").orderByChild("TrainNo").equalTo("12101");


            adapter = new myadapter(options);
            recview.setAdapter(adapter);
        }
    }
    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
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
}