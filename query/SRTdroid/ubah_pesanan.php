<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	/*	
	$str = "1, 1, 1, 1";
	$ar = explode('-', $str);
	
	foreach ($ar as $value) {
		echo $value."<br />";
	}
    */

	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$id_pesanan = $_POST["idPesanan"];

		$q = mysql_query("select A.id_kategori, A.nama_kategori, A.id, A.nama, A.harga_modal, A.harga, A.tersedia, A.deskripsi, A.jumlah_jual, B.jumlah
							from	(select P.id_kategori, P.id, P.nama, P.harga_modal, P.harga, P.tersedia, P.deskripsi, P.jumlah_jual, Q.nama as nama_kategori
									from menu P, kategori_menu Q
									where Q.id = P.id_kategori) AS A LEFT JOIN
									(select *
									from detail_pesanan AS M
									where M.id_pesanan = $id_pesanan)
									AS B ON (A.id_kategori = B.id_kategori AND A.id = B.id_menu)
							group by A.nama_kategori, A.nama");

		while($e=mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	}
?>