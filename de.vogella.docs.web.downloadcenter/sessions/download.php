<?php
/*
 * Author: Hendrik Still
 * Email: gamma32@gmail.com
 */
require_once 'config.php';
require_once 'lib.php';

$requested_file = $_GET['file']; //file alias
$file_path = $files[$requested_file]; //real filepath


if(isset($file_path)){ //check if alias exists
	if(check_download_ability()){
		$session_name = create_session_files();
		sent_header($requested_file,$file_path);
		readfile($file_path);
		remove_session_file($session_name);
	}else{
		header('Location:'.NO_DOWNLOAD_REDIRECTION);
	}
}else{
	header('HTTP/1.1 404 Not Found');
	header('Status: 404 Not Found');
}

?>