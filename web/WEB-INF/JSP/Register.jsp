<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/mainCSS.css"/>">
    <link rel="stylesheet" href="<c:url value="/CSS/myCSS/register.css"/>">
    <script src="/js/validation.js"></script>
</head>
<body>
<h1>Create new account</h1>

<form action="" class="register-form" method="post">
    <span style="color:red" class="error">${errfirstName}</span>
    <p><input name="firstName" type="text" value="${firstName}" class="form-control input-field" maxlength="20" placeholder="First name"></p>

    <span style="color:red" class="error">${errsecondName}</span>
    <p><input name="secondName" type="text" value="${secondName}" class="form-control input-field" maxlength="20" placeholder="Second name"></p>

    <span style="color:red" class="error">${erremail}</span>
    <p><input name="email" type="text" value="${email}" class="form-control input-field" maxlength="20" placeholder="Email Address"></p>

    <span style="color:red" class="error">${errpassword}</span>
    <p><input name="password" type="password" class="form-control input-field" placeholder="Password"></p>

    <span style="color:red" class="error">${errrepassword}</span></p>
    <p><input name="repassword" type="password" placeholder="Confirm Password"></p>

    <p><label>Sex : </label><input name="sex" type="radio" value="Male"
                       <c:if test="${sex eq 'M'}">checked</c:if> >
        <label>Male </label>

        <input name="sex" type="radio" value="Female"
               <c:if test="${sex eq 'F'}">checked</c:if> >
        <label>Female</label>
        <span style="color:red" class="error">${errsex}</span>
    </p>

    <p>
        <select name="city" class="form-control">
            <option value="null">Please, choose your city</option>
            <c:forEach var="k" items="${citys}">
                <option value=${k}      <c:if test="${k eq city}">selected</c:if>        >${k}</option>
            </c:forEach>
        </select>
        <span style="color:red">${errcity}</span>
    </p>

    <p><input name="age" type="text" value="${age}" maxlength="3" class="form-control input-field" placeholder="Age">
        <span style="color:red" class="error">${errage}</span>
    </p>

    <input name="btn" type="submit" onclick="validate(this.form)" value="Create Account">
</form>

<script>
    function showError(container, errorMessage) {
        container.className = '';
        var msgElem = document.createElement('label');
        msgElem.className = "error";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error") {
            container.removeChild(container.lastChild);
        }
    }

    function validate(form) {
        var elems = form.elements;
        var regName = new RegExp("^[A-Za-zА-Яа-я]{2,20}");
        var regPassword = new RegExp("^[A-Za-z0-9]{6,60}");
        var regEmail = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
                "(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
                "([a-z]+)$");
        var regAge = new RegExp("^d+");
        var f = new Boolean(true);
        resetError(elems.email.parentNode);
        if (!regEmail.test(elems.email.value)) {
            showError(elems.email.parentNode, ' Некорректный адрес почты');
            f = false;
        }
        resetError(elems.fname.parentNode);
        if (!regName.test(elems.fname.value)) {
            showError(elems.fname.parentNode, ' Некорректное имя');
            f = false;
        }
        resetError(elems.sname.parentNode);
        if (!regName.test(elems.sname.value)) {
            showError(elems.sname.parentNode, ' Некорректная фамилия');
            f = false;
        }
        resetError(elems.password.parentNode);
        if (!regPassword.test(elems.password.value)) {
            showError(elems.password.parentNode, ' Некорректный пароль.');
            f = false;
        } else if (elems.password.value != elems.repassword.value) {
            showError(elems.password.parentNode, ' Пароли не совпадают.');
            f = false;
        }
        resetError(elems.city.parentNode);
        if (!elems.city.value || elems.city.value === "null") {
            showError(elems.city.parentNode, ' Укажите ваш город');
            f = false;
        }
        if (!elems.sex.value) {
            f = false;
        }
        resetError(elems.age.parentNode);
        if(elems.age.value != null || !elems.age.value || elems.age.value != regAge){
            showError(elems.age.parentNode, ' Указана не правильная форма для возраста');
        }
        if (f) {
            form.submit();
        }
    }
</script>
</body>
</html>