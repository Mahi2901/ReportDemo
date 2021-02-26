<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="include/Head.jsp"%>
</head>
<body>
	<h1 align="center">Report</h1>
	<hr>
	<!-- <form action="/report/pdf" method="get">
	Enter Report ID : <input type="text" name="id1"><br><br>
	<input type="submit" name="report" value="Print">
	 
</form> -->
<form action="" method="post">
	<div align="center">
		<c:choose>
			<c:when test="${not empty listStudent}">
				<select name="id1">
					<option value="0"><bean:message code="Select" /></option>
					 <c:forEach items="${listStudent}" var="student" >
         				<option value="${student.studentID}">${student.studentName}</option>
      				</c:forEach>
				</select>
		</c:when>
			<c:otherwise>
				<select name="id1">
					<option value="0"><bean:message code="Select" /></option>
				</select>
			</c:otherwise>
		</c:choose>
		
		<button onclick="reportClick();return false;"><bean:message code="View" /></button>
		<button onclick="backClick();return false;"><bean:message code="Back" /></button>
	</div>
</form>
<script type="text/javascript">
function reportClick()
{
	window.document.forms[0].action="/report/pdf";
	window.document.forms[0].submit();
}
function backClick()
{
	window.document.forms[0].action="/index";
	window.document.forms[0].submit();
}
</script>
</body>
</html>