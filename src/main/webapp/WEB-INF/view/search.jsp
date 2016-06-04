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
<div class="container">
    <c:url var="searchAction" value="/search"></c:url>
    <form:form id="searchForm" role="form" action="${searchAction}" commandName="searchDto" method="POST">
        <div class="form-group row">
            <label class="col-sm-2 form-control-label" for="textarea">Текст для поиска:</label>
            <form:textarea path="searchText" class="form-control" id="textarea" rows="3"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2">Тип поиска</label>
            <div class="col-sm-10">
                <div class="radio">
                    <label> <form:radiobutton path="searchType"  name="gridRadio1"
                                              id="gridRadios1" value="1" checked="true"/>Поиск по теме
                    </label>
                </div>
                <div class="radio disabled">
                    <label> <form:radiobutton path="searchType" name="gridRadio2"
                                              id="gridRadios2" value="2" disabled="true"/> Поиск по описанию
                    </label>
                </div>
                <div class="radio disabled">
                    <label> <form:radiobutton path="searchType" name="gridRadio3"
                                              id="gridRadios3" value="3" disabled="false"/> Поиск по тексту документов
                    </label>
                </div>
            </div>
        </div>
        <a href="javascript:;" title="Поиск"
           onclick="document.getElementById('searchForm').submit();"><i
                class="fa fa-search"></i></a>
    <a title="Назад" onclick="history.go(-1);" ><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
    </form:form>
    <br/>
</div>

<c:if test="${!empty documents}">
    <br/>
    <table class="table">
        <tbody>
        <c:forEach items="${documents}" var="doc">
            <td>${doc.title}</td>
            <td>${doc.createdTs}</td>
            <td>${doc.group.name}</td>
            <td>
                <div class="pull-right">
                    <a href="${doc.selfHref}" target="_blank">file ${doc.fileName}</a>
                </div>
            </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<!-- Footer -->
<div id="footer">
    <div class="container"></div>
</div>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>