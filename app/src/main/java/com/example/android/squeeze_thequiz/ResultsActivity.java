package com.example.android.squeeze_thequiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {

    // Variables to keep track of score and number of correct and incorrect answers

    String challengerName;
    String totalQuestions;
    String score;
    String totalCorrectAnswers;
    String totalNotAttempted;
    String totalIncorrectAnswers;
    String quizType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Bundle to receive the values passed from QuestionActivity
        Bundle scoreSummary = getIntent().getExtras();
        quizType = scoreSummary.getString("quizType");
        challengerName = scoreSummary.getString("challengerName");
        totalQuestions = scoreSummary.getString("totalQuestions");
        totalCorrectAnswers = scoreSummary.getString("totalCorrectAnswers");
        totalIncorrectAnswers = scoreSummary.getString("totalIncorrectAnswers");
        totalNotAttempted = scoreSummary.getString("totalNotAttempted");
        score = scoreSummary.getString("score");

        // Call Function to Display the Score Summary
        showResult();

    }

    /**
     * Function to Display the Score Summary
     * */
    public void showResult() {

        TextView nameTextView = findViewById(R.id.name_textview);
        TextView totalQuestionsTextView = findViewById(R.id.totalQuestions_textview);
        TextView correctTextView = findViewById(R.id.correct_textview);
        TextView incorrectTextView = findViewById(R.id.incorrect_textview);
        TextView notAttendedTextView = findViewById(R.id.notAttempted_textview);
        TextView scoreTextView = findViewById(R.id.score_textview);
        ImageView quizTypeImage = findViewById(R.id.quiz_type_imageview);

        CardView scoreCardView = findViewById(R.id.card_score);

        // Score Star Images
        ImageView star1 = findViewById(R.id.star1);
        ImageView star2 = findViewById(R.id.star2);
        ImageView star3 = findViewById(R.id.star3);
        ImageView star4 = findViewById(R.id.star4);
        ImageView star5 = findViewById(R.id.star5);

        // Set the Score Summary for the Quiz Type
        nameTextView.setText(challengerName);
        totalQuestionsTextView.setText(totalQuestions);
        correctTextView.setText(totalCorrectAnswers);
        incorrectTextView.setText(totalIncorrectAnswers);
        notAttendedTextView.setText(totalNotAttempted);
        scoreTextView.setText(score);

        // Set the Image of Quiz Type
        if (quizType.equals("BASICS")) {

            quizTypeImage.setImageResource(R.drawable.basic);

        }else if (quizType.equals("MEDIUM")) {

            quizTypeImage.setImageResource(R.drawable.medium);

        }else if (quizType.equals("ADVANCED")) {
            quizTypeImage.setImageResource(R.drawable.advanced);
        }


        // Convert the score to integer
        int quizScore = Integer.valueOf(score);

        /**
         * Condition to check the score and show the message as per performance and
         * also make Message cardview visible which we had set invisible in XML.
         * If score is below 60 then
         * - user should be directed back to the previous level.
         * - the Button Title should be updated to Try Again!
         * - On pressing the button, user should be directed back to the quiz
         * - Show the feed back stars 5/4/3/2/1 based on score of user
         **/
        if (quizScore >= 90) {

            //scoreMessageCardView.setVisibility(View.VISIBLE);
            //scoreMessageTextView.setText("Excellent!!");

            star1.setImageResource(R.drawable.star_green);
            star2.setImageResource(R.drawable.star_green);
            star3.setImageResource(R.drawable.star_green);
            star4.setImageResource(R.drawable.star_green);
            star5.setImageResource(R.drawable.star_green);

        } else if (quizScore < 90 && quizScore >= 70) {

            star1.setImageResource(R.drawable.star_green);
            star2.setImageResource(R.drawable.star_green);
            star3.setImageResource(R.drawable.star_green);
            star4.setImageResource(R.drawable.star_green);

          //  scoreMessageCardView.setVisibility(View.VISIBLE);
           // scoreMessageTextView.setText("Good!!");

        } else if (quizScore < 70 && quizScore >= 60) {

            star1.setImageResource(R.drawable.star_green);
            star2.setImageResource(R.drawable.star_green);
            star3.setImageResource(R.drawable.star_green);

            //scoreMessageCardView.setVisibility(View.VISIBLE);
          //  scoreMessageTextView.setText("Average!!");

        } else if (quizScore < 60 && quizScore >= 40) {

            star1.setImageResource(R.drawable.star_green);
            star2.setImageResource(R.drawable.star_green);

            scoreCardView.setCardBackgroundColor(Color.RED);

            //scoreMessageCardView.setVisibility(View.VISIBLE);
          //  scoreMessageTextView.setText("You can do Better!!");

        } else {

            star1.setImageResource(R.drawable.star_green);

            scoreCardView.setCardBackgroundColor(Color.RED);

            // scoreMessageCardView.setVisibility(View.VISIBLE);
          //  scoreMessageTextView.setText("Need To Try Again!!");

           // levelButton = findViewById(R.id.level_Button);
           // levelButton.setText("TRY AGAIN!!");
        }



    }

}
