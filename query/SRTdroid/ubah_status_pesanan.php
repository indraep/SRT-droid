<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app

	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$id_pesanan = $_POST["idPesanan"];
		$new_status = $_POST['newStatus'];
		
		$q = mysql_query("UPDATE pesanan SET status='$new_status' where id='$id_pesanan'");
			
		print(json_encode($q));
		mysql_close();
	}
?>