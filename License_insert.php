<?php
include 'agroww.php';
$phone=$_REQUEST['phone'];
$uid=$_REQUEST['uid'];
$farm_img=$_REQUEST['farm_img'];
$crop_img=$_REQUEST['crop_img'];
$fadd=$_REQUEST['fadd'];
$desc=$_REQUEST['description'];
$query="INSERT INTO license_tbl(`USER_ID`,`PHONE`,`FARM_IMG`, `CROP_IMG`, `FARM_ADD`, `DESCRIPTION`) VALUES ('$uid','$phone','$farm_img','$crop_img','$fadd','$desc')";
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