package com.example.srt_droid.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

public class AccountController {

	public int login(String username, String password) {
		InputStream is = null;
		String result = "wa";

		// get the message from the message text box
		String name = username;  
		String pass = password;

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://10.0.2.2/SRTdroid/");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("username", name));
			nameValuePairs.add(new BasicNameValuePair("pass", pass));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (ClientProtocolException e) {
			Log.e("Error", e.getMessage());
		} catch (IOException e) {
			Log.e("Error", e.getMessage());
		}

		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		
		int ret = 0;
		Log.e("LOG", result);
		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				ret = Integer.parseInt(json_data.getString("peran"));
			}
			

		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		
		return ret;
	}
}
