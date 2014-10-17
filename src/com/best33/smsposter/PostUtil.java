package com.best33.smsposter;


import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class PostUtil {
	public static Context context;
	public static void PostMsg(String postUrl, String from, String message){
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.add("from", from);
		try {
			params.add("msg", new String(message.getBytes("UTF-8"), "UTF-8"));
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		client.post(postUrl, params, new AsyncHttpResponseHandler(){
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] response,
					Throwable e) {
				String errorMsg;
				try {
					errorMsg = "Request error : code " + statusCode + ", response: " + new String(response, "UTF-8");
					Log.e("HTTP_REQUEST_ERROR", errorMsg);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {
				String errorMsg;
				try {
					errorMsg = "Request sent, response: " + new String(response, "UTF-8");
					Log.i("HTTP_REQUEST_OK", errorMsg);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
