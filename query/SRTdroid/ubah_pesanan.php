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
		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$id_pesanan = $_POST["idPesanan"];

		$q = mysql_query("select A.id_kategori, A.id, A.nama, A.harga_modal, A.harga, A.tersedia, A.deskripsi, A.jumlah_jual, B.jumlah
							from	MENU AS A LEFT JOIN
									(select *
									from DETAIL_PESANAN AS M
									where M.id_pesanan = $id_pesanan)
									AS B ON (A.id_kategori = B.id_kategori AND A.id = B.id_menu)");

		while($e=mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	}
?>