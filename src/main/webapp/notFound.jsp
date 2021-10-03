<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Stock Market Exchange Business</title>
    <link rel="stylesheet" href="resources/bootstrap.min.css">
    <link rel="stylesheet" href="resources/bootstrap.bundle.min.js">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                    404 Not Found</h2>
                <div class="error-actions">
                    <a href="/assignment" class="btn btn-primary btn-lg"><span
                            class="glyphicon glyphicon-home"></span>
                        Take Me Home </a><a href="#" class="btn btn-default btn-lg"><span
                        class="glyphicon glyphicon-envelope"></span> Contact Support </a>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>
</body>
</html>
