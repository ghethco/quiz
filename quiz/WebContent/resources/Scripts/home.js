/*
 * Home page script
 */

jQuery(document).ready(function(){
	
    $('#newquiz-button').click( function () {
    	var question_source = $("#selectSource :selected").val();
        window.open(context_path + "/newquestion?select_source=" +
        		question_source + "&new_quiz=true", "_self");
    });
	
    $('#reset_stats-button').click( function () {
    	var source_id = $("#selectSource :selected").val();
        window.open(context_path + "/reset_stats?source_id=" +
        		source_id, "_self");
    });
	
})