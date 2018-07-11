<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">

			<table width="50%" border="1" align="left">
				<tr bgcolor=#F5C9FB>
					<th>Id
					</td>
					<th>Nombre restaurante
					</td>
					<th>Direccion
					</td>
					<th>Descripcion
					</td>
				</tr>

				<c:forEach var="restaurante" items="${listaRestaurantes}">
					<tr>

						<td>${restaurante.id}</td>
						<td>${restaurante.nombre}</td>
						<td>${restaurante.direccion}</td>
						<td>${restaurante.descripcion}</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>