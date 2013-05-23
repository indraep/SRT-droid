<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$namaKategori = $_POST["namaKategori"];

		$q = mysql_query("insert into kategori_menu(nama) values ('$namaKategori')");

		$q = mysql_query("select id from kategori_menu where nama='$namaKategori'");
		$row = mysql_fetch_array($q);
		$id_kategori = $row["id"];

		$nama = $_POST["nama"];
		$harga_modal = $_POST["hargaModal"];
		$harga = $_POST["harga"];
		$deskripsi = $_POST["deskripsi"];
		$imageName = $_POST["imageName"];
			
		$q = mysql_query("insert into menu(id_kategori, nama, harga_modal, harga, tersedia, deskripsi, jumlah_jual, image)".
			" values ('$id_kategori', '$nama', '$harga_modal', '$harga', 'true', '$deskripsi', '0', '$imageName')");
		print(json_encode($q));
		
		mysql_close();
	}
?>