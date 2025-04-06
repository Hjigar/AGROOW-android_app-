<?php
include'agroww.php';
	$st=date('Y-m-d');
	$end=date('Y-m-d');			   
	
                 $st=strtotime(date('Y-m-d'));
                           $st=strtotime("-1 day",$st);
                           $st=date('Y-m-d',$st);
                           $end=date('Y-m-d');
$day1="select SUM(price) as tot from product_buy Where pdate between '$st' and '$end'";
$d1=mysqli_query($conn,$day1);
$responce1=[];
if(mysqli_num_rows($d1)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d1))
	{
	$responce1['data'][]=$row; 
	#echo "</br>1";
	}
}

                 $st7=strtotime(date('Y-m-d'));
                           $st7=strtotime("-7 day",$st7);
                           $st7=date('Y-m-d',$st7);
                           $end7=date('Y-m-d');
$day7="select SUM(price) as tot from product_buy Where pdate between '$st7' and '$end7'";
$d7=mysqli_query($conn,$day7);
$responce7=[];
if(mysqli_num_rows($d7)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d7))
	{
	$responce7['data'][]=$row;
	#echo "</br>7";
	}
}

                 $st31=strtotime(date('Y-m-d'));
                           $st31=strtotime("-31 day",$st31);
                           $st31=date('Y-m-d',$st31);
                           $end31=date('Y-m-d');
$day31="select SUM(price) as tot from product_buy Where pdate between '$st31' and '$end31'";
$d31=mysqli_query($conn,$day31);
$responce31=[];
if(mysqli_num_rows($d31)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d31))
	{
	$responce31['data'][]=$row;
	#echo "</br>31";
	}
}
                 $st356=strtotime(date('Y-m-d'));
                           $st7=strtotime("-365 day",$st356);
                           $st356=date('Y-m-d',$st356);
                           $end356=date('Y-m-d');
$day356="select SUM(price) as tot from product_buy Where pdate between '$st356' and '$end356'";
$d356=mysqli_query($conn,$day356);
$responceY1=[];
if(mysqli_num_rows($d356)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($d356))
	{
	$responceY1['data'][]=$row;
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
	$responceS['data'][]=$row;
	#echo "</br>stock";
	}
}
$user="select count(F_NAME) as name from user_tbl";
$u=mysqli_query($conn,$user);
$responceU=[];
if(mysqli_num_rows($u)>0)
{
	#$responce['message']="success";
	while($row=mysqli_fetch_assoc($u))
	{
	$responceU['data'][]=$row;
	#echo "</br>Users";
	}
}
echo json_encode($responce1);
echo json_encode($responce7);
echo json_encode($responce31);
echo json_encode($responceY1);
echo json_encode($responceS);
echo json_encode($responceU);
?>
	