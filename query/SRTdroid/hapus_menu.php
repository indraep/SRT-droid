<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$q = mysql_query("delete from menu where id_kategori='$id_kategori' AND id='$id'");
		print(json_encode($q));
		mysql_close();
	}
?>