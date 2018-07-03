<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<c:forEach var="restaurante" items="${listaRestaurantes}">
	${restaurante.id} ${restaurante.nombre} ${restaurante.direccion} ${restaurante.descripcion} <br />
			</c:forEach>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>