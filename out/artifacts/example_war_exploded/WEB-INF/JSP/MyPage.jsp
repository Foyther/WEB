<%--
  Created by IntelliJ IDEA.
  User: Nurislam
  Date: 25.10.2016
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My page</title>

    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/mainCSS.css"/>">
</head>
<body>
<h1 class="hero-heading">What's up, ${name}</h1>
<div>
    <span>Your age: ${age}</span></br>
    <span>Your city: ${city}</span></br>
    <span>Your sex: ${sex}</span></br>
</div>
<a class="button" href="/addEvent">Add Event</a>
</body>
</html>
