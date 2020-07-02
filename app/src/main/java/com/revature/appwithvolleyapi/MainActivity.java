package com.revature.appwithvolleyapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        Button buttonParse = findViewById(R.id.button1);
        requestQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                jsonParse();
            }
        });

    }

    private void jsonParse() {
        String url = "https://next.json-generator.com/api/json/get/NyIDeaI0u";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray=response.getJSONArray("employees");
                            for (int i=0; i <jsonArray.length(); i++){

                                JSONObject employee=jsonArray.getJSONObject(i);
                                String firstName=employee.getString("firstName");
                                String lastNmae=employee.getString("lastName");
                                String mail=employee.getString("mail");
                                textView.append(firstName+ "," + lastNmae + "," + mail + "\n\n");
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}

