package srt_droid.Controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

import srt_droid.Utilities;
import srt_droid.Menu.KategoriMenu;
import srt_droid.Menu.MenuResto;

import android.util.Log;


public class MenuController {
	
	public boolean ubahAktif(MenuResto menu, int aktif) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "ubah_aktif_menu.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("id_menu", "" + menu.getId()));
			nameValuePairs.add(new BasicNameValuePair("aktif", "" + aktif));
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
	
	public boolean ubahKetersediaan(MenuResto menu, int tersedia) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "ubah_ketersediaan_menu.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("id_menu", "" + menu.getId()));
			nameValuePairs.add(new BasicNameValuePair("tersedia", "" + tersedia));
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
		
		return result.charAt(0) == 't' ? true : false;
	}
	
	public boolean buat(String namaKategori, String nama, int hargaModal, int harga, String deskripsi, String imageName) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_menu_kategori_baru.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			nameValuePairs.add(new BasicNameValuePair("namaKategori", "" + namaKategori));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("hargaModal", "" + hargaModal));
			nameValuePairs.add(new BasicNameValuePair("harga", "" + harga));
			nameValuePairs.add(new BasicNameValuePair("deskripsi", deskripsi));
			nameValuePairs.add(new BasicNameValuePair("imageName", imageName));
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
	
	public boolean buat(int id_kategori, String nama, int hargaModal, int harga, String deskripsi, String imageName) {
		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "buat_menu.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			nameValuePairs.add(new BasicNameValuePair("id_kategori", "" + id_kategori));
			nameValuePairs.add(new BasicNameValuePair("nama", nama));
			nameValuePairs.add(new BasicNameValuePair("hargaModal", "" + hargaModal));
			nameValuePairs.add(new BasicNameValuePair("harga", "" + harga));
			nameValuePairs.add(new BasicNameValuePair("deskripsi", deskripsi));
			nameValuePairs.add(new BasicNameValuePair("imageName", imageName));
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

		return result.charAt(0) == 't' ? true : false;
	}
	
	public ArrayList<MenuResto> getListOfMenuAktifTersedia(int aktif, int ntersedia) {
		ArrayList <MenuResto> ret = new ArrayList<MenuResto>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_menu_aktif_tersedia.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("aktif", "" + aktif));
			nameValuePairs.add(new BasicNameValuePair("tersedia", "" + ntersedia));
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

		Log.e("debug", "menu result = " + result);
		
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
						Integer.parseInt(json_data.getString("jumlah_jual")),
						json_data.getString("image"));
				ret.add(menu);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
	}
	
	public ArrayList<MenuResto> getListOfMenuTersedia(int ntersedia) {
		ArrayList <MenuResto> ret = new ArrayList<MenuResto>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_menu_tersedia.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("tersedia", "" + ntersedia));
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

		Log.e("debug", "menu result = " + result);
		
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
						Integer.parseInt(json_data.getString("jumlah_jual")),
						json_data.getString("image"));
				ret.add(menu);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
	}
	
	public ArrayList<MenuResto> getListOfMenuAktif(int aktif) {
		ArrayList <MenuResto> ret = new ArrayList<MenuResto>();

		InputStream is = null;
		String result = "";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Utilities.URL + "list_menu_aktif.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("aktif", "" + aktif));
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

		Log.e("debug", "menu result = " + result);
		
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
						Integer.parseInt(json_data.getString("jumlah_jual")),
						json_data.getString("image"));
				ret.add(menu);
			}


		}catch(JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return ret;
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

		Log.e("debug", "menu result = " + result);
		
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
						Integer.parseInt(json_data.getString("jumlah_jual")),
						json_data.getString("image"));
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

	public void upload(String path, String imageName) {
		HttpURLConnection connection = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;

		String pathToOurFile = path;
		String urlServer = Utilities.URL + "handle_upload.php?image_name=" + imageName;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary =  "*****";

		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 5*1024*1024;

		try
		{
			FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );

			URL url = new URL(urlServer);
			connection = (HttpURLConnection) url.openConnection();

			// Allow Inputs & Outputs
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// Enable POST method
			connection.setRequestMethod("POST");

			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

			outputStream = new DataOutputStream( connection.getOutputStream() );
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
			outputStream.writeBytes(lineEnd);

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// Read file
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0)
			{
				outputStream.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			// Responses from the server (code and message)
			int serverResponseCode = connection.getResponseCode();
			String serverResponseMessage = connection.getResponseMessage();

			Log.e("debug", "msg = " + serverResponseMessage);
			
			fileInputStream.close();
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception ex)
		{
			//Exception handling
		}
	}
}
