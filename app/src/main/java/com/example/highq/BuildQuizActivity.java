package com.example.highq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BuildQuizActivity extends AppCompatActivity {

    EditText topicName;
    EditText topicQuestion;
    EditText optionA;
    EditText optionB;
    EditText optionC;
    EditText optionD;
    EditText correctAnswer;
    Button saveButton;

    String tName, tQuestion;
    String opA,opB,opC,opD;
    String correctAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_quiz);

        topicName = findViewById(R.id.topicname);
        topicQuestion = findViewById(R.id.topicquestion);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        correctAnswer = findViewById(R.id.correct);
        saveButton = findViewById(R.id.saveButton);

        final Topic topic = new Topic();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extractString();
                topic.pushQuestionToFirebase(tName ,tQuestion ,opA,opB,opC,opD,correctAns);
                clearInputs();
            }
        });
    }

    public void extractString(){
        tName = topicName.getText().toString();
        tQuestion = topicQuestion.getText().toString();
        opA = optionA.getText().toString();
        opB = optionB.getText().toString();
        opC = optionC.getText().toString();
        opD = optionD.getText().toString();
        correctAns = correctAnswer.getText().toString();
    }

    public void clearInputs(){
        correctAnswer.setText("");
        topicQuestion.setText("");
        optionA.setText("");
        optionB.setText("");
        optionC.setText("");
        optionD.setText("");
    }
}
