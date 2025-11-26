<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결과</title>
</head>
<body>
    <p>
        <%= request.getAttribute("schools1") %>
    </p>
    <p>
        <%= request.getAttribute("students1") %>
    </p>
    <p>
        <%= request.getAttribute("students2") %>
    </p>
    <p>
        <%= request.getAttribute("students3") %>
    </p>
</body>
</html>
