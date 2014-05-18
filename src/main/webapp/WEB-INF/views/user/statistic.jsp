<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<jsp:useBean id="statistic" scope="request" type="java.util.List"/>

<body>
<div class="container-fluid">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    Click <a href="<c:url value="/j_spring_security_logout" />">here</a> to logout.

    <form:form action="showCategoriesPage">
        <button type="submit">Back</button>
    </form:form>

    <div class="row show-grid">

        <div class="col-xs-12 col-sm-6 col-lg-4">
            <div id="chartContainer"></div>
        </div>
        <div class="col-xs-12 col-sm-6 col-lg-4">
            <div id="chartContainer2"></div>
        </div>
    </div>
</div>


<%--pagination--%>

<div>
    <ul class="pagination">
        <c:forEach items="${months}" var="month">
            <li class="<c:if test="${month==activeMonth}">active</c:if>
                "><a class="testt" href="<c:url value="/viewStatisticForMonth?month=${month}" />">${monthNames.get(month)}</a>
            </li>
        </c:forEach>
    </ul>
</div>


<script>

    result = jQuery.parseJSON('${statistic}');
    var test = [];

    for (var i = 0; i < result.length; i++) {
        test[i] = {categoryName: result[i].name + ': ' + result[i].sum + '$', categorySum: parseInt(result[i].sum)};
    }

    $("#chartContainer").dxPieChart({
        dataSource: test,
        palette: "Soft Pastel",
        title: "Costs/Incomes",
        legend: {
            horizontalAlignment: "top",
            verticalAlignment: "top",
            margin: 0
        },
        pointClick: function (point) {
            point.select();
        },
        series: [
            {
                type: "doughnut",
                argumentField: "categoryName",
                valueField: "categorySum",
                hoverStyle: {
                    color: "#ffd700"
                }
            }
        ]
    });


    // Second Chart

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