<?php
/*
 * Author: Hendrik Still
 * Email: gamma32@gmail.com
 */
define('MAXIMUM_PARALLEL_DOWNLOADS',3); //Anzahl der Downloads die parallel laufen dürfen
define('MAXIMUM_DAILY_DOWNLOADS',15);//Anzahl der Downloads die innerhalb eines Kalendertages laufen dürfen
define('AVG_DOWNLOAD_TIME',1 * 360); //Maximale Zeit eines Downloads, bis der Download Slot wieder freigegeben wird in Sekunden
define('RESET_PASSWORD','bla'); // Passwort um den "Session Counter" über die URL http://meineurl/status.php?action=reset&password=bla zurückzusetzen 
define('NO_DOWNLOAD_REDIRECTION','./status.php'); //Weiterleitungsziel wenn die Download limits erreicht wurden


/* Angabe der Dateien mit Alias und Pfad 
*Bsp.:
$files = array(
	"meinAlias" => array('path' => './pfad7zur/Datei.zip', 'name'=>'downloadName.zip'),
	"test" => array('path' => './secure/test.txt' , 'name'=>'test.txt')
);
*/

$files = array(
	"alias" => array('path' => './secure/test2.txt', 'name'=>'test2.txt'),
	"test" => array('path' => './secure/test.txt' , 'name'=>'test.txt'),
	"android" => array('path'=> './secure/android-sdk-windows-1.5_r2.zip', 'name'=>'android.zip')
);

//am besten hier nichts ändern
define('CURRENT_DIR','./sessions/current/');
define('DAILY_DIR','./sessions/daily/'); 




?>
