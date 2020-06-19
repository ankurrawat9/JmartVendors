package com.example.jmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class selectcity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Spinner s1;
Button b1;
ArrayAdapter aa;
String city;
    String[] ss1={"jabalpur","bhopal","indore","gwalior","rewa","raisen","vidisha","ujjain"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcity);

    s1=findViewById(R.id.spinner1);
    b1=findViewById(R.id.button1);




        s1.setOnItemSelectedListener( this);

        aa= new ArrayAdapter(this,android.R.layout.simple_spinner_item,ss1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(aa);







    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,final int position, long id) {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= (String) aa.getItem(position);
                if (city.equals("jabalpur")){

                    Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(selectcity.this, "service not available in this city", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
