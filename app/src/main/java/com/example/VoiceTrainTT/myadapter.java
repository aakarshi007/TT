package com.example.VoiceTrainTT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)
    {
       holder.TrainNo.setText(model.getTrainNo());
       holder.Source.setText(model.getSource());
       holder.Destination.setText(model.getDestination());
       holder.Time.setText(model.getTime());
       holder.Type.setText(model.getType());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView TrainNo, Source, Destination, Time, Type;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            TrainNo=(TextView)itemView.findViewById(R.id.numb);
            Source=(TextView)itemView.findViewById(R.id.src);
            Destination=(TextView)itemView.findViewById(R.id.dst);
            Time=(TextView)itemView.findViewById(R.id.time);
            Type=(TextView)itemView.findViewById(R.id.type);
        }
    }
}
