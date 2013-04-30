<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$username=$_POST["username"]; 
		$pass=md5($_POST["pass"]);
		
		$q = mysql_query("select * from user where username = '$username' AND password = '$pass'");
		while($e=mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	}
?>