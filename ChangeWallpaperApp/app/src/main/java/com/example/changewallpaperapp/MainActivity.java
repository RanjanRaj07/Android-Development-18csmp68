package com.example.changewallpaperapp;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    boolean running;
    int[] ia = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.change_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(!running){
            new Timer().schedule(new MyTask(),0,3000);
            running=true;
        }
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            try{
                WallpaperManager wm =WallpaperManager.getInstance(getBaseContext());
                Random r = new Random();
                wm.setBitmap(BitmapFactory.decodeResource(getResources(),ia[r.nextInt(6)]));
            }
            catch(Exception e){
                Toast.makeText(MainActivity.this, "error changing wallpapers", Toast.LENGTH_LONG).show();
            }

        }
    }
}