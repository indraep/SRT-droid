package com.example.srt_droid.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import com.example.srt_droid.Utilities;
import com.example.srt_droid.Menu.MenuResto;
import com.example.srt_droid.Pesanan.DetailPesanan;
import com.example.srt_droid.Pesanan.Pesanan;

public class PesananController {
	public boolean hapus(Pesanan pesanan) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "hapus_pesanan.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("idPesanan", "" + pesanan.getId()));
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
		
		return result.charAt(0) == 't';
	}
	
	public ArrayList<MenuResto> getListOfDetailPesanan(Pesanan oldPesanan) {
		ArrayList <MenuResto> ret = new ArrayList<MenuResto>();
		
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "ubah_pesanan.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("idPesanan", "" + oldPesanan.getId()));
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

		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				boolean tersedia = json_data.getString("tersedia").equals("true") ? true : false;
				
				MenuResto menu = new MenuResto(Integer.parseInt(json_data.getString("id_kategori")), 
						json_data.getString("nama_kategori"),
						Integer.parseInt(json_data.getString("id")),
						json_data.getString("nama"),
						Integer.parseInt(json_data.getString("harga_modal")),
						Integer.parseInt(json_data.getString("harga")),
						tersedia,
						json_data.getString("deskripsi"),
						Integer.parseInt(json_data.getString("jumlah_jual")));
				
				String temp = json_data.getString("jumlah");
				
				menu.setJumlah(Integer.parseInt(temp.charAt(0) == 'n' ? "0" : temp));
				ret.add(menu);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		
		return ret;
	}
	
	public boolean ubah(ArrayList <MenuResto> pesanan, String noMeja, Pesanan oldPesanan) {
		
		InputStream is = null;
		String result = "";
		
		String strQuery = "";
		int len = pesanan.size();
		for (int i = 0; i < len; i++) {
			String query = (i + 1) + ", " + pesanan.get(i).getJumlah() + ", " + pesanan.get(i).getIdKategori() + ", " +
					pesanan.get(i).getId();
			
			if (strQuery.length() > 0)
				strQuery += "-" + query;
			else
				strQuery = query;
		}
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_pesanan_ubah.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("idPesanan", "" + oldPesanan.getId()));
			nameValuePairs.add(new BasicNameValuePair("noMeja", noMeja));
			nameValuePairs.add(new BasicNameValuePair("str_query", "" + strQuery));
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
		
		return result.length() > 4 && result.substring(0, 4).equals("true");
	}
	
	public boolean buat(ArrayList <MenuResto> pesanan, String noMeja) {
		Log.e("debug", "no meja = " + noMeja);
		
		InputStream is = null;
		String result = "";
		
		String strQuery = "";
		int len = pesanan.size();
		for (int i = 0; i < len; i++) {
			String query = (i + 1) + ", " + pesanan.get(i).getJumlah() + ", " + pesanan.get(i).getIdKategori() + ", " +
					pesanan.get(i).getId();
			
			if (strQuery.length() > 0)
				strQuery += "-" + query;
			else
				strQuery = query;
		}
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_pesanan.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("noMeja", noMeja));
			nameValuePairs.add(new BasicNameValuePair("str_query", "" + strQuery));
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
		
		return result.length() > 4 && result.substring(0, 4).equals("true");
	}
	
	public ArrayList<DetailPesanan> getListOfDetailPesanan(int idPesanan) {
		ArrayList <DetailPesanan> ret = new ArrayList<DetailPesanan>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_detail_pesanan.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("idPesanan", "" + idPesanan));
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

		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				DetailPesanan detailPesanan = new DetailPesanan(Integer.parseInt(json_data.getString("no")), 
						json_data.getString("nama"), 
						Integer.parseInt(json_data.getString("harga")),
						Integer.parseInt(json_data.getString("jumlah")));
				ret.add(detailPesanan);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
	}
	
	public ArrayList<Pesanan> getListOfPesanan() {
		ArrayList <Pesanan> ret = new ArrayList<Pesanan>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_pesanan.php");
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
				Pesanan pesanan = new Pesanan(Integer.parseInt(json_data.getString("id")), 
						Integer.parseInt(json_data.getString("no_meja")),
						json_data.getString("tanggal"), 
						Integer.parseInt(json_data.getString("total_harga")),
						Integer.parseInt(json_data.getString("status"))
						);
				ret.add(pesanan);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		
		Collections.sort(ret);

		return ret;
	}
}
