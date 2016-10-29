<?php

 $filename="twitterinfo";
 unlink($filename);
 $fh = fopen($filename, 'w') or die("Can't create file");
 file_put_contents($filename,$_POST["fusername"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["ffavcount"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["frtcount"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["ftext"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["ftime"]."<br />",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; ?>
