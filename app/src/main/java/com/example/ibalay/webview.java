package com.example.ibalay;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if required

        // Set up WebViewClient to handle navigation within the WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Redirect to the error page (Page404) if there's a network error
                handleNetworkError();
            }
        });

// Set up DownloadListener to handle file downloads
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // Create an intent to open the URL in the default browser
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));

                // Add additional headers to force download
                intent.putExtra("Content-Disposition", "attachment");

                startActivity(intent);
            }
        });



        // Load your website
        webView.loadUrl("https://ibalay-project.000webhostapp.com/iBalay.com/iBalay-student/");
    }

    // Method to handle network errors
    private void handleNetworkError() {
        Intent intent = new Intent(this, page404.class);
        startActivity(intent);
        finish(); // Optional: finish the current activity to prevent going back to it
    }
}
