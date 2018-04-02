package com.example.android.squeeze_thequiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {


    // Variable to store the quiz mode
    String quizMode_Type;

    String challengerName;

    // String Array to store the Questions
    String[] questions = {"What is an activity in Android?",
            "Which of the following is/are are the subclasses in Android?",
            "On which thread broadcast receivers will work in android?",
            "What is sleep mode in android?",
            "How to get current location in android?",

            "What is a base adapter in android?",
            "How to fix crash using log cat in android?",
            "What are the JSON elements in android?",
            "What is transient data in android?",
            "Can a class be immutable in android?"
    };

    // String Array to store the Answers
    String[][] answers = {{"Activity performs the actions on the screen", "Manage the Application content", "Screen UI", " None of the above"},
            {"Action Bar Activity", "Launcher Activity", "Preference Activity", " All of above"},
            {"Worker Thread", "Main Thread", "Activity Thread", "None of the Above"},
            {"Only Radio interface layer and alarm are in active mode", "Switched off", "Air plane mode", "None of the Above"},
            {"Using with Camera", "Using SMS", "Using location provider", "SQlite"},

            {"Common class for any adapter, can be used for both ListView and spinner", "A kind of adapter", "Data storage space", "None of the above."},
            {"Gmail", "log cat contains the exception name along with the line number", "Google Search", "None of the Above"},
            {"integer, boolean", "boolean", "null", "Number, string, boolean, null, array, and object"},
            {"Permanent data", "Secure Data", "Temporary Data", "Logical Data"},
            {"No, it can't", "Yes, it can", "Can't make class as final class", "None of the Above"}

    };

    // String Array to store the list of correct answers
    String[] correctAnswersList = {"answer1", "answer4", "answer2", "answer1", "answer3", "answer1", "answer2", "answer4", "answer4", "answer2"};

    // Flags to keep track of which answer button was selected
    boolean answer1Clicked = false;
    boolean answer2Clicked = false;
    boolean answer3Clicked = false;
    boolean answer4Clicked = false;

    // Variable to keep track of current question number on screen
    int currentQuestionNumber = 0;
    int totalQuestions = 0;

    // Variables to keep track of score and number of correct and incorrect answers
    int score = 0;
    int totalCorrectAnswers = 0;
    int totalNotAttempted = 0;
    int totalIncorrectAnswers = 0;

    // quiz Complete flag
    boolean quizComplete = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Get the values passed from QuizListActivity into the variable
        Bundle quizMode = getIntent().getExtras();

        // Store the passed value from QuizListActivity in variable
        quizMode_Type = quizMode.getString("quizMode").toUpperCase();
        challengerName = quizMode.getString("challengerName").toUpperCase();

        // Set the Title to quizMode that was passed i.e either Basics/Medium/Advanced
       // TextView quizMode_TextView = findViewById(R.id.quizMode_textview);
       // quizMode_TextView.setText(quizMode_Type);

        ImageView quizTypeImage = findViewById(R.id.quiz_type_imageview);

        if (quizMode_Type.equals("BASICS")) {
            quizTypeImage.setImageResource(R.drawable.basic);
        }else if (quizMode_Type.equals("MEDIUM")) {

            quizTypeImage.setImageResource(R.drawable.medium);
        }else if (quizMode_Type.equals("ADVANCED")) {

            quizTypeImage.setImageResource(R.drawable.advanced);
        }

        // Total number of questions
        totalQuestions = questions.length;
        // Show Questions in Questions textview under Questions Cardview's
        showQuestions();

    }

    /**
     * Submit button function is called when the Submit button is pressed by the user after
     * answering the question.
     * - Next question is displayed on the screen and this continues till last question is answered
     */
    public void submitButton(View view) {

        /**
         * Check if the current question number is less than total questions
         * - Increment the current question number if there are questions left in the quiz
         * - show the next question by calling the show questions function
         * */

        checkAnswers();

        TextView scoreTextView = findViewById(R.id.score_textview);

        if (currentQuestionNumber < totalQuestions) {

            currentQuestionNumber++;
            showQuestions();
            scoreTextView.setText(String.valueOf(score));

        } else {
            quizComplete = true;

        }

    }

    /**
     * Function to Check the Answers and Calculate
     * - Number of Correct Answers
     * - Number of Incorrect Answers
     * - Number of Unanswered questions
     * - Score of correct answers
     * */
    public void checkAnswers() {

        // If answer1 is clicked and it is the answer of question increment score and so on...
        if ((answer1Clicked) && (correctAnswersList[currentQuestionNumber].equals("answer1"))) {
            totalCorrectAnswers++;
        }else if ((answer2Clicked) && (correctAnswersList[currentQuestionNumber].equals("answer2"))) {
            totalCorrectAnswers++;
        }else if ((answer3Clicked) && (correctAnswersList[currentQuestionNumber].equals("answer3"))) {
            totalCorrectAnswers++;
        }else if ((answer4Clicked) && (correctAnswersList[currentQuestionNumber].equals("answer4"))) {
            totalCorrectAnswers++;
        }

        // Count Unanswered Questions
        if (!answer1Clicked && !answer2Clicked && !answer3Clicked && !answer4Clicked) {
            totalNotAttempted++;
        }

        // Calculate the score
        score = totalCorrectAnswers * 10;

        // Set the Answer flags to default
        answer1Clicked = false;
        answer2Clicked = false;
        answer3Clicked = false;
        answer4Clicked = false;
    }

    /**
     * showQuestions function is called to show the next question on the screen
     * - Show the next question only if the quiz has not been completed yet
     */
    private void showQuestions() {

        // Show the current question number and total questions in the quiz
        TextView totalQuestionsTextView = findViewById(R.id.totalQuestions_textview);

        // Set questions in the Question text view
        TextView questionsTextView = findViewById(R.id.questions_textview);

        RadioButton answer1RadioButton = findViewById(R.id.answer1_Radio);
        RadioButton answer2RadioButton = findViewById(R.id.answer2_Radio);
        RadioButton answer3RadioButton = findViewById(R.id.answer3_Radio);
        RadioButton answer4RadioButton = findViewById(R.id.answer4_Radio);

        // Reset the Radio Buttons so no answer is selected when new question is shown
        answer1RadioButton.setChecked(false);
        answer2RadioButton.setChecked(false);
        answer3RadioButton.setChecked(false);
        answer4RadioButton.setChecked(false);

        // Reset the colors of Radio button so no answer is selected by default

        answer1RadioButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        answer2RadioButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        answer3RadioButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        answer4RadioButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

        if (currentQuestionNumber < (totalQuestions)) {

            // Show question
            questionsTextView.setText(questions[currentQuestionNumber]);

            totalQuestionsTextView.setText(String.valueOf(currentQuestionNumber + 1) + "/" + String.valueOf(totalQuestions));

            // Show Answer option
            answer1RadioButton.setText(answers[currentQuestionNumber][0]);
            answer2RadioButton.setText(answers[currentQuestionNumber][1]);
            answer3RadioButton.setText(answers[currentQuestionNumber][2]);
            answer4RadioButton.setText(answers[currentQuestionNumber][3]);

        } else {
            quizComplete = true;

            // Calculate total incorrect answers
            totalIncorrectAnswers = (totalQuestions - (totalCorrectAnswers + totalNotAttempted));

            String scoreSummary = " Name: " + challengerName;
            scoreSummary += "\n Total Questions: " + totalQuestions;
            scoreSummary += "\n Correct: " + totalCorrectAnswers;
            scoreSummary += "\n InCorrect: " + totalIncorrectAnswers;
            scoreSummary += "\n Not Answered: " + totalNotAttempted;
            scoreSummary += "\n SCORE: " + score;


            // Show the Score Summary in toast
            Toast showResult = Toast.makeText(this, scoreSummary, Toast.LENGTH_LONG);
            showResult.show();

            // Disbale the Submit button once the quiz is finished
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.setEnabled(false);

            // INTENT TO RESULT ACTIVITY AFTER SHOWING TOAST

            Intent resultIntent = new Intent(this,ResultsActivity.class);
            resultIntent.putExtra("quizType",String.valueOf(quizMode_Type));
            resultIntent.putExtra("challengerName",String.valueOf(challengerName));
            resultIntent.putExtra("totalQuestions",String.valueOf(totalQuestions));
            resultIntent.putExtra("totalCorrectAnswers",String.valueOf(totalCorrectAnswers));
            resultIntent.putExtra("totalIncorrectAnswers",String.valueOf(totalIncorrectAnswers));
            resultIntent.putExtra("totalNotAttempted",String.valueOf(totalNotAttempted));
            resultIntent.putExtra("score",String.valueOf(score));

            startActivity(resultIntent);

        }

    }


    /**
     * Function that is called when a radio button is selected
     * Check which radio button was selected as answer
     * - Make its flag as true and other answer flag as false
     *
     */

    public void onRadioButtonClicked(View view) {

      switch (view.getId()) {

            case R.id.answer1_Radio:
                answer1Clicked = true;

                answer2Clicked = false;
                answer3Clicked = false;
                answer4Clicked = false;
                break;
            case R.id.answer2_Radio:
                answer2Clicked = true;

                answer1Clicked = false;
                answer3Clicked = false;
                answer4Clicked = false;
                break;
            case R.id.answer3_Radio:
                answer3Clicked = true;

                answer2Clicked = false;
                answer1Clicked = false;
                answer4Clicked = false;
                break;
            case R.id.answer4_Radio:
                answer4Clicked = true;

                answer1Clicked = true;
                answer2Clicked = false;
                answer3Clicked = false;
                break;

        }

    }

}
