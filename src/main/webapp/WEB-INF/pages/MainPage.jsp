<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/CurrencyParserTag.tld" prefix="cur" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="resources" var = "bundle"/>
</head>
<body>
<c:if test="${operationStatus != null}">
    <c:choose>
        <c:when test="${!operationStatus.equals('Success')}">
            <div class="note alert-danger" id = "error">
                    ${operationStatus}
            </div>
        </c:when>
        <c:otherwise>
            <div class="note alert-success" >
                    ${operationStatus}
            </div>
        </c:otherwise>
    </c:choose>

</c:if>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Game Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/mainPage"><fmt:message key="text.home" bundle="${bundle}"/></a>
                </li>
                <c:choose>
                    <c:when test="${user != null}">
                        <c:choose>
                            <c:when test="${user.admin}">
                                <li class="nav-item">
                                    <a class="nav-link" href="/users"><fmt:message key="text.show_all_users" bundle="${bundle}"/> </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/addNewGame"><fmt:message key="text.add_game" bundle="${bundle}"/> </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/logout">Logout</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <fmt:message key="text.my_profile" bundle="${bundle}"/> </a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                                        <li><a class="dropdown-item" href="/mainPage/myFriends/newFriendsList"><fmt:message key="text.new.friends" bundle="${bundle}"/></a></li>
                                        <li><a class="dropdown-item" href="/mainPage/myFriends/friends"><fmt:message key="text.friends" bundle="${bundle}"/></a></li>
                                        <li><a class="dropdown-item" href="/mainPage/myFriends/requests"><fmt:message key="text.req" bundle="${bundle}"/></a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="/mainPage/myGames"><fmt:message key="text.my_games" bundle="${bundle}"/> </a> </li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="/profile"><fmt:message key="text.my_profile" bundle="${bundle}"/> </a> </li>
                                        <li><a class="dropdown-item" href="/logout"><fmt:message key="text.logout" bundle="${bundle}"/></a> </li>
                                    </ul>
                                </li>

                                <li class="nav-item"><a class="nav-link" href="/cart"><fmt:message key="text.shopping_bag" bundle="${bundle}"/></a></li>
                                <li class="nav-item"><a class="nav-link" href="/donatePage"> <fmt:message key="text.donate" bundle="${bundle}"/> </a> </li>

                                <li class="nav-item">
                                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true"><cur:currency lan="${lan}">${user.money}</cur:currency></a>
                                </li>

                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href = "/login" ><fmt:message key="text.login" bundle="${bundle}" /></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <form class="d-flex" action="/searchGame">
                <input class="form-control me-2" type="search" name ="searchGame" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" name="Find" type="submit">Search</button>
            </form>

            <form action="/lan" method="get">
                <input type="hidden" name="lan" value="en_US">
                <input class="engEU" type="submit" value="">
            </form>
            <form action="/lan" method="get">
                <input type="hidden" name="lan" value="ru_RU">
                <input class="ruEU" type="submit" value="">
            </form>
        </div>
    </div>
</nav>
</body>
</html>
