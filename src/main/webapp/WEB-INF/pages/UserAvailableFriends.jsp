<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>New Friends</title>
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
    <c:forEach var="Friend" items="${availableFriends}">
        <div class="nearby-user">
            <div class="row">
                <div class="col-md-2 col-sm-2">
                    <img src="https://ui-avatars.com/api/?name=${Friend.name}+${Friend.sName}" alt="user"
                         class="profile-photo-lg">
                </div>
                <div class="col-md-7 col-sm-7">
                    <h5>${Friend.name} ${Friend.sName}</h5>
                    <p>${Friend.eMail}</p>
                    <p>
                        <c:choose>
                            <c:when test="${lan != null}">
                                <c:if test="${lan.equals('ru_RU')}">
                                    <fmt:formatDate value="${Friend.dateOfBirthday}" pattern="dd.MM.yyyy"/>
                                </c:if>
                                <c:if test="${lan.equals('en_US')}">
                                    <fmt:formatDate value="${Friend.dateOfBirthday}" pattern="MM.dd.yyyy"/>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${Friend.dateOfBirthday}" pattern="MM.dd.yyyy"/>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <div class="col-md-3 col-sm-3">
                    <form action="/addFriend" method="get">
                        <input type="hidden" name="addFriendID" value="${Friend.id}">
                        <input type="submit" class="btn btn-primary" value="Add">
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>

