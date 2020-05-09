package com.sudha.precipitationstudyinsubsaharanafrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppDescription extends AppCompatActivity {
    Button back,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_description);
        back= (Button)findViewById(R.id.back_btn1);
        next=(Button)findViewById(R.id.next_btn2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AppDescription.this,MainActivity.class);
                startActivity(i);


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AppDescription.this,UsersInput.class);
                startActivity(i);


            }
        });


    }
}
