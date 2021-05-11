<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cur" uri="/WEB-INF/CurrencyParserTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
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
<div class="container bg-light" style="margin-top: 40px; margin-bottom: 40px; padding: 10px !important;">
    <div class="row">
        <div class="col-md-12 d-flex justify-content-center">
            <h4 class="card-title">${gameInfo.name}</h4>
        </div>
        <div class="col-md-12 d-flex justify-content-center">
            <p class="card-text"><fmt:message key="text.cost" bundle="${bundle}"/>
                <cur:currency lan="${lan}">${gameInfo.cost}</cur:currency>
            </p>
        </div>
        <div class="col-md-12 d-flex justify-content-center"><p class="card-text">
            <fmt:message key="text.release_date" bundle="${bundle}"/>:
            <c:choose>
                <c:when test="${lan != null}">
                    <c:if test="${lan.equals('ru_RU')}">
                        <fmt:formatDate value="${gameInfo.releaseDate}" pattern="dd.MM.yyyy"/>
                    </c:if>
                    <c:if test="${lan.equals('en_US')}">
                        <fmt:formatDate value="${gameInfo.releaseDate}" pattern="MM.dd.yyyy"/>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <fmt:formatDate value="${gameInfo.releaseDate}" pattern="MM.dd.yyyy"/>
                </c:otherwise>
            </c:choose>
        </p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 d-flex justify-content-center">
            <p><img src="data:${gameInfo.poster.mimeType};base64, ${gameInfo.poster.base64file}"
                    height="360" width="240"></p>
        </div>
        <div class="col-md-4">
            <h5 class="card-title"><fmt:message key="text.min_sys_req" bundle="${bundle}"/></h5>
            <p class="card-text"><fmt:message key="text.OS" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.operationSystem}</p>
            <p class="card-text"><fmt:message key="text.cpu_name" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.cpuName} ${gameInfo.minimalSystemRequirements.cpuFrequency}</p>
            <p class="card-text"><fmt:message key="text.ram" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.ram} GB</p>
            <p class="card-text"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.videoAdapterName} ${gameInfo.minimalSystemRequirements.videoAdapterMemory}
                GB</p>
            <p class="card-text"><fmt:message key="text.free_space" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.freeSpace}</p>
        </div>
        <div class="col-md-4">
            <h5 class="card-title"><fmt:message key="text.rec_sys_req" bundle="${bundle}"/></h5>
            <p class="card-text"><fmt:message key="text.OS" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.operationSystem}</p>
            <p class="card-text"><fmt:message key="text.cpu_name" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.cpuName} ${gameInfo.minimalSystemRequirements.cpuFrequency}</p>
            <p class="card-text"><fmt:message key="text.ram" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.ram}</p>
            <p class="card-text"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.videoAdapterName} ${gameInfo.minimalSystemRequirements.videoAdapterMemory}
                GB</p>
            <p class="card-text"><fmt:message key="text.free_space" bundle="${bundle}"/>:
                ${gameInfo.minimalSystemRequirements.freeSpace}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            ${gameInfo.description}
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 d-flex justify-content-end">
            <c:if test="${user != null}">
                <c:choose>
                    <c:when test="${user.admin}">
                        <form action="/deleteGame" method="get">
                            <input type="hidden" name="deletedGame" value="${gameInfo.id}"/>
                            <input type="submit" class="btn btn-danger" value="DELETE"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${user != null}">
                            <div class="str">
                                <form action="/addGameToUser" method="get">
                                    <input type="hidden" name="userID" value="${gameInfo.id}"/>
                                    <input type="hidden" name="addedGame"
                                           value="${gameInfo.id}"/>
                                    <input type="submit" class="btn btn-primary"
                                           value="Add"/>
                                </form>
                            </div>
                            <div class="str">
                                <form action="/addGameToCart" method="get">
                                    <input type="hidden" name="userID" value="${gameInfo.id}">
                                    <input type="hidden" name="addedGame"
                                           value="${gameInfo.id}"/>
                                    <input type="submit" class="btn btn-primary"
                                           value="Add to cart"/>
                                </form>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
    <section class="content-item" id="comments">
        <div class="container">
            <div class="row ">
                <div class="col-sm-8">
                    <c:if test="${user != null}">
                        <form action="/addNewComment" id="addComment">
                            <h3 class="d-flex justify-content-sm-start">New Comment</h3>
                            <input type="hidden" name="game_id" value="${gameInfo.id}">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-2 col-lg-2 hidden-xs">
                                        <img src="https://ui-avatars.com/api/?name=${user.name}+${user.sName}"
                                             alt="user"
                                             class="profile-photo-lg">
                                    </div>
                                    <div class="form-group col-xs-12 col-sm-8 col-lg-10">
                                    <textarea form="addComment" class="form-control" id="message"
                                              name="message" placeholder="Your message" required="true"
                                              maxlength="250"></textarea>
                                    </div>
                                    <div class="col-sm-1 col-lg-2 hidden-xs">
                                        <input type="submit" class="btn btn-primary"
                                               value="Submit"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </c:if>
                    <c:forEach var="comment" items="${gameInfo.comments}">
                        <div class="media">
                            <a class="pull-left" href="#"><img
                                    src="https://ui-avatars.com/api/?name=${comment.user.name}+${comment.user.sName}"
                                    alt="user"
                                    class="profile-photo-lg"></a>
                            <div class="media-body">
                                <h4 class="media-heading">${comment.user.name} ${comment.user.sName}</h4>
                                <p>${comment.message}</p>
                                <ul class="list-unstyled list-inline media-detail pull-left">
                                    <li><i class="fa fa-calendar"></i>
                                        <c:choose>
                                            <c:when test="${lan != null}">
                                                <c:if test="${lan.equals('ru_RU')}">
                                                    <fmt:formatDate value="${comment.createdAt}"
                                                                    pattern="dd.MM.yyyy hh:mm"/>
                                                </c:if>
                                                <c:if test="${lan.equals('en_US')}">
                                                    <fmt:formatDate value="${comment.createdAt}"
                                                                    pattern="MM.dd.yyyy hh:mm"/>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate value="${comment.createdAt}"
                                                                pattern="MM.dd.yyyy hh:mm"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
