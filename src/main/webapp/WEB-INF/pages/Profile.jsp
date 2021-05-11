<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet"/>
    <title>Profile</title>

    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="resources" var="bundle"/>
</head>
<body>
<jsp:include page="MainPage.jsp"/>
<div class="profile bg-light gradient-border">
    <form class="row g-3" action="/updateUser" method="post">
        <div>
            <label>ID ${user.id} </label>
        </div>
        <div class="col-md-6">
            <label for="login" class="form-label"><fmt:message key="text.login" bundle="${bundle}"/></label>
            <input type="text" required="required" class="form-control" id="login" name="login" pattern="^[a-zA-Z][a-zA-Z0-9-]{1,20}$"
            readonly value="${user.login}">
        </div>
        <div class="col-md-6">
            <label for="password" class="form-label"><fmt:message key="text.password" bundle="${bundle}"/></label>
            <input type="password" id="password" required="required" name="password" minlength="8"
                   pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" class="form-control" value="${user.password}">
        </div>
        <div class="col-md-6">
            <label for="name" class="form-label"><fmt:message key="text.user_name" bundle="${bundle}"/></label>
            <input type="text" required="required" class="form-control" id="name" name="name" pattern="^[а-яА-ЯёЁa-zA-Z '-]+$"
                   value="${user.name}">
        </div>
        <div class="col-md-6">
            <label for="password" class="form-label"><fmt:message key="text.user_s_name" bundle="${bundle}"/></label>
            <input type="text" id="sname" required="required" name="sname" minlength="8" value="${user.sName}"
                   pattern="^[а-яА-ЯёЁa-zA-Z '-]+$" class="form-control">
        </div>
        <div class="col-12">
            <label for="email" class="form-label"><fmt:message key="text.eMail" bundle="${bundle}"/></label>
            <input id="email" class="form-control" type="email" name="email" required="required"
                   pattern="([.[^@\s]]+)@([.[^@\s]]+)\.([a-z]+)" value="${user.eMail}"/>
        </div>
        <div class="col-12">
            <label for="date" class="form-label"><fmt:message key="text.date_of_br" bundle="${bundle}"/></label>
            <input id="date" class="form-control" type="date" name="date_of_birthday" required="" value="${user.dateOfBirthday}"/>
        </div>
        <input name="address_id" type="hidden" value="${user.address.id}">
        <div class="col-md-6">
            <label for="country" class="form-label"><fmt:message key="text.country" bundle="${bundle}"/></label>
            <input id="country" class="form-control" type="text" name="country" pattern="^[а-яА-ЯёЁa-zA-Z]+(?:[-][а-яА-ЯёЁa-zA-Z]+)*$"
                   value="${user.address.country}"/>
        </div>
        <div class="col-md-6">
            <label for="city" class="form-label"><fmt:message key="text.user_city" bundle="${bundle}"/> </label>
            <input id="city" class="form-control" type="text" name="city" pattern="^[а-яА-ЯёЁa-zA-Z]+(?:[-][а-яА-ЯёЁa-zA-Z]+)*$"
                   value="${user.address.city}"/>
        </div>
        <div class="col-md-6">
            <label for="street" class="form-label"><fmt:message key="text.street" bundle="${bundle}"/></label>
            <input id="street" class="form-control" type="text" name="street" pattern="^[а-яА-ЯёЁa-zA-Z]+(?:[-][а-яА-ЯёЁa-zA-Z]+)*$"
                   value="${user.address.street}"/>
        </div>
        <div class="col-md-6">
            <label for="num" class="form-label"><fmt:message key="text.num_of_house" bundle="${bundle}"/></label>
            <input id="num" class="form-control" type="text" name="number_of_house" value="${user.address.numberOfHouse}"/>
        </div>
        <div class="col-md-6">
            <button type="submit" class="btn btn-primary" name="submit" >Update</button>
        </div>
    </form>
</div>
</body>
</html>
