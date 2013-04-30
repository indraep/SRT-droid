<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$username = $_POST["username"];
		
		$q = mysql_query("delete from user where username='".$username."'");
		print(json_encode($q));
		mysql_close();
	}
?>