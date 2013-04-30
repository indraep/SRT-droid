<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$nama = $_POST['nama'];
		$username = $_POST["username"];
		$password = md5($_POST['password']);
		$alamat = $_POST['alamat'];
		$peran = $_POST['peran'];
		
		$q = mysql_query("select * from user where username = '$username'");
		if (mysql_num_rows($q) == 1) {
			echo "0";
		}
		else {
			$q = mysql_query("insert into user(username, password, nama, alamat, peran) values ('$username', '$password', '$nama', '$alamat', $peran)");
			print(json_encode($q));
		}
		mysql_close();
	}
?>