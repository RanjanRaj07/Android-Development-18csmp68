package com.example.counterapp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button start,stop;
    TextView cvalue;
    public int counter=0;
    public boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.btn_start);
        stop = findViewById(R.id.btn_stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        cvalue = findViewById(R.id.cvalue);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(start)) {
            counterStart();
        }
        else if(view.equals(stop)){
            counterStop();
        }

    }

    private void counterStart() {
        counter=0;
        running=true;
        System.out.println("start->"+Thread.currentThread().getName());
        new MyCounter().start();
        start.setEnabled(false);
        stop.setEnabled(true);
    }

    private void counterStop() {
        this.running=false;
        start.setEnabled(true);
        stop.setEnabled(false);
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            cvalue.setText(formatTime(msg.what));
        }
        private String formatTime(long time) {
            long minutes = (time / 60000) % 60;
            long seconds = (time / 1000) % 60;
            long milliseconds = time % 1000;
            return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
        }
    };

    class MyCounter extends Thread {
        public void run(){
            System.out.println("MyCounter -> "+Thread.currentThread().getName());
            while(running){
                counter++;
                handler.sendEmptyMessage(counter);
                try{
                    sleep(1);
                }catch(Exception e){}
            }
        }
    }
}