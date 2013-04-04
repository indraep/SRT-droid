package com.example.srt_droid.Pesanan;

public class Pesanan {
	private int id;
	private int noMeja;
	private String tanggal;
	private int totalHarga;
	
	public Pesanan() {
		
	}
	
	public Pesanan(int id, int noMeja, String tanggal, int totalHarga) {
		this.id = id;
		this.noMeja = noMeja;
		this.tanggal = tanggal;
		this.totalHarga = totalHarga;
	}
	
	public void setId(int x) {
		id = x;
	}
	
	public void setNoMeja(int x) {
		noMeja = x;
	}
	
	public void setTanggal(String x) {
		tanggal = x;
	}
	
	public void setTotalHarga(int x) {
		totalHarga = x;
	}
	
	public int getId() {
		return id;
	}
	
	public int getNoMeja() {
		return noMeja;
	}
	
	public String getTanggal() {
		return tanggal;
	}
	
	public int getTotalHarga() {
		return totalHarga;
	}
}
