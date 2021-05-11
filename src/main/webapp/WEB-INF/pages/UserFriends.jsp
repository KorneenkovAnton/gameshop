<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Friends</title>
    <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet"/>
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
<div class="people-nearby profile bg-light gradient-border">
    <c:forEach var="Friend" items="${user.friends}">
        <div class="nearby-user">
            <div class="row">
                <div class="col-md-2 col-sm-2">
                    <img src="https://ui-avatars.com/api/?name=${Friend.name}+${Friend.sName}" alt="user"
                         class="profile-photo-lg">
                </div>
                <div class="col-md-7 col-sm-7">
                    <h5>${Friend.name} ${Friend.sName}</h5>
                    <p>${Friend.eMail}</p>
                </div>
                <div class="col-md-3 col-sm-3">
                    <form method="get" action="/deleteFriend">
                        <input type="hidden" name="friendID" value="${Friend.id}"/>
                        <input type="submit" class="btn-danger btn row" value="Delete friend"/>
                    </form>
                    <c:if test="${status == 0}">
                        <form action="/confirmFriend" method="get">
                            <input type="hidden" name="friendID" value="${Friend.id}"/>
                            <input type="submit" class="btn-primary btn row" value="Add Friend"/>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%--<form action="/addFriend" method="get">--%>
<%--    <input type="number" name="addFriendID" required="required" pattern="[0-9]+">--%>
<%--    <input type="submit" value="Add">--%>
<%--</form>--%>
<%--<c:forEach var="Friend" items="${user.friends}">--%>
<%--    <div class="friend">--%>
<%--        <div><span><fmt:message key="text.user_id" bundle="${bundle}"/> ${Friend.id}</span></div>--%>
<%--        <div><span><fmt:message key="text.user_name" bundle="${bundle}"/> ${Friend.name}</span></div>--%>
<%--        <div><span><fmt:message key="text.user_s_name" bundle="${bundle}"/>${Friend.sName}</span></div>--%>
<%--        <div><span><fmt:message key="text.eMail" bundle="${bundle}"/> ${Friend.eMail}</span></div>--%>

<%--    </div>--%>
<%--</c:forEach>--%>
</body>
</html>

