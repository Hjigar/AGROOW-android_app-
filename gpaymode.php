<?php
session_start();

include 'agroww.php';
$sql="select *from product_buy";
$res=mysqli_query($conn,$sql);
while($row=mysqli_fetch_assoc($res))
{
	$_SESSION['uid']=$row['USER_ID'];
}
echo $query="UPDATE product_buy SET pmode='Google Pay' Where USER_ID=".$_SESSION['uid']."";
if(mysqli_query($conn,$query))
{
	$responce['message']="BUY SUCESSFULY";
}
else
{
	$responce['message']="ERROR";
}
echo json_encode($responce);
?>