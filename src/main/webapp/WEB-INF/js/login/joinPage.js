'use strict';
	
var myApp = angular.module('joinPageApp',[]);
	
 myApp
 	.controller('joinPageCtrl', function($scope,$http) {
	 	init();
	 	
	 	function init(){
            $scope.name = "";
            $scope.email = "";
            $scope.textPw = "";
        }

        $("#userPw").click(function(){
        		//alert("userPw click");
        		$scope.textPw = "";
        		$("#userPw").val("");
        });
        
        $("#userEmail").click(function(){
        		$scope.email = "";
        		$("#userEmail").val("");
        });

        $scope.subMemInfo = function(){
	       	 if(!$scope.fn_validCheck){
	                return;
	            }
	       	 
	            // use $.param jQuery function to serialize data from JSON 
	            var data = $.param({
		            	name: $scope.name,
	            		email: $scope.email,
	            		textPw: $scope.textPw
	            });
	        
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
	            
	            $http.post('/login001C01.do',data,config)
	            .then(
	                function(response){
	                  // success callback
	                	 $scope.PostDataResponse = response;
	                	 
	                	 console.log("##success: " + JSON.stringify($scope.PostDataResponse));
	                }, 
	                function(response){
	                  // failure callback
	                	 $scope.ResponseDetails = "Data: " + data +
	                    "<hr />status: " + status +
	                    "<hr />headers: " + header +
	                    "<hr />config: " + config;
	                	 
	                	 console.log("##error: " + JSON.stringify(response));
	                }
	             );
	        };
        
        $scope.fn_validCheck = function(){
        		console.log("fn_validCheck");
            if($scope.name == ""){
                alert("please input name");
                $scope.name.focus;
                return false;
            }

            if($scope.email == ""){
                alert("please input email");
                $scope.email.focus;
                return false;
            }

            if($scope.textPw == ""){
                alert("please input password");
                $scope.textPw.focus;
                return false;
            }

            return true;
        };
 });

