package com.example.highq;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Question> {

    public ListAdapter(Activity Context , ArrayList<Question> questItems ){
        super(Context,0,questItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Question currentItem = getItem(position);

        TextView dNameView = (TextView) listItemView.findViewById(R.id.tname);

        dNameView.setText(currentItem.getQuestion());

        return listItemView;
    }
}
