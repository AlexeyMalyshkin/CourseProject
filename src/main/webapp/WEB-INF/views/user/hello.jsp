<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<div class="content">

			<h1>
				Hello,
				<c:out value="${user.firstName}" />
				!
			</h1>
			Click <a href="<c:url value="/j_spring_security_logout" />" >here</a> to logout.
		</div>

	</div>
</body>


