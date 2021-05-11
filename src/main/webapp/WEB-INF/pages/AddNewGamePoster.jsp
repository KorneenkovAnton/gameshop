<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>Add image</title>
    <meta charset="UTF-8">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/preview.js"></script>
</head>
<body>
<jsp:include page="MainPage.jsp"/>
<div class="profile gradient-border bg-light text-center">
    <img class="rounded" src="#" id="preview" height="480" width="320" alt="" style="padding-bottom: 5px !important;">
    <form action="/addGameWithPoster" method="post" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <input type="file" class="form-control" name="file" id="imgInput" onchange="previewFunc()"/>
            <input class="btn btn-primary" type="submit" name="submit" value="Add"/>
        </div>
    </form>
</div>
</body>
</html>

