<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
</head>
<body bgcolor="#f0f8ff">
<div id="notfound" class="bg-light">
    <div class="notfound">
        <div class="notfound-404">
            <h1>${HTTPError}</h1>
            <h4>${errorMessage}</h4>
        </div>
        <a href="/mainPage">Go To Homepage</a>
    </div>
</div>
</body>
</html>
