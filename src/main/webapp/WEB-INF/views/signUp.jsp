<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <form:form method="POST" action="signUp" commandName="user">
        
        <div class="form_group">
            <label>Email</label>
            <form:input path="email" class="form_control" type="email"
                   name="password" placeholder="Enter email"/>
            <form:errors path="email" cssclass="error"/>
        </div>

        <div class="form_group">
            <label>Password</label>
            <form:input path="password" class="form_control" type="password"
                                           name="password" placeholder="Enter password"/>
            <form:errors path="password" cssclass="error"/>
        </div>

        <div class="form-group">
            <input type="submit" value="Sign Up">
            </form:form>
            <form:form action="login">
                <button type="submit">Back</button>
            </form:form>
        </div>

</div>
</body>