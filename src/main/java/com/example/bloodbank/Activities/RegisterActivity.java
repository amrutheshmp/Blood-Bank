package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.VolleySingleton;
import Utils.Endpoints;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEt, cityEt , bloodGroupEt, passwordEt , mobileEt;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.City);
        bloodGroupEt = findViewById(R.id.BloodGroup);
        passwordEt = findViewById(R.id.password);
        mobileEt = findViewById(R.id.MobileNumber);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,city,blood_group,password,mobile;
                name=nameEt.getText().toString();
                city=cityEt.getText().toString();
                blood_group=bloodGroupEt.getText().toString();
                password=passwordEt.getText().toString();
                mobile=mobileEt.getText().toString();
                showMessage(name+"\n"+city+"\n"+blood_group+"\n"+password+"\n"+mobile);
                if(isValid(name, city,blood_group,password,mobile)){
                    register(name, city,blood_group,password,mobile);
                }
            }
        });

    }

    private void register(final String name, final String city , final String blood_group , final String password , final String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("SUCCESS")){
                            Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                            //open main activity
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            RegisterActivity.this.finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT)
                        .show();
                Log.d("VOLLEY", volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("city", city);
                params.put("blood_group", blood_group);
                params.put("password", password);
                params.put("number", mobile);

                return params;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean isValid(String name, String city , String blood_group , String password ,String mobile){
        List<String> valid_blood_groups = new ArrayList<>();
        valid_blood_groups.add("A+");
        valid_blood_groups.add("AB+");
        valid_blood_groups.add("AB-");
        valid_blood_groups.add("B+");
        valid_blood_groups.add("B-");
        valid_blood_groups.add("o+");
        valid_blood_groups.add("o-");
        valid_blood_groups.add("A-");


        if(name.isEmpty()){
            showMessage("Name is empty");
            return false;
        }else if(city.isEmpty()){
            showMessage("Not entered a city name");
            return false;
        }
        else if(!valid_blood_groups.contains(blood_group)){
            showMessage("Invalid bllod group choice "+valid_blood_groups);
            return false;
        }
        else if(mobile.isEmpty() ||mobile.length() != 10){
            showMessage("invalid mobile numbert Please enter 10 degits");
            return false;
        }

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class ));

        return false;
    }
    private  void showMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}

