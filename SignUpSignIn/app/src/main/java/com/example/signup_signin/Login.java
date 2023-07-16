package com.example.signup_signin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText login_uname,login_pwd;
    Button login_btn;
    String user,pass;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_uname = findViewById(R.id.login_uname);
        login_pwd = findViewById(R.id.login_pwd);
        login_btn = findViewById(R.id.login);
        login_btn.setOnClickListener(this);
        Bundle bundle = getIntent().getBundleExtra("data");
        user = bundle.getString("uname");
        pass = bundle.getString("pwd");

    }

    @Override
    public void onClick(View view) {
        String user1 = login_uname.getText().toString();
        String pass1 = login_pwd.getText().toString();
        if(user.equals(user1) && pass.equals(pass1) ){
            Toast.makeText(this,"LOGIN SUCCESSFUL",Toast.LENGTH_LONG).show();
        }
        else{
            count++;
            if(count == 3){
                login_btn.setEnabled(false);
                Toast.makeText(this, "FAILED LOGIN ATTEMPTS", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            }
        }

    }
}