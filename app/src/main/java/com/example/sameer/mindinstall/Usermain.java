package com.example.sameer.mindinstall;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.provider.Telephony.Carriers.PASSWORD;

public class Usermain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main", "UserMain entered");
        setContentView(R.layout.activity_usermain);
        Log.d("Usermain","Oncreate");

        ImageButton send_values = (ImageButton) findViewById(R.id.send_values_button);
        ImageButton view_values = (ImageButton) findViewById(R.id.view_values_button);

        send_values.setOnClickListener(this);
        view_values.setOnClickListener(this);


    }

    private void sendData() throws UnsupportedEncodingException {
        String urlString = "http://13.233.23.12:8082/server_push"; // URL to call

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        String url = "http://13.233.23.12:8082/server_push";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Usermain", response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.d("Usermain", "Server error", error);
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("data_to_send", "Value"); //Add the data you'd like to send to the server.
                return MyData;
            }
        };

    MyRequestQueue.add(MyStringRequest);
    }

    private void viewData() throws UnsupportedEncodingException{
        Intent viewdata = new Intent(Usermain.this , ViewActivity.class);
        startActivity(viewdata);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_values_button:
                Log.d("Usermain", "Send values to a physician");
                try {
                    sendData();
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.view_values_button:
                Log.d("Usermain", "View values button clicked");
                try{
                    viewData();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                break;

            default:
                Log.d("Usermain", "Some button not accounted for.");
                break;
        }
    }
}