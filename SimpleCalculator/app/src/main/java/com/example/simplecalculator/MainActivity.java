package com.example.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    Button btnPlus,btnMinus,btnMul,btnDiv,btnEquals,btnDot,btnClear,btnDel;

    EditText inputText,resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

        btn8 = findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);

        btn9 = findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);

        btn0 = findViewById(R.id.btn_0);
        btn0.setOnClickListener(this);

        btnPlus = findViewById(R.id.btn_Plus);
        btnPlus.setOnClickListener(this);

        btnMinus =  findViewById(R.id.btn_Minus);
        btnMinus.setOnClickListener(this);

        btnMul =  findViewById(R.id.btn_Mul);
        btnMul.setOnClickListener(this);

        btnDiv = findViewById(R.id.btn_Div);
        btnDiv.setOnClickListener(this);

        btnDot = findViewById(R.id.btn_Dot);
        btnDot.setOnClickListener(this);

        btnClear = findViewById(R.id.btn_Clear);
        btnClear.setOnClickListener(this);

        btnDel = findViewById(R.id.btn_Del);
        btnDel.setOnClickListener(this);

        btnEquals = findViewById(R.id.btn_Equals);
        btnEquals.setOnClickListener(this);

        resultText = findViewById(R.id.result_Text);
        resultText.setText("");

        inputText = findViewById(R.id.input_Text);
        inputText.setText("");

    }

    @Override
    public void onClick(View v) {
        String originalText = inputText.getText().toString();
        String updatedText;
        Button btn = (Button) v;
        if(v.equals(btnClear))
            inputText.setText("");
        else if(v.equals(btnDel)) {
            if (originalText.length() > 0) {
                updatedText = originalText.substring(0, originalText.length() - 1);
                inputText.setText("");
                inputText.append(updatedText);
            }
        }
        else if(v.equals(btnEquals)) {
            try {
                String data = inputText.getText().toString();
                if(data.contains("/")){
                    divide(data);
                }
                else if(data.contains("*")){
                    multiply(data);
                }
                else if(data.contains("+")){
                    add(data);
                }
                else if(data.contains("-")){
                    subtract(data);
                }
                else{
                    displayInvalidMessage("invalid operator");
                }
            }
            catch(Exception e){
                displayInvalidMessage("invalid operator");
            }
        }
        else {
            resultText.setText("");
            inputText.append(btn.getText());
        }
    }

    private void displayInvalidMessage(String mes) {
        Toast.makeText(getBaseContext(),mes,Toast.LENGTH_LONG).show();
    }

    private void subtract(String data) {
        String[] operands = data.split("-");
        if(operands.length==2){
            double result = Double.parseDouble(operands[0])-Double.parseDouble(operands[1]);
            resultText.setText(String.valueOf(result));
            inputText.setText("");
        }
        else{
            displayInvalidMessage("invalid input");
        }
    }

    private void multiply(String data) {
        String[] operands = data.split(Pattern.quote("*"));
        if(operands.length==2){
            double result = Double.parseDouble(operands[0])*Double.parseDouble(operands[1]);
            resultText.setText(String.valueOf(result));
            inputText.setText("");
        }
        else{
            displayInvalidMessage("invalid input");
        }
    }


    private void add(String data) {
        String[] operands = data.split(Pattern.quote("+"));
        if(operands.length==2){
            double result = Double.parseDouble(operands[0])+Double.parseDouble(operands[1]);
            resultText.setText(String.valueOf(result));
            inputText.setText("");
        }
        else{
            displayInvalidMessage("invalid input");
        }
    }


    private void divide(String data) {
        String[] operands = data.split(Pattern.quote("/"));
        if(operands.length==2 && Integer.parseInt(operands[1])!=0){
            double result = Double.parseDouble(operands[0])/Double.parseDouble(operands[1]);
            resultText.setText(String.valueOf(result));
            inputText.setText("");
        }
        else{
            displayInvalidMessage("invalid input");
        }
    }
}