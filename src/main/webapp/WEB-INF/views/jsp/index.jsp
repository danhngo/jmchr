<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/jsp/partials/header.jsp" %>



<div class="container">
	
	<div>
		<h1>${title}</h1>
		<p>
			<c:if test="${not empty msg}">
				Hello ${msg}
			</c:if>

			<c:if test="${empty msg}">
				Welcome Welcome!
			</c:if>
		<p>
			<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
		</p>
	</div>
	
	<div class="row">
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
	</div>


	<hr>
	
	<%@ include file="/WEB-INF/views/jsp/partials/footer.jsp" %>
	
</div>



<!--
<script src="//ssl.google-analytics.com/ga.js"></script> 
 <script src="//code.jquery.com/jquery-latest.min.js"></script>

  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
  <script src="//downloads/bootstrap-hover-dropdown/bootstrap-hover-dropdown.js"></script>
 -->


</body>
</html>