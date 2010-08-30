<?php
/*
 * Author: Hendrik Still
 * Email: gamma32@gmail.com
 */
require_once 'config.php';

/**
 * Sents the http header for the download process
 * @param $file_name string
 * @param $file_path string
 * @return void
 */
function sent_header($file_name,$file_path){
	header("Content-Disposition: filename=\"$file_name\""); // Dateiname
	header("Content-Length: ".filesize($file_path)); // Dateigrφίe
	header("Content-Type: application/octet-stream"); // MIME-Typ
}

/**
 * @return boolean
 */
function check_download_ability(){
	refresh_session_folder();
	if(check_download_ability_current() && check_download_ability_daily()){
		return true;
	}else{
		return false;
	}
}

/**
 * @return boolean
 */
function check_download_ability_current(){
	if(get_current_downloads() < MAXIMUM_PARALLEL_DOWNLOADS){
		return true;
	}else{
		return false;
	}
}

/**
 * @return boolean
 */
function check_download_ability_daily(){
	if(get_daily_downloads() < MAXIMUM_DAILY_DOWNLOADS){
		return true;
	}else{
		return false;
	}
}

/**
 * Returns count of running downloads
 * @return integer
 */
function get_current_downloads(){
	$current_downloads = scandir(CURRENT_DIR);
	$count = count($current_downloads) - 2;
	return $count;
}

/**
 * Returns count of downloads today
 * @return integer
 */
function get_daily_downloads(){
	$daily_downloads = scandir(DAILY_DIR);
	$count = count($daily_downloads) - 2;
	return $count;
}

/**
 * creates the session file for the current and daily session.
 * @return string
 */
function create_session_files(){
	$session_name = time();
	$fp = fopen(CURRENT_DIR.$session_name,'w');
	fclose($fp);
	$fp = fopen(DAILY_DIR.$session_name,'w');
	fclose($fp);

	return $session_name;

}

function remove_session_file($session_name){
	unlink(CURRENT_DIR.$session_name);
}

/**
 * Loops over all session folder and removes "old" session files
 * @return void
 */
function refresh_session_folder(){
	$current_time = time();
	$current_downloads = scandir(CURRENT_DIR);
	foreach ($current_downloads as $file_name){
		if($file_name != '.' && $file_name != '..'){
			if($file_name < $current_time - AVG_DOWNLOAD_TIME){
				unlink(CURRENT_DIR.$file_name);
			}
		}
	}

	$daily_downloads = scandir(DAILY_DIR);
	foreach ($daily_downloads as $file_name){
		if($file_name != '.' && $file_name != '..'){
			if(date('dmY',$file_name) != date('dmY',$current_time)){ //Check if session was today
				unlink(DAILY_DIR.$file_name);
			}
		}
	}
}

/**
 * @return void
 */
function reset_sessions(){
	$current_downloads = scandir(CURRENT_DIR);
	foreach ($current_downloads as $file_name){
		if($file_name != '.' && $file_name != '..'){
			unlink(CURRENT_DIR.$file_name);
		}
	}

	$daily_downloads = scandir(DAILY_DIR);
	foreach ($daily_downloads as $file_name){
		if($file_name != '.' && $file_name != '..'){
			unlink(DAILY_DIR.$file_name);
		}
	}
}

function get_oldest_current(){
	$current_downloads = scandir(CURRENT_DIR);
	sort($current_downloads);
	$oldest_current = $current_downloads[2];
	
	return $oldest_current;
	
}

?>