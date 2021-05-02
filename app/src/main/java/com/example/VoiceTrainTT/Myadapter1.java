package com.example.VoiceTrainTT;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Myadapter1 extends FirebaseRecyclerAdapter<model, Myadapter1.myviewholder>
{
    public Myadapter1(@NonNull FirebaseRecyclerOptions<model> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {
       holder.TrainNo.setText(model.getTrainNo());
       holder.Source.setText(model.getSource());
       holder.Destination.setText(model.getDestination());
       holder.Time.setText(model.getTime());
       holder.Type.setText(model.getType());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.TrainNo.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText TrainNo=myview.findViewById(R.id.trainno);
                final EditText Source=myview.findViewById(R.id.source);
                final EditText Destination=myview.findViewById(R.id.dest);
                final EditText Time=myview.findViewById(R.id.times);
                final EditText Type=myview.findViewById(R.id.types);

                Button submit=myview.findViewById(R.id.usubmit);

                TrainNo.setText(model.getTrainNo());
                Source.setText(model.getSource());
                Destination.setText(model.getDestination());
                Time.setText(model.getTime());
                Type.setText(model.getType());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("TrainNo",TrainNo.getText().toString());
                        map.put("Source",Source.getText().toString());
                        map.put("Destination",Destination.getText().toString());
                        map.put("Time",Time.getText().toString());
                        map.put("Type",Type.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("Locals").child("Central")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.TrainNo.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Locals").child("Central")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_admin,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView TrainNo, Source, Destination, Time, Type;
        ImageView edit,delete;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            TrainNo=(TextView)itemView.findViewById(R.id.numb);
            Source=(TextView)itemView.findViewById(R.id.src);
            Destination=(TextView)itemView.findViewById(R.id.dst);
            Time=(TextView)itemView.findViewById(R.id.time);
            Type=(TextView)itemView.findViewById(R.id.type);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
