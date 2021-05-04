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

import java.util.Random;

public class ActivityCreateNurse extends AppCompatActivity {

    userDetails user;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference collectionReference = db.collection("NurseDetails");
    //private final DocumentReference nurseDoc = db.collection("NurseDetails").document();

    EditText nameN, pwdN, contactN, mailN, wardAllotedN;
    Button create;

    Random random = new Random();
    int id = random.nextInt(1000); //For generating id of the user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_nurse);

        nameN = (EditText)findViewById(R.id.nameEdTxtN);
        pwdN = (EditText)findViewById(R.id.pwdEdTxtN);
        contactN = (EditText)findViewById(R.id.phnEdTxtN);
        mailN = (EditText)findViewById(R.id.mailEdTxtN);
        wardAllotedN = (EditText)findViewById(R.id.wardEdTxtN);


        create = (Button)findViewById(R.id.buttonN);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameN.getText().toString();
                String mail = mailN.getText().toString();
                String pwd = pwdN.getText().toString();
                String wardAlloted = wardAllotedN.getText().toString();
                String contact = contactN.getText().toString();

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
                Toast.makeText(getApplicationContext(),"Account created successfully for a nurse",Toast.LENGTH_LONG).show();
                Intent login = new Intent(getApplicationContext(), MainActivityPatient.class);
                startActivity(login);
            }
        });
    }
}