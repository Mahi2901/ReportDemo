<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="include/Head.jsp" %>
<bean:eval expression="@environment.getProperty('default.page.size')" var="pageSize"></bean:eval>
</head>
<body>
<h1 align="center">Report Demo </h1>
<hr>
<!-- <form action="/report/pdf" method="get">
	Enter Report ID : <input type="text" name="id1"><br><br>
	<input type="submit" name="report" value="Print">
	 
</form> -->
<div>
				<c:if test="${not empty successMsg}">
					<h3 class="successClass">${successMsg}</h3>
				</c:if>
				<c:if test="${not empty errMsg}">
					<h3 class="errClass">${errMsg}</h3>
				</c:if>
			</div>
<center>
<h1><a href="${pageContext.request.contextPath}/showaddStudent" class="btn btn-primary btn-block">Add Student</a></h1>
<h1><a href="${pageContext.request.contextPath}/showReport" class="btn btn-primary btn-block">Report</a></h1>

<div>
				<div>
					<h2><bean:message code="TotalRecords"/> :: ${totalItems}</h2>
				</div>
				<c:choose>
					<c:when test="${not empty listStudent}">
						<table class="table" border="1">
							<tr>
								<th><bean:message code="StudentName"/></th>
								<th><bean:message code="City"/></th>
								<th><bean:message code="Gender"/></th>
								<th><bean:message code="Action"/></th>
							</tr>
							<c:forEach items="${listStudent}" var="student">
								<tr>
									<td>${student.studentName}</td>
									<td>${student.city}</td>
									<td>${student.gender}</td>
									<td>
										<a href="${pageContext.request.contextPath}/updateStudent/${student.studentID}" class="btn btn-primary">Update</a>
										<a href="${pageContext.request.contextPath}/deleteStudent/${student.studentID}" class="btn btn-danger">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<h3 class="col-md-4 offset-4 mt-4"><bean:message code="label_no_record_found"/></h3>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="paginateDiv"></div>
			
</center>
<script type="text/javascript">
	$(document).ready(function(){
		var totalPages = Math.ceil(${totalItems}/${pageSize});
		if(totalPages > 1){
			$('#paginateDiv').append("<ul id='appendLine' class='pagination'>");
			for(var i=1; i<=totalPages; i++){
				if(i == ${currentPage}){
					$('#appendLine').append("<li class='page-item active'><a class='page-link' href='#'>" + i + "</a></li>");
				} else {
					$('#appendLine').append("<li><a class='page-link' href='${pageContext.request.contextPath}/" + i + "'" + ">" + i + "</a></li>");
				}
			}
			$('#paginateDiv').append("</ul>");
		}
	});
	</script>
</body>
</html>