<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	//if ($_POST) {
		require("DB/connect_to_db.php");
		
		$q = mysql_query("select * from user order by nama");
		while($e = mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		print(json_encode($output));
		
		mysql_close();
	//}
?>