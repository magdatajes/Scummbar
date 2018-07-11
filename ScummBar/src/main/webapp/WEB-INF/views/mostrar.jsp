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

		<table width="50%" border="1" align="left">
			<tr bgcolor=#FD02E3>
				<th style="color: #FDFCFC">Localizador
				</td>
				<th style="color: #FDFCFC">Nombre restaurante
				</td>
				<th style="color: #FDFCFC">Direccion
				</td>
				<th style="color: #FDFCFC">Personas
				</td>
				<th style="color: #FDFCFC">Turno
				</td>
			</tr>

			<c:forEach var="reserva" items="${listaReservas}">
				<tr>

					<td>${reserva.localizador}</td>
					<td>${reserva.restaurante.nombre}</td>
					<td>${reserva.restaurante.direccion }</td>
					<td>${reserva.personas}personas</td>
					<td>${reserva.turno.descripcion }</td>
				</tr>
			</c:forEach>

		</table>

	</tiles:putAttribute>
</tiles:insertDefinition>
