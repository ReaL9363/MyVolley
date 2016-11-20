package com.example.android.myvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvId, tvUserName, tvPassword;
    Button btn;
    String url = "http://120.50.8.57/Test/login_doctor.php";
    //String url = "http://192.168.56.1/login.php";
    //String url = "http://192.168.56.1/testJson.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MainActivity", "in oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvId = (TextView) findViewById(R.id.textView);
        tvUserName = (TextView) findViewById(R.id.textView2);
        tvPassword = (TextView) findViewById(R.id.textView3);
        btn = (Button) findViewById(R.id.button);

        /*JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("MainActivity", "in response");
                        try {
                            tvId.setText(response.getString("result"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "something went wrong ...", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "ErrorListener--something went wrong ...", Toast.LENGTH_SHORT).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // making json object request
                makeJsonObjectRequest();

            }
        });
    }

    private void makeJsonObjectRequest() {

        /*JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()*/
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //tvId.setText("Response: " + response.toString());
                try {
                    JSONObject phone = response.getJSONObject("result");
                    String id = phone.getString("id").toString();
                    String name = phone.getString("name").toString();
                    String password = phone.getString("email").toString();
                    tvId.setText(id);
                    tvUserName.setText(name);
                    tvPassword.setText(password);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something wrong...", Toast.LENGTH_SHORT).show();

            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjReq);
    }
}
