package com.example.jmart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class neworders extends AppCompatActivity {
ImageButton i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neworders);




    i1=findViewById(R.id.imageButton);


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(getApplicationContext(), homePanel.class);
                startActivity(i);
            }
        });






    }
}
