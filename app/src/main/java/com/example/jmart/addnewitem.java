package com.example.jmart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class addnewitem extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static  int PICK_IMAGE = 1;
    private static final int CAMERA_REQUEST=1888;
    private static final int PERMISSION_CAMERA =0;

    ImageButton i1;
Button b1,b2,b3;
ImageView imageView;
ArrayAdapter aa,aa2;
EditText e1,e2,e3,e4,e5,e6,e7;
String itemname,subtype,brandname,mandate,expdate,price,quantity,size,unit;
Spinner s1,s2;

    String[] ss1={"kilogram","litres"};

    String[] ss2={"large","extra large"};
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewitem);

        imageView = findViewById(R.id.imageView3);
        i1=findViewById(R.id.imageButton);

        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);


        e1 =findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        e5=findViewById(R.id.editText5);
        e6 =findViewById(R.id.editText6);
        e7 =findViewById(R.id.editText7);

        s1 =(Spinner)findViewById(R.id.spinner1);
        s2 =(Spinner)findViewById(R.id.spinner2);



        s1.setOnItemSelectedListener( this);
        aa= new ArrayAdapter(this,android.R.layout.simple_spinner_item,ss1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(aa);


        s2.setOnItemSelectedListener( this);
        aa2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,ss2);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(aa2);





        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(addnewitem.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            openGallery();

            }
        });



        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



 takePhoto();




            }
        });








        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemname= e1.getText().toString();
                subtype= e2.getText().toString();
                brandname= e3.getText().toString();
                mandate= e4.getText().toString();
                expdate= e5.getText().toString();
                price= e6.getText().toString();
                quantity= e7.getText().toString();

                Toast.makeText(addnewitem.this, size, Toast.LENGTH_SHORT).show();
                Toast.makeText(addnewitem.this, unit, Toast.LENGTH_SHORT).show();


                if (itemname.matches("")  || subtype .matches("") || brandname.matches("")|| mandate.matches("") || expdate.matches("") || price.matches("") || quantity.matches(""))
                {
                    Toast.makeText(addnewitem.this, "kindly fill each field", Toast.LENGTH_SHORT).show();
                }

            }
        });



        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(getApplicationContext(), homePanel.class);
                startActivity(i);
            }
        });




    }

    private void openGallery() {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, PICK_IMAGE);



    }


    private void takePhoto(){

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==CAMERA_REQUEST)
        {
            Bitmap photo= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);


        }




        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));






        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String select = (String) aa.getItem(position);
        String select2 = (String) aa2.getItem(position);


        if(select.equals("kilogram"))

        {
            unit="kilogram";


        }  else

        {
            unit="litres";
        }



        if(select2.equals("large"))

        {
            size="large";

        }  else

        {
            size="extra large";
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
