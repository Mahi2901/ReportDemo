<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="include/Head.jsp" %>
		<title>
			<c:choose>
				<c:when test="${operation eq 'add'}">
					<bean:message code="AddStudent"/>
				</c:when>
				<c:otherwise>
					<bean:message code="UpdateStudent"/>
				</c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
		<div class="container-fluid">
			
			<html:form action="${pageContext.request.contextPath}/addStudent" method="post" modelAttribute="student">
				<html:hidden path="studentID"/>
				<input type="hidden" id="operation" name="operation" value="${operation}">
				<center>	
					<table>
						<tr>
							<td>
								<label><bean:message code="EnterName"/> :: </label>
							</td>
							<td>
								<html:input class="form-control" path="studentName"/>
								<html:errors class="errClass" path="studentName"></html:errors>
							</td>
						</tr>
						<tr>
							<td>
								<label><bean:message code="EnterCity"/> :: </label>
							</td>
							<td>
								<html:input class="form-control" path="city"/>
								<html:errors class="errClass" path="city"></html:errors>
							</td>
						</tr>
						<tr>
							<td>
								<label><bean:message code="SelectGender"/> :: </label>
							</td>
							<td>
								<html:radiobutton path="gender" value="male"/> <bean:message code="Male"/>  
								<html:radiobutton path="gender" value="female"/> <bean:message code="Female"/>
								<html:errors class="errClass" path="gender"></html:errors>
							</td>
						</tr>
						<tr>
							<td>
								<button type="submit" class="btn btn-primary btn-block">
									<c:choose>
										<c:when test="${operation eq 'add'}">
											<bean:message code="Add"/>
										</c:when>
										<c:otherwise>
											<bean:message code="Update"/>
										</c:otherwise>
									</c:choose>
								</button>
							</td>
						</tr>
					</table>
				</center>
			</html:form>
		</div>
	</body>
</html>