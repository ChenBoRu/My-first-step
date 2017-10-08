<?php 
  session_start();
  //執行登出動作，將SESSION資料清除，並重導回首頁
  unset($_SESSION["account"]);
  header("Location: index.php");
?>