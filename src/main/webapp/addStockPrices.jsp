
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>SME:Assign Stock Price Details</title>
    <link rel="stylesheet" href="resources/bootstrap.min.css">
    <link rel="stylesheet" href="resources/bootstrap.bundle.min.js">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="authorized" value="true"/>
</jsp:include>
<div class="container" style="padding: 10px">
    <c:if test="${result}">
        <div class="alert alert-success" role="alert">
            Stock has been added successfully.
        </div>
    </c:if>
    <h4>Add Stock Prices</h4>
    <div style="margin: 20px"></div>
    <form:form id="frm" method="post" modelAttribute="stockmodel">

<%--        <form:form action="get-companies" method="get">--%>
            <div class="form-group">
                <label for="cexchange">Stock Market Exchange</label>
                <form:select required="true" class="form-control" id="cexchange" path="cexchange" onchange="onSubmit('fetch')">
                    <form:option value="" label="--Select Exchange--"/>
                    <form:option value="BSE" label="BSE"/>
                    <form:option value="NSE" label="NSE"/>
                </form:select>
            </div>
            <div class="form-group">
                <label for="companyId">Name of the Company <i>(will be filled based on exchange)</i></label>
                <form:select required="true" class="form-control"
                             id="companyId" path="companyId" items="${companyModels}"/>
            </div>
<%--        </form:form>--%>
        <div class="form-group">
            <label for="sprice">Current Stock Price ($)</label>
            <form:input required="true" type="number" class="form-control" path="sprice" id="sprice" placeholder="Enter stock price"/>
        </div>
        <div class="form-group">
            <label for="priceDate">Stock Price @Date (mm/dd/yyyy)</label>
            <form:input required="true" type="text" class="form-control" path="priceDate" id="priceDate"
                        placeholder="Enter Stock Price Date"/>
        </div>
        <div class="form-group">
            <label for="priceTime">Stock Price @Time (hh:mm)</label>
            <form:input required="true" type="text" class="form-control" path="priceTime" id="priceTime"
                        placeholder="Enter Stock Price Time"/>
        </div>

        <button onclick="onSubmit('save')" class="btn btn-primary">Add Stock</button>
    </form:form>
</div>

<script type="application/javascript">
    function onSubmit(operation){
        var frm = document.getElementById("frm");


        if(operation==='save'){
            if(
                !document.getElementById("cexchange").checkValidity() ||
                !document.getElementById("companyId").checkValidity() ||
                !document.getElementById("sprice").checkValidity() ||
                !document.getElementById("priceDate").checkValidity() ||
                !document.getElementById("priceTime").checkValidity()
            ){
                return;
            }
            else {
                frm.action = 'save-stock?action=' + operation;
            }
        }
        else{
            frm.action='save-stock?action='+operation;
        }
        frm.submit();
    }
</script>

<jsp:include page="footer.jsp"/>
</body>
</html>
