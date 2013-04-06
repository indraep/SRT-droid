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
		$q = mysql_query("delete from PESANAN where id = $id_pesanan");

		print(json_encode($q));
		mysql_close();
	}
?>