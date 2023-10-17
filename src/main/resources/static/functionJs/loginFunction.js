$(document).ready(function(){
	
	localStorage.clear();
	
	$(document).ajaxStart(function () {
		//$('.preloader').show();
    })
    .ajaxStop(function () {
         //$('[data-toggle="tooltip"]').tooltip();
         //$('[data-toggle="popover"]').popover();
         //$('.preloader').hide();
    });
	
	$("#login-request-form").click(function(event) {
		
		var forms = document.getElementsByClassName('needs-validation-login-request');
		
		formValidation(forms, function(data){
			
			if(data === false){
			}else{
				
				var username = $("#userName").val();
				var password = $("#password").val();
		
				var validateUserUrlPath = apiUrl + "api/user/login";
			
				var validateUserDataString = { username, password }
				var validateUserDataJSON = JSON.stringify(validateUserDataString);
				
				postJSONAjaxCall(validateUserUrlPath, "", validateUserDataJSON, function(response){
					
					JSON.stringify(response);
					
			        if(response.status == "success") {
			        	
			        	localStorage.setItem("login_token", response.token);
			        	localStorage.setItem("login_name", response.name);
			        	
			        	if(localStorage.getItem("login_role") == 'ROLE_ADMIN') {
							window.location.href = webUrl + "/findTrains";
						} else {
							window.location.href = webUrl + "/chat";
						}
			        	
			        }  else if(response.status == failStatus) {
			        	swal("Login", response.message, "error")
			        }  else {
			        	swal("Login", "Something went wrong", "error")
			        }
			        
			    });
				
			}
		})
		
		return false;
	})
	
	function formValidation(forms, callback){
		
		//Remove class
		$(".was-validated").removeClass("was-validated")
	    // Loop over them and prevent submission
	    var validation = Array.prototype.filter.call(forms, function(form) {
	        if (form.checkValidity() === false) {
	          callback(form.checkValidity());
	          
	          /*event.preventDefault();
	          event.stopPropagation();*/
	          
	        }else{
	        	callback("true");
	        }
	        form.classList.add('was-validated');
	    });
	}
	
	$("input").on("keypress", function(e) {
	    if (e.which === 32 && !this.value.length)
	        e.preventDefault();
	});
	
})