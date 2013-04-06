<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		$oldId = $_POST["oldId"];
		$id_kategori = $_POST["idKategori"];
		$nama = $_POST["nama"];
		$hargaModal = $_POST["hargaModal"];
		$harga = $_POST["harga"];
		$deskripsi = $_POST["deskripsi"];

		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$q = mysql_query("UPDATE MENU SET id_kategori='$id_kategori', nama='$nama', harga_modal='$hargaModal', harga='$harga', deskripsi='$deskripsi' where id='$oldId'");
		print(json_encode($q));
		mysql_close();
	}
?>