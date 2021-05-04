package com.example.techcroods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivityNurse extends AppCompatActivity {

    EditText username, pwd;
    Button login, sigup;
    SharedPreferences preferences;
    AlertDialog.Builder ab;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference nurseReference = db.collection("NurseDetails");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);


        preferences = getSharedPreferences("user_details",MODE_PRIVATE);
        username = (EditText)findViewById(R.id.usernameN);
        pwd = (EditText)findViewById(R.id.passwordN);


        login = (Button) findViewById(R.id.loginN);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urMail = username.getText().toString();
                final String pass = pwd.getText().toString();
                nurseReference.document(urMail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                if((document.getString("password")).equals(pass)) {
                                    //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("AccountType","Nurse");
                                    editor.putString("Collection","NurseDetails");
                                    editor.putString("userMail",urMail);
                                    editor.apply();
                                    Intent next = new Intent(getApplicationContext(), ActivityHome.class);
                                    startActivity(next);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Wrong nurse password",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                //Log.d(TAG, "No such document");
                                Toast.makeText(getApplicationContext(),"Wrong nurse username",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Something wrong in your action",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        sigup = (Button) findViewById(R.id.signupN);
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), ActivityCreateNurse.class);
                startActivity(next);
            }
        });
    }

    //creating option menu
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.loginpage_menu, m);
        return true;
    }

    //Function to be performed when user selects any option
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.op) {
            ab = new AlertDialog.Builder(this);
            ab.setTitle("Close the App");
            ab.setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "App closes", Toast.LENGTH_LONG).show();
                            MainActivityNurse.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            ab.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}