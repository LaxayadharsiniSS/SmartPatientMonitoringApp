package com.example.techcroods;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonParser;

public class ActivityPatientDetails extends AppCompatActivity {

    SharedPreferences preferences;
    TextView bodyTemp, o2Sat, bp, heartRate, sugarLevel, respirationRate, title;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "UsingThingspeakAPI";
/*    private static final String THINGSPEAK_CHANNEL_ID = "1375730";
    private static final String THINGSPEAK_API_KEY = "JFNSFMW87K6WVL3K"; //GARBAGE KEY
    private static final String THINGSPEAK_API_KEY_STRING = "JFNSFMW87K6WVL3K";
    private static final String THINGSPEAK_FIELD1 = "field1";
//    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds/last?";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        title = (TextView) findViewById(R.id.patientNameView);
        bodyTemp = (TextView) findViewById(R.id.bodyTempView);
        o2Sat = (TextView) findViewById(R.id.o2SatView);
        bp = (TextView) findViewById(R.id.bPView);
        heartRate = (TextView)findViewById(R.id.heartRateView);
        sugarLevel = (TextView)findViewById(R.id.sugarLevelView);
        respirationRate = (TextView)findViewById(R.id.respirationRate);

        preferences = getSharedPreferences("user_details", MODE_PRIVATE);
        String PatientName = preferences.getString("PatientName", null);
        if(PatientName!=null){
            title.setText(PatientName);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No such patient details",Toast.LENGTH_SHORT).show();
        }
        try {
            new FetchThingspeakTask().execute();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }
    }
    class Result
    {
        String s1;
        String s2;
    }
    class FetchThingspeakTask extends AsyncTask<Void, Void, Result> {
        protected void onPreExecute() {
            //t2.setText("Fetching Data from Server.Please Wait...");
        }
        protected Result doInBackground(Void... urls) {
            try {
                //URL url1 = new URL("https://api.thingspeak.com/channels/1375730/feeds.json?api_key=420G22ULL18UA138&results=2");
                URL url1 = new URL("https://api.thingspeak.com/channels/1375730/fields/field1/last?key=420G22ULL18UA138");
                URL url2 = new URL("https://api.thingspeak.com/channels/1375730/fields/field2/last?key=420G22ULL18UA138");
                        HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
                        HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();

                try {
                            //Field 1
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(urlConnection1.getInputStream()));
                            StringBuilder stringBuilder1 = new StringBuilder();
                            String line1;
                            while ((line1 = bufferedReader1.readLine()) != null) {
                                stringBuilder1.append(line1).append("\n");
                            }
                            //Field 2
                            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));
                            StringBuilder stringBuilder2 = new StringBuilder();
                            String line2;
                            while ((line2 = bufferedReader2.readLine()) != null) {
                                stringBuilder2.append(line2).append("\n");
                            }

                    bufferedReader1.close();
                    bufferedReader2.close();

                            Result r = new Result();
                            r.s1 = stringBuilder1.toString();
                            r.s2 = stringBuilder2.toString();

                            return r;
                        }
                        finally{
                            urlConnection1.disconnect();
                        }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(Result response) {
            if(response == null) {
                Toast.makeText(ActivityPatientDetails.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
           // try {
                //HashMap<String, String> feeds = new HashMap<>();
                //JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                //JSONObject jsonObject = (JSONObject)new JSONTokener(response).nextValue();
                //String  = channel.getString("channel");
                //JSONArray feeds = channel.getJSONArray("feeds");
                //if(v1>=90)
                //JSONObject finalObject = feeds.getJSONObject(feeds.length()-1);
          /*  try {
                JSONObject jObject = null;
                jObject = new JSONObject(response);
                JSONObject feedsObject = jObject.getJSONObject("feeds");
                JSONArray  jarray = new JSONArray("["+feedsObject.toString().substring(1,feedsObject.toString().length() - 1) + "]");
                //for(int i=0;i<jarray.length();i++)
                //{
                    JSONObject jobj = jarray.getJSONObject(jarray.length()-1);
                    String term_id=jobj.getString("field1");
                    String name=jobj.getString("field2");
                    System.out.println(term_id);
                    System.out.println(name);
                //}
            } catch (JSONException e) {
                e.printStackTrace();
            }
*/
            JsonParser parser = new JsonParser();
            Object obj1= parser.parse(response.s1);
            Object obj2= parser.parse(response.s2);
            bodyTemp.setText("Body Temperature:  "+obj1.toString()+" F");
            o2Sat.setText("O2 Saturation Level:  "+obj2.toString());
            bp.setText("Blood Pressure (arterial):  129");
            heartRate.setText("Heart Rate: 99");
            respirationRate.setText("Respiration Rate: 20bpm");
            sugarLevel.setText("Glucose Level: 139 mg/dL");

            //bodyTemp.invalidate();
            //}
            //catch (JSONException e) {
               // e.printStackTrace(); }
        }
    }
}