package com.example.highq;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TopicListAdapter extends ArrayAdapter<String> {

    public TopicListAdapter(Activity Context , ArrayList<String> questItems ){
        super(Context,0,questItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        final String currentItem = getItem(position);

        TextView dNameView = (TextView) listItemView.findViewById(R.id.tname);

        dNameView.setText(currentItem.toString());

        dNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),GameActivity.class);
                intent.putExtra("TopicName",currentItem.toString());
                getContext().startActivity(intent);
            }
        });
        return listItemView;
    }


}
