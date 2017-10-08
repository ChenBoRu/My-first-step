<?php
	$name=$_POST['name'];
	$account=$_POST['account'];
	$password=$_POST['password'];
	$time=date("Y-m-d H:i:s");
	$sport=$_POST['sport'];
	$nt=$_POST['nt'];
	$calories=$_POST['calories'];
	date_default_timezone_set('Asia/Taipei');
		
	include 'database.php';
	
	$link=mysqli_connect($host, $user, $password, $database);
	$query="INSERT INTO $table (name,account,password,time,sport,nt,calories) VALUES ('$name','$account','$password','$time','$sport','$nt','$calories')";
	
	if(mysqli_query($link, $query)){
		echo "Welcome " . $name . " ( " . $account . " ) !";	
	}
	else{
		echo mysqli_error($link);
	}
	
	mysqli_close($link);
?>