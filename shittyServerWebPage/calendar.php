<?php

 $filename="calanderinfo";
# unlink($filename);
# $fh = fopen($filename, 'w') or die("Can't create file");
 file_put_contents($filename,$_POST["fevent"]."\n",FILE_APPEND);
# file_put_contents($filename,$_POST["fstock2"]."\n",FILE_APPEND);
# file_put_contents($filename,$_POST["fstock3"]."\n",FILE_APPEND);
# file_put_contents($filename,$_POST["fstock4"]."\n",FILE_APPEND);
# file_put_contents($filename,$_POST["ftext"]."\n",FILE_APPEND);
# file_put_contents($filename,$_POST["ftime"]."\n\n",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; 
# $cmd = "go run ";
# exec($cmd . "twittertojson.go");

 ?>
