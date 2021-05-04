package com.example.techcroods;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDailyAppointments extends AppCompatActivity {

    TextView t1, t2, t3;
    Button mrng, aftrn, evng, next;
    DatePickerDialog datePkr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        t1 = (TextView)findViewById(R.id.textView1);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);

        // 1st Button logic
        mrng=(Button)findViewById(R.id.btn1);
        mrng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(ActivityDailyAppointments.this, mrng);
                p.getMenuInflater().inflate(R.menu.popup, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getApplicationContext(),"Fixed morning appointment to "+item.getTitle(),Toast.LENGTH_SHORT).show();
                        t1.setText("Morning Appointment: " +item.getTitle());
                        return true;
                    }
                });
                p.show();
            }
        });

        // 2nd Button logic
        aftrn=(Button)findViewById(R.id.btn2);
        aftrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(ActivityDailyAppointments.this, aftrn);
                p.getMenuInflater().inflate(R.menu.popup, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getApplicationContext(),"Fixed afternoon appointment to "+item.getTitle(),Toast.LENGTH_SHORT).show();
                        t2.setText("Afternoon Appointment: " +item.getTitle());
                        return true;
                    }
                });
                p.show();
            }
        });

        // 3rd Button logic
        evng=(Button)findViewById(R.id.btn3);
        evng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(ActivityDailyAppointments.this, evng);
                p.getMenuInflater().inflate(R.menu.popup, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {
                        Toast.makeText(getApplicationContext(),"Fixed evening appointment to "+item.getTitle(),Toast.LENGTH_SHORT).show();
                        t3.setText("Evening Appointment: " +item.getTitle());
                        return true;
                    }
                });
                p.show();
            }
        });

        next = (Button)findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act4 = new Intent(getApplicationContext(), ActivityGenAppointments.class);
                startActivity(act4);
            }
        });

    }

/*
    protected String getMyDate(){
        StringBuilder sb = new StringBuilder();
        sb.append(datePkr.getMonth()+1);
        return sb.toString();
    }*/
}