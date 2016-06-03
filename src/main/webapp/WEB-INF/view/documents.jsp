<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
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
        /* make sidebar nav vertical */
        @media (min-width: 768px) {
            .sidebar-nav .navbar .navbar-collapse {
                padding: 0;
                max-height: none;
            }

            .sidebar-nav .navbar ul {
                float: none;
                display: block;
            }

            .sidebar-nav .navbar li {
                float: none;
                display: block;
            }

            .sidebar-nav .navbar li a {
                padding-top: 12px;
                padding-bottom: 12px;
            }
        }
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
    <div class="row">
        <div class="col-sm-3">
            <h4>Группы документов <a href="/groups/add"><i class="fa fa-plus" aria-hidden="true"></i></a></h4>
            <br/>
            <table class="table">
                <tbody>
                <c:forEach items="${groups}" var="g">
                    <c:if test="${!empty group && group.uuid == g.uuid}">
                        <c:set value="active" var="classActive"/>
                    </c:if>
                    <tr>

                        <td class="${classActive}">
                            <a style="margin-top: 0px;margin-right: 0px;" class="pull-right"
                               href="javascript:;" title="Delete"
                               onclick="document.getElementById('formRemove').submit();"><i
                                    class="fa fa-trash-o"></i></a>
                            <a style="margin-top: 0px;margin-right: 4px;" class="pull-right"
                               href="javascript:;" title="Delete"
                               onclick="document.getElementById('formRemove').submit();"><i
                                    class="fa fa-pencil-square-o"></i></a>
                            <a href="/documents/${g.uuid}">${g.name}</a></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--<c:if test="${!empty groups}">
                <div class="sidebar-nav">
                    <div class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target=".sidebar-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <span class="visible-xs navbar-brand">Sidebar menu</span>
                        </div>

                        <div class="navbar-collapse collapse sidebar-navbar-collapse">
                            <ul class="nav navbar-nav">
                                <c:forEach items="${groups}" var="g">
                                    <c:if test="${!empty group && group.uuid == g.uuid}">
                                        <c:set value="active" var="classActive"/>
                                    </c:if>
                                    <a style="margin-top: 11px;margin-right: 10px;" class="pull-right"
                                       href="javascript:;" title="Delete"
                                       onclick="document.getElementById('formRemove').submit();"><i
                                            class="fa fa-trash-o"></i></a>
                                    <a style="margin-top: 11px;margin-right: 10px;" class="pull-right"
                                       href="javascript:;" title="Delete"
                                       onclick="document.getElementById('formRemove').submit();"><i
                                            class="fa fa-pencil-square-o"></i></a>
                                    <li class="${classActive}"><a href="/documents/${g.uuid}">${g.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->

                    </div>
                </div>
            </c:if>
            <c:if test="${empty groups}">
                <div class="sidebar-nav">
                    <div class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target=".sidebar-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <span class="visible-xs navbar-brand">Sidebar menu</span>
                        </div>

                        <div class="navbar-collapse collapse sidebar-navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li>У вас нет групп</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:if>--%>
        </div>
        <div class="col-sm-9">

            <h4>Документы <a href="/documents/add"><i class="fa fa-plus" aria-hidden="true"></i></a></h4>
            <c:if test="${!empty group.uuid && !empty documents}">
                <br/>
                <table class="table">
                    <tbody>
                    <c:forEach items="${documents}" var="doc">
                        <tr>
                            <td>${doc.title}</td>
                            <td>${doc.createdTs}</td>
                            <td class="pull-right"><a href="${doc.selfHref}" target="_blank">file ${doc.fileName}</a>
                                <a href="<c:url value='/edit/${doc.uuid}' />"><em
                                        class="fa fa-pencil"></em></a>
                                <a href="#delete_${doc.uuid}" role="button" data-toggle="modal"><em
                                        class="fa fa-trash"></em></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
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
