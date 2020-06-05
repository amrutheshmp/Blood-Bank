package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.bloodbank.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        WebView webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl("https://in.000webhost.com/members/website/amruzak47/database");

    }
}
