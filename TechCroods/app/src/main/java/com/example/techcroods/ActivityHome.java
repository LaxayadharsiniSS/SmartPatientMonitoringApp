package com.example.techcroods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityHome extends AppCompatActivity {

    Button visit, contextBtn, dctr, updateSchedule;
    private Object Menu;
    SharedPreferences preferences;
    //Closing the application properly with an AlertDialog
    AlertDialog.Builder ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences("user_details",MODE_PRIVATE);

        // Doctor button logic
        dctr = (Button)findViewById(R.id.icuDetailsBtn);
        dctr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Nurse".equals(preferences.getString("AccountType",null))) {
                    Intent dctrIntent = new Intent(getApplicationContext(), ActivityICU.class);
                    startActivity(dctrIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Patients have access only to their medical data",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ActivityPatientDetails.class));
                }
            }
        });

        updateSchedule=(Button)findViewById(R.id.updateScheduleBtn);
        updateSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityUpdateSchedule.class));
            }
        });

        // logic added to the "visit" button - when clicked, it directs to the hospital's website
        visit = (Button)findViewById(R.id.visitBtn);
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.askapollo.com/";
                Intent next = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(next);
            }
        });

        // logic added to the "Emergency button"
        contextBtn = (Button)findViewById(R.id.emergencyBtn);
        registerForContextMenu(contextBtn);
    }

    //creating option menu
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, m);
        return true;
    }

    //Function to be performed when user selects any option
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.op1:
                startActivity(new Intent(getApplicationContext(),ActivityMoreInfo.class));
                return true;
            case R.id.op2:
                Intent settings = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(settings);
                return true;
            case R.id.op3:
                Toast.makeText(getApplicationContext(),"Issue reported to the Application owner",Toast.LENGTH_LONG).show();
                return true;
            case R.id.op4:
                ab  = new AlertDialog.Builder(this);
                ab.setTitle("Logout");
                ab.setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"App closes",Toast.LENGTH_LONG).show();
                        ActivityHome.this.finish();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //creating context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if ("Nurse".equals(preferences.getString("AccountType",null))){
            menu.add(0, v.getId(), 0, "Call Peer Nurse!!").setIcon(R.drawable.ic_launcher_background);
            menu.add(0, v.getId(), 0, "Message Peer Nurse!");
            menu.add(0, v.getId(), 0, "Call Doctor!!");
            menu.add(0, v.getId(), 0, "Message Doctor!");
        }
        else
        {
            menu.add(0, v.getId(), 0, "Call Nurse!!");
            menu.add(0, v.getId(), 0, "Message Nurse!");
            menu.add(0, v.getId(), 0, "Call Guardian!!");
            menu.add(0, v.getId(), 0, "Message Guardian!");
        }
    }

    //Function to be performed when user selects any context
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle()=="Call Peer Nurse!!")
        {
            String phn = "9999988888";
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+phn));
            Toast.makeText(getApplicationContext(),"Proceed to call the peer nurse",Toast.LENGTH_SHORT).show();
            startActivity(call);
        }
        else if(item.getTitle()=="Call Doctor!!")
        {
            String phn = "8989876767";
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+phn));
            Toast.makeText(getApplicationContext(),"Proceed to call the doctor",Toast.LENGTH_SHORT).show();
            startActivity(call);
        }
        else if(item.getTitle() == "Call Nurse!!"){

            String phn = "8887776665";
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+phn));
            Toast.makeText(getApplicationContext(),"Proceed to call the nurse",Toast.LENGTH_SHORT).show();
            startActivity(call);
        }
        else if(item.getTitle() == "Call Guardian!!"){

            String phn = "8887776665";
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+phn));
            Toast.makeText(getApplicationContext(),"Proceed to call the guardian",Toast.LENGTH_SHORT).show();
            startActivity(call);
        }
        else if(item.getTitle() == "Message Peer Nurse!"){
            Toast.makeText(getApplicationContext(),"Emergency message sent to your Peer Nurse",Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle() == "Message Doctor!"){
            Toast.makeText(getApplicationContext(),"Emergency message sent to Doctor",Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle() == "Message Nurse!"){
            Toast.makeText(getApplicationContext(),"Emergency message sent to Nurse",Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle() == "Message Guardian!"){
            Toast.makeText(getApplicationContext(),"Emergency message sent to Guardian",Toast.LENGTH_SHORT).show();
          }
        else
        {
            return false;
        }
        return true;
    }
}