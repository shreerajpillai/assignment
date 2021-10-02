
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


<div class="container" style="padding: 100px 200px 100px 200px">
    <form action="/examples/actions/confirmation.php" method="post">
        <h2 class="text-center">Log in</h2>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Username" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Log in</button>
        </div>
        <div class="clearfix">
             <a href="#" class="float-right">Forgot Password?</a>
        </div>
    </form>
    <p class="text-center"><a href="#">New User</a></p>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
