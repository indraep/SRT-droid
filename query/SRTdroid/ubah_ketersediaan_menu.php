<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app

	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$id_menu = $_POST["id_menu"];
		$tersedia = $_POST['tersedia'];
		
		$q = mysql_query("UPDATE menu SET tersedia='$tersedia' where id='$id_menu'");
			
		print(json_encode($q));
		mysql_close();
	}
?>