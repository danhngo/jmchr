<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Import Employee</title>
</head>
<%@ include file="/WEB-INF/views/jsp/partials/header.jsp" %>
<body>
	    <div class="container">    
	    	<div id="paddingTop100">
		        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
		            <div class="panel panel-info" >
		                    <div class="panel-heading">
		                        <div class="panel-title">Import Employee</div>
		                    </div>     
		
		                    <div style="padding-top:30px" class="panel-body" >
		
		                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
		                        
		                        	<form id="importInfoForm" class="form-horizontal" role="form" action="/jmchr/employee/importexcel" method="POST" >
		                         	
		                       		  	<input type="file" id="filePath" class="filestyle" data-buttonName="btn-primary" data-buttonText="Browse" data-iconName="glyphicon glyphicon-search" />
		                         
		                                <div style="margin-top:10px" class="form-group">
		                                    <!-- Button -->
			                                  <div class="col-sm-12 controls">
		                                      	  <a id="btnimport" href="#" class="btn btn-success">Import Employee</a>                                     	
		                                    </div>
		                                </div>
		                            </form>    
		                            
		                        	<%-- <form:form id="importInfoForm" class="form-horizontal" role="form" action="/jmchr/employee/importexcel" method="POST" commandName="importInfoForm">
		                         	
		                         	<form:input type="file" id="filePath" path="filePath" class="filestyle" data-buttonName="btn-primary" data-buttonText="Browse" data-iconName="glyphicon glyphicon-search" />
		                         	
		                         	<!--            
		                            <div style="margin-bottom: 25px" class="input-group">
		                                  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		                                  <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="file path">
		                                   <span class="input-group-btn"> 
		                                   <a id="btn-login" href="#" class="btn btn-primary">Browse... </a>
		                                   </span>                             
		                              </div>
		                             -->    
		                         
		                                <div style="margin-top:10px" class="form-group">
		                                    <!-- Button -->
			                                  <div class="col-sm-12 controls">
		                                      	  <a id="btnimport" href="#" class="btn btn-success">Import Employee</a>
		                                      	 <!-- 
		                                      	 <button type="submit" class="btn btn-success">Import Employee</button>
		                                      	  -->	
		                                    </div>
		                                </div>
		                            </form:form>      --%>
		
		
		
		                        </div>                     
		                    </div>  
		        </div>
        	</div>
       
    </div>
	
</body>
</html>