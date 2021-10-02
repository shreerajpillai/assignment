<%--
  Created by IntelliJ IDEA.
  User: ShreeDev
  Date: 10/1/2021
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/assignment_war">Stock Market Exchange Business |</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="addCompanyInfo">Add Company</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="addStockPrices.jsp">Add Stock</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="list-companies?selectedValue=ALL">List of Companies</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Fetch Company</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Stock details by date range</a>
                </li>

            </ul>

        </div>
    </nav>

</header>
