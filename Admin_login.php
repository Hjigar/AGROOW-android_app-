<?php
session_start();
include 'agroww.php';
$email=$_REQUEST['email'];
$pass=$_REQUEST['pass'];
$query="SELECT * FROM admin_tbl where email='$email' && password='$pass'";
$result=mysqli_query($conn,$query);
$responce=[];
$res=mysqli_query($conn,$query);
if(mysqli_num_rows($res)>0)
{
	$responce['message']="LOGIN SUCSESS";
	$responce['type']="admin";
	while($row=mysqli_fetch_assoc($res))
		{
			$responce['uid']=$row['A_ID'];
			$_SESSION['admin']=$row['A_ID'];
			$responce['fname']=$row['A_NAME'];
		}	

}
else
{
	$query1="SELECT * FROM user_tbl where email='$email' && password='$pass'";
	$result1=mysqli_query($conn,$query);

	$res1=mysqli_query($conn,$query1);
	if(mysqli_num_rows($res1)>0)
	{
		$responce['message']="LOGIN SUCSESS";
		$responce['type']="user";
		while($row=mysqli_fetch_assoc($res1))
		{
			$responce['uid']=$row['USER_ID'];
			$_SESSION['user']=$row['USER_ID'];
			$responce['fname']=$row['F_NAME'];
		}	
	}

	else
	{
		$responce['message']="INVALID EMAIL OR PASSWORD";
	}
}
echo json_encode($responce);	
?>