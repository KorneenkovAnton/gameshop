<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <c:forEach var="Game" items="${user.ownedGames}">
            <div class="col-xs-12 col-sm-6 col-md-4 wrapper d-flex justify-content-center">
                <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                    <div class="mainflip" style="width: 500px !important;">
                        <div class="frontside" style="width: 500px !important;">
                            <div class="card bg-light" style="width: 500px !important;">
                                <div class="text-center bg-light" style="width: 500px !important;">
                                    <p><img src="data:${Game.poster.mimeType};base64, ${Game.poster.base64file}"
                                            height="480"
                                            width="240"></p>
                                    <h4 class="card-title">${Game.name}</h4>
                                    <p class="card-text">${Game.description}</p>
                                </div>
                            </div>
                        </div>
                        <div class="backside" style="width: 500px !important;">
                            <div class="card" style="width: 500px !important;">
                                <div class="card-body" style="width: 500px !important;">
                                    <h5 class="card-title"><fmt:message key="text.min_sys_req" bundle="${bundle}"/></h5>
                                    <p class="card-text"><fmt:message key="text.OS" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.operationSystem}</p>
                                    <p class="card-text"><fmt:message key="text.cpu_name" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.cpuName} ${Game.minimalSystemRequirements.cpuFrequency}</p>
                                    <p class="card-text"><fmt:message key="text.ram" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.ram} GB</p>
                                    <p class="card-text"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.videoAdapterName} ${Game.minimalSystemRequirements.videoAdapterMemory}
                                        GB</p>
                                    <p class="card-text"><fmt:message key="text.free_space" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.freeSpace}</p>
                                    <h5 class="card-title"><fmt:message key="text.rec_sys_req" bundle="${bundle}"/></h5>
                                    <p class="card-text"><fmt:message key="text.OS" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.operationSystem}</p>
                                    <p class="card-text"><fmt:message key="text.cpu_name" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.cpuName} ${Game.minimalSystemRequirements.cpuFrequency}</p>
                                    <p class="card-text"><fmt:message key="text.ram" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.ram}</p>
                                    <p class="card-text"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.videoAdapterName} ${Game.minimalSystemRequirements.videoAdapterMemory}
                                        GB</p>
                                    <p class="card-text"><fmt:message key="text.free_space" bundle="${bundle}"/>:
                                            ${Game.minimalSystemRequirements.freeSpace}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

