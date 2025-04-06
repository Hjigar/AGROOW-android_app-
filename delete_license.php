<?php
include 'agroww.php';
$lid=$_REQUEST['lid'];
$query="DELETE FROM license_tbl WHERE L_ID=$lid";
if(mysqli_query($conn,$query))
{
	$response['messge']="DATA DELETED SUCSESSFULY";
}
else
{
	$response['messge']="DATA NOT INSERTED";
}
echo json_encode($response);
?>