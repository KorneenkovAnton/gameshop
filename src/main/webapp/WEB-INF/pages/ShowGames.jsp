<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cur" uri="/WEB-INF/CurrencyParserTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Games</title>
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
<div class="container" style="margin-top: 40px !important;">
    <div class="row">
        <c:forEach var="Game" items="${games}">
            <div class="col-xs-12 col-sm-6 col-md-3 wrapper d-flex justify-content-center">
                <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                    <div class="mainflip">
                        <div class="frontside">
                            <div class="card">
                                <div class="text-center gradient-border">
                                    <p><img src="data:${Game.poster.mimeType};base64, ${Game.poster.base64file}"
                                            height="480" width="240"></p>
                                </div>
                            </div>
                        </div>
                        <div class="backside gradient-border">
                            <div class="card">
                                <div class="card-body position-relative">
                                    <h4 class="card-title">${Game.name}</h4>
                                    <p class="card-text">${Game.description}</p>
                                    <div class="position-absolute bottom-0 start-0">
                                        <form action="/showGameInfo" method="post">
                                            <input type="hidden" name="gameInfo" value="${Game.id}">
                                            <input type="submit" class="btn btn-success" value="Info">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<ul class="pagination justify-content-center">
    <c:choose>
        <c:when test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link" href="/mainPage?currentPage=${currentPage-1}" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
        </c:when>
        <c:otherwise>
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="1" end="${countOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="page-item active"><a class="page-link" href="#">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a href="/mainPage?currentPage=${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>
</body>
</html>

