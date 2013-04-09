package com.srt_droid.Pesanan;

public class Pesanan implements Comparable<Pesanan>{
	private int id;
	private int noMeja;
	private String tanggal;
	private int totalHarga;
	private int status;
	
	public Pesanan() {
		
	}
	
	public Pesanan(int id, int noMeja, String tanggal, int totalHarga, int status) {
		this.id = id;
		this.noMeja = noMeja;
		this.tanggal = tanggal;
		this.totalHarga = totalHarga;
		this.status = status;
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
	
	public void setStatus(int x) {
		
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
	
	public int getStatus() {
		return status;
	}

	@Override
	public int compareTo(Pesanan a) {
		// TODO Auto-generated method stub
		return status - a.status;
	}
	
	
}
