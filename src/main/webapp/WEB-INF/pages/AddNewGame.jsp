<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AddGame</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
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
<div class="bg-light profile gradient-border">
    <form action="/addGame" method="post" id="addGame" class="row g-3">
        <div class="col-12">
            <label class="form-label" for="name"><fmt:message key="text.name" bundle="${bundle}"/> </label>
            <input id="name" class="form-control" type="text" required="required" name="name" pattern="^[a-zA-Z ,-:0-9]+$"/>
        </div>
        <div class="col-md-6">
            <label class="form-label" for="desc"><fmt:message key="text.cost" bundle="${bundle}"/> </label>
            <input class="form-control" id="cost" type="text" name="cost" pattern="[0-9]{,3}"/>
        </div>
        <div class="col-md-6">
            <label class="form-label" for="desc"><fmt:message key="text.release_date" bundle="${bundle}"/> </label>
            <input class="form-control" id="date" type="date" name="release_date"/>
        </div>
        <div class="mb-3">
            <label for="desc" class="form-label"><fmt:message key="text.descr" bundle="${bundle}"/> </label>
            <textarea id="desc" class="form-control" name="description" rows="3" form="addGame" size="1000"></textarea>
<%--            <input id="desc" type="text" name="description" pattern="^[а-яА-ЯёЁa-zA-Z '-.:?0-9]+$" size="400"/>--%>
        </div>
        <div class="col-12">
            <label for="desc" class="form-label"><fmt:message key="text.dev" bundle="${bundle}"/> </label>
            <input id="dev" class="form-control" type="text" name="developer" required="required" pattern="^[a-zA-Z0-9]+$"/>
        </div>

        <label><fmt:message key="text.min_sys_req" bundle="${bundle}"/></label><br/><br/>

        <div class="minSys">
            <div class="col-12">
                <label class="form-label" for="operSysMin"><fmt:message key="text.OS" bundle="${bundle}"/> </label>
                <input class="form-control" id="operSysMin" type="text" name="operation_systemMin" required="required"
                       pattern="^[a-zA-Z 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label class="form-label" for="cpuNameMin"><fmt:message key="text.cpu_name" bundle="${bundle}"/></label>
                <input class="form-control" id="cpuNameMin" type="text" name="cpu_nameMin" required="required" pattern="^[a-zA-Z 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label class="form-label" for="cpruFreqMin"><fmt:message key="text.cpu_freq" bundle="${bundle}"/> </label>
                <input class="form-control" id="cpruFreqMin" type="text" name="cpu_frequencyMin" required="required"
                       pattern="\d+(\.\d{1})?"/>
            </div>
            <div class="col-12">
                <label class="form-label" for="ramMin"><fmt:message key="text.ram" bundle="${bundle}"/> </label>
                <input class="form-control" id="ramMin" type="text" name="ramMin" required="required" pattern="^[ 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label class="form-label" for="adapterNameMin"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> </label>
                <input class="form-control" id="adapterNameMin" type="text" name="video_adapter_nameMin" required="required"
                       pattern="^[a-zA-Z 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label class="form-label" for="adapterMemMin"><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> </label>
                <input class="form-control" id="adapterMemMin" type="text" name="video_adapter_memoryMin" required="required"
                       pattern="^[ 0-9]+$"/>
            </div>
            <div class="col-12">
                <label class="form-label" for="freeSpaceMin"><fmt:message key="text.free_space" bundle="${bundle}"/> </label>
                <input class="form-control" id="freeSpaceMin" type="text" name="free_spaceMin" required="required" pattern="^[ 0-9]+$"/>
            </div>
        </div>

        <label><fmt:message key="text.rec_sys_req" bundle="${bundle}"/> </label><br/><br/>

        <div class="recSys">
            <div class="col-12">
                <label for="ip1" class="form-label"><fmt:message key="text.OS" bundle="${bundle}"/> </label>
                <input id="ip1" class="form-control" type="text" name="operation_systemRec" required="required" pattern="^[a-zA-Z 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label for="ip2" class="form-label"><fmt:message key="text.cpu_name" bundle="${bundle}"/> </label>
                <input id="ip2" class="form-control" type="text" name="cpu_nameRec" required="required" pattern="^[a-zA-Z 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label for="ip3" class="form-label"><fmt:message key="text.cpu_freq" bundle="${bundle}"/> </label>
                <input id="ip3" class="form-control" type="text" name="cpu_frequencyRec" required="required" pattern="\d+(\.\d{1})?"/>
            </div>
            <div class="col-12">
                <label for="ip4" class="form-label"><fmt:message key="text.ram" bundle="${bundle}"/> </label>
                <input id="ip4" class="form-control" type="text" name="ramRec" required="required" pattern="^[ 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label for="ip5" class="form-label" for="adapterNameMin"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> </label>
                <input id="ip5" class="form-control" type="text" name="video_adapter_nameRec" required="required" pattern="^[a-zA-Z 0-9]+$"/>
            </div>
            <div class="col-md-6">
                <label for="ip6" class="form-label"><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> </label>
                <input id="ip6" class="form-control" type="text" name="video_adapter_memoryRec" required="required" pattern="^[0-9]+$"/>
            </div>
            <div class="col-12">
                <label for="ip7" class="form-label" for="freeSpaceMin"><fmt:message key="text.free_space" bundle="${bundle}"/> </label>
                <input id="ip7" class="form-control" type="text" name="free_spaceRec" required="required" pattern="^[0-9]+$"/>
            </div>
        </div>
        <input class="btn btn-primary" type="submit" name="submit" value="ADD"/>
    </form>
</div>
</body>
</html>
