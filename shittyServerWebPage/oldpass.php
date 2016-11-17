<?php

# $filename="passtest.html";
# unlink($filename);
# $fh = fopen($filename, 'w') or die("Can't create file");
# file_put_contents($filename,$_POST["fname"]."<br />",FILE_APPEND);
# file_put_contents($filename,$_POST["fpass"]."<br />",FILE_APPEND);
# file_put_contents($filename,$_POST["femail"]."<br />",FILE_APPEND);
# file_put_contents($filename,$_POST["fcomment"]."<br />",FILE_APPEND);
# $msg=file_get_contents($filename);
# echo $msg; 
 #http_response_code(404);
 $file = "testingmd5";#$_POST["fname"];
 unlink($file);
 $fh = fopen($file, 'w') or die("Can't create file");
 $username = $_POST["fname"];
 $password = $_POST["fpass"];
 $username = md5($username);
 $password = md5($password);
 file_put_contents($file,$username."\n",FILE_APPEND);
 file_put_contents($file,$password."\n",FILE_APPEND);
 $msg=file_get_contents($file);
 echo $msg;
 $handle = fopen("users", "r");
 if ($handle) {
    while (($line = fgets($handle)) !== false) {
        // process the line read.
	if ($line != $username."\n" && $line != $password."\n"){
		header('HTTP/1.1 410 Not Found');
	}
    }
 } 
 fclose($handle);

# header('HTTP/1.1 411 Not Found')
?>
