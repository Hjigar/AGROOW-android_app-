<?php
include 'demo.php';
$uid=$_REQUEST['uid'];
$query="DELETE FROM `registeration` WHERE ID='$uid'";
if(mysqli_query($conn,$query))
{
	$response['messge']="Data DELETED";
}
else
{
	$response['messge']="erroorororororor";
}
echo json_encode($response);
?>