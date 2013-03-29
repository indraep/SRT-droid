package com.example.srt_droid;

public class MenuResto {
	private int id_kategori;
	private int id;
	private String nama;
	private int harga;
	private String deskripsi;
	private String username;
	
	public MenuResto() {
		
	}
	
	public MenuResto(int id_kategori, int id, String nama, int harga, String deskripsi, String username) {
		this.id_kategori = id_kategori;
		this.id = id;
		this.nama = nama;
		this.harga = harga;
		this.deskripsi = deskripsi;
		this.username = username;
	}
	
	public int getIdKategori() {
		return id_kategori;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public int getHarga() {
		return harga;
	}
	
	public String getDeskripsi() {
		return deskripsi;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public void setIdKategori(int x) {
		id_kategori = x;
	}
	
	public void setId(int x) {
		id = x;
	}
	
	public void setNama(String x) {
		nama = x;
	}
	
	public void setHarga(int x) {
		harga = x;
	}
	
	public void setDeskripsi(String x) {
		deskripsi = x;
	}
	
	public void setUsername(String x) {
		username = x;
	}
}
