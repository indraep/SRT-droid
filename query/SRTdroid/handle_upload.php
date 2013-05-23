<?php
	$image_name = $_GET['image_name'];

	$target_path  = "./image/";
	$ext = pathinfo($_FILES['uploadedfile']['name'], PATHINFO_EXTENSION);
	$target_path = $target_path . $image_name;
	
	if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
		echo "The file ".  basename( $_FILES['uploadedfile']['name'])." has been uploaded";
	} else{
		echo "There was an error uploading the file, please try again!";
	}
	
?>