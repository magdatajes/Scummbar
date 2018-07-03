<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<head>
<title>Lista de reservas</title>
	</head>
		<tiles:putAttribute name="body">
			<div class="body">
				<c:forEach var="reserva" items="${listaReservas}">
				${reserva.localizador} ${reserva.restaurante.nombre} ${reserva.restaurante.direccion } 
				${turno.descripcion } ${reserva.personas} personas<br/>
				</c:forEach>
			</div>
		</tiles:putAttribute>
</tiles:insertDefinition>
