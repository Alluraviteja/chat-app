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
	
	$("#send-otp-request-form").click(function(event) {
		
		var forms = document.getElementsByClassName('needs-validation-send-otp');
		
		formValidation(forms, function(data){
			
			if(data === false){
			}else{
				
				var email = $("#email").val();
				
				$(".needs-validation-password-reset").removeClass('d-none');
				$("#send-otp-request-form").parent().addClass('d-none');
				$("#email").prop('disabled', true);
				/*if(password == confirmPassword) {
				
					var validateUserUrlPath = apiUrl + "api/user/login";
				
					var validateUserDataString = { username, password }
					var validateUserDataJSON = JSON.stringify(validateUserDataString);
					
					postJSONAjaxCall(validateUserUrlPath, "", validateUserDataJSON, function(response){
						
						JSON.stringify(response);
						
				        if(response.status == "success") {
				        	
				        	localStorage.setItem("login_token", response.token);
				        	localStorage.setItem("login_name", response.name);
				        	localStorage.setItem("login_role", response.roleName);
				        	
				        }  else if(response.status == failStatus) {
				        	swal("Reset password", response.message, "error")
				        }  else {
				        	swal("Reset password", "Something went wrong", "error")
				        }
				        
				    });
				 
				 } else if(response.status == failStatus) {
				 	swal("Reset password", response.message, "error")
				 }*/
				
			}
		})
		
		return false;
	})
	
	$("#password-reset-request-form").click(function(event) {
		
		var forms = document.getElementsByClassName('needs-validation-password-reset');
		
		formValidation(forms, function(data){
			
			if(data === false){
			}else{
				
				var email = $("#email").val();
				var otp = $("#otp").val();
				var password = $("#password").val();
				var confirmPassword = $("#confirmPassword").val();
				
				if(password == confirmPassword) {
				
					var validateUserUrlPath = apiUrl + "api/user/setPassword";
				
					var validateUserDataString = { email, otp, password};
					var validateUserDataJSON = JSON.stringify(validateUserDataString);
					
					postJSONAjaxCall(validateUserUrlPath, "", validateUserDataJSON, function(response){
						
						JSON.stringify(response);
						
				        if(response.status == "success") {
				        	
				        	swal(response.message)
							.then((value) => {
							  window.location.replace(apiUrl);
							});
				        	
				        }  else if(response.status == failStatus) {
				        	swal("Reset password", response.message, "error")
				        }  else {
				        	swal("Reset password", "Something went wrong", "error")
				        }
				        
				    });
				 
				 } else {
				 	swal("Reset password", "Passwords did not match", "error")
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