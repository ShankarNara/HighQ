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

public class BeginFragment extends Fragment {

    TextView beginText;
    private beginInterface listener;

    public interface beginInterface{
        void startGame();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View itemView = inflater.inflate(R.layout.begin_fragment,container,false);

        beginText = (TextView) itemView.findViewById(R.id.begin_text);

        beginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.startGame();
            }
        });

        return itemView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof beginInterface){
            listener = (beginInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement beginInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
