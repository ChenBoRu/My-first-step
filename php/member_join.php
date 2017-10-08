<?php 
  header("Content-Type: text/html; charset=utf-8");
  require_once("connMysql.php");
  //確認帳號是否已註冊
  $sql = "SELECT account FROM member WHERE account='".$_POST["account"]."'";
  $record = mysql_query($sql);
  if(mysql_num_rows($record)>0) {
    header("Location: member_join_form.php?registered=true&account=".$_POST["account"]);
  } else {
    //若此帳號尚未註冊，則執行新增的動作
    $sql = "INSERT INTO member (name, account, password,time,sport,nt,calories) VALUES (";
    $sql .= "'".$_POST["name"]."',";
    $sql .= "'".$_POST["account"]."',";
    $sql .= "'".$_POST["password1"]."',";
	$sql .= "'0000-00-00 00:00:00',";
	$sql .= "' ',";
	$sql .= "' ',";
	$sql .= "' ')";
    mysql_query($sql);
  }
?>
<script language="javascript">
  alert("註冊成功\n請用申請的帳號密碼登入。");
  window.location.href = "index.php";
</script>
