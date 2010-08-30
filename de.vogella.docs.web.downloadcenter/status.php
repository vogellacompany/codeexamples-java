<?php
/*
 * Author: Hendrik Still
 * Email: gamma32@gmail.com
 */
require_once 'lib.php';
require_once 'config.php';

refresh_session_folder();
switch ($_GET['action']){

	case 'test':
		echo get_oldest_current();
		$seconds_to_next_reset_daily = ((mktime(0,0,0,date('m')+$mth,date('d')+1,date('Y')))-time()) ;
		$seconds_to_next_reset_current = AVG_DOWNLOAD_TIME - (time() - get_oldest_current());
		$seconds_to_next_reset = (!check_download_ability_current() && check_download_ability_daily()) ? $seconds_to_next_reset_current : $seconds_to_next_reset_daily;
		echo $seconds_to_next_reset;
		echo '<br>';
		echo $seconds_to_next_reset_current;
		break;

	case 'json':
		echo "{
			\"MAXIMUM_PARALLEL_DOWNLOADS\" : ".MAXIMUM_PARALLEL_DOWNLOADS.",
			\"MAXIMUM_DAILY_DOWNLOADS\" : ".MAXIMUM_DAILY_DOWNLOADS.",
			\"PARALLEL_DOWNLOADS\" : ".get_current_downloads().",
			\"DAILY_DOWNLOADS\" : ".get_daily_downloads().",
			\"DOWNLOAD_ABILITY\" : ";
		echo check_download_ability() ? 'true' : 'false';
		echo "}";
		break;
	case 'reset':
		if($_GET['password'] == RESET_PASSWORD){
			reset_sessions();
		}
	default:
		$template_vars['maximum_parallel'] = MAXIMUM_PARALLEL_DOWNLOADS;
		$template_vars['maximum_daily'] = MAXIMUM_DAILY_DOWNLOADS;
		$template_vars['current'] = get_current_downloads();
		$template_vars['day'] = get_daily_downloads();
		$template_vars['download_ability'] = check_download_ability();
		
		
		$seconds_to_next_reset_daily = ((mktime(0,0,0,date('m')+$mth,date('d')+1,date('Y')))-time()) ;
		$seconds_to_next_reset_current = AVG_DOWNLOAD_TIME - (time() - get_oldest_current());
		$seconds_to_next_reset = (!check_download_ability_current() && check_download_ability_daily()) ? $seconds_to_next_reset_current : $seconds_to_next_reset_daily;
		$next_reset_houre = floor($seconds_to_next_reset/60/60) ;
		$next_reset_minute = round(($seconds_to_next_reset/60) - ($next_reset_houre * 60));
		$template_vars['next_reset'] = $next_reset_houre.' hours '.$next_reset_minute.' minutes';

		echo loadTemplate('./template/status.html',$template_vars);
}


/**
 * My cool template engine ;-)
 * @param $template_path
 * @param $template_variables
 * @return string
 */
function loadTemplate($template_path, $template_variables ){
	$template_content = file_get_contents($template_path);
	foreach ($template_variables as $key => $template_variable)
	{
		$template_content = str_replace('{'.$key.'}',$template_variable,$template_content);
	}
	return $template_content;
}
?>