/**
 * admin page script
 */

jQuery(document).ready(function(){
	
	var table = $('#user-table').dataTable({
		dom: 'T<"clear">lfrtip',
		tableTools: {
			"sRowSelect": "single"
		}
	});
	
//    $('#user-table tbody').on( 'click', 'tr', function () {
//    	$(this).toggleClass('selected');
//    	var d = this;
//    	console.log("Selected username = " + d.cells[0].innerHTML);
//    });
//    
    $('#edit_user_button').click( function () {
    	var t_table = $.fn.dataTable.TableTools.fnGetInstance('user-table');
    	var t_data = t_table.fnGetSelectedData();
    	var username = t_data[0][0];
    	window.open(context_path + "/editaccount?username=" + username, "_self");
    });
    $('#delete_user_button').click( function () {
    	var t_table = $.fn.dataTable.TableTools.fnGetInstance('user-table');
    	var t_data = t_table.fnGetSelectedData();
    	var username = t_data[0][0];
    	window.open(context_path + "/deleteaccount?username=" + username, "_self");
    });
})