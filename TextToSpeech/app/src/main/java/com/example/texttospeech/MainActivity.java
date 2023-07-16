package com.example.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private EditText inputText;
    private Button speakButton;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.text_entered);
        speakButton = findViewById(R.id.btn_convert);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, this);

        speakButton.setOnClickListener(v -> speak());
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Set language for TextToSpeech
            Toast.makeText(this,"tts initialized",Toast.LENGTH_SHORT).show();
            int result = textToSpeech.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or the language is not supported.
                // Display an error message.
                Toast.makeText(MainActivity.this, "Language not supported.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Initialization failed.
            // Display an error message.
            Toast.makeText(MainActivity.this, "Failed to initialize TextToSpeech.", Toast.LENGTH_SHORT).show();
        }
    }

    private void speak() {
        String text = inputText.getText().toString();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        inputText.setText("");
    }

    @Override
    protected void onDestroy() {
        // Shut down TextToSpeech when the activity is destroyed.
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}