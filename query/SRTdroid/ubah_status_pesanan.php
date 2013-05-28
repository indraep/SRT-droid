<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app

	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$id_pesanan = $_POST["idPesanan"];
		$new_status = $_POST["newStatus"];

		$username_pelayan = $_POST["username_pelayan"];
		$username_koki = $_POST["username_koki"];
		$username_kasir = $_POST["username_kasir"];

		if ($username_pelayan != "") {
			$q = mysql_query("UPDATE pesanan SET status='$new_status', username_pelayan='$username_pelayan' where id='$id_pesanan'");
		}
		else if ($username_koki != "") {
			$q = mysql_query("UPDATE pesanan SET status='$new_status', username_koki='$username_koki' where id='$id_pesanan'");
		}
		else if ($username_kasir != "") {
			$q = mysql_query("UPDATE pesanan SET status='$new_status', username_kasir='$username_kasir' where id='$id_pesanan'");
		}

		print(json_encode($q));
		mysql_close();
	}
?>