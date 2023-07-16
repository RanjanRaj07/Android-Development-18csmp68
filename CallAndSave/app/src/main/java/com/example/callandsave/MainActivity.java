package com.example.callandsave;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CALL = 1;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnStar,btnHash;
    Button btnCall,btnDel,btnSave;
    EditText viewText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        btnStar = findViewById(R.id.btnStar);
        btnStar.setOnClickListener(this);

        btnHash = findViewById(R.id.btnHash);
        btnHash.setOnClickListener(this);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        viewText = findViewById(R.id.viewArea);
        viewText.setText("");
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String originalText = viewText.getText().toString();
        String updatedText;
        if(v.equals(btnSave)){
            String phno = viewText.getText().toString();
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME,"");
            intent.putExtra(ContactsContract.Intents.Insert.PHONE,phno);
            startActivity(intent);
        }else if(v.equals(btnCall)){
            makePhoneCall();
        }else if(v.equals(btnDel)){
            if (originalText.length() > 0) {
                updatedText = originalText.substring(0, originalText.length() - 1);
                viewText.setText("");
                viewText.append(updatedText);
            }
        }else{
            viewText.append(btn.getText());
        }
    }
    private void makePhoneCall() {
        String phoneNumber = viewText.getText().toString().trim();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
        } else {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

