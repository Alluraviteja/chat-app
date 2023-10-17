$(document).ready(function(){
	
	var authorization = localStorage.getItem("login_token");
	
	fetchFriendsList();
	
	function fetchFriendsList() {
		
		var fetchFriendsListUrlPath = apiUrl + "api/user/fetchFriendsByUsername";
		
		getJSONAjaxCall(fetchFriendsListUrlPath, authorization, function(response){
			
			JSON.stringify(response);
			
	        if(response.status == "success") {
	       		
	        	$("#chatFriendsList").empty();
	        	
				for(let i = 0; i < response.userFriendsList.length; i++) {
	        	
		        	var userLi = '<li class="active">'+
	                                '<a href="javascript:void(0)">'+
	                                    '<div class="media">'+
	                                        '<div class="align-self-center me-3">'+
	                                            '<i class="mdi mdi-circle text-success font-size-10"></i>'+
	                                        '</div>'+
	                                        '<div class="avatar-xs align-self-center me-3">'+
	                                        	'<span class="avatar-title rounded-circle bg-soft-primary text-primary d-none recieverUserId">'+
	                                                ''+response.userFriendsList[i].id+''+
	                                            '</span>'+
	                                            '<span class="avatar-title rounded-circle bg-soft-primary text-primary">'+
	                                                ''+response.userFriendsList[i].name.charAt(0).toUpperCase() + response.userFriendsList[i].name.slice(1)+''+
	                                            '</span>'+
	                                        '</div>'+
	                                        '<div class="media-body align-self-center overflow-hidden">'+
	                                            '<h5 class="text-truncate font-size-14 mb-1">'+response.userFriendsList[i].name+'</h5>'+
	                                        '</div>'+
	                                    '</div>'+
	                                '</a>'+
	                            '</li>';
	        	
	        		$("#chatFriendsList").append(userLi);
	           	}
	        	
	        	fetchMessages();
	        	
	        }  else if(response.status == failStatus) {
	        	swal("Chat", response.message, "error");
	        }  else {
	        	swal("Chat", "Something went wrong", "error");
	        }
	        
	    });
		
		return false;
		
	}
	
	$("#searchByUsernameButton").click(function(event) {
		
		var userName = $("#searchByUsername").val();
		
		var searchByUsernameUrlPath = apiUrl + "api/user/searchByUsername";
	
		var searchByUsernameDataString = { userName }
		var searchByUsernameDataJSON = JSON.stringify(searchByUsernameDataString);
		
		postJSONAjaxCall(searchByUsernameUrlPath, authorization, searchByUsernameDataJSON, function(response){
			
			JSON.stringify(response);
			
	        if(response.status == "success") {
	        	
	        	var userLi = '<li class="active">' +
	                                    '<a href="javascript:void(0)">' +
	                                        '<div class="media">' +
	                                            '<div class="avatar-xs align-self-center me-3">' +
	                                                '<span class="avatar-title rounded-circle bg-soft-primary text-primary">' +
	                                                    'A' +
	                                                '</span>' +
	                                            '</div>' +
	                                            '<div class="media-body align-self-center overflow-hidden">' +
	                                            	'<h5 class="text-truncate font-size-14 mb-1 d-none" id="newUserId">'+response.id+'</h5>' +
	                                                '<h5 class="text-truncate font-size-14 mb-1" id="newUser">'+response.name+'</h5>' +
	                                            '</div>' +
	                                            '<div class="avatar-xs align-self-center">' +
		                                            '<button type="button" class="btn btn-primary btn-rounded waves-effect waves-light" id="addUserToFriends">' +
		                                            	'<i class="mdi mdi-plus"></i>' +
		                                           	'</button>' +
		                                        '</div>' +
	                                        '</div>' +
	                                    '</a>' +
	                                '</li>';
	        	
	        	$("#searchByUsernameList").empty();
	        	$("#searchByUsernameList").append(userLi);
	        	
	        	addUserToFriends();
	        	
	        }  else if(response.status == failStatus) {
	        	swal("Chat", response.message, "error");
	        }  else {
	        	swal("Chat", "Something went wrong", "error");
	        }
	        
	    });
		
		return false;
	})
	
	$("input").on("keypress", function(e) {
	    if (e.which === 32 && !this.value.length)
	        e.preventDefault();
	});
	
	function addUserToFriends() {
		$("#addUserToFriends").click(function(e) {
			
			var newUsernameId = $("#newUserId").html();
		
			var addFriendsUsernameUrlPath = apiUrl + "api/user/addFriendsUsername";
		
			var addFriendsUsernameDataString = { newUsernameId }
			var addFriendsUsernameDataJSON = JSON.stringify(addFriendsUsernameDataString);
			
			postJSONAjaxCall(addFriendsUsernameUrlPath, authorization, addFriendsUsernameDataJSON, function(response) {
				
				JSON.stringify(response);
				$("#searchByUsername").val('');
				$("#searchByUsernameList").empty();
				
		        if(response.status == "success") {
		        	
		        	swal("Add Friend", response.message, "success")
								.then((value) => {
								  	fetchFriendsList();
								});
		        	
		        }  else if(response.status == failStatus) {
		        	swal("Add Friend", response.message, "error")
		        }  else {
		        	swal("Add Friend", "Something went wrong", "error")
		        }
		        
		    });
			
			return false;
			
		})
	}
	
	function fetchMessages() {
		
		$("#chatFriendsList li").click(function(e) {
			
			var recieverUserId = $(this).find(".recieverUserId")[0].innerText;
		
			var fetchMessagesUrlPath = apiUrl + "api/chat/fetchChatMessages";
		
			var fetchMessagesDataString = { recieverUserId }
			var fetchMessagesDataJSON = JSON.stringify(fetchMessagesDataString);
			
			postJSONAjaxCall(fetchMessagesUrlPath, authorization, fetchMessagesDataJSON, function(response) {
				
				JSON.stringify(response);
				
				$(".user-chat").children().remove()
				if(response.status == "success") {
		      		
		      		var messagesLi = '';
		      		
		      		for(let i = 0; i < response.userChat.length; i++) {
	        			
	        			if(response.userChat[i].senderChat) {
			        		
			        		messagesLi += '<li>' +
	                                        '<div class="conversation-list">' +
	                                            '<div class="media">' +
	                                                '<img src="assets/images/avatar-7.jpg" class="rounded-circle avatar-xs" alt="">' +
	                                                '<div class="media-body arrow-left ms-3">' +
	                                                    '<div class="ctext-wrap">' +
	                                                        '<p>' +
	                                                            ''+response.userChat[i].message+'' +
	                                                        '</p>' +
	                                                        '<p class="chat-time mb-0"><i class="bx bx-time-five align-middle me-1"></i>'+response.userChat[0].createdDate+'</p>' +
	                                                    '</div>' +
	                                                '</div>' +
	                                            '</div>' +
	                                        '</div>' +
	                                    '</li>';
	                	} else {
							
							messagesLi += '<li class="right">' +
	                                            '<div class="conversation-list">' +
	                                                '<div class="media">' +
	                                                    '<div class="media-body arrow-right me-3">' +
	                                                        '<div class="ctext-wrap">' +
	                                                            '<p>' +
	                                                                ''+response.userChat[i].message+'' +
	                                                            '</p>' +
	                                                            '<p class="chat-time mb-0"><i class="bx bx-time-five align-middle me-1"></i>'+response.userChat[0].createdDate+'</p>' +
	                                                        '</div>' +
	                                                    '</div>' +
	                                                    '<img src="assets/images/avatar-4.jpg" class="rounded-circle avatar-xs" alt="">' +
	                                            	'</div>' +
	                                            '</div>' +
	                                        '</li>';
							
						}
						  
					}
		      		
					var userMessages = '<div class="card">' +
	                            '<div class="p-4 border-bottom ">' +
	                                '<div class="row">' +
	                                    '<div class="col-md-4 col-9">' +
	                                    	'<h5 class="font-size-15 mb-4 text-truncate d-none" id="recieverNameId">'+response.recieverId+'</h5>' +
	                                        '<h5 class="font-size-15 mb-4 text-truncate" id="recieverName">'+response.recieverName+'</h5>' +
	                                    '</div>' +
	                                '</div>' +
	                            '</div>' +
								
	                            '<div>' +
	                                '<div class="chat-conversation p-3">' +
	                                    '<ul class="list-unstyled" data-simplebar style="max-height: 600px;">' +
	                                        ''+messagesLi+'' +
	                                    '</ul>' +
	                                '</div>' +
	                                '<div class="p-3 chat-input-section">' +
	                                    '<div class="row">' +
	                                        '<div class="col">' +
	                                            '<div class="position-relative">' +
	                                                '<input type="text" class="form-control" id="messageContent" placeholder="Enter Message...">' +
	                                            '</div>' +
	                                        '</div>' +
	                                        '<div class="col-auto">' +
	                                            '<button type="submit" class="btn btn-primary btn-rounded chat-send w-md waves-effect waves-light">' +
	                                            	'<span class="d-none d-sm-inline-block me-2" id="sendMessageButton">Send</span> <i class="mdi mdi-send"></i>' +
	                                           	'</button>' +
	                                        '</div>' +
	                                    '</div>' +
	                                '</div>' +
	                            '</div>' +
	                        '</div>';
		       		
		       		$(".user-chat").append(userMessages);
		       		
		       		sendMessageButtonFunction();
		       		
		    	}
		    	 
		    });
			
			return false;
			
		})
		
	}
	
	function sendMessageButtonFunction() {
		
		$("#sendMessageButton").click(function(e) {
			
			var recieverUserId = $("#recieverNameId").html();
			var message = $("#messageContent").val();
		
			var sendMessageUrlPath = apiUrl + "api/chat/sendChatMessage";
			
			var sendMessageDataString = { recieverUserId, message };
			var sendMessageDataJSON = JSON.stringify(sendMessageDataString);
			
			postJSONAjaxCall(sendMessageUrlPath, authorization, sendMessageDataJSON, function(response) {
				
				JSON.stringify(response);
				
		        if(response.status == "success") {
		        	
		        	console.log("success")
		        	
		        }  else if(response.status == failStatus) {
		        	swal("Add Friend", response.message, "error")
		        }  else {
		        	swal("Add Friend", "Something went wrong", "error")
		        }
		        
		    });
			
			return false;
			
		})
		
	}
	
})