package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class NextActivity extends AppCompatActivity{

    EditText ed;
    private static final String LOG_CAT = NextActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.example.android.MyApplication.extra.REPLY";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        ed = findViewById(R.id.editText);
        TextView tv = findViewById(R.id.textView2);
        //Get the Intent that activated this Activity
        Intent intent = getIntent();
        //Get the string containing the message from the Intent extras using the nextActivity.EXTRA_MESSAGE static variable as the key
        String message = intent.getStringExtra(SendActivity.EXTRA_MESSAGE);
        tv.setText(message);
    }
    public void returnReply(View view){
        Log.d(LOG_CAT, "Button Clicked!");
        String rply = ed.getText().toString();
        //Arguments = appContext , component that receives the intent
        Intent rplyIntent = new Intent();
        //Add the reply string from the EditText to the new intent as an Intent extra.
        //Because extras are key/value pairs, here the key is EXTRA_REPLY, and the value is the reply:
        rplyIntent.putExtra(EXTRA_REPLY, rply);
        //Set the result to RESULT_OK to indicate that the response was successful.
        //The Activity class defines the result codes, including RESULT_OK and RESULT_CANCELLED
        setResult(RESULT_OK,rplyIntent);
        //Call finish() to close the Activity and return to MainActivity.
        finish();
    }
}
