<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$tanggal_awal = $_POST['tanggal_awal'];
		$tanggal_akhir = $_POST['tanggal_akhir'];

		$q = mysql_query("select	B.id, B.nama, sum(A.jumlah) as total_terjual, 
									B.harga, B.harga_modal, ((B.harga - B.harga_modal) * sum(A.jumlah)) as untung
						from		detail_pesanan A, menu B, pesanan C
						where		A.id_menu = B.id and C.id = A.id_pesanan and C.status = 4 and C.tanggal >= '$tanggal_awal' and C.tanggal <= '$tanggal_akhir'
						group by	B.id
						order by	total_terjual DESC");
		while($e = mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	}
?>