package com.example.jmart.ui.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.jmart.R;
import com.example.jmart.addnewitem;
import com.example.jmart.generatebill;
import com.example.jmart.homePanel;
import com.example.jmart.neworders;
import com.example.jmart.pendingorders;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    FusedLocationProviderClient fusedLocationProviderClient;
    public String add;
    public DrawerLayout navDrawer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
          navDrawer = root.findViewById(R.id.drawer_layout);

        final Button b1 = root.findViewById(R.id.button);
        final Button b2 = root.findViewById(R.id.button2);
        final Button b3 = root.findViewById(R.id.button3);
       final Button b4 = root.findViewById(R.id.button4);
       final Button b5 = root.findViewById(R.id.button5);

       
       
       final TextView t1 = root.findViewById(R.id.textView8);
       fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(getActivity());



        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {


                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    Location location=task.getResult();
                    if (location!=null){
                        try {
                            Geocoder geocoder=new Geocoder(getActivity(), Locale.getDefault());

                            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                            String x=addresses.get(0).getCountryName();
                            String y=addresses.get(0).getLocality();
                            String z=addresses.get(0).getAdminArea();
                            String w=addresses.get(0).getPostalCode();


                            add =  "आप की लोकेशन  :   "+y+" ,"+z+" ,"+x+" ,"+w;

                        t1.setText(add);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }
            });



        }else {

            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},44);
        }








        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
             }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(getActivity(), neworders.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(getActivity(), addnewitem.class);
                startActivity(i);
            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(getActivity(), pendingorders.class);
                startActivity(i);
            }
        });



        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(getActivity(), generatebill.class);
                startActivity(i);
            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((homePanel) getActivity()).openDrawer();
            }
        });















        return root;
    }


}
