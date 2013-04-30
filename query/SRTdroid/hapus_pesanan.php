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
		$q = mysql_query("delete from PESANAN where id = $id_pesanan");

		print(json_encode($q));
		mysql_close();
	}
?>