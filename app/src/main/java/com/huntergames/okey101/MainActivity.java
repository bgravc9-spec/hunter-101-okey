package com.huntergames.okey101;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.graphics.Color;

import androidx.webkit.WebViewAssetLoader;

/**
 * Native Android shell. The original playable Hunter 101 HTML is shipped
 * inside app/src/main/assets/index.html and loaded from a secure HTTPS origin.
 */
public final class MainActivity extends Activity {
    private WebView webView;
    private long backPressedAt = 0L;
    private static final String HOME =
            "https://appassets.androidplatform.net/assets/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(3, 13, 31));
        getWindow().setNavigationBarColor(Color.rgb(3, 13, 31));

        webView = new WebView(this);
        setContentView(webView);
        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                .build();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowContentAccess(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        webSettings.setMediaPlaybackRequiresUserGesture(true);

        CookieManager.getInstance().setAcceptCookie(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return assetLoader.shouldInterceptRequest(request.getUrl());
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(Color.rgb(5, 13, 29));
        webView.loadUrl(HOME);
    }

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if (now - backPressedAt < 2000) {
            super.onBackPressed();
        } else {
            backPressedAt = now;
            Toast.makeText(this, "Çıkmak için tekrar geri bas", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
