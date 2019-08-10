package com.example.highq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button buildQuizButton;
    ArrayList<Question> qarray;
    ArrayList<String> topicList;
    TextView topicHeading;
    TopicListAdapter adapter;
    ListView listView;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        topicHeading = findViewById(R.id.topicHeading);
        buildQuizButton = (Button) findViewById(R.id.addTopics);
        buildQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,BuildQuizActivity.class);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        topicList = new ArrayList<String>();
        onRetrieveData();
//        topicList.add(new String("C"));
//        topicList.add(new String("Java"));

        adapter = new TopicListAdapter(this,topicList);

        listView = findViewById(R.id.testview);

        listView.setAdapter(adapter);
    }

    public void onRetrieveData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    topicList.add(new String(postSnapshot.getKey()));
                    //topicHeading.setText(postSnapshot.getKey());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
               // Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }
}
