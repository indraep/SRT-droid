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
		$hargaModal = $_POST["hargaModal"];
		$harga = $_POST["harga"];
		$deskripsi = $_POST["deskripsi"];
		$oldId = $_POST["oldId"];
			
		$q = mysql_query("UPDATE menu SET id_kategori='$id_kategori', nama='$nama', harga_modal='$hargaModal', harga='$harga', deskripsi='$deskripsi' where id='$oldId'");
		print(json_encode($q));
		
		mysql_close();
	}
?>