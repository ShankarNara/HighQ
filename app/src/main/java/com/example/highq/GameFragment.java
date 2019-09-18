package com.example.highq;

import android.content.Context;
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

    String correct,questionText,opA,opB,opC,opD;
    gameInterface listener;

    public interface gameInterface{
        void updateQuestion();
        void updateOptionA();
        void updateOptionB();
        void updateOptionC();
        void updateOptionD();
        void updateCorrectAnswer();
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

        return item;
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
