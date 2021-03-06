<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/assignment">Stock Market Exchange Business |</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <c:if test="${param.authorized}">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="addCompanyInfo">Add Company</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="addStock">Add Stock</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="list-companies?selectedValue=ALL">List all Companies</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="search-company">List Company Stock Details</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="stock-index">Stock Price Index</a>
                    </li>

                </ul>

            </div>
        </c:if>
    </nav>

</header>
