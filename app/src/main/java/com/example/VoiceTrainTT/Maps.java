package com.example.VoiceTrainTT;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

public class Maps extends MainActivity{
    ImageView imageView;
    ZoomControls zoomControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        imageView=(ImageView)findViewById(R.id.maps);
//        zoomControls=(ZoomControls)findViewById(R.id.zoom_controls);
//        zoomControls.hide();
//
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return false;
//            }
//        });
//        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                float x=imageView.getScaleX();
//                float y=imageView.getScaleY();
//                imageView.setScaleX((float)(x+1));
//                imageView.setScaleY((float)(y+1));
//                zoomControls.hide();
//            }
//        });
//        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                float x=imageView.getScaleX();
//                float y=imageView.getScaleY();
//                if(x==1 && y==1){
//                    imageView.setScaleX(x);
//                    imageView.setScaleY(y);
//                }else{
//                    imageView.setScaleX((float)(x-1));
//                    imageView.setScaleY((float)(y-1));
//                    zoomControls.hide();
//                }
//
//            }
//        });

    }

}
