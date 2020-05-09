package com.sudha.precipitationstudyinsubsaharanafrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UsersInput extends AppCompatActivity {

    Button for_prec, work_complete_btn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_input);

        for_prec=(Button)findViewById(R.id.prec_btn);
        for_prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView addressTextView = findViewById(R.id.editTextAddress);
                    String address = addressTextView.getText().toString();
                    Geocoder geocoder = new Geocoder(context);
                    List<Address> addresses = geocoder.getFromLocationName(address, 1);
                    if(!addresses.isEmpty()) {
                        Address geoAddress = addresses.get(0);
                        String latitude = Double.toString(geoAddress.getLatitude());
                        String longitude = Double.toString(0 + geoAddress.getLongitude());
                        Log.d("Geo location", "Retrieved information from address Latitude : [ " + latitude + " ] Longitude : [ " + longitude + " ]");

                        TextView dateTextView = findViewById(R.id.date_entered);
                        Date date = new SimpleDateFormat("M/d/yyyy").parse(dateTextView.getText().toString());

                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
                        databaseAccess.open();

                        String precipitation = databaseAccess.getprecipitationdata(latitude, longitude, date);
                        String minprecipitation = databaseAccess.getMinPrecipitationdata(latitude, longitude, date);
                        String maxprecipitation = databaseAccess.getMaxPrecipitationdata(latitude, longitude, date);
                        String avgprecipitation = databaseAccess.getAvgPrecipitationdata(latitude, longitude, date);
                        String totprecipitation = databaseAccess.getTotalPrecipitationdata(latitude, longitude, date);

                        TextView output = findViewById(R.id.result_precipitation);
                        TextView output1 = findViewById(R.id.max_precipitation);
                        TextView output2 = findViewById(R.id.min_precipitation);
                        TextView output3 = findViewById(R.id.Avg_precipitation);
                        TextView output4 = findViewById(R.id.total_precipitation);


                        output.setText(precipitation + " " + "mm");
                        output2.setText(minprecipitation + " " + "mm");
                        output1.setText(maxprecipitation + " " + "mm");
                        output3.setText(avgprecipitation + " " + "mm");
                        output4.setText(totprecipitation + " " + "mm");
                    }
                    else {


                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        work_complete_btn=findViewById(R.id.finish_btn);
        work_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(UsersInput.this,AppDescription.class);
                startActivity(i);
            }
        });





    }
}
