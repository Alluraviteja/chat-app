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
	
	$("#signup-request-form").click(function(event) {
		
		var forms = document.getElementsByClassName('needs-validation-signin-request');
		
		debugger
		
		formValidation(forms, function(data){
			
			if(data === false){
			}else{
				
				var userName = $("#userName").val();
				var password = $("#password").val();
				var confirmPassword = $("#confirmPassword").val();
				
				if(password != confirmPassword) {
					
					swal("Signup", "Password did not match", "error")
					
				} else {
				
					var validateUserUrlPath = apiUrl + "api/user/signup";
				
					var validateUserDataString = { userName, password }
					var validateUserDataJSON = JSON.stringify(validateUserDataString);
					
					postJSONAjaxCall(validateUserUrlPath, "", validateUserDataJSON, function(response){
						
						JSON.stringify(response);
						
				        if(response.status == "success") {
				        	
				        	swal("Sign up", response.message, "success")
								.then((value) => {
								  window.location.replace(apiUrl);
								});
				        	
				        }  else if(response.status == failStatus) {
				        	swal("Sign up", response.message, "error")
				        }  else {
				        	swal("Sign up", "Something went wrong", "error")
				        }
				        
				    });
				}
				
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