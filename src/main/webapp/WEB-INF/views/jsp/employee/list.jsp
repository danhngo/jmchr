<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
		<table id="emplist" class="table table-striped table-bordered" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Name</th>
	                <th>Startdate</th>
	            </tr>
	        </thead>
	 
	        <tbody>
	             <c:forEach var="employee" items="${employeeList}" >
		              <tr>
		                <td>${employee.id}</td>
		                <td>${employee.name}</td>
		                <td>${employee.startdate}</td>
		             </tr>
	         
	            </c:forEach>
	           
           </tbody>
          </table>
	</div>
	
		<div style="margin-top:10px" class="form-group">
	         <!-- Button -->
	        <div class="col-sm-12 controls">
	           	  <a id="btnexportcontract" class="btn btn-success">Export Contract</a>   
	           	  <a id="btnexportresignation" class="btn btn-success">Export Resignation</a>
	           	  <a id="btnleave" class="btn btn-success">Leave</a>
	           	  <a id="btnperformance" class="btn btn-success">Performance</a>
	         </div>	          
	     </div>
		
	</div>
	<hr>
	
	<%@ include file="/WEB-INF/views/jsp/partials/footer.jsp" %>
	
</div>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#example').dataTable();
	} );
</script>	

</body>
</html>