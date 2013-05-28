package srt_droid.Pesanan;

public class Pesanan implements Comparable<Pesanan>{
	private int id;
	private int noMeja;
	private String tanggal;
	private int totalHarga;
	private int status;
	private String addition;
	private String usernamePelayan;
	private String usernameKoki;
	private String usernameKasir;
	
	public Pesanan() {
		
	}
	
	public Pesanan(int id, int noMeja, String tanggal, int totalHarga, int status, String addition) {
		this.id = id;
		this.noMeja = noMeja;
		this.tanggal = tanggal;
		this.totalHarga = totalHarga;
		this.status = status;
		this.addition = addition;
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
		status = x;
	}
	
	public void setAddition(String x) {
		addition = x;
	}
	
	public void setUsernamePelayan(String x) {
		this.usernamePelayan = x;
	}
	
	public void setUsernameKoki(String x) {
		this.usernameKoki = x;
	}
	
	public void setUsernameKasir(String x) {
		this.usernameKasir = x;
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
	
	public String getAddition() {
		return addition;
	}
	
	public String getUsernamePelayan() {
		return usernamePelayan;
	}
	
	public String getUsernameKoki() {
		return usernameKoki;
	}
	
	public String getUsernameKasir() {
		return usernameKasir;
	}
	
	public String getStatusPesanan() {
		if (status == 0) { // baru buat
			return "Pesanan Baru";
		}
		else if (status == 1) { // antrian
			return "Menunggu Dibuat";
		}
		else if (status == 2) { // sedang dimasak
			return "Sedang Dibuat";
		}
		else if (status == 3) { // sudah matang
			return "Siap Diantar";
		}
		else { // sudah lunas
			return "Lunas";
		}
	}

	@Override
	public int compareTo(Pesanan a) {
		// TODO Auto-generated method stub
		return status - a.status;
	}
	
	
}
