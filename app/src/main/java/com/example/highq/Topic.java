package com.example.highq;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Topic {
    public ArrayList<Question> qarray;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference currRef;

    public Topic(){
        qarray = new ArrayList<Question>();
    }

    public void pushQuestionToFirebase(String tName ,String tQuestion ,String opA,String opB,String opC,String opD,String correctAns){

        Question currQuest = new Question(tQuestion ,opA,opB,opC,opD,correctAns);

        //Firebase functions
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        currRef = myRef.child(tName).child(tQuestion).child("question");
        currRef.setValue(tQuestion);

        currRef = myRef.child(tName).child(tQuestion).child("optionA");
        currRef.setValue(opA);

        currRef = myRef.child(tName).child(tQuestion).child("optionB");
        currRef.setValue(opB);

        currRef = myRef.child(tName).child(tQuestion).child("optionC");
        currRef.setValue(opC);

        currRef = myRef.child(tName).child(tQuestion).child("optionD");
        currRef.setValue(opD);

        currRef = myRef.child(tName).child(tQuestion).child("correct");
        currRef.setValue(correctAns);

    }

}
