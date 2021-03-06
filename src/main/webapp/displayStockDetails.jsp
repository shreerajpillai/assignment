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
        if (operation === 'fetch') {
            frm.action = 'list-stocks?action=' + operation;
        } else {
            frm.action = 'list-stocks?action=' + operation;
        }
        frm.submit();
    }
</script>
<jsp:include page="header.jsp">
    <jsp:param name="authorized" value="true"/>
</jsp:include>

<div class="container" style="padding: 10px">
    <h4>Stock Details of a Company</h4>
    <div style="margin: 20px"></div>
    <form:form id="frm2" method="post" modelAttribute="searchcompanymodel">
        <label for="cexchange">Stock Market Exchange</label>
        <form:select class="form-control" id="cexchange" path="cexchange" onchange="onSubmit('fetch')">
            <form:option value="" label="--Select Exchange--"/>
            <form:option value="BSE" label="BSE"/>
            <form:option value="NSE" label="NSE"/>
        </form:select>
        <%--            <option value="">--Select Exchange--</option>--%>
        <%--            <option value="BSE">BSE</option>--%>
        <%--            <option value="NSE">NSE</option>--%>
        <div class="form-group">
            <label for="companyId">Name of the Company <i>(will be filled based on exchange)</i></label>
            <form:select class="form-control" onchange="onSubmit('fetch')"
                         id="companyId" path="companyId" items="${companyModels}"/>
        </div>
        <div class="form-group">
            <label for="ccode">Company Code</label>
            <form:input type="text" class="form-control" path="ccode" id="ccode" disabled="true" />
        </div>
        <div class="form-group">
            <label for="cceo">Company CEO</label>
            <form:input type="text" class="form-control" path="cceo" id="cceo" disabled="true" />
        </div>
        <div class="form-group">
            <label for="cturnover">Turnover($)</label>
            <form:input type="text" class="form-control" path="cturnover" id="cturnover" disabled="true" />
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
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
