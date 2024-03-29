package srt_droid.Menu;

public class MenuResto {
	private int id_kategori;
	private String namaKategori;
	private int id;
	private String nama;
	private int hargaModal;
	private int harga;
	private boolean tersedia;
	private String deskripsi;
	private int jumlahJual;
	private int jumlah = 0;
	private String image;
	
	public MenuResto() {
		
	}
	
	public MenuResto(MenuResto x) {
		this.id_kategori = x.id_kategori;
		this.id = x.id;
		this.nama = x.nama;
		this.hargaModal = x.hargaModal;
		this.harga = x.harga;
		this.tersedia = x.tersedia;
		this.deskripsi = x.deskripsi;
		this.jumlahJual = x.jumlahJual;
		this.image = x.image;
	}
	
	public MenuResto(int id_kategori, String namaKategori, int id, String nama, int hargaModal, int harga, boolean tersedia, String deskripsi, int jumlahJual, String image) {
		this.id_kategori = id_kategori;
		this.namaKategori = namaKategori;
		this.id = id;
		this.nama = nama;
		this.hargaModal = hargaModal;
		this.harga = harga;
		this.tersedia = tersedia;
		this.deskripsi = deskripsi;
		this.jumlahJual = jumlahJual;
		this.image = image;
	}
	
	public int getIdKategori() {
		return id_kategori;
	}
	
	public String getNamaKategori() {
		return namaKategori;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public int getHargaModal() {
		return hargaModal;
	}
	
	public int getHarga() {
		return harga;
	}
	
	public boolean getTersedia() {
		return tersedia;
	}
	
	public String getDeskripsi() {
		return deskripsi;
	}
	
	public int getJumlahJual() {
		return jumlahJual;
	}
	
	public int getJumlah() {
		return jumlah;
	}
	
	public String getImage() {
		return image;
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
	
	public void setHargaModal(int x) {
		hargaModal = x;
	}
	
	public void setHarga(int x) {
		harga = x;
	}
	
	public void setTersedia(boolean x) {
		tersedia = x;
	}
	
	public void setDeskripsi(String x) {
		deskripsi = x;
	}
	
	public void setJumlahJual(int x) {
		jumlahJual = x;
	}
	
	public void setJumlah(int x) {
		jumlah = x;
	}
}
