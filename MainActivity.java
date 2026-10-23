package com.emontopup.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceError;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.KeyEvent;
import android.net.Uri;

public class MainActivity extends Activity {
    // Replace this with your live website URL (must start with https://).
    private static final String WEBSITE_URL = "https://YOUR-WEBSITE-DOMAIN.example";
    private WebView webView;
    private ProgressBar progressBar;
    private LinearLayout errorPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);

        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        root.addView(progressBar, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(3)));

        webView = new WebView(this);
        root.addView(webView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));

        errorPanel = new LinearLayout(this);
        errorPanel.setOrientation(LinearLayout.VERTICAL);
        errorPanel.setGravity(android.view.Gravity.CENTER);
        errorPanel.setPadding(dp(24), dp(24), dp(24), dp(24));
        errorPanel.setVisibility(View.GONE);

        TextView errorText = new TextView(this);
        errorText.setText("ওয়েবসাইটটি লোড হয়নি। ইন্টারনেট সংযোগ দেখুন এবং আবার চেষ্টা করুন।");
        errorText.setTextColor(Color.DKGRAY);
        errorText.setTextSize(16);
        errorText.setGravity(android.view.Gravity.CENTER);
        errorPanel.addView(errorText);

        TextView retry = new TextView(this);
        retry.setText("আবার চেষ্টা করুন");
        retry.setTextColor(Color.WHITE);
        retry.setTextSize(16);
        retry.setGravity(android.view.Gravity.CENTER);
        retry.setPadding(dp(20), dp(12), dp(20), dp(12));
        retry.setBackgroundColor(Color.rgb(20, 130, 80));
        LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        retryParams.topMargin = dp(16);
        errorPanel.addView(retry, retryParams);
        retry.setOnClickListener(v -> {
            errorPanel.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.reload();
        });
        root.addView(errorPanel, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));

        setContentView(root);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // Needed for many modern websites.
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSupportMultipleWindows(false);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                progressBar.setVisibility(newProgress >= 100 ? View.GONE : View.VISIBLE);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String scheme = uri.getScheme();
                // Keep HTTPS pages in the app. Open non-HTTPS/external links in a browser.
                if ("https".equalsIgnoreCase(scheme)) {
                    return false;
                }
                try {
                    startActivity(new android.content.Intent(android.content.Intent.ACTION_VIEW, uri));
                } catch (Exception ignored) { }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                errorPanel.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (request.isForMainFrame()) {
                    webView.setVisibility(View.GONE);
                    errorPanel.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else if (!WEBSITE_URL.contains("YOUR-WEBSITE-DOMAIN")) {
            webView.loadUrl(WEBSITE_URL);
        } else {
            webView.loadData(
                "<html><meta name='viewport' content='width=device-width, initial-scale=1'>" +
                "<body style='font-family:sans-serif;padding:24px;color:#222'>" +
                "<h2>Emon Top-Up</h2><p>প্রথমে MainActivity.java ফাইলে WEBSITE_URL-এ আপনার লাইভ HTTPS ওয়েবসাইটের ঠিকানা বসান।</p>" +
                "</body></html>", "text/html", "UTF-8");
        }
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (webView != null) webView.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
        }
        super.onDestroy();
    }
}
