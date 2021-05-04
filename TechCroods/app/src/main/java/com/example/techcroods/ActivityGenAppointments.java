package com.example.techcroods;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TextView;

public class ActivityGenAppointments extends AppCompatActivity {
    Button schedule;
    String event;
    DatePickerDialog datePkr;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_appointments);
        t1 = (TextView) findViewById(R.id.textView0);

        // 1st Button logic
        schedule = (Button) findViewById(R.id.btn);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(ActivityGenAppointments.this, schedule);
                p.getMenuInflater().inflate(R.menu.schedule_pop_up, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {
                        //Setting time picker
                        datePkr = new DatePickerDialog(ActivityGenAppointments.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                event = item.getTitle()+": "+": "+day+"/" +(month+1)+ "/"+year+"\n\n"; //user defined method for getting the selected date
                                t1.append(event);
                            }
                        },2021,6,0);
                        datePkr.show();
                        return true;
                    }
                });
                p.show();
            }
        });
    }
}