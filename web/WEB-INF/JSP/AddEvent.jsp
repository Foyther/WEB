<%--
  Created by IntelliJ IDEA.
  User: Nurislam
  Date: 01.11.2016
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Event</title>
    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/mainCSS.css"/>">
    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/register.css"/>">
    <script src="/js/validation.js"></script>
</head>
<body>
<h1>New event</h1>

<form action="" class="addevent-form" method="POST">

    <div id="list"><span style="color:red" class="error">${errnameEvent}</span>
    <p><input name="nameEvent" type="text" value="${nameEvent}" class="form-control input-field"  placeholder="Event's name"></p>

    <span style="color:red" class="error">${errplace}</span>
    <p><input name="place" type="text" value="${place}" class="form-control input-field"  placeholder="What's a place"></p>

    <input type="datetime-local" name="date" value="${date}" max="2222-06-04" min="2016-11-01">

    <span style="color:red" class="error">${errdescription}</span>
    <p><input name="description" type="text" value="${description}" class="form-control input-full-field" placeholder="Description"></p>
    </div>
    <input name="btn" type="submit" value="Add event">
</form>
</body>
</html>
