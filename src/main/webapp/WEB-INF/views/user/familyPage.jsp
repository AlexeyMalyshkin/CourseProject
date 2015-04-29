<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<jsp:useBean id="familyMembers" scope="request" type="java.util.List"/>
<jsp:useBean id="familyInvites" scope="request" type="java.util.List"/>

<body>
<div class="container-fluid">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    Click <a href="<c:url value="/j_spring_security_logout" />">here</a> to logout.


    <div class="content" style="padding:0 15px;">
        <div>
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

            <input type="button" value="+"/>

            Family invites:

            <table class="table" style="width: 40%; margin-left: 30px;">
                <thead>
                <%--<tr>--%>
                <%--<td><b>User</b></td>--%>
                <%--<td><b>Can see statistic</b></td>--%>
                <%--</tr>--%>
                </thead>

                <tbody>
                <c:forEach items="${familyInvites}" var="item">
                    <tr>
                        <td>${item.user.email}</td>
                        <td>${item.family.id}</td>
                        <td><input type="button" value="Accept"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>


    Family Page

    <script>
        function changeFamilyAdmin(id)
        {
//            alert(a);
            $.ajax({
                url:'changeRights',
                data:({id: id}),
                success: function(data){
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