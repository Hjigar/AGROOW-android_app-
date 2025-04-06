<?php
$hostname="localhost";
$username="root";
$password="";
$Databasename="demo";
$conn=mysqli_connect($hostname,$username,$password,$Databasename);
if(!$conn)
{
	die("Coonnection failed".mysqli_connect_error());
}
?>