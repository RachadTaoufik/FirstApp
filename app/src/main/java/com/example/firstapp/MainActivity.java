package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout_one;
    LinearLayout two;
    LinearLayout layout_three;
    LinearLayout layout_four;
    Button button_login;
    Button button_logout;
    Button button_signup;
    EditText edit_login;
    EditText edit_password;
    TextView text_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         layout_one=findViewById(R.id.layout_one);
         /* de meme pour
         layout_two=;
         layout_three;
         layout_four;
         edit_login;
         edit_password;
         text_state;
        */

        button_login=findViewById(R.id.button_login);
        button_logout=findViewById(R.id.button_logout);
        button_signup=findViewById(R.id.button_signup);

        button_login.setOnClickListener(this);
        button_logout.setOnClickListener(this);
        button_signup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.button_login){

        }
        else if (view.getId()==R.id.button_logout){

        }
        else if (view.getId()==R.id.button_signup){

        }
    }
}