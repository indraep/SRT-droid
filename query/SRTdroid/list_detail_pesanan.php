<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$id_pesanan = $_POST["idPesanan"];

		$query  = "SELECT A.no, B.nama, B.harga, A.jumlah
					FROM detail_pesanan AS A, menu AS B
					WHERE id_pesanan = $id_pesanan AND B.id = A.id_menu
					AND B.id_kategori = A.id_kategori
					order by A.no";

		$q = mysql_query($query);
		while($e = mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	}
?>