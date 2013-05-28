<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$tanggal_awal = $_POST["tanggal_awal"];
		$tanggal_akhir = $_POST["tanggal_akhir"];

		$query  = "SELECT A.id, A.status, A.no_meja, A.tanggal, sum(C.harga * B.jumlah) as total_harga, A.addition, A.username_pelayan, A.username_koki, A.username_kasir
					FROM pesanan AS A, detail_pesanan AS B, menu AS C
					WHERE A.id = B.id_pesanan
					AND C.id = B.id_menu
					AND C.id_kategori = B.id_kategori
					AND A.status = 4
					AND A.tanggal >= '$tanggal_awal' 
					AND A.tanggal <= '$tanggal_akhir'
					group by A.id";

		$q = mysql_query($query);
		while($e = mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	}
?>