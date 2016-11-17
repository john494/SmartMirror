<?php

 $filename="zipcode";
 file_put_contents($filename,$_POST["fzipcode"]."<br />",FILE_APPEND);
 $msg=file_get_contents($filename);
 echo $msg; ?>
