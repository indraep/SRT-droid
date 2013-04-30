<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$oldId = $_POST["oldId"];
		$id_kategori = $_POST["idKategori"];
		$nama = $_POST["nama"];
		$hargaModal = $_POST["hargaModal"];
		$harga = $_POST["harga"];
		$deskripsi = $_POST["deskripsi"];
		
		$q = mysql_query("UPDATE MENU SET id_kategori='$id_kategori', nama='$nama', harga_modal='$hargaModal', harga='$harga', deskripsi='$deskripsi' where id='$oldId'");
		print(json_encode($q));
		mysql_close();
	}
?>