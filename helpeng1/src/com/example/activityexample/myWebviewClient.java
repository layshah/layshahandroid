package com.example.activityexample;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class myWebviewClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url) {
		v.loadUrl(url);
		
		return true;
	}
	
}
