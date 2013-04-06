<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		$id_kategori = $_POST["id_kategori"];
		$id = $_POST["id"];

		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$q = mysql_query("delete from MENU where id_kategori='$id_kategori' AND id='$id'");
		print(json_encode($q));
		mysql_close();
	}
?>