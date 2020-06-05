package com.example.bloodbank.Activities;

import android.content.Intent;
import android.hardware.usb.UsbEndpoint;
import android.icu.text.Collator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.R;

import java.util.HashMap;
import java.util.Map;

import Utils.Endpoints;
import Utils.VolleySingleton;

public class LoginActivity extends AppCompatActivity {

    EditText numberEt,passwordEt;
    Button submit_button;
    TextView signUpText;
    private Collator VolleySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        numberEt = findViewById(R.id.number);
        passwordEt = findViewById(R.id.password);
        submit_button = findViewById(R.id.submit_button);
        signUpText = findViewById(R.id.signup);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberEt.setError(null);
                passwordEt.setError(null);
                String number = numberEt.getText().toString();
                String password = passwordEt.getText().toString();

                if(isValid(number,password)){

                   login(number,password);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                }
            }
        });
    }

    private void login(final String number, final String password){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.login_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("SUCCESS")){
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                            //open main activity
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            LoginActivity.this.finish();
                        }else{
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
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

                params.put("password", password);
                params.put("number", number);

                return params;
            }

        };

        Utils.VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private  boolean isValid(String number,String pasword){
        if(number.isEmpty()){
            showMessage("Empty Mobile number");
            numberEt.setError("Empty Mobile number");
            return false;
        }
        else if (pasword.isEmpty()){
            showMessage("Empty password");
            passwordEt.setError("Empty password");
            return false;
        }

        return true;
    }
    private void showMessage(String msg){
        Toast.makeText(this, msg ,Toast.LENGTH_SHORT).show();
    }
}
