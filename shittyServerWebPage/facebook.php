<?php

 $filename="facebookinfo";
 unlink($filename);
 $fh = fopen($filename, 'w') or die("Can't create file");
 file_put_contents($filename,$_POST["fdate"]."<br />",FILE_APPEND);
# file_put_contents($filename,$_POST["ftime"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["fstring"]."<br />",FILE_APPEND);
# file_put_contents($filename,$_POST["fcomment"]."<br />",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; ?>
