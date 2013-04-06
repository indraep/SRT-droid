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
		
		$q = mysql_query("insert into PESANAN(status, no_meja, tanggal, username_koki, username_kasir) values (0, 1, CURDATE(), NULL, NULL)");
		
		$q = mysql_query("select max(id) as id from PESANAN where no_meja=1 AND tanggal=CURDATE()");
		$row = mysql_fetch_array($q);
		$id_pesanan = $row["id"];

		$str_query = $_POST["str_query"];
		$ar_query = explode('-', $str_query);

		foreach ($ar_query as $value) {
			$q = mysql_query("insert into DETAIL_PESANAN(id_pesanan, no, jumlah, id_kategori, id_menu) values ($id_pesanan, ".$value.")");		
		}
		
		echo "true";
		mysql_close();
		
		// insert into DETAIL_PESANAN(id_pesanan, no, jumlah, id_kategori, id_menu) values ($id_pesanan, 1, 1, 1, 4);
	}
?>