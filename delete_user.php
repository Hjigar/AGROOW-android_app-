<?php
include 'agroww.php';
$uid=$_REQUEST['uid'];
$query="DELETE FROM user_tbl WHERE USER_ID=$uid";
if(mysqli_query($conn,$query))
{
	$response['messge']="DATA DELETED SUCSESSFULY";
}
else
{
	$response['messge']="DATA NOT DELETED";
}
echo json_encode($response);
?>