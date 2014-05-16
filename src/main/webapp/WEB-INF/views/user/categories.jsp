<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container-fluid">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
Click <a href="<c:url value="/j_spring_security_logout" />">here</a> to logout.

<jsp:useBean id="incomes" scope="request" type="java.util.List"/>

<div class="content" style="padding:0 15px;">
    <div class="row show-grid">

        <%--Incomes--%>

        <div class="col-xs-6 col-sm-4 col-lg-2">

            <c:forEach items="${incomes}" var="item">
                <p>

                <div style="height: 50px;">
                    <button class="btn btn-primary" data-toggle="modal" style="width: 100%; height: 100%"
                            data-target=".bs-example-modal-sm-${item.id}">${item.name}</button>
                </div>
                </p>

                <div class="modal fade bs-example-modal-sm-${item.id}" tabindex="-1" role="dialog"
                     aria-labelledby="mySmallModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <form:form method="POST" action="addTransaction" commandName="transaction">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Add transaction</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="form-group">
                                        <h4>Sum:<span class="label label-default"> ${item.sum}</span></h4>
                                    </div>

                                    <div class="form_group">
                                        <form:input path="sum" id="transactionSum" class="form_control" type="text"
                                                    placeholder="Enter sum"/>

                                        <form:hidden path="category.id" value="${item.id}"/>
                                    </div>

                                    <br>

                                </div>
                                <div class="modal-footer">

                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel
                                    </button>

                                    <button type="submit" class="btn btn-primary">Add</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>


        <%--Costs    --%>

        <div class="col-xs-6 col-sm-4 col-lg-2">

            <c:forEach items="${costs}" var="item">
                <p>

                <div style="height: 50px;">
                    <button class="btn btn-primary" data-toggle="modal" style="width: 100%; height: 100%"
                            data-target=".bs-example-modal-sm-${item.id}">${item.name}</button>
                </div>
                </p>

                <div class="modal fade bs-example-modal-sm-${item.id}" tabindex="-1" role="dialog"
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


    <%--Add Category "+" button--%>

    <div class="row">
        <div class="col-xs-6 col-sm-4 col-lg-2">
            <div style="">
                <button class="btn btn-primary" data-toggle="modal" style=""
                        data-target=".addCategory">
                    <span class="glyphicon glyphicon-plus"></span>
                </button>
            </div>
        </div>

        <div class="col-xs-6 col-sm-4 col-lg-2">
            <form:form action="viewStatistic">
                <button type="submit">Stat</button>
            </form:form>
        </div>
    </div>

    <div class="modal fade addCategory" id="addCategory" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <form:form method="POST" action="addCategory" commandName="category">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Add Category</h4>
                    </div>
                    <div class="modal-body">


                        <div class="form_group">
                            <form:input path="name" id="categoryName" class="form_control" type="text"
                                        placeholder="Enter name"/>

                            <form:errors path="name" cssclass="error"/>
                        </div>

                        <br>

                        <div class="form_group">
                            <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-primary">
                                    <input type="radio" name="options" id="option1"
                                           onchange="document.getElementById('type').value='INCOME'"> Income
                                </label>
                                <label class="btn btn-primary">
                                    <input type="radio" name="options" id="option2"
                                           onchange="document.getElementById('type').value='COST'"> Cost
                                </label>
                            </div>
                        </div>

                        <form:hidden path="type" id="type" value=""/>

                    </div>


                    <div class="modal-footer">

                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>


</div>


<script>
    $('#categoryName').bind('keyup blur', function () {
        $(this).val($(this).val().replace(/[^A-Za-z]/g, ''))
    });

    var dataSource = [
        { country: "USA", medals: 110 },
        { country: "China", medals: 100 },
        { country: "Russia", medals: 72 },
        { country: "Britain", medals: 47 },
        { country: "Australia", medals: 46 },
        { country: "Germany", medals: 41 },
        { country: "France", medals: 40 },
        { country: "South Korea", medals: 31 }
    ];

    $("#chartContainer").dxPieChart({
        dataSource: dataSource,
        palette: "Soft Pastel",
        title: "Olympic Medals in 2008",
        legend: {
            horizontalAlignment: "right",
            verticalAlignment: "top",
            margin: 0
        },
        pointClick: function (point) {
            point.select();
        },
        series: [
            {
                type: "doughnut",
                argumentField: "country",
                valueField: "medals",
                hoverStyle: {
                    color: "#ffd700"
                }
            }
        ]
    });

    $("#chartContainer2").dxPieChart({
        dataSource: dataSource,
        palette: "Soft Pastel",
        title: "Olympic Medals in 2008",
        legend: {
            horizontalAlignment: "right",
            verticalAlignment: "top",
            margin: 0
        },
        pointClick: function (point) {
            point.select();
        },
        series: [
            {
                type: "doughnut",
                argumentField: "country",
                valueField: "medals",
                hoverStyle: {
                    color: "#ffd700"
                }
            }
        ]
    });
</script>
</body>