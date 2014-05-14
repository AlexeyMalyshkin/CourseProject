<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    Click <a href="<c:url value="/j_spring_security_logout" />">here</a> to logout.

    <div class="content">
        <div id="categories">
            <div id="incomes"></div>
            TEST
            ${testString}


            <jsp:useBean id="incomes" scope="request" type="java.util.List"/>

            <c:forEach items="${incomes}" var="item">
                <button class="btn btn-primary" data-toggle="modal"  data-target=".bs-example-modal-lg">${item.name}</button>

                <%--${item.name}<br>--%>
            </c:forEach>



            <button class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button>

            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        ...
                    </div>
                </div>
            </div>


            <div id="costs"></div>
        </div>
    </div>

</div>
</body>