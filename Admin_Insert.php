<?php
include 'agroww.php';
$name=$_REQUEST['name'];
$phone=$_REQUEST['ph'];
$email=$_REQUEST['email'];
$password=$_REQUEST['pass'];
$query="INSERT INTO admin_tbl(`A_NAME`, `PHONE`, `EMAIL`, `PASSWORD`) VALUES ('$name','$phone','$email','$password')";
if(mysqli_query($conn,$query))
{
	$response['messge']="DATA INSERTED SUCESSFULY";
}
else
{
	$response['messge']="DATA NOT INSERTED";
}
echo json_encode($response);
?>