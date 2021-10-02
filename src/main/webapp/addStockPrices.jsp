<%--
  Created by IntelliJ IDEA.
  User: ShreeDev
  Date: 10/1/2021
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SME:Assign Stock Price Details</title>
    <link rel="stylesheet" href="resources/bootstrap.min.css">
    <link rel="stylesheet" href="resources/bootstrap.bundle.min.js">


</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" style="padding: 10px">
    <form>
        <div class="form-group">
            <label for="stockMarketExchange">Stock Market Exchange</label>
            <input type="text" class="form-control" id="stockMarketExchange" aria-describedby="stockMarketExchange"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="companyName">Name of the Company</label>
            <input type="text" class="form-control" id="companyName" placeholder="Enter company name">
        </div>
        <div class="form-group">
            <label for="currentStockPrice">Current Stock Price</label>
            <input type="text" class="form-control" id="currentStockPrice" placeholder="Enter Current Stock Price">
        </div>
        <div class="form-group">
            <label for="stockPriceDate">Stock Price @Date</label>
            <input type="text" class="form-control" id="stockPriceDate" placeholder="Enter Stock Price Date">
        </div>
        <div class="form-group">
            <label for="stockPriceTime">Stock Price @Time</label>
            <input type="text" class="form-control" id="stockPriceTime"
                   placeholder="Enter Stock Price Time">
        </div>

        <button type="submit" class="btn btn-primary">Add Stock</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
