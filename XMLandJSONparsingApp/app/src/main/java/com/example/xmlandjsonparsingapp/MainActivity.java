package com.example.xmlandjsonparsingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button xml,json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xml = findViewById(R.id.btn_xml);
        json = findViewById(R.id.btn_json);
        xml.setOnClickListener(this);
        json.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(xml)){
            Intent intent = new Intent(this,viewData.class);
            intent.putExtra("data","xml");
            startActivity(intent);
        }else if(view.equals(json)){
            Intent intent = new Intent(this,viewData.class);
            intent.putExtra("data","json");
            startActivity(intent);
        }
    }
}