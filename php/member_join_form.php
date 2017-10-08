<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>會員管理系統</title>
  <script language="javascript">
  function check_form() {
	  var acct = document.form1.account.value;
	  if(acct=="") {		
		  alert("請填寫帳號!");
		  return false;
	  } 
		if(acct.length<5 || acct.length>20){
			alert( "您的帳號長度只能5至20個字元!");
			return false;
		}
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
	  if(document.form1.name.value=="") {
		  alert("請填寫姓名!");
		  return false;
	  }
	  return confirm("確定送出？");
  }
  </script>
</head>
<body>
<table width="800" border="0" align="center" cellpadding="4" cellspacing="0">
  <tr valign="top">
    <td width="600">
    	<form name="form1" method="post" action="member_join.php" onSubmit="return check_form();">
        <p><font size="6" color="#FF0000">加入會員</font></p>
		  	<?php if(isset($_GET["registered"])){?>
          <div>帳號 <?php echo $_GET["account"];?> 已經有人使用！</div>
        <?php }?>
        <div>
          <hr size="1" />
          <p><strong>使用帳號</strong>：
          	<input type="text" name="account">
          </p>
          <p><strong>使用密碼</strong>：
            <input type="password" name="password1">
          </p>
          <p><strong>確認密碼</strong>：
            <input type="password" name="password2">
          </p>
          <p><strong>真實姓名</strong>：
            <input type="text" name="name">
          </p>
         
          <p><font color="#FF0000">*</font>姓名確認後無法再更改，請謹慎填寫</p>
        </div>
        <hr size="1" />
        <p align="center">
          <input type="submit" name="join" value="加入會員">
          <input type="reset" name="reset" value="重設資料">
        </p>
      </form>
    </td>
  </tr>
</table>
</body>
</html>