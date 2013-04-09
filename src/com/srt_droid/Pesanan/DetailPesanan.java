package com.srt_droid.Pesanan;

public class DetailPesanan {
	private int no;
	private String nama;
	private int harga;
	private int jumlah;
	
	public DetailPesanan() {}
	
	public DetailPesanan(int no, String nama, int harga, int jumlah) {
		this.no = no;
		this.nama = nama;
		this.harga = harga;
		this.jumlah = jumlah;
	}
	
	public void setNo(int x) {
		no = x;
	}
	
	public void setNama(String x) {
		nama = x;
	}
	
	public void setHarga(int x) {
		harga = x;
	}
	
	public void setJumlah(int x) {
		jumlah = x;
	}
	
	public int getNo() {
		return no;
	}
	
	public String getNama() {
		return nama;
	}
	
	public int getHarga() {
		return harga;
	}
	
	public int getJumlah() {
		return jumlah;
	}
}
