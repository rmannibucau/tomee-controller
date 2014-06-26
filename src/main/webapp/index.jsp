<!DOCTYPE html>
<html ng-app="tomeeControllerApplication">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>TomEE Controller Application</title>

    <link href="${pageContext.request.contextPath}/lib/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/lib/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/lib/css/plugins/timeline/timeline.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/lib/css/sb-admin.css" rel="stylesheet">
</head>

<body>

<div id="wrapper">

    <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">TomEE Controller</a>
        </div>

        <div class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}#/home"><i class="fa fa-dashboard fa-fw"></i> Home</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}#/resources">
                            <i class="fa fa-dashboard fa-fw"></i> Resource Actions
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse out" style="height: auto;">
                            <li><a href="${pageContext.request.contextPath}#/resources">Resources</a></li>
                            <li><a href="${pageContext.request.contextPath}#/resources/new">Create</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="page-wrapper" ng-view>
    </div>
</div>

<script src="${pageContext.request.contextPath}/lib/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/sb-admin.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/angular-route.min.js"></script>

<script src="${pageContext.request.contextPath}/app/js/environment.js"></script>
<script src="${pageContext.request.contextPath}/app/js/router.js"></script>
<script src="${pageContext.request.contextPath}/app/js/controllers.js"></script>
<script src="${pageContext.request.contextPath}/app/js/app.js"></script>
</body>

</html>
