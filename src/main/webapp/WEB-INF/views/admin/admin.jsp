<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>

<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/js/script.js"></script>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<div class="content">
			<a href="edit?login=&command=add" id="addUser">Add new user</a>
			<div id="allUsersList"></div>
		</div>

		<div id="formAddEdit">

			<html:form modelAttribute="userWrapper" id="add-user-form"
				formUrl="/userAjaxCustomTag.htm">
				<form:hidden path="commandName" />

				<html:inputField label="Login" name="login" />
				<html:inputField label="Password" name="password" />
				<html:inputField label="Password Again" name="passVerify" />
				<html:inputField label="Email" name="email" />
				<%--<html:inputField label="First Name" name="firstName" />--%>
				<%--<html:inputField label="Last Name" name="lastName" />--%>
				<%--<html:inputDate label="Date of Birth(dd-MM-yyyy)" name="birthDate" />--%>
				<html:inputRoleSelect label="Role" name="role" />

				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</html:form>
			<button onclick="clearForm()" id="cancel" onclick="clearInputs()"
				class="btn">Cancel</button>
		</div>
	</div>

	<ajax:formPartialRefresh validateUrl="/addUser.ajax"
		formName="add-user-form" submitUrl="/submit.ajax" />

</body>