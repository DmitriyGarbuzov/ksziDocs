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
        .content_hide {
            width: 100%;
            /* Full Width */
            height: 5px;
            margin: -20px 0px 50px 0px;
            background: #FFFFFF;
            display: none;
        }

        .content_visible {
            width: 100%;
            /* Full Width */
            height: 5px;
            margin: -20px 0px 50px 0px;
            background: #FFFFFF;
            display: block;
        }

        .expand {
            width: 100%;
            height: 1px;
            margin: 2px 0;
            background: #504A4B;
            position: absolute;
                box-shadow: 0px 0px 10px 1px rgba(229, 228, 226,0.7);
            -moz-animation: fullexpand 13s ease-out;
            -webkit-animation: fullexpand 13s ease-out;
        }

        /* Full Width Animation Bar */
        @-moz-keyframes fullexpand {
            0% { width: 0px;
            }

            100% {
                width: 100%;
            }
        }

        @-webkit-keyframes fullexpand {
            0% {
                width: 0px;
            }

            100% {
                width: 100%;
            };
        }
    </style>
    <script>
        function processSave() {
            document.getElementById("save").disabled = true;
            document.getElementById("back").disabled = true;
            document.getElementById('content').className = 'content_visible';
            document.getElementById('mainForm').submit();
        }
        function main() {
            document.getElementById('content').className = 'content_hide';
        }
        document.addEventListener("DOMContentLoaded", main);

    </script>
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
            <sec:authorize access="isAnonymous()">
                <li><a href="/login">Login </a></li>
                <li><a href="/registration">Registration </a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><p></p></li>
                <li><a href="#>"><%= request.getUserPrincipal().getName() %>
                </a></li>
                <li style="margin-top: 15px;margin-right: 5px;"><form:form id="log_f"
                                                                           action="${pageContext.request.contextPath}/logout"
                                                                           method="POST">
                    <a href="javascript:;" title="logout"
                       onclick="document.getElementById('log_f').submit();">Logout</a>
                </form:form></li>
            </sec:authorize>
        </ul>
    </div>

</nav>
<div id="content">
    <span class="expand"></span>
</div>
<!-- Body -->
<c:url var="saveUpdateAction" value="/documents/add"></c:url>

<div class="container">
    <c:if test="${!empty document.uuid}">
        <h4>Редактирование документа</h4>
    </c:if>
    <c:if test="${empty document.uuid}">
        <h4>Создание документа</h4>
    </c:if>
    <form:form id="mainForm" role="form" action="${saveUpdateAction}" method="POST"
               commandName="document" enctype="multipart/form-data">

        <c:if test="${!empty addEditErrorMess}">
            <div class="alert alert-danger fade in">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Ошибка!</strong> ${addEditErrorMess}
            </div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-2 form-control-label" for="title">Заголовок:</label>
            <form:input path="title" type="text" class="form-control"
                        id="title" required="true"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 form-control-label" for="description">Ключевые слова:</label>
            <form:textarea path="description" class="form-control" id="description" rows="3"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 form-control-label" for="groupSelect">Папка:</label>
            <form:select path="group.uuid" id="groupSelect" class="form-control">
                <c:forEach var="g" items="${groups}">
                    <option value="${g.uuid}" ${group.uuid == g.uuid ? 'selected="selected"' : ''} >${g.name}</option>
                </c:forEach>
            </form:select>
        </div>
        <c:if test="${!empty document.fileName and !empty document.selfHref}">
            <div class="form-group row">
                <label class="col-sm-2 form-control-label" for="fileHref">Загруженный файл:</label>
                <label class="col-sm-2 form-control-label" id="fileHref"><a
                        href="${document.selfHref}" target="_blank">${document.fileName}</a></label>
            </div>
        </c:if>
        <fieldset class="form-group">
            <label for="file">Загрузить</label>
            <input class="btn btn-default" type="file" class="form-control-file" id="file" name="file"/>
        </fieldset>
        <br/>
        <c:if test="${!empty document.uuid}">
            <form:hidden path="uuid"/>
            <button id="save" onclick="processSave()" type="submit" class="btn btn-default">Обновить</button>
        </c:if>
        <c:if test="${empty document.uuid}">
            <button id="save" onclick="processSave()" type="submit" class="btn btn-default">Сохранить</button>
        </c:if>
        <button id="back" type="button" onclick="history.go(-1);" class="btn btn-grey">Назад</button>
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
