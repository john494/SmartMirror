<?php

 $filename="layout";
 unlink($filename);
 $fh = fopen($filename, 'w') or die("Can't create file");
 file_put_contents($filename,$_POST["flayout"]."\n",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; 
 #$cmd = "go run ";
 #exec($cmd . "twittertojson.go");

 ?>
