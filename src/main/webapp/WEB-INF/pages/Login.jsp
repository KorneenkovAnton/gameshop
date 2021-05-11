<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <fmt:setBundle basename="resources" var="bundle"/>
    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
</head>
<body>
<jsp:include page="MainPage.jsp"/>
<div class="login bg-light gradient-border">
    <form action="/getUser" method="post">
        <div class="row mb-3">
            <label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <input type="text" name="login" placeholder="Login" required="required"
                       pattern="^[a-zA-Z][a-zA-Z0-9-]{1,20}$" class="form-control" id="inputLogin">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" name="password" placeholder="Password" required="required"
                       pattern="^(?=.*\d)(?=.*[a-z])(?!.*\s).*$"
                       minlength="8" class="form-control" id="inputPassword3">
            </div>
        </div>
        <button type="submit" name="submit" class="btn btn-primary">Sign in</button>
        <a href="/register">Register</a>
    </form>
</div>
</body>
</html>
