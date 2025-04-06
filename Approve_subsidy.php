<?php
session_start();
$Subsidy_id="KISAN".rand(1000,9999);

include 'agroww.php';
$sql="select *from subsidy_tbl";
$res=mysqli_query($conn,$sql);
while($row=mysqli_fetch_assoc($res))
{
	$_SESSION['uid']=$row['USER_ID'];
}
$query="UPDATE subsidy_tbl SET status='Approve',Subsidy_id='$Subsidy_id' Where USER_ID=".$_SESSION['uid']."";
if(mysqli_query($conn,$query))
{
	$responce['message']="APPROVE SUBSIDY SUCESSFULY";
}
else
{
	$responce['message']="DATA NOT UPDATED";
}
echo json_encode($responce);
?>