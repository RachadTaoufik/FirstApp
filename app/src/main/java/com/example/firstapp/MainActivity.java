package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView myTextView;
    Button myButton;
    Boolean hd=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView=findViewById(R.id.MyTextView);
        myButton=findViewById(R.id.MyButton);
        myButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.MyButton && hd) {
            myTextView.setVisibility(View.VISIBLE);
            hd = false;
        }
        else if (view.getId()==R.id.MyButton && !hd){
          myTextView.setVisibility(View.INVISIBLE);
        hd=true;}
    }
}