package com.example.highq;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {

    public TextView question;
    public TextView optionA;
    public TextView optionB;
    public TextView optionC;
    public  TextView optionD;



    int red,green,score;
    String correct,questionText,opA,opB,opC,opD;
    String userAnswer;
    gameInterface listener;


    public interface gameInterface{
        void updateQuestion();
        void updateOptionA();
        void updateOptionB();
        void updateOptionC();
        void updateOptionD();
        void updateCorrectAnswer();
        void spawnQuestInFragment();
        void updateScore(int score);
    }

    public void updateQuestion(){
//        listener.updateCorrectAnswer();
//        listener.updateOptionA();
//        listener.updateOptionB();
//        listener.updateOptionC();
//        listener.updateOptionD();
//        listener.updateQuestion();

    }

    public static GameFragment newInstance (String quest,String A,String B,String C,String D, String correct){
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString("quest",quest);
        args.putString("A",A);
        args.putString("B",B);
        args.putString("C",C);
        args.putString("D",D);
        args.putString("correct",correct);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View item = inflater.inflate(R.layout.question_fragment,container,false);

        question = item.findViewById(R.id.question);
        optionA = item.findViewById(R.id.optionA);
        optionB = item.findViewById(R.id.optionB);
        optionC = item.findViewById(R.id.optionC);
        optionD = item.findViewById(R.id.optionD);

        red = Color.parseColor("#FF0000");
        green = Color.parseColor("#4FECAE");

        if(getArguments() != null){
            questionText = getArguments().getString("quest");
            opA = getArguments().getString("A");
            opB = getArguments().getString("B");
            opC = getArguments().getString("C");
            opD = getArguments().getString("D");
            correct = getArguments().getString("correct");
        }

        question.setText(questionText);
        optionA.setText(opA);
        optionB.setText(opB);
        optionC.setText(opC);
        optionD.setText(opD);

        score=0;
        getAnswer();


        return item;


    }

    public void getAnswer(){
        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct.equals("A")){
                    listener.updateScore(10);
                } else {
                   // optionA.setText(correct);
                    greenify("D");
                    redify("A");
                }
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct.equals("B")){
                    greenify("B");
                    listener.updateScore(10);
                } else {
                    // optionA.setText(correct);
                    greenify(correct);
                    redify("B");
                }
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct.equals("C")){
                    listener.updateScore(10);
                    greenify("C");
                } else {
                    // optionA.setText(correct);
                    greenify(correct);
                    redify("C");
                }
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct.equals("D")){
                    listener.updateScore(10);
                    greenify("D");
                } else {
                    // optionA.setText(correct);
                    greenify(correct);
                    redify("D");
                }
            }
        });
    }

    public void greenify(String option){
        if (option.equals("A")){
            optionA.setBackgroundColor(green);
        } else if (option.equals("B")){
            optionB.setBackgroundColor(green);
        } else if (option.equals("C")){
            optionC.setBackgroundColor(green);
        } else if (option.equals("D")){
            optionD.setBackgroundColor(green);
        }
    }

    public void redify(String option){
        if (option.equals("A")){
            optionA.setBackgroundColor(red);
        } else if (option.equals("B")){
            optionB.setBackgroundColor(red);
        } else if (option.equals("C")){
            optionC.setBackgroundColor(red);
        } else if (option.equals("D")){
            optionD.setBackgroundColor(red);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof gameInterface){
            listener = (gameInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement gameInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
