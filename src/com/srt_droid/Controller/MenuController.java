package com.srt_droid.Controller;

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

import com.srt_droid.Utilities;
import com.srt_droid.Menu.KategoriMenu;
import com.srt_droid.Menu.MenuResto;

public class MenuController {
	
	
	public boolean ubah(String namaKategori, String nama, int hargaModal, int harga, String deskripsi, MenuResto oldMenu) {
		InputStream is = null;
		String result = "";
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "ubah_menu_kategori_baru.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			nameValuePairs.add(new BasicNameValuePair("oldId", "" + oldMenu.getId()));
			nameValuePairs.add(new BasicNameValuePair("namaKategori", "" + namaKategori));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("hargaModal", "" + hargaModal));
			nameValuePairs.add(new BasicNameValuePair("harga", "" + harga));
			nameValuePairs.add(new BasicNameValuePair("deskripsi", deskripsi));
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
		
		Log.e("Result", "Result = " + result);
		
		return result.charAt(0) == 't' ? true : false;
	}
	
	public boolean ubah(int id_kategori, String nama, int hargaModal, int harga, String deskripsi, MenuResto oldMenu) {
		InputStream is = null;
		String result = "";
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "ubah_menu.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			nameValuePairs.add(new BasicNameValuePair("oldId", "" + oldMenu.getId()));
			nameValuePairs.add(new BasicNameValuePair("idKategori", "" + id_kategori));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("hargaModal", "" + hargaModal));
			nameValuePairs.add(new BasicNameValuePair("harga", "" + harga));
			nameValuePairs.add(new BasicNameValuePair("deskripsi", deskripsi));
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
		
		Log.e("Result", "Result = " + result);
		
		return result.charAt(0) == 't' ? true : false;
	}
	
	public boolean buat(String namaKategori, String nama, int hargaModal, int harga, String deskripsi) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_menu_kategori_baru.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("namaKategori", "" + namaKategori));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("hargaModal", "" + hargaModal));
			nameValuePairs.add(new BasicNameValuePair("harga", "" + harga));
			nameValuePairs.add(new BasicNameValuePair("deskripsi", deskripsi));
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
	
	public boolean buat(int id_kategori, String nama, int hargaModal, int harga, String deskripsi) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_menu.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("id_kategori", "" + id_kategori));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("hargaModal", "" + hargaModal));
			nameValuePairs.add(new BasicNameValuePair("harga", "" + harga));
			nameValuePairs.add(new BasicNameValuePair("deskripsi", deskripsi));
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
	
	public boolean hapus(MenuResto menu) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "hapus_menu.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("id_kategori", "" + menu.getIdKategori()));
			nameValuePairs.add(new BasicNameValuePair("id", "" + menu.getId()));
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
		Log.e("Result", "result = " + result);
		return result.charAt(0) == 't' ? true : false;
	}
	
	public ArrayList<MenuResto> getListOfMenu() {
		ArrayList <MenuResto> ret = new ArrayList<MenuResto>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_menu.php");
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
				boolean tersedia = json_data.getString("tersedia").equals("true") ? true : false;
				
				MenuResto menu = new MenuResto(Integer.parseInt(json_data.getString("id_kategori")), 
						Integer.parseInt(json_data.getString("id")),
						json_data.getString("nama"),
						Integer.parseInt(json_data.getString("harga_modal")),
						Integer.parseInt(json_data.getString("harga")),
						tersedia,
						json_data.getString("deskripsi"),
						Integer.parseInt(json_data.getString("jumlah_jual")));
				ret.add(menu);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
	}

	public KategoriMenu[] getListOfKategori() {
		ArrayList<KategoriMenu> ret = new ArrayList<KategoriMenu>();
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_kategori.php");
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
				KategoriMenu kategori = new KategoriMenu(json_data.getString("nama"),
						Integer.parseInt(json_data.getString("id")));
				ret.add(kategori);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		
		int len = ret.size();
		KategoriMenu t[] = new KategoriMenu[len];
		for(int i = 0; i < len; i++) {
			t[i] = ret.get(i);
		}
		
		return t;
	}
}
