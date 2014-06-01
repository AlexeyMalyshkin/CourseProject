<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container-fluid">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
Click <a href="<c:url value="/j_spring_security_logout" />">here</a> to logout.


<div class="content" style="padding:0 15px;">
<div class="row show-grid">

    <%--Incomes--%>

    <div class="col-xs-6 col-sm-4 col-lg-2">
        <jsp:useBean id="incomes" scope="request" type="java.util.List"/>
        <c:forEach items="${incomes}" var="item">
            <p>

            <div style="height: 50px;">
                <button class="btn btn-primary" data-toggle="modal" style="width: 100%; height: 100%"
                        onclick="$('#incomesModal-${item.id}').modal('show');">${item.name}</button>
            </div>
            </p>


            <%--Incomes modal--%>
            <div id="incomesModal-${item.id}" class="modal fade bs-example-modal-sm-${item.id}" tabindex="-1"
                 role="dialog"
                 aria-labelledby="mySmallModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Add transaction</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <h4>Sum:<span class="label label-default"> ${item.sum}</span></h4>
                            </div>
                            <form:form method="POST" action="addTransaction" commandName="transaction">
                                <div class="form_group">
                                    <form:input path="sum" id="transactionSum"  class="form_control numeric" type="text"
                                                placeholder="Enter sum"/>
                                    <form:hidden path="category.id" value="${item.id}"/>
                                </div>

                                <br>

                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form:form>
                            <button class="btn btn-primary" data-toggle="modal"
                                    data-target="#modalSure" onclick="$('#incomesSureModal${item.id}').modal('hide');
                                    $('#incomesSureModal-${item.id}').modal('show')">Remove
                                category : ${item.id}
                            </button>
                        </div>
                    </div>
                </div>
            </div>


            <!-- Are U Sure modal income -->
            <div id="incomesSureModal-${item.id}" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
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
                            <button type="button" onclick="$('#incomesModal-${item.id}').modal('show')"
                                    class="btn btn-default" data-dismiss="modal">Cancel
                            </button>
                            <form:form action="removeCategory" method="POST" commandName="category">
                                <form:hidden path="id" value="${item.id}"/>
                                <button type="submit" class="btn btn-primary" data-toggle="modalSure">Remove
                                    category : ${item.id}
                                </button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>


    <%--Costs--%>

    <div class="col-xs-6 col-sm-4 col-lg-2">
        <jsp:useBean id="costs" scope="request" type="java.util.List"/>
        <c:forEach items="${costs}" var="item">
            <p>

            <div style="height: 50px;">
                <button class="btn btn-primary" data-toggle="modal" style="width: 100%; height: 100%"
                        onclick="$('#costsModal-${item.id}').modal('show');">${item.name}</button>
            </div>
            </p>

            <%--Costs modal--%>
            <div id="costsModal-${item.id}" class="modal fade bs-example-modal-sm-${item.id}" tabindex="-1" role="dialog"
                 aria-labelledby="mySmallModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                            <%--<form:form method="POST" action="addTransaction" commandName="transaction">--%>
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Add transaction</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <h4>Sum:<span class="label label-default"> ${item.sum}</span></h4>
                            </div>
                            <form:form method="POST" action="addTransaction" commandName="transaction">
                                <div class="form_group">
                                    <form:input path="sum" id="transactionSum" class="form_control numeric" type="text"
                                                placeholder="Enter sum"/>
                                    <form:hidden path="category.id" value="${item.id}"/>
                                </div>

                                <br>

                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form:form>
                            <button class="btn btn-primary" data-toggle="modal"
                                    data-target="#modalSure2" onclick="$('#costsModal-${item.id}').modal('hide');
                                    $('#costsSureModal-${item.id}').modal('show');">Remove
                                category
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Are U Sure modal costs -->
            <div id="costsSureModal-${item.id}" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
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
                            <button type="button" onclick="$('#costsModal-${item.id}').modal('show')"
                                    class="btn btn-default" data-dismiss="modal">Cancel
                            </button>
                            <form:form action="removeCategory" method="POST" commandName="category">
                                <form:hidden path="id" value="${item.id}"/>
                                <button type="submit" class="btn btn-primary" data-toggle="modalSure">Remove
                                    category
                                </button>
                            </form:form>
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
            <button class="btn btn-primary" data-toggle="modal"
                    data-target=".addCategory">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
        </div>
    </div>
    <div class="col-xs-6 col-sm-4 col-lg-2">
        <form:form action="viewStatistic">
            <button class="btn btn-primary" type="submit">Statistic</button>
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
                                <input id="incomesRadio" type="radio" name="options"
                                       onchange="document.getElementById('type').value='INCOME'; enableSubmit();">
                                Income
                            </label>
                            <label class="btn btn-primary">
                                <input id="costsRadio" type="radio" name="options"
                                       onchange="document.getElementById('type').value='COST'; enableSubmit();">
                                Cost
                            </label>
                        </div>
                    </div>
                    <form:hidden path="type" id="type" value=""/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button id="submitButton" type="submit" disabled="disabled" class="btn btn-primary">Add</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</div>

<script>
    $("#deleteButton").click(function () {
        $('#myModal').modal('hide')
        $('#myModal2').modal('show')
    });


    function enableSubmit() {
        if (categoryNameNotEmpty()) {
            $('#submitButton').attr("disabled", false);
        }
    }

    $('#categoryName').bind('keyup blur', function () {
        $(this).val($(this).val().replace(/[^A-Za-z]/g, ''));
        validateForm();
    });

    $('.numeric').on('input', function (event) {
        this.value = this.value.replace(/[^0-9]/g, '');
    });

//    $('#transactionSum').bind('keyup blur', function () {
//        $(this).val($(this).val().replace(/[0-9]/g, ''));
//    });

    function radioChecked() {
        return $('#incomesRadio').is(':checked') ||
                $('#costsRadio').is(':checked');
    }

    function categoryNameNotEmpty() {
        var categoryName = $('#categoryName').val();
        return !(categoryName == null || categoryName == '');
    }

    function validateForm() {
        if (categoryNameNotEmpty()) {
            if (radioChecked()) {
                $('#submitButton').attr("disabled", false);
            }
        } else {
            $('#submitButton').attr("disabled", true);
        }
    }

</script>
</body>