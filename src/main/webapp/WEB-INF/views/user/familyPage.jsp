<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<jsp:useBean id="familyMembers" scope="request" type="java.util.List"/>
<jsp:useBean id="familyInvites" scope="request" type="java.util.List"/>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="container-fluid">
    <%@ include file="/WEB-INF/jspf/actionbar.jspf" %>


    <div class="content" style="padding:0 15px;">
        <br>
            <form:form modelAttribute="familyMembers" method="POST" action="/showFamilyPage">
                <table class="table" style="width: 40%; margin-left: 30px;">
                    <thead>
                    <tr>
                        <td><b>User</b></td>
                        <td><b>Can see family statistic</b></td>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${familyMembers}" var="item" varStatus="status">
                        <tr>
                            <td>${item.email}</td>

                            <td>
                                <input type="checkbox" id="familyAdmin" value="${item.familyAdmin}"
                                        <c:if test="${item.familyAdmin}">
                                            checked
                                        </c:if>
                                       onclick="changeFamilyAdmin(${item.id})"/>
                                    <%--onclick="changeFamilyAdmin(${item.id})"/>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form:form>

        <button class="btn btn-primary" data-toggle="modal"
                onclick="$('#inviteMember').modal('show');">Invite family member
        </button>

        <jsp:useBean id="hasFamily" scope="request" type="java.lang.Boolean"/>
        <c:if test="${hasFamily}">
            <button class="btn btn-primary" data-toggle="modal"
                    onclick="$('#leaveFamily').modal('show');">Leave Family
            </button>
        </c:if>


        <br>
            <br>
            Family invites:

            <table class="table" style="width: 40%; margin-left: 30px;">
                <thead>
                </thead>

                <tbody>
                <c:forEach items="${familyInvites}" var="item">
                    <tr>
                        <td>${item.from.email}</td>
                        <td>${item.family.id}</td> <%--remove this--%>

                        <form:form method="POST" action="acceptInvite" commandName="invite">
                                <form:hidden path="id" value="${item.id}"/>
                            <td><button type="submit" class="btn btn-primary">Accept</button></td>
                        </form:form>

                        <form:form method="POST" action="declineInvite" commandName="invite">
                            <form:hidden path="id" value="${item.id}"/>
                            <td><button type="submit" class="btn btn-primary">Decline</button></td>
                        </form:form>

                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>


    Family Page


<%--Invite member modal--%>
<div class="modal fade inviteMember" id="inviteMember" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <form:form method="POST" action="inviteMember" commandName="user">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Invite family member</h4>
                </div>
                <div class="modal-body">
                    <div class="form_group">
                        <form:input path="email" id="email" class="form_control" type="text"
                                    placeholder="enter email"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button id="submitButton" type="submit" class="btn btn-primary">Add</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%--Leave family modal--%>
<div id="leaveFamily" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title">Are U Sure?!</h4>
            </div>
            <div class="modal-body">
                <button type="button" onclick="$('#leaveFamily').modal('show')"
                        class="btn btn-default" data-dismiss="modal">Cancel
                </button>
                <form:form action="leaveFamily" method="POST">
                    <button type="submit" class="btn btn-primary" data-toggle="modalSure">Leave</button>
                </form:form>
            </div>
        </div>
    </div>
</div>


    <script>
        function changeFamilyAdmin(id) {
//            alert(a);
            $.ajax({
                url: 'changeRights',
                data: ({id: id}),
                success: function (data) {
                    alert(data);
                }
            });

//            if(){
//                alert("checked");
//            } else {
//                alert("unchecked");
//            }
        }
    </script>

</body>