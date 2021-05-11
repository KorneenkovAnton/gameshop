
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Donate</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/style.css" rel = "stylesheet" type="text/css">
</head>
<body>
<div class="gradient-border" id="box">
    <form action="/donate" method="post">
        <div class="row mb-3">
            <div class="col-sm-10">
                <input type="text" class="form-control" name="donate" placeholder="Currency" aria-label="City">
            </div>
        </div>
        <div>
            <input type="submit" class="btn btn-primary" name="submit" value="DONATE">
        </div>
    </form>
</div>
</body>
</html>
