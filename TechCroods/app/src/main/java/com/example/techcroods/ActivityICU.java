package com.example.techcroods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ActivityICU extends AppCompatActivity {

//    TextView t1, t2, t3;
    Button selectWard, scheduleAppointments;
    DatePickerDialog datePkr;
    SharedPreferences preferences;
    String fetchWardAllot;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icu);

        preferences = getSharedPreferences("user_details",MODE_PRIVATE);

        selectWard = (Button)findViewById(R.id.selectWardBtn);
        registerForContextMenu(selectWard);


        db.collection(preferences.getString("Collection",null)).document(preferences.getString("userMail",null))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        fetchWardAllot = document.getString("wardAlloted");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"No Ward Alloted",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        selectPatient = (Button)findViewById(R.id.selectPatientBtn);
//        registerForContextMenu(selectPatient);
        // 3rd Button logic
        /*selectPatient=(Button)findViewById(R.id.selectPatientBtn);
        selectPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(ActivityICU.this, selectPatient);
                p.getMenuInflater().inflate(R.menu.popup, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {
                        Toast.makeText(getApplicationContext(),"Fixed evening appointment to "+item.getTitle(),Toast.LENGTH_SHORT).show();
                    //    t3.setText("Evening Appointment: " +item.getTitle());
                        return true;
                    }
                });
                p.show();
            }
        });*/

        scheduleAppointments = (Button)findViewById(R.id.scheduleAppointBtn);
        scheduleAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act4 = new Intent(getApplicationContext(),ActivityDailyAppointments.class);
                startActivity(act4);
            }
        });
    }

    //creating context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //menu.add(0, v.getId(), 0, fetchWardAllot);

        final SubMenu subMenu = menu.addSubMenu("Ward "+fetchWardAllot);
            db.collection("PatientDetails").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int count=0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if(document.getString("wardAlloted").equals(fetchWardAllot)){
                                        subMenu.add(0,1,count++,document.getString("mailid"));
                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {

        db.collection("PatientDetails").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if((item.getTitle()).equals(document.getString("mailid")))
                        {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("PatientName",document.getString("name"));
                            editor.apply();
                            startActivity(new Intent(getApplicationContext(),ActivityPatientDetails.class));
                        }
                    }
                } else
                    {
                        Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        return super.onContextItemSelected(item);
    }
}