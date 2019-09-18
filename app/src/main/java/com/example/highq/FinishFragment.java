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

public class FinishFragment extends Fragment {

    TextView finishText;
    public finishInterface listener;

    public interface finishInterface{
        void finishGame();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View itemView = inflater.inflate(R.layout.finish_fragment,container,false);

        finishText = (TextView) itemView.findViewById(R.id.finish_text);

        finishText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.finishGame();
            }
        });

        return itemView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof finishInterface){
            listener = (finishInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement finishInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
