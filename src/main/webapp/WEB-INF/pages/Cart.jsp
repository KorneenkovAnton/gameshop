<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cur" uri="/WEB-INF/CurrencyParserTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <meta charset="UTF-8">
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
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info panel-shadow">
            <div class="profile panel-body bg-light">
                <div class="table-responsive ">
                    <table class="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Description</th>
                                <th><fmt:message key="text.cost" bundle="${bundle}"/></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="game" items="${cart}">
                                <tr>
                                    <td><img src="data:${game.poster.mimeType};base64, ${game.poster.base64file}" width="70px"
                                             height="70px"></td>
                                    <td>
                                        <strong>${game.name}</strong>
                                        <p>${game.developer}</p>
                                    </td>
                                    <td>
                                        <cur:currency lan="${lan}">${game.cost}</cur:currency>
                                    </td>
                                    <td>
                                        <form class="form-inline" action="/deleteFromCart" method="post">
                                            <input type="hidden" name="deleteCart" value="${game.id}">
                                            <input type="submit" class="btn-danger btn" value="Delete">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <form action="/buyGames" method="post">
                    <input type="submit" class="btn-primary btn" value="Buy">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
