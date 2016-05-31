<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
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
    <!-- Collection of nav links and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/bachelors">Бакалаври</a></li>
            <li><a href="/specialists">Спеціалісти</a></li>
            <li><a href="/masters">Магістри</a></li>
            <li><a href="/search">Пошук</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Login</a></li>
        </ul>
    </div>
</nav>
<!-- Body -->
<div class="container">
    <c:if test="${!empty addEditSuccessMess}">
        <div class="alert alert-success fade in">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Успіх!</strong> ${addEditSuccessMess}.
        </div>
    </c:if>
    <c:if test="${!empty removeSuccessMess}">
        <div class="alert alert-success fade in">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Успіх!</strong> ${removeSuccessMess}.
        </div>
    </c:if>
    <c:if test="${!empty removeErrorMess}">
        <div class="alert alert-danger fade in">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Помилка!</strong> ${removeErrorMess}.
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-29 col-md-offset-1">
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title"></h3>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <a href="/add" class="btn btn-primary">Створити</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="input-group"> <span class="input-group-addon">Фільтр</span>

                        <input id="filter" type="text" class="form-control" placeholder="Ключове слово...">
                    </div>
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th>Тема</th>
                            <th>Студент</th>
                            <th>Факультет</th>
                            <th>Кафедра</th>
                            <th>Група</th>
                            <th>Керівник</th>
                            <th>Рік</th>
                            <th>Текст роботи</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${graduateWorkList}" var="graduateWork">
                            <tr>
                                <td align="center">
                                    <a href="<c:url value='/edit/${graduateWork.uuid}' />" class="btn btn-default"><em
                                            class="fa fa-pencil"></em></a>
                                    <a href="#myModal_${graduateWork.uuid}" role="button" class="btn btn-danger" data-toggle="modal"><em
                                            class="fa fa-trash"></em></a>
                                </td>
                                <td>${graduateWork.subject}</td>
                                <td>${graduateWork.student.surname}
                                        ${graduateWork.student.firstName}
                                        ${graduateWork.student.patronymic}</td>
                                <td>${graduateWork.cathedra.faculty.facultyName}</td>
                                <td>${graduateWork.cathedra.cathedraName}</td>
                                <td>${graduateWork.group.groupName}</td>
                                <td>${graduateWork.headWork.surname}
                                        ${graduateWork.headWork.firstName}
                                        ${graduateWork.headWork.patronymic}</td>
                                <td>${graduateWork.year}</td>
                                <td><a href="${graduateWork.selfHref}" target="_blank">${graduateWork.fileName}</a></td>
                            </tr>
                            <div id="myModal_${graduateWork.uuid}" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title">Підтвердження видилення</h4>
                                        </div>

                                        <div class="modal-body">
                                            <p>Ви впевнені що хочите видалити цю дипломну роботу? </p>
                                        </div>
                                        <div class="modal-footer">
                                            <form:form id="formRemove" action="/remove/${graduateWork.uuid}" method="post">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Закрити</button>
                                                <a href="javascript:;" title="Delete" onclick="document.getElementById('formRemove').submit();"><i class="fa fa-trash-o"></i>Видалити</a>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- <div class="panel-footer">
                    <div class="row">
                        <div class="col col-xs-4">Page 1 of 5</div>
                        <div class="col col-xs-8">
                            <ul class="pagination hidden-xs pull-right">
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                            </ul>
                            <ul class="pagination visible-xs pull-right">
                                <li><a href="#">«</a></li>
                                <li><a href="#">»</a></li>
                            </ul>
                        </div>
                    </div>
                </div> -->
            </div>
        </div>
    </div>
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
