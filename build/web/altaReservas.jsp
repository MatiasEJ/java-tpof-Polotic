<%@page import="logica.controladoras.ControladoraCliente"%>
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
<!DOCTYPE html>
<html>

	<% String titulo = DatosReservas.TITULO;%>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="<%= titulo%>" />
	</jsp:include>
    <body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>

		<%			List<Habitacion> listaHabitaciones = (List) request.getSession().getAttribute("listaHabitacion");
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
								<form action="SvReservasCrear" method="POST">
									<div class="form-group">
										<div class="input-group mb-3">
											<label class="input-group-text">Fecha Check-in</label> 
											<input  class="form-control" type="Date" name="<%= DatosReservas.FECHA_IN%>" required>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Fecha Check-out</label> 
											<input  class="form-control" type="Date" name="<%= DatosReservas.FECHA_OUT%>" required>
											<input hidden value="<%= request.getSession().getAttribute("cantDiasReserva")%>" name="diasReserva" >

										</div>
										<div class="input-group mb-3">

											<label class="input-group-text">Habitaciones disponibles</label> 
											<select class="form-control" name="idHabitacion"  required>
												<option >-</option>
												<%
													ControladoraHabitacion controladoraHab = new ControladoraHabitacion();
													for (Habitacion hab : controladoraHab.findAllHabitacion() ) {
														if (!hab.isIsReservada()) {
												%>
												<option value="<%= hab.getId()%>"><%= hab.getTematica().toString() + " " + hab.getTipoHabitacion()%></option>
												<% }%>
												<% }%>
											</select>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Cantidad de huespedes</label> 
											<input  class="form-control" type="text" name="<%= DatosReservas.CANT_HUESPEDES%>" required pattern="[1-8]" >
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">DNI</label>
											<input  class="form-control" type="text" name="dniCliente" >
											<span class="input-group-text">
												Sin puntos. Ej: 20200201	
											</span>
										</div>

										<div class="input-group mb-3">
											<label class="input-group-text">Cliente</label> 
											<select class="form-control" name="idCliente"  required>
												<option hidden selected value="0" >-</option>
												<%
													ControladoraCliente cc = new ControladoraCliente();
													for (Cliente cliente : cc.findAllClientes() ) {
												%>
												<option value="<%= cliente.getId()%>"><%= cliente.getNombre()+" "+cliente.getApellido()+" "+cliente.getDni() %></option>
												<% }%>
											</select>
										</div>


										<div class="form-group">
											<input hidden value="<%=empleado.getId()%> "  name="idEmpleado" >
											<div class="py-4">
												<input class="form-control" type="submit" value="Confirmar Reserva">
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
