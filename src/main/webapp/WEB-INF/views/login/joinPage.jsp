<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html ng-app="joinPageApp">
<head>
<meta charset="UTF-8">
<title>Join Page</title>
<script type="text/javascript" src="js/login/joinPage.js"></script>
</head>
<body>
<!-- Content -->
<div id="content">
	<div class="inner">
		<form class="mui-form" name="frm" id="frm" ng-controller="joinPageCtrl">
		    <legend>Member Information</legend>
		    <div class="mui-textfield mui-textfield--float-label" style="width:60%;">
		        <input type="text" id="userName" name="userName" ng-model="name" required>
		        <label>Member Name</label>
		    </div>
		    <div class="mui-textfield mui-textfield--float-label" style="width:60%;">
		        <input type="email" id="userEmail" name="userEmail" ng-model="email" required>
		        <label>Email Address</label>
		    </div>
		    <div class="mui-textfield" style="width:60%;">
		        <input type="password" id="userPw" name="userPw" ng-model="textPw" required>
		        <label>Password</label>
		    </div>
		    
		    <button type="submit" class="mui-btn mui-btn--raised mui-btn--primary" ng-click="subMemInfo()">Submit</button>
		</form>
	</div>
</div>
 
</body>
</html>


