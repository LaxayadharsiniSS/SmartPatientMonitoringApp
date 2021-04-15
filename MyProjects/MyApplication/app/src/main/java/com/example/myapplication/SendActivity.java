package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.MyApplication.extra.MESSAGE";
    EditText ed;
    TextView rplyView;
    TextView headView;
    String msg;
    private static final String LOG_CAT = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ed = findViewById(R.id.editText);
        headView = findViewById(R.id.text_header);
        rplyView = findViewById(R.id.textView1);
    }

    public void launchNextAcitivity(View view){
        Log.d(LOG_CAT, "Button Clicked!");
        msg = ed.getText().toString();
        //Arguments = appContext , component that receives the intent
        Intent intent = new Intent(this, NextActivity.class);
        //Add that string to the Intent as an extra with the EXTRA_MESSAGE constant as the key and the string as the value
        intent.putExtra(EXTRA_MESSAGE, msg);
        //call the method with the new intent as the argument
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                //The Activity class defines the result codes.
                // The code can be RESULT_OK (the request was successful), RESULT_CANCELED (the user cancelled the operation), or
                // RESULT_FIRST_USER (for defining your own result codes).
                String reply = data.getStringExtra(NextActivity.EXTRA_REPLY);
                headView.setVisibility(View.VISIBLE);
                rplyView.setVisibility(View.VISIBLE);
                rplyView.setText(reply);
            }
        }
    }
}
