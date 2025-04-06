<?php
session_start();
include 'agroww.php';
$sql="select *from license_tbl";
$res=mysqli_query($conn,$sql);
while($row=mysqli_fetch_assoc($res))
{
	$_SESSION['uid']=$row['USER_ID'];
}
$query="UPDATE license_tbl SET status='Decline' Where USER_ID=".$_SESSION['uid']."";
if(mysqli_query($conn,$query))
{
	$responce['message']="DECLINE LICENSE SUCESSFULY";
}
else
{
	$responce['message']="DATA NOT UPDATED";
}
echo json_encode($responce);
?>