<?php 
  header("Content-Type: text/html; charset=utf-8");
  require_once("connMysql.php");
  session_start();
  //檢查是否已登入
  require_once("login_check.php");
  //查詢登入會員資料
  $sql = "SELECT * FROM member WHERE account='".$_SESSION["account"]."'";
  $record = mysql_query($sql);
  $row = mysql_fetch_assoc($record);
?>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>會員管理系統</title>
  <script language="javascript">
  function check_form() {
	  var pw1 = document.form1.password1.value;
	  var pw2 = document.form1.password2.value;
	  if(pw1=="") {
		  alert("密碼不可以空白!");
		  return false;
	  }
	  for(var i=0; i<pw1.length; i++) {
		  if(pw1.charAt(i)==" " || pw1.charAt(i)=="\"") {
			  alert("密碼不可以含有空白或雙引號!\n");
			  return false;
		  }
	  }
	  if(pw1.length<5 || pw1.length>20) {
		  alert("密碼長度只能5到20個字元!\n" );
		  return false;
	  }
	  if(pw1!=pw2) {
		  alert("兩次輸入密碼不一樣,請重新輸入!\n");
		  return false;
	  }
  }
  </script>
</head>
<body>
<table width="800" border="0" align="center" cellpadding="4" cellspacing="0">
  <tr valign="top">
    <td width="600">
      <form action="member_change_password.php" method="POST" name="form1" onSubmit="return check_form();">
        <p><font size="6" color="#FF0000">修改會員密碼</font></p>
        <hr size="1" />
        <p><strong>使用帳號</strong>：
        	<?php echo $row["account"];?>
        </p>
	      <p><strong>使用密碼</strong>：
	      	<input type="password" name="password1"><br>
	      </p>
        <p><strong>確認密碼</strong>：
        	<input type="password" name="password2"><br>
        </p>
        <hr size="1" />
        <p align="center">
          <input name="id" type="hidden" value="<?php echo $row["id"];?>">
          <input type="submit" name="change" value="修改密碼">
          <input type="reset" name="reset" value="重設資料">
        </p>
      </form>
    </td>
    <td width="200">
      <?php require_once("menu.php"); ?>
    </td>
  </tr>
</table>
</body>
</html>
