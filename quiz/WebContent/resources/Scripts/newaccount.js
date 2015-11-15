/*
 * 
 */

jQuery(document).ready(function(){

    function canSubmit() {
            var password = $("#inputPassword2").val();
            var confirmpass = $("#inputConfirmPassword2").val();

            if (password != confirmpass) {
                    alert(pass_no_match)
                    return false;
            } else {
                    return true;
            }
    }

    function checkPasswordsMatch() {
            var password = $("#inputPassword2").val();
            var confirmpass = $("#inputConfirmPassword2").val();
            
            if (password.length < 3 || confirmpass.length < 3) {
                    return;
            }
            
            if (password == confirmpass) {
                    $("#matchpass").text(pass_match);
                    $("#matchpass").addClass("valid");
                    $("#matchpass").removeClass("error");
            } else {
                    $("#matchpass").text(pass_no_match);		
                    $("#matchpass").removeClass("valid");
                    $("#matchpass").addClass("error");
            }
    }

	$("#inputPassword2").keyup(checkPasswordsMatch);
	$("#inputConfirmPassword2").keyup(checkPasswordsMatch);
	
	$("#details").submit(canSubmit);
})
