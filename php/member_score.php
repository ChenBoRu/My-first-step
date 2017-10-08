<?php 
if(!isset($_SESSION)){
   session_start();
}

  require_once("connMysql.php");
  //查詢登入會員資料
  
  $sql = "SELECT *  FROM member WHERE account='".$_SESSION["account"]."'";

mysql_select_db("member");//我要從member這個資料庫撈資料
mysql_query("set names utf8");//設定utf8 中文字才不會出現亂碼
$data=mysql_query($sql);//從member中選取全部(*)的資料
?>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>會員紀錄</title>
    </head>
    <body>
<table width="800" border="1" align="left" cellpadding="4" cellspacing="0">
<tr>
      <p><font size="6" color="#FF0000">會員運動紀錄</font></p>
      <hr size="1" />
</tr>	  
<tr> 	
<td>時間</td>
<td>運動項目</td>
<td>次數</td>
<td>消耗卡路里</td>

</tr>

<?php
for($i=2;$i<=mysql_num_rows($data);$i++)
{ $rs=mysql_fetch_row($data);
?><tr>
<td><?php echo $rs[5]?></td>
<td><?php echo $rs[6]?></td>
<td><?php echo $rs[7]?></td>
<td><?php echo $rs[8]?></td>
</tr>
<?php }?>
</table>
<table align="right">
    <td width="200" >
      <?php require_once("menu.php"); ?>
    </td>
	</table>
</body>
</html>