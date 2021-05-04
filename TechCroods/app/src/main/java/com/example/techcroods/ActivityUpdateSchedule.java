package com.example.techcroods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ActivityUpdateSchedule extends AppCompatActivity {

    userDetails user;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private final CollectionReference collectionReference = db.collection("NurseDetails");
    //private final DocumentReference nurseDoc = db.collection("NurseDetails").document();

    EditText Mail1, Phn1, Mail2, Phn2;
    Button update;
    SharedPreferences preferences;
    Random random = new Random();
    int id = random.nextInt(1000); //For generating id of the user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);

        Mail1 = (EditText)findViewById(R.id.dctrOrNurseMailEdTxt);
        Phn1 = (EditText)findViewById(R.id.dctrOrNursePhnEdTxtN);
        Mail2 = (EditText)findViewById(R.id.peerOrGuardianMailEdTxt);
        Phn2 = (EditText)findViewById(R.id.peerorGuardianPhnEdTxt);

        preferences = getSharedPreferences("user_details",MODE_PRIVATE);
        final Map<String,Object> updates = new HashMap<>();

        update = (Button)findViewById(R.id.updateDetails);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail1 = Mail1.getText().toString();
                String phn1 = Phn1.getText().toString();
                String mail2 = Mail2.getText().toString();
                String phn2 = Phn2.getText().toString();

                if ("Nurse".equals(preferences.getString("AccountType", null))) {
                    updates.put("DoctorMail", mail1);
                    updates.put("DoctorContact", phn1);
                    updates.put("PeerMail", mail2);
                    updates.put("PeerContact", phn2);
                }
                else if("Patient".equals(preferences.getString("AccountType", null))){
                    updates.put("NurseMail", mail1);
                    updates.put("NurseContact", phn1);
                    updates.put("GuardianMail", mail2);
                    updates.put("GuardianContact", phn2);
                }
                db.collection(preferences.getString("Collection",null)).document(preferences.getString("userMail",null)).update(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                             //Log.d(TAG, "DocumentSnapshot successfully written!");
                             Toast.makeText(getApplicationContext(),"Successfully updated the details",Toast.LENGTH_SHORT).show();
                             Intent homeIntent = new Intent(getApplicationContext(), ActivityHome.class);
                             startActivity(homeIntent);
                           }

                            })
                        .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                            //Log.w(TAG, "Error writing document", e);
                            Toast.makeText(getApplicationContext(),"Unable to update the details",Toast.LENGTH_SHORT).show();
                           }
                            });

            }
        });
    }
}