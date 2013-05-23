package srt_droid;

public class SejarahPenjualan {
	private int id;
	private String nama;
	private int totalTerjual;
	private int totalUntung;
	
	public SejarahPenjualan(int id, String nama, int totalTerjual, int totalUntung) {
		this.id = id;
		this.nama = nama;
		this.totalTerjual = totalTerjual;
		this.totalUntung = totalUntung;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public int getTotalTerjual() {
		return totalTerjual;
	}
	
	public int getTotalUntung() {
		return totalUntung;
	}
}
