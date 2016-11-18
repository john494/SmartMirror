<?php

 $filename="twitterinfo";
 if(!empty($_POST['ffirst']))
 {
 	unlink($filename);
 	$fh = fopen($filename, 'w') or die("Can't create file");
 }
 else{
 	file_put_contents($filename,$_POST["fusername"]."\n",FILE_APPEND);
 	file_put_contents($filename,$_POST["fscrnname"]."\n",FILE_APPEND);
 	file_put_contents($filename,$_POST["ffavcount"]."\n",FILE_APPEND);
 	file_put_contents($filename,$_POST["frtcount"]."\n",FILE_APPEND);
 	file_put_contents($filename,$_POST["ftext"]."\n",FILE_APPEND);
 	file_put_contents($filename,$_POST["ftime"]."\n\n",FILE_APPEND);
 	$msg=file_get_contents($filename);
 	echo $msg; 
 	$cmd = "go run ";
 	exec($cmd . "twittertojson.go");
}
 ?>
