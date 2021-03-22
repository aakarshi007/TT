package com.example.VoiceTrainTT;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

public class LocalMaps extends AppCompatActivity {
    ImageView imageView;
    ZoomControls zoomControls;
    @SuppressLint("ClickableViewAccessability")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_maps);
        imageView=(ImageView)findViewById(R.id.imgmap);
        zoomControls=(ZoomControls)findViewById(R.id.zoom_controls);
        zoomControls.hide();

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                zoomControls.show();
                return false;
            }
        });
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x=imageView.getScaleX();
                float y=imageView.getScaleY();
                imageView.setScaleX((float)(x+1));
                imageView.setScaleY((float)(y+1));
                zoomControls.hide();
            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x=imageView.getScaleX();
                float y=imageView.getScaleY();
                if(x==1 && y==1){
                    imageView.setScaleX(x);
                    imageView.setScaleY(y);
                }else{
                    imageView.setScaleX((float)(x-1));
                    imageView.setScaleY((float)(y-1));
                    zoomControls.hide();
                }

            }
        });

    }

}