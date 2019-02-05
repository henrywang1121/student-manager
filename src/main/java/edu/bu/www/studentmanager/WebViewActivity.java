package edu.bu.www.studentmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import java.net.URL;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        Uri data = intent.getData(); URL url = null;
        try {
            url = new URL(data.getScheme(),
                    data.getHost(),
                    data.getPath()); }
        catch (Exception e) {
            e.printStackTrace(); }
        WebView webView = (WebView) findViewById(R.id.webView1);
        webView.loadUrl(url.toString());

    }


}
