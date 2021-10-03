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
<jsp:include page="header.jsp">
    <jsp:param name="authorized" value="true"/>
</jsp:include>

<div class="container" style="padding: 10px">
    <h4>Listed Companies Info</h4>
    <div style="margin: 20px"></div>
    <form action="list-companies" method="get">
        <div class="form-group">
            <label for="cexchange">Stock Market Exchange: (displaying companies in exchange -
                <strong>${selectedValue}</strong>)</label>
            <select class="form-control" name="selectedValue" id="cexchange" onchange="this.form.submit()">
                <option value="ALL" selected>--Select an exchange to filter the data--</option>
                <option value="BSE">BSE</option>
                <option value="NSE">NSE</option>
            </select>
        </div>

    </form>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Company Code</th>
            <th scope="col">Company Name</th>
            <th scope="col">BoD</th>
            <th scope="col">CEO</th>
            <th scope="col">Turnover</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${companyModels}" var="company">
            <tr>

                <th>${company.cexchange}</th>
                <th>${company.cname}</th>
                <th>${company.cdirectors}</th>
                <th>${company.cceo}</th>
                <th>$${company.cturnover}</th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
