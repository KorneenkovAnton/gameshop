<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="resources" var="bundle"/>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="MainPage.jsp"/>
<div class="container profile bg-light gradient-border">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="text.user_id" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="text.user_name" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="text.user_s_name" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="text.eMail" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="text.date_of_br" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="text.country" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="text.user_city" bundle="${bundle}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="User" items="${users}">
            <tr>
                <th scope="row">${User.id}</th>
                <th>${User.name}</th>
                <th>${User.sName}</th>
                <th>${User.eMail}</th>
                <th>
                    <c:choose>
                        <c:when test="${lan != null}">
                            <c:if test="${lan.equals('ru_RU')}">
                                <fmt:formatDate value="${User.dateOfBirthday}" pattern="dd.MM.yyyy"/>
                            </c:if>
                            <c:if test="${lan.equals('en_US')}">
                                <fmt:formatDate value="${User.dateOfBirthday}" pattern="MM.dd.yyyy"/>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${User.dateOfBirthday}" pattern="MM.dd.yyyy"/>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th>${User.address.country}</th>
                <th>${User.address.city}</th>
                <th>
                    <form action="/deleteUser" method="get">
                        <input type="hidden" name="deletingUser" value="${User.id}"/>
                        <input type="hidden" name="deletingAddress" value="${User.address.id}"/>
                        <input type="submit" class="btn btn-danger" value="DELETE"/>
                    </form>
                </th>
                <th>
                <c:if test="${!User.admin}">
                    <form action="/setAdmin" method="get">
                        <input type="hidden" name="newAdminId" value="${User.id}"/>
                        <input type="submit" class="btn btn-primary" value="SET ADMIN"/>
                    </form
                </c:if>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

