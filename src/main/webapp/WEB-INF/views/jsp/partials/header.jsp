<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>  

<head>
<title>JMC HR</title>

<s:url value="/resources/core/css/jmchr.css" var="jmchrCss" />
<s:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<s:url value="/resources/core/css/dataTables.bootstrap.css" var="dataTableBootstrapCss" />

<link href="${dataTableBootstrapCss}" rel="stylesheet" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${jmchrCss}" rel="stylesheet" />

<s:url value="/resources/core/js/jquery-2.1.4.min.js" var="jqueryJs" />
<s:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
<s:url value="/resources/core/js/bootstrap-hover-dropdown.min.js" var="bootstrapHoverJs" />
<s:url value="/resources/core/js/bootstrap-filestyle.min.js" var="bootstrapFileJs" />
<s:url value="/resources/core/js/jmchr.js" var="jmchrJs" />
<s:url value="/resources/core/js/jquery.dataTables.min.js" var="jqueryDatatableJs" />
<s:url value="/resources/core/js/dataTables.bootstrap.js" var="dataTablesBootstrapJs" />


<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${bootstrapHoverJs}"></script>
<script src="${bootstrapFileJs}"></script>
<script src="${jmchrJs}"></script>
<script src="${jqueryDatatableJs}"></script>
<script src="${dataTablesBootstrapJs}"></script>

</head>

<header class="navbar navbar-fixed-top navbar-inverse">
   <div class="container">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
     </button>
     <a class="navbar-brand" href="/jmchr">JMC HRMS</a>
     <div class="navbar-collapse nav-collapse collapse navbar-header">
       <ul class="nav navbar-nav">
         <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated">Employee<b class="caret"></b></a>
           <ul class="dropdown-menu">
             <li><a href="/jmchr/employee/list">Management</a></li>
             <li class="divider"></li>
             <li><a href="/jmchr/employee/import">Import Profile</a></li>
           </ul>
         </li>
         <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Payroll<b class="caret"></b></a>
           <ul class="dropdown-menu">
              <li><a href="#">Import Attendance</a></li>
			  <li><a href="#">Formula</a></li>
			  <li><a href="#">Calculation</a></li>
           </ul>
         </li>
          <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Social Insurance<b class="caret"></b></a>
           <ul class="dropdown-menu">
              <li><a href="#">Form Management</a></li>
			  <li><a href="#">Export...</a></li>
           </ul>
         </li>
          <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Personal Income Tax<b class="caret"></b></a>
           <ul class="dropdown-menu">
              <li><a href="#">Formula Management</a></li>
			  <li><a href="#">Calculation</a></li>
           </ul>
         </li>
         
         <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Account <b class="caret"></b></a>
           <ul class="dropdown-menu">
             <li><a tabindex="-1" href="#">My Account</a></li>
             <li class="divider"></li>
             <li><a tabindex="-1" href="#">Change Email</a></li>
             <li><a tabindex="-1" href="#">Change Password</a></li>
             <li class="divider"></li>
             <li><a tabindex="-1" href="#">Logout</a></li>
           </ul>
         </li>
        
       </ul>
     </div> <!-- .nav-collapse -->
   </div> <!-- .container -->
 </header> <!-- .navbar -->