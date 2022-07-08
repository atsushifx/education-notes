<?php
header("Access-Control-Allow-Origin: *");
$json = file_get_contents ("grid_bts.json");
echo $json;
 ?>