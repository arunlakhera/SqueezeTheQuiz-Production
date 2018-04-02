package com.example.android.squeeze_thequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startButtonClicked(View view) {

        EditText nametext = (EditText) findViewById(R.id.name_textview);
        String challengerName = String.valueOf(nametext.getText());

        if (challengerName.length() > 0) {

            // Set the button image from Black to Pink
            ImageView startButton = findViewById(R.id.start_button);
            startButton.setImageResource(R.drawable.startbutton_pink);

            // Send the user entered challenger name to quiz activity
            Intent quizListIntent = new Intent(this,QuizListActivity.class);
            quizListIntent.putExtra("challengerName", String.valueOf(challengerName));
            startActivity(quizListIntent);

        }else {
            Toast userName = Toast.makeText(this,"Please Enter Name", Toast.LENGTH_LONG);
            userName.show();
        }


    }

}
