<?php 
  header("Content-Type: text/html; charset=utf-8");
  require_once("connMysql.php");
  //執行會員登入
  if(isset($_POST["account"]) && isset($_POST["password"])) {		
    //查詢登入會員資料
    $sql = "SELECT * FROM member WHERE account='".$_POST["account"]."'";
    $record = mysql_query($sql);		
    //取出帳號密碼的值
    $row = mysql_fetch_assoc($record);
    $account = $row["account"];
    $password = $row["password"];
    //比對密碼，若登入成功則呈現登入狀態
    if($_POST["password"]==$password) {
	  echo 'T';
    } else {
	  echo 'F';
    }
  }
?>