<?php
session_start();
$License_id="AGROW".rand(1000,9999);
include 'agroww.php';
$sql="select *from license_tbl";
$res=mysqli_query($conn,$sql);
while($row=mysqli_fetch_assoc($res))
{
	$_SESSION['uid']=$row['USER_ID'];
}
$query="UPDATE license_tbl SET status='Approve',License_id='$License_id' Where USER_ID=".$_SESSION['uid']."";
if(mysqli_query($conn,$query))
{
	$responce['message']="APPROVE LICENSE SUCESSFULY";
}
else
{
	$responce['message']="DATA NOT UPDATED";
}
echo json_encode($responce);
?>