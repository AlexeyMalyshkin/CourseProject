<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <c:if test="${not empty error}">
        <div class="errorblock">
            Bad credentials.
        </div>
        <br/>
    </c:if>

    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <fieldset>
            <div class="form-group">
                <label>Email &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label> <input
                    class="form_control" name="j_username" type="text"
                    placeholder="Enter login"/><br/>
            </div>

            <div class="form_group">
                <label>Password</label> <input class="form_control" type="password"
                                               name="j_password" placeholder="Enter password"/>
            </div>

            <br/> <input class="btn btn-default" type="submit" value="Login">

            <input class="btn btn-default" type="button" value="Sign up"
                   onclick="location.href='signUpPage'"/>
        </fieldset>
    </form>
</div>
</body>
