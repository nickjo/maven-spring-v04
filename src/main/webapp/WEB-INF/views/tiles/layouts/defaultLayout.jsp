<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
 
<!DOCTYPE html>
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%--<meta name="viewport" content="width=device-width, initial-scale=1" />--%>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title><tiles:getAsString name="title" /></title>
    <!-- <link type="text/css" href="css/layout.css" rel="stylesheet"/> -->
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" type="text/css" href="components/mui/packages/cdn/css/mui.css">
    <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->

    <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <script type="text/javascript" src="components/jquery/dist/jquery.js"></script>
    <script src="assets/js/skel.min.js"></script>
    <script src="assets/js/util.js"></script>
    <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="assets/js/main.js"></script>

	<!-- angular -->
	<script type="text/javascript" src="components/angular/angular.js"></script>	
	<script src="components/angular-aria/angular-aria.js"></script>
    <script src="components/angular-animate/angular-animate.js"></script>
    <script src="components/angular-material/angular-material.js"></script>
    <script type="text/javascript" src="components/angular-ui-router/release/angular-ui-router.js"></script>
    <script type="text/javascript" src="components/angular-mocks/angular-mocks.js"></script>
    <script type="text/javascript" src="components/angular-resource/angular-resource.js">
	
	<!-- requirejs -->	
	<!-- <script type="text/javascript" src="components/requirejs/require.js"></script>	 -->

    <%--mui--%>
    <script type="text/javascript" src="components/mui/packages/cdn/js/mui.js"></script>
    <script type="text/javascript" src="components/mui/packages/cdn/angular/mui-angular.js"></script>
<title>JSP</title>
</head>
<body>
<!-- Wrapper -->
<div id="wrapper">
	<header id="header">
	    <tiles:insertAttribute name="header" />
	</header>
	
	<section id="sidemenu">
	    <tiles:insertAttribute name="menu" />
	</section>
	
	<section id="siteContent">
	    <tiles:insertAttribute name="body" />
	</section>
	
	<footer id="footer">
	    <tiles:insertAttribute name="footer" />
	</footer>
</div>
 
</body>
</html>


