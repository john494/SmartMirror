<?php

 $filename="twitterinfo";
 file_put_contents($filename,$_POST["fusername"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["fscrname"]."\n",FILE_APPEND);
 file_put_contents($filename,$_POST["ffavcount"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["frtcount"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["ftext"]."<br />",FILE_APPEND);
 file_put_contents($filename,$_POST["ftime"]."<br />",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; ?>
