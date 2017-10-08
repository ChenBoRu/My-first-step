<?php
mysql_connect("127.0.0.1","root","");
mysql_select_db("phpbook_db");
mysql_query("set names utf8");
$category=$_POST['category'];
$q=mysql_query("SELECT * from article where category='".$category."' limit 10");
while($e=mysql_fetch_assoc($q))
$output[]=$e;
print(json_encode($output));
mysql_close();
?>