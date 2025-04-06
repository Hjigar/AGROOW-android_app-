<?php
include 'agroww.php';
$sid=$_REQUEST['sid'];
$query="DELETE FROM subsidy_tbl WHERE S_ID=$sid";
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