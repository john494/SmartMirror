<?php

 $filename="facebookinfo";
# unlink($filename);
# $fh = fopen($filename, 'w') or die("Can't create file");
 file_put_contents($filename,$_POST["fdate"]."\n",FILE_APPEND);
# file_put_contents($filename,$_POST["ftime"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["fpost"]."\n",FILE_APPEND);
 file_put_contents($filename,$_POST["fmessage"]."\n",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; ?>
