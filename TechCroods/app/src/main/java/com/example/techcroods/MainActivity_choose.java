package com.example.techcroods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_choose extends AppCompatActivity {

    Button patient, nurse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choose_acctype);

        patient = (Button)findViewById(R.id.patientBtn);
        nurse = (Button)findViewById(R.id.nurseBtn);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),MainActivityPatient.class);
                startActivity(login);
            }
        });
        nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),MainActivityNurse.class);
                startActivity(login);
            }
        });
    }
}