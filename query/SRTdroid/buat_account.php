<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app
	
	if ($_POST) {
		$nama = $_POST['nama'];
		$username = $_POST["username"];
		$password = md5($_POST['password']);
		$alamat = $_POST['alamat'];
		$peran = $_POST['peran'];

		mysql_connect("127.0.0.1","root","");
		mysql_select_db("SRT-droid");
		
		$q = mysql_query("select * from user where username = '$username'");
		if (mysql_num_rows($q) == 1) {
			echo "0";
		}
		else {
			$q = mysql_query("insert into USER(username, password, nama, alamat, peran) values ('$username', '$password', '$nama', '$alamat', $peran)");
			print(json_encode($q));
		}
		mysql_close();
	}
?>