<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("title")%> </title>
    <style>
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
<ul>
    <li>
        <a href="?">English</a>
    </li>
    <li>
        <a href="?locale=zh">Chinese</a>
    </li>
</ul>

<h2 class="center"><%=request.getAttribute("welcome")%></h2>

</body>
</html>
