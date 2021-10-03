<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add new company</title>
    <link rel="stylesheet" href="resources/bootstrap.min.css">
    <link rel="stylesheet" href="resources/bootstrap.bundle.min.js">


</head>
<body>
<script type="application/javascript">
    function onSubmit(operation) {
        var frm = document.getElementById("frm2");
        if (operation === 'search') {
            if (
                !document.getElementById("cexchange").checkValidity() ||
                !document.getElementById("companyId").checkValidity() ||
                !document.getElementById("startdate").checkValidity() ||
                !document.getElementById("enddate").checkValidity()
            ) {
                return;
            }
        }
        frm.action = 'search-stock-index?action=' + operation;
        frm.submit();
    }
</script>
<jsp:include page="header.jsp">
    <jsp:param name="authorized" value="true"/>
</jsp:include>

<div class="container" style="padding: 10px">
    <h4>Stock Price Index</h4>
    <div style="margin: 20px"></div>
    <form:form id="frm2" method="post" modelAttribute="searchcompanymodel">
        <label for="cexchange">Stock Market Exchange</label>
        <form:select required="true" class="form-control" id="cexchange" path="cexchange" onchange="onSubmit('fetch')">
            <form:option value="" label="--Select Exchange--"/>
            <form:option value="BSE" label="BSE"/>
            <form:option value="NSE" label="NSE"/>
        </form:select>
        <div class="form-group">
            <label for="companyId">Name of the Company <i>(will be filled based on exchange)</i></label>
            <form:select required="true" class="form-control" onchange="onSubmit('fetch')"
                         id="companyId" path="companyId" items="${companyModels}"/>
        </div>
        <div class="form-group">
            <label for="ccode">Company Code</label>
            <form:input required="true" type="text" class="form-control" path="ccode" id="ccode" disabled="true"/>
        </div>
        <div class="form-group">
            <label for="startdate">Start Date (mm/dd/yyyy)</label>
            <form:input required="true" type="text" class="form-control" path="startdate" id="startdate"/>
        </div>
        <div class="form-group">
            <label for="enddate">End Date (mm/dd/yyyy)</label>
            <form:input required="true" type="text" class="form-control" path="enddate" id="enddate"/>
        </div>
        <button onclick="onSubmit('search')" class="btn btn-primary">Search</button>

    </form:form>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Exchange</th>
            <th scope="col">Stock Price</th>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Remark</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${stockModels}" var="stock">
            <tr>
                <th>${stock.cexchange}</th>
                <th>${stock.sprice}</th>
                <th>${stock.priceDate}</th>
                <th>${stock.priceTime}</th>
                <th></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="row" novalidate>
        <div class="col-md-4">
            <div class="input-group has-validation">
                <span class="input-group-text" id="inputMax">MAX</span>
                <input type="text" class="form-control" id="max" value="${rangemodel.max}"
                       disabled="true">
            </div>
        </div>
        <div class="col-md-4">
            <div class="input-group has-validation">
                <span class="input-group-text" id="inputMin">MIN</span>
                <input type="text" class="form-control" id="min" value="${rangemodel.min}"
                       disabled="true">
            </div>
        </div>
        <div class="col-md-4">
            <div class="input-group has-validation">
                <span class="input-group-text" id="inputAvg">AVG</span>
                <input type="text" class="form-control" id="avg" value="${rangemodel.avg}"
                       disabled="true">
            </div>
        </div>
    </form>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
