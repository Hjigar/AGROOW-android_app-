<?php
include 'demo.php';
$name=$_REQUEST['name'];
$ph=$_REQUEST['ph'];
$query="INSERT INTO `registeration`(`name`, `ph`) VALUES ('$name','$ph')";
if(mysqli_query($conn,$query))
{
	$response['messge']="Data inserted";
}
else
{
	$response['messge']="erroorororororor";
}
echo json_encode($response);
?>