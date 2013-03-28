package com.example.srt_droid;

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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginView extends Activity {
	Spinner roleSpinner;
	EditText userName, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);

		userName = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_view, menu);
		return true;
	}

	public void login(View v) {
		boolean canLogin = send();
		Toast.makeText(getBaseContext(), canLogin? "LOGIN" : "FAILED", 1000).show();
		
		//getServerData();
	}

	private boolean send()
	{
		InputStream is = null;
		String result = "";
		boolean ret = false;
		
		// get the message from the message text box
		String name = userName.getText().toString();  
		String pass = password.getText().toString();

		// make sure the fields are not empty
		if (name.length() > 0 && pass.length() > 0)
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/PPL/");
			try {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("user_name", name));
				nameValuePairs.add(new BasicNameValuePair("pass", pass));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				
				
				userName.setText(""); // clear text box
				password.setText("");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getBaseContext(), e.getMessage(), 5000).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getBaseContext(), e.getMessage(), 5000).show();
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
				Log.e("log_tag", "Error converting result "+e.toString());
			}

			String returnString = "";
			//parse json data
			try{
				JSONArray jArray = new JSONArray(result);
				for(int i=0;i<jArray.length();i++){
					ret = true;
					//JSONObject json_data = jArray.getJSONObject(i);
					//Log.i("log_tag", "name: "+json_data.getString("nama"));
					//Get an output to the screen
					returnString += "\n\t" + jArray.getJSONObject(i); 
				}
			}catch(JSONException e) {
				Log.e("log_tag", "Error parsing data "+e.toString());
			}
			
			//((TextView) findViewById(R.id.id1)).setText(result);
			//((TextView) findViewById(R.id.id2)).setText(returnString);
			//Toast.makeText(getBaseContext(), "result = " + result + " returnString = " + returnString, 5000).show();
		}
		else
		{
			// display message if text fields are empty
			Toast.makeText(getBaseContext(),"All field are required",Toast.LENGTH_SHORT).show();
		}
		return ret;
	}

	private String getServerData() {

		InputStream is = null;

		String result = "";
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("year","1970"));


		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/PPL/index.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();


		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
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
			Log.e("log_tag", "Error converting result "+e.toString());
		}
		Toast.makeText(getBaseContext(), result, 5000).show();
		String returnString = "";
		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				//JSONObject json_data = jArray.getJSONObject(i);
				//Log.i("log_tag", "name: "+json_data.getString("nama"));
				//Get an output to the screen
				returnString += "\n\t" + jArray.getJSONObject(i); 
			}
		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data "+e.toString());
		}
		return returnString; 
	}

}
