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
	
		<table id="tbemplist" class="table table-striped table-bordered" cellspacing="0" width="100%">
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
		                <td>${employee.empId}</td>
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
	           	  <a id="btnAddEmp" class="btn btn btn-primary">Add</a>
	           	  <a id="btnModifyEmp" class="btn btn btn-primary">Modify</a>
	           	  <a id="btnDeleteEmp" class="btn btn btn-primary">Delete</a>
	           	  |	
	           	  <a id="btnexportcontract" class="btn btn btn-primary">Export Contract</a>   
	           	  <a id="btnexportresignation" class="btn btn btn-primary">Export Resignation</a>
	           	  <a id="btnleave" class="btn btn btn-primary">Leave</a>
	           	  <a id="btnperformance" class="btn btn btn-primary">Performance</a>
	         </div>	          
	     </div>
		
	</div>
	<hr>
	
	<!-- Delete Confirmation -->
	<div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            
	            <h4 class="modal-title" id="myModalLabel">Confirmation</h4>
	            </div>
	            <div class="modal-body">
	                <p>Are you sure to delete the selected employee?</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
	                <button type="button" class="btn btn-primary" id="btnConfirmDelete">Yes</button>
	        </div>
	    </div>
	  </div>
	</div>
	
	<!-- Add Employee -->
	<div class="modal fade" id="modalAddEmployee" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            
	            <h4 class="modal-title" id="myModalLabel">Modify Employee</h4>
	            </div>
	            <div class="modal-body" >
	                 <sf:form id="formAddEmployee" class="form-horizontal" role="form" action="/jmchr/employee/update" commandName="employeeForm" method="POST">
	                      <div class="row">
				            <div class="col-md-4"><label for="recipient-name" class="control-label">ID:</label><sf:input type="text" class="form-control" id="textEmpId" path="empId"/></div>
				            <div class="col-md-4"><label for="recipient-name" class="control-label">Name:</label><sf:input type="text" class="form-control" id="textName" path="name"/></div>
				          </div>
				          <div class="row">
				            <div class="col-md-4"><label for="message-text" class="control-label">Startdate:</label><sf:input type="text" class="form-control" id="textStartdate" path="startdate"/></div>
				          </div>
			        </sf:form>
	                
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	                <button type="button" class="btn btn-primary" id="btnSaveChanges">Save Changes</button>
	        	</div>
	    	</div>
	  </div>
	</div>
	
	<%@ include file="/WEB-INF/views/jsp/partials/footer.jsp" %>
	
</div>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#tbemplist').dataTable();
	} );
</script>	

</body>
</html>