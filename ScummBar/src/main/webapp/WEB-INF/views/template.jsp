<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ScummBar reservas</title>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	height: 100%;
	overflow: hidden;
}

.page {
	minheight: 100%;
	position: relative;
}

.header {
	padding: 10px;
	width: 100%;
	textalign: center;
	fontsize: xx-large;
}

.content {
	padding: 10px;
	paddingbottom: 20px; /* Height of the footer element */
	overflow: hidden;
}

.menu {
	padding: 50px 10px 0px 10px;
	width: 200px;
	float: left;
}

.body {
	margin: 50px 10px 0px 250px;
}

.footer {
	clear: both;
	position: absolute;
	bottom: -200;
	left: 600;
	textalign: center;
	width: 100%;
	height: 20px;
	fontsize: x-small;
}
</style>
</head>
<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>