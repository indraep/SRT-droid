package com.example.srt_droid;

public class User {
	private String nama;
	private String username;
	private String password;
	private int peran;

	public String getNama() {
		return nama;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getPeran() {
		return peran;
	}
	
	public String[] getListOfPeran() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if ((peran & (1 << i)) > 0)
				count++;
		}
		
		String[] ret = new String[count];
		String[] allPeran = {"Pemilik Restoran", "Pelayan", "Koki", "Kasir"};
		int id = 0;
		for (int i = 0; i < 4; i++) {
			if ((peran & (1 << i)) > 0)
				ret[id] = allPeran[id++];
		}
		
		return ret;
	}
	
	public void setNama(String x) {
		nama = x;
	}
	
	public void setUsername(String x) {
		username = x;
	}
	
	public void setPassword(String x) {
		password = x;
	}
	
	public void setPeran(int x) {
		peran = x;
	}
}
