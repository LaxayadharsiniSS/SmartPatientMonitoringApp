package com.example.techcroods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityCreatePatient extends AppCompatActivity {

    userDetails user;
    EditText nameP, pwdP, contactP, mailP, wardAllotedP;
    Button create;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference collectionReference = db.collection("PatientDetails");
    //private final DocumentReference patientDoc = db.collection("PatientDetails").document();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);

        nameP = (EditText)findViewById(R.id.nameEdTxt);
        pwdP = (EditText)findViewById(R.id.pwdEdTxt);
        contactP = (EditText)findViewById(R.id.phnEdTxt);
        mailP = (EditText)findViewById(R.id.mailEdTxt);
        wardAllotedP = (EditText)findViewById(R.id.wardEdTxt);


        user = new userDetails();

        create = (Button)findViewById(R.id.button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameP.getText().toString();
                String mail = mailP.getText().toString();
                String pwd = pwdP.getText().toString();
                String wardAlloted = wardAllotedP.getText().toString();
                String contact = contactP.getText().toString();

                user = new userDetails();
                user.setName(name);
                user.setMailid(mail);
                user.setPassword(pwd);
                user.setWardAlloted(wardAlloted);
                user.setContact(contact);

                collectionReference.document(mail).set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.w(TAG, "Error writing document", e);
                            }
                        });
                Toast.makeText(getApplicationContext(),"Account created successfully for a patient",Toast.LENGTH_LONG).show();
                Intent login = new Intent(getApplicationContext(), MainActivityPatient.class);
                startActivity(login);
            }
        });
    }
}