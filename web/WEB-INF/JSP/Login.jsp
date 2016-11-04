<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>

    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/mainCSS.css"/>">
    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/register.css"/>">
    <script src="/js/validation.js"></script>
</head>
<body>

<form action="" class="login-form" method="post">
    <h1>Login</h1>
    <p>
            <span style="color:red">${wrong}</span>
        </p>
    <P><input name="email" type="text" maxlength="20" placeholder="Введите свой email"></P>
        <P><input name="password" type="password" placeholder="Введите свой пароль"></P>
        <P><input type="submit" class="btn-sm" value="Sign in">
</form>
</body>
</html>