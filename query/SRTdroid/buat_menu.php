<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		$id_kategori = $_POST["id_kategori"];
		$nama = $_POST["nama"];
		$harga_modal = $_POST["hargaModal"];
		$harga = $_POST["harga"];
		$deskripsi = $_POST["deskripsi"];

		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$q = mysql_query("insert into MENU(id_kategori, nama, harga_modal, harga, tersedia, deskripsi, jumlah_jual)".
			" values ('$id_kategori', '$nama', '$harga_modal', '$harga', 'true', '$deskripsi', '0')");
		print(json_encode($q));
		mysql_close();
	}
?>