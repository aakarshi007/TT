package com.example.VoiceTrainTT;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Localtrain extends AppCompatActivity {
//    DatabaseAccess databaseAccess;
    TextView txt;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localtrain);
//        databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        listView=(ListView)findViewById(R.id.list);
//        txt.setText(databaseAccess.getRecords());
        final ArrayList<String> list=new ArrayList<>();
        final ArrayAdapter adp=new ArrayAdapter<String>(this,R.layout.listview_item,list);
        listView.setAdapter(adp);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Locals").child("Central");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                List<String> keys =new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    keys.add(snapshot.getKey());
                    model model1=snapshot.getValue(model.class);
                    list.add(snapshot.getValue().toString());
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}