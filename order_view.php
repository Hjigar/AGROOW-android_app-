<?php
session_start();
include 'agroww.php';
$uid=$_REQUEST['uid'];
$query="SELECT * FROM product_buy where USER_ID='$uid'";
$result=mysqli_query($conn,$query);
$responce=[];

if(mysqli_num_rows($result)>0)
{
	$responce['message']="success";
	while($row=mysqli_fetch_assoc($result))
	{
	$responce['data'][]=$row;
	}
}
else
{
	$responce['message']="NO DATA AVAILBALE";
}
echo json_encode($responce);
?>
