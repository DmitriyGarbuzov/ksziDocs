<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Документы</title>
    <meta charset="utf-8">
    <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
            crossorigin="anonymous">
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
            rel='stylesheet' type='text/css'>
    <style type="text/css">

    </style>
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
        <a href="/documents" class="navbar-brand">Документы</a>
    </div>
    <!-- Collection of nav links and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/search">Поиск</a></li>
        </ul>
        <%--refactoring--%>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Login</a></li>
        </ul>
    </div>
</nav>
<!-- Body -->
<c:url var="saveUpdateAction" value="/groups/add"></c:url>
<div class="container">
    <c:if test="${!empty group.uuid}">
        <h2>Редактирование группы</h2>
    </c:if>
    <c:if test="${empty group.uuid}">
        <h2>Создание группы</h2>
    </c:if>
    <form:form role="form" action="${saveUpdateAction}" method="POST"
               commandName="group" enctype="multipart/form-data">

        <c:if test="${!empty addEditErrorMess}">
            <div class="alert alert-danger fade in">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Ошибка!</strong> ${addEditErrorMess}
            </div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-2 form-control-label" for="name">Имя:</label>
            <form:input path="name" type="text" class="form-control"
                        id="name" required="true"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 form-control-label" for="description">Описание:</label>
            <form:textarea path="description" class="form-control" id="description" rows="3"/>
        </div>
        <br/>
        <br/>
        <br/>
        <c:if test="${!empty group.uuid}">
            <form:hidden path="uuid"/>
            <button type="submit" class="btn btn-success">Обновить</button>
        </c:if>
        <c:if test="${empty group.uuid}">
            <button type="submit" class="btn btn-success">Сохранить</button>
        </c:if>
        <button type="button" onclick="history.go(-1);" class="btn btn-danger">Назад</button>
    </form:form>
    <br/> <br/> <br/> <br/> <br/> <br/>
</div>
<!-- Footer -->
<div class="navbar-fixed-bottom row-fluid">
    <div class="navbar-inner">
        <div class="container"></div>
    </div>
</div>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

