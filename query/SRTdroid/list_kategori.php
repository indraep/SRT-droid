<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	//if ($_POST) {
		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$q = mysql_query("select * from KATEGORI_MENU");
		while($e = mysql_fetch_assoc($q)) {
			$output[]=$e;
		}
		
		//if ($output != NULL)
			print(json_encode($output));
		
		mysql_close();
	//}
?>