<?php 
  header("Content-Type: text/html; charset=utf-8");
  require_once("connMysql.php");
  session_start();
  //檢查是否已登入
  require_once("login_check.php");
  //刪除會員資料
  $sql = "DELETE FROM member ";
  $sql .= "WHERE account='".$_SESSION["account"]."'";
  mysql_query($sql);
  //刪除後，登出回到首頁
  unset($_SESSION["account"]);
  header("Location: index.php");
?>
