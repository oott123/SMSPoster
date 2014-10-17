package com.best33.smsposter;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.best33.smsposter.R;

public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.best33.smsposter.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		EditText editText = (EditText) findViewById(R.id.edit_message);
		SharedPreferences sharedPref = getSharedPreferences("url", Context.MODE_PRIVATE);
		editText.setText(sharedPref.getString("url", "http://httpbin.org/post"));
	}
	
	public void saveURL(View view) {
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String url = editText.getText().toString();
		SharedPreferences sharedPref = getSharedPreferences("url", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString("url", url);
		editor.commit();
		PostUtil.PostMsg(url, "10086", "Test message sent when you save the URL. 这是你保存url的时候自动发送的测试消息。");
		Toast.makeText(MainActivity.this, "url saved", Toast.LENGTH_LONG).show();
	}
}
