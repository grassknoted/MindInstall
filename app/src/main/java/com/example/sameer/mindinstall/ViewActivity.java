package com.example.sameer.mindinstall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        try {
            showData();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    private void showData() throws UnsupportedEncodingException {
        String urlString = "http://13.233.23.12:8082/server_get"; // URL to call

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);


        String url = "http://13.233.23.12:8082/server_get";
        StringRequest MyStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Usermain", response);
                TextView datatext = (TextView) findViewById(R.id.text_view_id);
                datatext.setText("response is:" + response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.d("Usermain", "Server error", error);
            }
        });

        MyRequestQueue.add(MyStringRequest);
    }
}
