<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Реєстрація</title>
    <meta charset="utf-8">
    <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
            crossorigin="anonymous">
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
            rel='stylesheet' type='text/css'>
</head>
<body>
<h2>Реєстрація</h2>
<br>
<div class="container">
    <c:if test="${!empty errorMessage}">
        <div class="alert alert-danger">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Помилка!</strong> ${errorMessage}.
        </div>
    </c:if>
    <c:url var="registration" value="/registration"></c:url>
    <form:form role="form" action="${registration}" method="POST"
               commandName="user" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-xs-3" for="firstName">Ім'я (Логин):</label>
            <div class="col-xs-9">
                <form:input path="userName" type="text" class="form-control" id="firstName" placeholder="Логин" required="true"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-3" for="inputEmail">Email:</label>
            <div class="col-xs-9">
                <form:input path="email" type="email" class="form-control" id="inputEmail" placeholder="Email" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="inputPassword">Пароль:</label>
            <div class="col-xs-9">
                <form:input path="password" type="password" class="form-control" id="inputPassword" placeholder="Ваш пароль" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="confirmPassword">Підтвердіть пароль:</label>
            <div class="col-xs-9">
                <form:input path="repeatPassword" type="password" class="form-control" id="confirmPassword" placeholder="Введіть пароль повторно" required="true"/>
            </div>
        </div>
        <br />
        <div class="form-group">
            <div class="col-xs-offset-3 col-xs-9">
                <button type="submit" class="btn btn-primary">Зареєструвати</button>
                <button type="reset" class="btn btn-default" >Очистити форму</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
