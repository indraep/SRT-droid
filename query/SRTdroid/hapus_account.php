<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		$username = $_POST["username"];

		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$q = mysql_query("delete from USER where username='".$username."'");
		print(json_encode($q));
		mysql_close();
	}
?>