$(document).ready(function() {

	var authorization = localStorage.getItem("login_token");
	
	if (localStorage.length == 0 || !authorization) {
		localStorage.clear();
		window.top.location = webUrl;
	}
	
	window.addEventListener('popstate', function(event) {
	 console.log("called 1");
	})

	$("#loginUserNameHeader").html(localStorage.getItem("login_name"));
	$("#loginUserNameMenuSmall").html(localStorage.getItem("login_name"));
	$("#loginUserNameMenuLarge").html(localStorage.getItem("login_name"));
	$("#loginUserNameNotification").html(localStorage.getItem("login_name"));
	
	$('#logoutButton').click(function() {

		localStorage.clear();
		window.top.location = webUrl;

	});

	$(document).ajaxStart(function() {
		//$('.preloader').removeClass("d-none");
		$('.preloader').show()
	})
	.ajaxStop(function() {
		$('[data-toggle="tooltip"]').tooltip();
		$('[data-toggle="popover"]').popover();
		//$('.preloader').addClass("d-none");
		$('.preloader').fadeOut("slow")
	});

	$("input").on("keypress", function(e) {
		if (e.which === 32 && !this.value.length)
			e.preventDefault();
	});

	$("textarea").on("keypress", function(e) {
		if (e.which === 32 && !this.value.length)
			e.preventDefault();
	});

	$(".mobileNumber").keypress(function(e) {
		if (this.value.length == 0 && e.which == 48) {
			return false;
		}
		if (this.value.length == 10) {
			return false;
		}
	});

});

function pageResize() {
	//$('.table').resize();
}

