<?php

 $filename="zipcode";
 unlink($filename);
 $fh = fopen($filename, 'w') or die("Can't create file");
 file_put_contents($filename,$_POST["fzipcode"]."\n",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg;
// $cmd = "go run ";
// exec($cmd . "toJson.go");

 ?>

Command line:
go run helloworld.go
