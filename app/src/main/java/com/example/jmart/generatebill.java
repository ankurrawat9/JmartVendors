package com.example.jmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class generatebill extends AppCompatActivity {
    EditText e1;
    Button b1;
    String itemname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatebill);


        e1=findViewById(R.id.editText);
        b1=findViewById(R.id.button);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                itemname =e1.getText().toString();

                Toast.makeText(generatebill.this, itemname , Toast.LENGTH_SHORT).show();





            }
        });











    }
}
