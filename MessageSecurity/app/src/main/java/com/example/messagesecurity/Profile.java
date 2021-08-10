package com.example.messagesecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://m.facebook.com/ngoctien.TNT/");
    }
}