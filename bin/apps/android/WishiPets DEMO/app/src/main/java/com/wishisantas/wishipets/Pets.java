package com.wishisantas.wishipets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class Pets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Object o = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        final TextView mNameText = (TextView) findViewById(R.id.nameText);
        final TextView mBreedText = (TextView) findViewById(R.id.breedText);
        final ImageView image = (ImageView) findViewById(R.id.petImage);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://s87ambfrwd.execute-api.us-east-1.amazonaws.com/dev/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        PetCase[] results = gson.fromJson(response, PetCase[].class);
                        mNameText.setText(results[0].getName());
                        mBreedText.setText(results[0].getBreed());

                        Picasso.with(getBaseContext())
                                .load(results[0].getProfilePicture())
                                .into(image);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mNameText.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }



// Add the request to the RequestQueue.
}
