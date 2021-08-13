<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="java.util.Date"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="java.util.Date"%>
<%@page import="logica.modelos.personas.Empleado"%>
<%@page import="logica.modelos.habitaciones.Habitacion"%>
<%@page import="logica.controladoras.ControladoraHabitacion"%>
<%@page import="logica.modelos.reserva.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="logica.util.TipoHabitacion"%>
<%@page import="logica.util.TematicaHabitacion"%>
<%@page import="logica.util.DatosReservas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<% String titulo = "Modificar Reserva"; %>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="<%= titulo%>" />
	</jsp:include>
    <body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>

		<%			List<Habitacion> listaHabitaciones = (List) mySession.getAttribute("listaHabitaciones");
			String fechain = (String) mySession.getAttribute(DatosReservas.FECHA_IN);
			String fechaout = (String) mySession.getAttribute(DatosReservas.FECHA_OUT);
			Habitacion habit = (Habitacion) mySession.getAttribute("objHabitacion");
			Reserva res = (Reserva) mySession.getAttribute("objReserva");
			int cantHuesp = (int) mySession.getAttribute("cantHuespedes");
			Cliente cliente = (Cliente) mySession.getAttribute("objCliente");
		%>
		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="card">
							<div class="card-header">
								<h1><%= titulo%></h1> 
							</div>
							<div class="card-body">
								<form action="SvReservasMod" method="GET">
									<div class="form-group">

										<div class="input-group mb-3">
											<label class="input-group-text">Fecha Check-in</label> 
											<input  class="form-control" type="Date" name="<%= DatosReservas.FECHA_IN%>" value="<%= fechain%>"  required>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Fecha Check-out</label> 
											<input  class="form-control" type="Date" name="<%= DatosReservas.FECHA_OUT%>" value="<%= fechaout%>"  required>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Habitaciones disponibles</label> 
															<input type="hidden"  value="<%= habit.getId() %>" name="oldHabitacion"/>
											<select class="form-control" name="idHabitacion"  required>
												<option  selected disabled hidden > <%= habit.getTipoHabitacion().toString() + " " + habit.getTematica().toString()%></option>
												<%
													for (Habitacion hab : listaHabitaciones) {
														if (!hab.isIsReservada()) {
												%>
												<option value="<%= hab.getId()%>"><%= hab.getTipoHabitacion().toString() + " " + hab.getTematica().toString()%></option>
												<% }%>
												<% }%>
											</select>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Cantidad de huespedes</label> 
											<input  class="form-control" type="text" name="<%= DatosReservas.CANT_HUESPEDES%>"  required pattern="[\d]" value="<%= cantHuesp %>" >
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">DNI</label>
											<input  class="form-control" type="text" name="dniCliente" value="<%= cliente.getDni() %>" >
											<label class="input-group-text"><%= cliente.getApellido()+" "+cliente.getNombre() %></label>

										</div>


										<div class="row">
											<div class="col-md-6 mx-auto">
												<div class="card text-center text-muted">
													<form action="SvReservasConfirmar" method="POST">
														<div class="py-4">
															<input type="hidden"  value="<%= empleado.getId() %>" name="idEmpleado"/>
															<input type="hidden"  value="<%= habit.getId() %>" name="idHabitacion"/>
															<input type="hidden"  value="<%= cliente.getId() %>" name="idCliente"/>
															<input type="hidden" value="<%= res.getId() %>" name="idReserva"/>
															<button type="submit" value="Editar" class="btn btn-warning btn-lg">Confirmar Reserva</button>
														</div>
													</form>
												</div>
											</div>
											<div class="col-md-6 mx-auto">
												<div class="card text-center text-muted">
														<div class="py-4">
															<a href="listaReservas.jsp" value="Borrar" class="btn btn-danger btn-lg">Cancelar Reserva</a>
														</div>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<%
			String error = (String) request.getSession().getAttribute("error");
			if (error != null) {
		%>
		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="card">
							<jsp:include page="<%= Paginas.ERROR%>" >
								<jsp:param name="error" value="<%= error%>" />
								<jsp:param name="volver" value="<%= Paginas.ALTA_RESERVAS%>" />
							</jsp:include>

						</div>
					</div>
				</div>
			</div>
		</section>

		<%}
			request.getSession().removeAttribute("error");
		%>
		<%@include file="_footer.jspf" %>
	</body>
</html>
