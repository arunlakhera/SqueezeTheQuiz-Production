package com.example.android.squeeze_thequiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class QuizListActivity extends AppCompatActivity {

    boolean basicClickedFlag = false;
    boolean mediumClickedFlag = false;
    boolean advancedClickedFlag = false;

    String challengerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        Bundle userName = getIntent().getExtras();
        challengerName = userName.getString("challengerName");

    }

    /**
     *  Function to execute when Basic Button is Clicked.
     *  - Set the basicClickedFlag to true
     *  - Set other flags to false
     *  - Call changeCardBackgroundColor() function to change the background color of mode selected by user
    * */

    public void basicClicked(View view) {

        // Update the Flags
        basicClickedFlag = true;
        mediumClickedFlag = false;
        advancedClickedFlag = false;

        // Call the function to change the background color
        changeCardBackgroundColor();
    }

    /**
     *  Function to execute when Medium Button is Clicked.
     *  - Set the mediumClickedFlag to true
     *  - Set other flags to false
     *  - Call changeCardBackgroundColor() function to change the background color of mode selected by user
     * */

    public void mediumClicked(View view) {

        // Update the Flags
        mediumClickedFlag = true;
        basicClickedFlag = false;
        advancedClickedFlag = false;

        // Call the function to change the background color
        changeCardBackgroundColor();

    }


    /**
     *  Function to execute when Advanced Button is Clicked.
     *  - Set the advancedClickedFlag to true
     *  - Set other flags to false
     *  - Call changeCardBackgroundColor() function to change the background color of mode selected by user
     * */

    public void advancedClicked(View view) {

        // Update the Flags
        advancedClickedFlag = true;
        basicClickedFlag = false;
        mediumClickedFlag = false;

        // Call the function to change the background color
        changeCardBackgroundColor();

    }

    /**
     *  Function to execute changeCardBackgroundColor is called.
     *  - If Basic Card is selected then change the background color of basic card and pass the
     *    argument "Basics" to the function questionActivityIntent() else background should be white.
     *
     *  - If Medium Card is selected then change the background color of medium card and pass the
     *    argument "Medium" to the function questionActivityIntent() else background should be white.
     *
     *  - If Advanced Card is selected then change the background color of Advanced card and pass the
     *    argument "Advanced" to the function questionActivityIntent() else background should be white.
     *
     *
     * */


    private void changeCardBackgroundColor() {


        // Make XML references to Cards
        CardView basicCard = findViewById(R.id.card_basic);
        CardView mediumCard = findViewById(R.id.card_medium);
        CardView advancedCard = findViewById(R.id.card_advanced);

        // Check if Basic Flag is clicked
        if (basicClickedFlag) {

            basicCard.setCardBackgroundColor(Color.parseColor("#8BC34A"));
            questionActivityIntent("Basics");

        }else{
            basicCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        // Check if the Medium flag is clicked
        if(mediumClickedFlag) {

            mediumCard.setCardBackgroundColor(Color.parseColor("#8BC34A"));
            questionActivityIntent("Medium");

        } else{
            mediumCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        // Check if the Advanced flag is clicked
        if (advancedClickedFlag) {

            advancedCard.setCardBackgroundColor(Color.parseColor("#8BC34A"));
            questionActivityIntent("Advanced");

        } else {
            advancedCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }

    }

    /**
     * Function that takes the quiz mode selected and passes that as value to QuestionActivity
     * */

    private void questionActivityIntent(String quizMode){

        Intent questionsIntent = new Intent(this, QuestionActivity.class);
        questionsIntent.putExtra("quizMode",String.valueOf(quizMode));
        questionsIntent.putExtra("challengerName",String.valueOf(challengerName));
        startActivity(questionsIntent);
    }


}
