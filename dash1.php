<?php
include'agroww.php';
	$st=date('Y-m-d');
	$end=date('Y-m-d');			   
	
                 $st=strtotime(date('Y-m-d'));
                           $st=strtotime("-1 day",$st);
                           $st=date('Y-m-d',$st);
                           $end=date('Y-m-d');
$day1="select SUM(price) as day1 from product_buy Where pdate between '$st' and '$end'";
$d1=mysqli_query($conn,$day1);
$responce;
if(mysqli_num_rows($d1)>0)
{
	$responce['message']="success";
	while($row=mysqli_fetch_assoc($d1))
	{
	$responce['data'][]=$row; 
	#echo "</br>1";
	}
}

                 $st7=strtotime(date('Y-m-d'));
                           $st7=strtotime("-7 day",$st7);
                           $st7=date('Y-m-d',$st7);
                           $end7=date('Y-m-d');
$day7="select SUM(price) as day7 from product_buy Where pdate between '$st7' and '$end7'";
$d7=mysqli_query($conn,$day7);
if(mysqli_num_rows($d7)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d7))
	{
	$responce['data'][]=$row;
	#echo "</br>7";
	}
}

                 $st31=strtotime(date('Y-m-d'));
                           $st31=strtotime("-31 day",$st31);
                           $st31=date('Y-m-d',$st31);
                           $end31=date('Y-m-d');
$day31="select SUM(price) as day31 from product_buy Where pdate between '$st31' and '$end31'";
$d31=mysqli_query($conn,$day31);
if(mysqli_num_rows($d31)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d31))
	{
	$responce['data'][]=$row;
	#echo "</br>31";
	}
}
                 $st365=strtotime(date('Y-m-d'));
                           $st365=strtotime("-365 day",$st365);
                           $st365=date('Y-m-d',$st365);
                           $end365=date('Y-m-d');
$day365="select SUM(price) as day365 from product_buy Where pdate between '$st365' and '$end365'";
$d365=mysqli_query($conn,$day365);
if(mysqli_num_rows($d365)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d365))
	{
	$responce['data'][]=$row;
	#echo "</br>365";
	}
}
$stock="select SUM(P_PRICE*P_QUANTITY) as stock from product_tbl";
$s=mysqli_query($conn,$stock);
$responceS=[];
if(mysqli_num_rows($s)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($s))
	{
	$responce['data'][]=$row;
	#echo "</br>stock";
	}
}
$user="select count(F_NAME) as user from user_tbl";
$u=mysqli_query($conn,$user);
if(mysqli_num_rows($u)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($u))
	{
	$responce['data'][]=$row;
	#echo "</br>Users";
	}
}
echo json_encode($responce);

?>
	