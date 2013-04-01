package com.example.srt_droid.Menu;

public class KategoriMenu {
	private String nama;
	private int id;
	
	public KategoriMenu() {}
	
	public KategoriMenu(String nama, int id) {
		this.nama = nama;
		this.id = id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNama(String x) {
		nama = x;
	}
	
	public void setId(int x) {
		id = x;
	}
	
	public String toString() {
		return nama;
	}
}
