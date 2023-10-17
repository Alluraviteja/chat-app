<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<!doctype html>
<html lang="en">
    <head>       
        <meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="Raviteja - Portfolio" name="description" />
		<meta content="Portfolio" name="author" />
		<!-- App favicon -->
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico">
		
		<!-- Bootstrap Css -->
		<link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
		<!-- Icons Css -->
		<link href="${pageContext.request.contextPath}/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
		<!-- App Css-->
		<link href="${pageContext.request.contextPath}/assets/css/app.css" id="app-style" rel="stylesheet" type="text/css" />
	    
	    <title><tiles:insertAttribute name="title" /></title>
    </head>

    <body class="authentication-bg bg-primary">
    	
    	<!-- Preloader -->
		<div class="preloader">
		  <div class="loader"></div>
		</div>
		<!-- ./ Preloader -->
    	
    	<script src="${pageContext.request.contextPath}/configJs/jquery-3.1.1.min.js"></script>
    
	   	<tiles:insertAttribute name="body" />
   		
   		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   		<script src="${pageContext.request.contextPath}/configJs/configVariables.js?v1.0"></script>
    	<script src="${pageContext.request.contextPath}/ajaxJs/configAjax.js?v1.0"></script>
   		
        <!-- JAVASCRIPT -->
	    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/js/metisMenu.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/js/waves.min.js"></script>
	    
	    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

    </body>
</html>
