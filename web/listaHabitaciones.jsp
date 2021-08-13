<%@page import="logica.util.DatosHabitacion"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="logica.modelos.habitaciones.Habitacion"%>
<%@page import="logica.controladoras.ControladoraHabitacion"%>
<%@page import="java.util.List"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.controladoras.ControladoraCliente"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Lista de Habitaciones" />
	</jsp:include>
	<body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>
		<%		List<Habitacion> listaHabitaciones = (List<Habitacion>) mySession.getAttribute("listaHabitaciones");
			if (!listaHabitaciones.isEmpty()) {
		%>
		<jsp:include page="_banner.jsp" >
			<jsp:param name="titulo" value="Habitaciones" />
		</jsp:include>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Tematica</th>
					<th scope="col">Tipo habitacion</th>
					<th scope="col">Descripción</th>
					<th scope="col">Reserva</th>
					<th scope="col">Check-in</th>
					<th scope="col">Check-out</th>
					<th scope="col">Reserva</th>
					<th scope="col">Precio Noche</th>
					<th scope="col">Piso</th>
					<th scope="col">Cant. Maxima Huespedes</th>
					<th scope="col">Editar</th>
					<th scope="col">Borrar</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Habitacion habitacion : listaHabitaciones) {
				%>
				<tr>
					<td> <i class="bi-emoji-neutral"></i></td>
						<%
							int id = habitacion.getId();
						%>
					<td><p> <%= habitacion.getTematica()%> </p></td>
					<td><p> <%= habitacion.getTipoHabitacion()%> </p></td>
					<td><p> <%= habitacion.getDescripcion()%> </p></td>
					<td><p> <%= habitacion.isIsReservada()%> </p></td>
					<td><p> <%= habitacion.getFechaCheckinString()%> </p></td>
					<td><p> <%= habitacion.getFechaCheckOutString()%> </p></td>
					<td><p> <%= habitacion.getFechaReservaString()%> </p></td>
					<td><p> <%= habitacion.getPrecioNoche()%> </p></td>
					<td><p> <%= habitacion.getPiso()%> </p></td>
					<td><p> <%= habitacion.getCantMaxHuespedes()%> </p></td>
					<td>
						<form action="SvHabitacionesMod" method="POST">
							<input type="hidden" value="<%= id%>" name="idHabitacion"/>
							<button type="submit" value="Editar" class="btn btn-warning btn-sm">Editar</button>
						</form>

					</td>
					<td>
						<form action="SvHabitacionesBaja" method="POST">
							<input type="hidden" value="<%= id%>" name="idHabitacion"/>
							<button type="submit" value="Editar" class="btn btn-danger btn-sm">Borrar</button>
						</form>

					</td>
				</tr>

				<% }%>
			</tbody>
		</table>

		<%} else {%>
		<jsp:include page="<%= Paginas.ERROR%>" >
			<jsp:param name="error" value="Lista Habitaciones vacia" />
			<jsp:param name="volver" value="<%= Paginas.ALTA_HABITACIONES%>" />
		</jsp:include>
		<%}%>
		<%@include file="_footer.jspf" %>
	</body>
</html>
