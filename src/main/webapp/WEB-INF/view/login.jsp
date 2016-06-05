<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
         language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Дипломи</title>
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
<!-- Header -->
<nav role="navigation" class="navbar navbar-default">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" data-target="#navbarCollapse"
                data-toggle="collapse" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span> <span
                class="icon-bar"></span> <span class="icon-bar"></span> <span
                class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand">Дипломи</a>
    </div>
</nav>
<!-- Body -->
<div class="container">
    <h1 style="text-align: center">Войдите в свой аккаунт</h1>
    <br/>
    <br/>
    <c:if test="${param.error ne null}">
        <div class="alert alert-danger"><strong>Внимание</strong> Не правильный пароль либо username.</div>
    </c:if>
    <c:if test="${param.logout ne null}">
        <div class="alert alert-danger"><strong>Внимание</strong> Вы вышли из аккаунта.</div>
    </c:if>
    <form role="form" action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group row">
            <label for="username" class="col-sm-2 form-control-label">Username</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="username" id="username" required autofocus>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 form-control-label">Пароль</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" required/>
            </div>
        </div>
        <br/>
        <br/>
        <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-10 ">
                <button type="submit" id="login" name="login" class="btn btn-default">
                    Войти
                </button>
                <a href="/registration" class="btn btn-grey">Регистрация</a>
            </div>
        </div>
    </form>
</div>
<!-- Footer -->
<div id="footer">
    <div class="container"></div>
</div>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
