package com.example.highq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class GameActivity extends AppCompatActivity implements BeginFragment.beginInterface,FinishFragment.finishInterface,GameFragment.gameInterface{

    String topicName;
    TextView topicView;
    ArrayList<Question> questList;
    Button nextButton ;

    //Index for the questions being printed
    int index;
    int currScore;

    BeginFragment beginFragment;
    FinishFragment finishFragment;
    GameFragment gameFragment;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String question,optionA,optionB,optionC,optionD,correct;
    Question current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        topicName = getIntent().getStringExtra("TopicName");

        topicView = (TextView) findViewById(R.id.topic_name);
        topicView.setText(topicName);

        beginFragment = new BeginFragment();
        //finishFragment = new FinishFragment();
       // gameFragment = new GameFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_id,beginFragment)
                .commit();

        //retrieving questions from the firebase database
        questList = new ArrayList<Question>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(topicName);

        nextButton = findViewById(R.id.next_button);

        index=-1;
        currScore=0;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spawnNextQuestion();
            }
        });
       // onRetrieveData();

    }


    public void onRetrieveData(){
       // final CountDownLatch done = new CountDownLatch(10);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post

                    question = (String)postSnapshot.child("question").getValue();
                    optionA = (String)postSnapshot.child("optionA").getValue();
                    optionB = (String)postSnapshot.child("optionB").getValue();
                    optionC = (String)postSnapshot.child("optionC").getValue();
                    optionD = (String)postSnapshot.child("optionD").getValue();
                    correct = (String)postSnapshot.child("correct").getValue();

                    questList.add(new Question(question,optionA,optionB,optionC,optionD,correct));
       //             done.countDown();
                    //QuestList.add(new String(postSnapshot.getKey()));
                    //topicHeading.setText(postSnapshot.getKey());

                }
                Toast.makeText(GameActivity.this,"Question retrieved!",Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
                Toast.makeText(GameActivity.this,"Question not retrieved from firebase!",Toast.LENGTH_SHORT);
            }
        });

    }

    @Override
    public void startGame() {
        onRetrieveData();

        spawnFirstQuestion();
    }

    public void spawnFirstQuestion(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index >= 3){
                    finishFragment = FinishFragment.newInstance(Integer.toString(currScore));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_id,finishFragment)
                            .commit();

                    myRef = database.getReference();
                    myRef.child("score").setValue(currScore);
                } else {

                    index++;
                    current = questList.get(index);
                    // current = new Question("something","A","B","C","D","A");

                    gameFragment = GameFragment.newInstance(current.getQuestion(), current.getOptionA(), current.getOptionB(), current.getOptionC(), current.getOptionD(), current.getCorrectAnswer());
                    //gameFragment.updateQuestion();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_id, gameFragment)
                            .commit();
                }
            }
        },2000);

    }

    @Override
    public void spawnQuestInFragment() {

    }

    public void spawnNextQuestion(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index >= 3){
                    finishFragment = FinishFragment.newInstance(Integer.toString(currScore));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_id,finishFragment)
                            .commit();
                } else {
                    index++;
                    current = questList.get(index);
                    // current = new Question("something","A","B","C","D","A");

                    gameFragment = GameFragment.newInstance(current.getQuestion(), current.getOptionA(), current.getOptionB(), current.getOptionC(), current.getOptionD(), current.getCorrectAnswer());
                    //gameFragment.updateQuestion();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_id, gameFragment)
                            .commit();
                }
            }
        },2000);
    }

    @Override
    public void updateScore(int score) {
        currScore+=score;
    }

    @Override
    public void finishGame() {

    }

    @Override
    public void updateQuestion() {
        gameFragment.question.setText(current.getQuestion());
    }

    @Override
    public void updateOptionA() {
        gameFragment.optionA.setText(current.getOptionA());
    }

    @Override
    public void updateOptionB() {
        gameFragment.optionB.setText(current.getOptionB());
    }

    @Override
    public void updateOptionC() {
        gameFragment.optionC.setText(current.getOptionC());
    }

    @Override
    public void updateOptionD() {
        gameFragment.optionD.setText(current.getOptionD());
    }

    @Override
    public void updateCorrectAnswer() {
        gameFragment.correct = current.getCorrectAnswer();
    }
}
