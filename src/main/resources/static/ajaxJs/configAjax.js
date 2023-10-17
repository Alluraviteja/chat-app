function postJSONDatatableAjaxCall(apiURL, authToken, ajaxPostData, callback){
    
	var apiDrawCount = 0;
	
	tables = $('#datatable-responsive').DataTable({
		destroy : true,
		searching : true,
  		lengthChange: false,
		"ajax" : {
			url : apiURL,
			type : 'POST',
			contentType: "application/json",
			headers: {
		        'Authorization': authToken
		    },
			data : function (data) {
				
				apiDrawCount++;
				
				ajaxPostData[0].drawCount = apiDrawCount;
				
	    		return data = JSON.stringify(ajaxPostData[0]);
	    		
			},
			success : function(data) {
				callback(data);
			},
			error : function() {
				var failDataString = { status: failAjaxStatus};
				var failDataJSON = JSON.stringify(failDataString);
				
				callback(failDataJSON);
			}
		}
	})
}

function getJSONDatatableAjaxCall(apiURL, authToken, callback){
    
	$.ajax({
		url : apiURL,
		async: false,
		type : "GET",
		headers: {
	        'Authorization': authToken
	    },
		success : function(data) {
			callback(data);
		},
		error : function() {
			var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataJSON);
		}
	});
	
}

function postFormDataAjaxCall(apiURL, authToken, apiData, callback) {
    
    $.ajax({
        url : apiURL,
        type : "POST",
        headers: {
	        'Authorization': authToken
	    },
        data : apiData,
        contentType : false,
        mimeType : "multipart/form-data",
        processData : false,
        success : function(data) {
            callback(data);
        },
        error : function() {
        	var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataJSON);
        }
    });
    
}

function postJSONAjaxCall(apiURL, authToken, apiData, callback){
    
	$.ajax({
		url : apiURL,
		type : "POST",
		headers: {
	        'Authorization': authToken
	    },
		data : apiData,
		contentType : 'application/json',
		success : function(data) {
			callback(data);
		},
		error : function() {
			var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataJSON);
		}
	});
    
}

function getJSONAjaxCall(apiURL, authToken, callback){
	
	$.ajax({
		url : apiURL,
		type : "GET",
        headers: {
            'Authorization': authToken
        },
		success : function(data) {
			callback(data);
		},
		error : function() {
			var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataJSON);
		}
	});
	
}
function getJSONAjaxCallwithOutToken(apiURL, callback){
	
	$.ajax({
		url : apiURL,
		type : "GET",
      
		success : function(data) {
			callback(data);
		},
		error : function() {
			var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataJSON);
		}
	});
	
}
function getJSONAjaxCallWithoutHeader(apiURL, callback){
	
	$.ajax({
		url : apiURL,
		type : "GET",
    	timeout : 2000,
		success : function(data) {
			callback(data);
		},
		error : function(xmlhttprequest, textstatus, message) {
			
			if(textstatus==="timeout") {
	            
	            var failDataString = { status: failAjaxTimeoutStatus};
	            var failDataJSON = failDataString;
	            
	        } else {
	        	
	        	var failDataString = { status: failAjaxStatus};
				var failDataJSON = JSON.stringify(failDataString);
	        	
	        }
			
			callback(failDataJSON);
		}
	});
	
}

function postJSONAjaxCallEdocHeader(apiURL, authToken, apiData, callback){
    
	$.ajax({
		url : apiURL,
		type : "POST",
		headers: {
	        'xauthtoken': authToken
	    },
		data : apiData,
		contentType : 'application/json',
		success : function(data) {
			callback(data);
		},
		error : function() {
			var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataJSON);
		}
	});
    
}

function postUrlFormEncodedAjaxCall(apiURL, apiData, callback) {
    
    $.ajax({
        url : apiURL,
        type : "POST",
        headers: {
	        'Content-Type': 'application/x-www-form-urlencoded'
	    },
	    /*transformRequest: function(obj) {
	        var str = [];
	        for(var p in obj)
	        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	        return str.join("&");
	    },*/
        data: apiData,
        success : function(data) {
        	JSON.stringify(data)
        	data.status = successStatus;
            callback(data);
        },
        error : function() {
        	var failDataString = { status: failAjaxStatus};
			var failDataJSON = JSON.stringify(failDataString);
			
			callback(failDataString);
        }
    });
    
}