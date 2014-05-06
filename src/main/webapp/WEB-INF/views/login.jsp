<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<c:if test="${not empty error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
			<br />
		</c:if>


        <%--<form action="${loginUrl}" method="post">--%>

		<%--<form name="loginForm" method="POST" action="${loginUrl}">--%>


        <%--<c:url value="/j_spring_security_check" var="loginUrl"/>--%>
        <%--<form action="${loginUrl}">--%>
        <%--<fieldset>--%>
				<%--<div class="form-group">--%>
					<%--<label>Login &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label> <input--%>
						<%--class="form_control" name="j_username" type="text"--%>
						<%--placeholder="Enter login" /><br />--%>
				<%--</div>--%>

				<%--<div class="form_group">--%>
					<%--<label>Password</label> <input class="form_control" type="password"--%>
						<%--name="j_password" placeholder="Enter password" />--%>
				<%--</div>--%>

				<%--<br /> <input class="btn btn-default" type="submit" value="Login">--%>
				<%--<input class="btn btn-default" type="button" value="Sign up"--%>
					<%--onclick="location.href='edit?login=&command=signUp'" />--%>
			<%--</fieldset>--%>
		<%--</form>--%>

        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="form-control" name="j_username" placeholder="Email address">
            <input type="password" class="form-control" name="j_password" placeholder="Password">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Go</button>
        </form>
	</div>
</body>
