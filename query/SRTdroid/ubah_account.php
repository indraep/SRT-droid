<?php
	// get the "message" variable from the post request
	// this is the data coming from the Android app

	if ($_POST) {
		require("DB/connect_to_db.php");
		
		$usernameLama = $_POST["usernameLama"];
	
		$nama = $_POST['nama'];
		$username = $_POST["username"];
		$password = md5($_POST['password']);
		$alamat = $_POST['alamat'];
		$peran = $_POST['peran'];
		
		if ($usernameLama != $username)
			$q = mysql_query("select * from user where username = '$username'");
		
		if ($usernameLama != $username && mysql_num_rows($q) == 1) {
			echo "0";
		}
		else {
			if ($password == md5("")) {
				$q = mysql_query("UPDATE USER SET nama='$nama', username='$username', alamat='$alamat', peran=$peran where username='$usernameLama'");
			}
			else {
				$q = mysql_query("UPDATE USER SET nama='$nama', username='$username', password='$password', alamat='$alamat', peran=$peran where username='$usernameLama'");	
			}
			print(json_encode($q));
		}
		mysql_close();
	}
?>