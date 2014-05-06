<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<c:choose>
			<c:when test="${command == 'add'}">
				<h1>Add user</h1>
			</c:when>
			<c:when test="${command=='signUp'}">
				<h1>Registration</h1>
			</c:when>
			<c:otherwise>
				<h1>Edit user</h1>
			</c:otherwise>
		</c:choose>

		<div>
			<form:form method="POST" commandName="userWrapper"
				action="editUser?command=${command}">
				<table class="table">
					<tr>
						<td>Login</td>
						<td><c:choose>
								<c:when test="${command == 'add'||command=='signUp'}">
									<form:input path="login" />
									<td><form:errors path="login" ccsClass="error" /></td>

								</c:when>
								<c:otherwise>
									<form:input path="login" readonly="true" />
									<td><form:errors path="login" ccsClass="error" /></td>
								</c:otherwise>
							</c:choose></td>
						<form:hidden path="commandName" />

					</tr>
					<tr>
						<td>Password</td>
						<td><form:input path="password" /></td>
						<td><form:errors path="password" cssClass="error" />
					</tr>
					<tr>
						<td>Password again</td>
						<td><form:input path="passVerify" /></td>
						<td><form:errors path="passVerify" cssClass="error" /></td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td><form:input path="email" /></td>
						<td><form:errors path="email" cssClass="error" /></td>
					</tr>
					<%--<tr>--%>
						<%--<td>Firstname</td>--%>
						<%--<td><form:input path="firstName" /></td>--%>
						<%--<td><form:errors path="firstName" cssClass="error" /></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>Lastname</td>--%>
						<%--<td><form:input path="lastName" /></td>--%>
						<%--<td><form:errors path="lastName" cssClass="error" /></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>Date of birth (yyyy-mm-dd)</td>--%>
						<%--<td><form:input path="birthDate" id="datepicker" /></td>--%>
						<%--<td><form:errors path="birthDate" ccsClass="error" /></td>--%>
					<%--</tr>--%>
					<tr>
						<td>Role</td>
						<td><form:select path="role">
								<form:option value="ROLE_ADMIN" label="Admin" />
								<form:option value="ROLE_USER" label="User" />
							</form:select></td>
					</tr>

					<c:if test="${command == 'signUp'}">
						<tr>
							<td><img src="<c:url value="/captcha"/>" /></td>
							<td><input type="text" name="answer" />
							<td><form:errors path="captcha" ccsClass="error" /></td>
						</tr>
					</c:if>

					<tr>
						<td colspan="2"><input type="submit" class="btn btn-default"
							value="Ok" /> <c:choose>
								<c:when test="${command=='signUp'}">

									<input type="button" class="btn btn-default" value="Back"
										type="button" onclick="location.href='login'" />
								</c:when>
								<c:otherwise>
									<input type="button" class="btn btn-default" value="Back"
										type="button" onclick="location.href='select'" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
