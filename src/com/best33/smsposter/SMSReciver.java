package com.best33.smsposter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		SmsMessage[] msg= null;
		Bundle bundle = intent.getExtras();
		if (bundle != null){
			Object[] pdusObj = (Object[]) bundle.get("pdus");
			msg= new SmsMessage[pdusObj.length];
			for (int i = 0; i<pdusObj.length; i++){
				msg[i] = SmsMessage.createFromPdu ((byte[]) pdusObj[i]);
			}
		}
		for (int i = 0; i < msg.length; i++){
			String msgTxt = msg[i].getMessageBody();
			Toast.makeText(context, "收到了短信：" + msgTxt, Toast.LENGTH_LONG).show();

			SharedPreferences sharedPref = context.getSharedPreferences("url", Context.MODE_PRIVATE);
			String url = sharedPref.getString("url", "http://httpbin.org/post");
			PostUtil.PostMsg(url, msg[i].getOriginatingAddress(), msgTxt);
		}
	}

}
