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

		$no_meja = $_POST["noMeja"];
		
		$q = mysql_query("insert into pesanan(status, no_meja, tanggal, username_koki, username_kasir) values (0, $no_meja, CURDATE(), NULL, NULL)");
		
		$q = mysql_query("select max(id) as id from pesanan where no_meja=$no_meja AND tanggal=CURDATE()");
		$row = mysql_fetch_array($q);
		$id_pesanan = $row["id"];

		$str_query = $_POST["str_query"];
		$ar_query = explode('-', $str_query);

		foreach ($ar_query as $value) {
			$q = mysql_query("insert into detail_pesanan(id_pesanan, no, jumlah, id_kategori, id_menu) values ($id_pesanan, ".$value.")");		
		}
		
		echo "true";
		mysql_close();
		
		// insert into DETAIL_PESANAN(id_pesanan, no, jumlah, id_kategori, id_menu) values ($id_pesanan, 1, 1, 1, 4);
	}
?>