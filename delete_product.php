<?php
include 'agroww.php';
$pid=$_REQUEST['pid'];
$query="DELETE FROM product_tbl WHERE P_ID=$pid";
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