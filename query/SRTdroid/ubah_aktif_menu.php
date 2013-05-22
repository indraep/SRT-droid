<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app

	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$id_menu = $_POST["id_menu"];
		$aktif = $_POST['aktif'];
		
		$q = mysql_query("UPDATE menu SET aktif='$aktif' where id='$id_menu'");
			
		print(json_encode($q));
		mysql_close();
	}
?>