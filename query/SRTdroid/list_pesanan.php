<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	//if ($_POST) {
		require("DB/connect_to_db.php");
		
		$query  = "SELECT A.id, A.status, A.no_meja, A.tanggal, sum(C.harga * B.jumlah) as total_harga, A.addition
					FROM pesanan AS A, detail_pesanan AS B, menu AS C
					WHERE A.id = B.id_pesanan
					AND C.id = B.id_menu
					AND C.id_kategori = B.id_kategori
					AND A.status < 4
					group by A.id";

		$q = mysql_query($query);
		while($e = mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	//}
?>