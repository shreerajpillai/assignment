
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
<jsp:include page="header.jsp"/>
<div class="container" style="padding: 10px">
    <c:if test="${result}">
        <div class="alert alert-success" role="alert">
            Company has been added successfully.
        </div>
    </c:if>
    <h4>Add Company Details</h4>
    <div style="margin: 20px"></div>
    <form:form action="add-company" method="post" modelAttribute="companymodel">
        <div class="form-group">
            <label for="cexchange">Stock Market Exchange</label>
            <form:select class="form-control" id="cexchange" path="cexchange">
                <form:option value="BSE" label="BSE"/>
                <form:option value="NSE" label="NSE"/>
            </form:select>
        </div>
        <div class="form-group">
            <label for="cname">Name of the Company</label>
            <form:input pattern=".{3,100}" title="3 to 100 characters"  required="true" type="text" class="form-control" path="cname" id="cname" placeholder="Enter company name"/>
        </div>
        <div class="form-group">
            <label for="cceo">Company CEO</label>
            <form:input  pattern=".{3,100}" title="3 to 100 characters" required="true" type="text" class="form-control" path="cceo" id="cceo" placeholder="Enter company CEO"/>
        </div>
        <div class="form-group">
            <label for="cturnover">Company Turnover ($)</label>
            <form:input  required="true" type="number" class="form-control" path="cturnover" id="cturnover" placeholder="Enter company turnover"/>
        </div>
        <div class="form-group">
            <label for="cdirectors">Company Board of Directors</label>
            <form:input  pattern=".{5,500}" title="5 to 500 characters" required="true" type="text" class="form-control" path="cdirectors" id="cdirectors"
                   placeholder="Enter company board of directors"/>
        </div>
        <div class="form-group">
            <label for="cprofile">About the Company</label>
            <form:textarea pattern=".{5,500}" title="5 to 500 characters" required="true" rows="3" class="form-control" path="cprofile" id="cprofile" placeholder="Enter about the company"/>
        </div>
        <button type="submit" class="btn btn-primary">Post Company</button>
    </form:form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
