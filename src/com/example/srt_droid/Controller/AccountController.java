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

import com.example.srt_droid.Utilities;
import com.example.srt_droid.Account.User;


import android.util.Log;

public class AccountController {
	
	public String ubah(User oldUser, String nama, String username, String password, String alamat, int peran) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "ubah_account.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			nameValuePairs.add(new BasicNameValuePair("usernameLama", oldUser.getUsername()));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("alamat", alamat));
			nameValuePairs.add(new BasicNameValuePair("peran", "" + peran));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch (ClientProtocolException e) {} 
		catch (IOException e) {}
		
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

		if (result.charAt(0) == '0')
			return "Username baru yang anda masukkan sudah terpakai sebelumnya!";
		
		return result.charAt(0) == 't' ? "Account berhasil diubah!" : "Gagal mengubah account";
	}
	
	public String buat(String nama, String username, String password, String alamat, int peran) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_account.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("alamat", alamat));
			nameValuePairs.add(new BasicNameValuePair("peran", "" + peran));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch (ClientProtocolException e) {} 
		catch (IOException e) {}
		
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
		
		if (result.charAt(0) == '0')
			return "Username yang anda masukkan sudah terpakai sebelumnya!";
		
		return result.charAt(0) == 't' ? "Account berhasil dibuat!" : "Gagal membuat account";
	}
	
	public boolean hapus(User user) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "hapus_account.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("username", user.getUsername()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch (ClientProtocolException e) {} 
		catch (IOException e) {}
		
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
		
		return result.charAt(0) == 't' ? true : false;
	}

	public User login(String username, String password) {
		InputStream is = null;
		String result = "";

		String name = username;  
		String pass = password;


		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "login.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("username", name));
			nameValuePairs.add(new BasicNameValuePair("pass", pass));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch (ClientProtocolException e) {} 
		catch (IOException e) {}

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

		Log.e("debug", "result = " + result);
		User ret = new User();

		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				ret.setNama(json_data.getString("nama"));
				ret.setUsername(json_data.getString("username"));
				ret.setPassword(json_data.getString("password"));
				ret.setAlamat(json_data.getString("alamat"));
				ret.setPeran(Integer.parseInt(json_data.getString("peran")));
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
	}

	public ArrayList<User> getListOfAccount() {
		ArrayList <User> ret = new ArrayList<User>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_account.php");
		try {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch (ClientProtocolException e) {} 
		catch (IOException e) {}

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

		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				User user = new User(json_data.getString("nama"), json_data.getString("username"),
						json_data.getString("password"), json_data.getString("alamat"), 
						Integer.parseInt(json_data.getString("peran")));
				ret.add(user);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
	}
}
