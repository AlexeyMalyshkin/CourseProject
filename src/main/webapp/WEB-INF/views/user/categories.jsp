<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container-fluid">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    Click <a href="<c:url value="/j_spring_security_logout" />">here</a> to logout.

    <jsp:useBean id="incomes" scope="request" type="java.util.List"/>

    <div class="content" style="padding:0 15px;">
        <%--<div id="categories">--%>
        <%--<div id="incomes" class="btn-group-vertical" style="width:15%">--%>
        <div class="row show-grid">
            <div class="col-xs-6 col-sm-4 col-lg-2">

                <c:forEach items="${incomes}" var="item">
                    <p>
                    <div style="height: 50px;">
                        <button class="btn btn-primary" data-toggle="modal" style="width: 100%; height: 100%"
                                data-target=".bs-example-modal-sm-${item.name}">${item.name}</button>
                    </div>
                    </p>

                    <div class="modal fade bs-example-modal-sm-${item.name}" tabindex="-1" role="dialog"
                         aria-labelledby="mySmallModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    ...
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>


            <div class="col-xs-6 col-sm-4 col-lg-2">

                <%--<div id="incomes" class="btn-group-vertical">--%>

                <c:forEach items="${incomes}" var="item">
                    <p>
                    <div style="height: 50px;">
                        <button class="btn btn-primary" data-toggle="modal"  style="width: 100%; height: 100%"
                                data-target=".bs-example-modal-sm-${item.name}">${item.name}</button>
                    </div>
                    </p>

                    <div class="modal fade bs-example-modal-sm-${item.name}" tabindex="-1" role="dialog"
                         aria-labelledby="mySmallModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    ...
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
<%--</div>--%>
</body>