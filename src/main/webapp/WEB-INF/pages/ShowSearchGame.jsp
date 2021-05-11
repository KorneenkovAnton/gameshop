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
    <fmt:setBundle basename="resources" var = "bundle"/>
    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
</head>
<body>
<jsp:include page="MainPage.jsp"/>

<div class="usersAdminPage">
    <form action="/searchGame">
        <input type="search" name ="searchGame" PLACEHOLDER="Search">
        <input type="submit" name="Find">
    </form>
</div>

<div class="wrapper">

    <c:forEach  var="Game" items="${games}">
        <header class="header">
            <div><span>${Game.name}</span></div>
        </header>

        <div class="middle">

            <div class="container">
                <main class="content">
                    <div><span><fmt:message key="text.cost" bundle="${bundle}"/>   <cur:currency lan="${lan}">${Game.cost}</cur:currency></span></div>
                    <div><span><fmt:message key="text.release_date" bundle="${bundle}"/>:

                    <c:choose>
                        <c:when test="${lan != null}">
                            <c:if test="${lan.equals('ru_RU')}">
                                <fmt:formatDate value="${Game.releaseDate}" pattern="dd.MM.yyyy"/>
                            </c:if>
                            <c:if test="${lan.equals('en_US')}">
                                <fmt:formatDate value="${Game.releaseDate}" pattern="MM.dd.yyyy"/>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${Game.releaseDate}" pattern="MM.dd.yyyy"/>
                        </c:otherwise>
                    </c:choose>

            </span></div>

                    <div><span><fmt:message key="text.descr" bundle="${bundle}"/> ${Game.description}</span></div>
                    <div><span><fmt:message key="text.dev" bundle="${bundle}"/> ${Game.developer}</span></div>

                    <div><span><fmt:message key="text.min_sys_req" bundle="${bundle}"/> </span></div>

                    <div><span><fmt:message key="text.OS" bundle="${bundle}"/> ${Game.minimalSystemRequirements.operationSystem}</span></div>
                    <div><span><fmt:message key="text.cpu_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuName}</span></div>
                    <div><span><fmt:message key="text.cpu_freq" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuFrequency}</span></div>
                    <div><span><fmt:message key="text.ram" bundle="${bundle}"/> ${Game.minimalSystemRequirements.ram}</span></div>
                    <div><span><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterName}</span></div>
                    <div><span><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterMemory}</span></div>
                    <div><span><fmt:message key="text.free_space" bundle="${bundle}"/> ${Game.minimalSystemRequirements.freeSpace}</span></div>

                    <div><span><fmt:message key="text.rec_sys_req" bundle="${bundle}"/> </span></div>

                    <div><span><fmt:message key="text.OS" bundle="${bundle}"/> ${Game.minimalSystemRequirements.operationSystem}</span></div>
                    <div><span><fmt:message key="text.cpu_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuName}</span></div>
                    <div><span><fmt:message key="text.cpu_freq" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuFrequency}</span></div>
                    <div><span><fmt:message key="text.ram" bundle="${bundle}"/> ${Game.minimalSystemRequirements.ram}</span></div>
                    <div><span><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterName}</span></div>
                    <div><span><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterMemory}</span></div>
                    <div><span><fmt:message key="text.free_space" bundle="${bundle}"/> ${Game.minimalSystemRequirements.freeSpace}</span></div>

                </main>
            </div>

            <aside class="left-sidebar">
                <img src="data:${Game.poster.mimeType};base64, ${Game.poster.base64file}"/>	</aside><!-- .left-sidebar -->

        </div>

        <footer class="footer">
            <c:if test="${user != null}">
                <c:choose>
                    <c:when test="${user.admin}">
                        <form action="/deleteGame" method="get">
                            <input type="hidden" name = "deletedGame" value="${Game.id}"/>
                            <input type="submit" class="delete" value="DELETE"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${user != null}">
                            <div class="str">
                                <form action="/addGameToUser" method="get">
                                    <input type="hidden" name="userID" value="${user.id}"/>
                                    <input type="hidden" name = "addedGame" value="${Game.id}"/>
                                    <input type="submit" class="delete" value="ADD"/>
                                </form>
                            </div>
                            <div class="str">
                                <form action="/addGameToCart" method="get">
                                    <input type="hidden" name="userID" value="${user.id}">
                                    <input type="hidden" name = "addedGame" value="${Game.id}"/>
                                    <input type="submit" class="delete" value="ADD to cart"/>
                                </form>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </footer>
    </c:forEach>
</div>
<div class="center">
    <div class="pagination">
        <c:if test="${currentPage != 1}">
            <a href="/searchGame?currentPage=${currentPage-1}">Previous</a>
        </c:if>
        <c:forEach begin="1" end="${countOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <a>${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="/searchGame?currentPage=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>

</body>
</html>

