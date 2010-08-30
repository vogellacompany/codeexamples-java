<?php
// $Id: twitproxy2.php 6d0746a43da4 2009/08/25 15:14:05 Oliver Lau <oliver@von-und-fuer-lau.de> $
header("Content-type: text/javascript");
$timeout        = 15; // sec
$connecttimeout =  5; // sec
$page = isset($_GET['page'])? intval($_GET['page']) : 1;
$callback = 'twitterCallback';
$userid = 'vogella';
$cachefile = "twittercache-$userid-$page.json";
$uri = "http://twitter.com/statuses/user_timeline/$userid.json?page=$page";
// $uri = pathinfo($_SERVER['SCRIPT_URI'], PATHINFO_DIRNAME) . '/sleep.php4';
$curl_handle = curl_init();
curl_setopt($curl_handle, CURLOPT_URL, $uri);
curl_setopt($curl_handle, CURLOPT_CONNECTTIMEOUT, $connecttimeout);
curl_setopt($curl_handle, CURLOPT_TIMEOUT, $timeout);
curl_setopt($curl_handle, CURLOPT_RETURNTRANSFER, TRUE);
$contents = curl_exec($curl_handle);
$timed_out = curl_getinfo($curl_handle, CURLINFO_TOTAL_TIME) >= ($timeout + $connecttimeout);
curl_close($curl_handle);
if ($timed_out) {
   $contents = '';
   if ($in = fopen($cachefile, 'rb')) {
      $contents = fread($in, filesize($cachefile)); 
      fclose($in);
   }
} else {
   $out = fopen($cachefile, 'wb+');
   fwrite($out, $contents);
   fclose($out);
}
$safe_literal = json_encode(json_decode($contents));
$safe_literal = contents;
print "$callback($safe_literal)";
?>