<%@page import="logica.controladoras.ControladoraEmpleado"%>
<%@page import="java.util.Date"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="logica.modelos.reserva.Reserva"%>
<%@page import="logica.controladoras.ControladoraReservas"%>
<%@page import="logica.modelos.habitaciones.Habitacion"%>
<%@page import="logica.controladoras.ControladoraHabitacion"%>
<%@page import="java.util.List"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.controladoras.ControladoraCliente"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Lista Reservas" />
	</jsp:include>
	<body>
		<div class="container-fluid">

			<%@include file="_nav.jsp" %>
			<%@include file="_checkUser.jsp"%>
			<%		List<Cliente> listaClientes = (List<Cliente>) mySession.getAttribute("listaClientes");
				List<Reserva> listaReservas = (List) mySession.getAttribute("listaReservas");
				if (!listaClientes.isEmpty() && !listaReservas.isEmpty()) {
			%>
			<jsp:include page="_banner.jsp" >
				<jsp:param name="titulo" value="Reservas" />
			</jsp:include>
			<div class="row">
				<div class="col-sm-4">
					<div class="card">
						<div class="card-header">
							<h6>Reservas por fecha.</h6>
						</div>
						<div class="card-body">
							<form action="SvConsultas" method="POST">
								<div class="row">
									<div class="col">
										<div class="input-group input-group-sm mb-3">
											<label class="input-group-text">Fecha Reserva</label> 
											<input  class="form-control" type="Date" name="fechaReserva" >

											<input type="hidden" value="consultaReservaFecha" name="consulta">
											<button class="btn btn-outline-primary" type="submit" >Consultar</button>
										</div>
									</div>

								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-sm">
					<div class="card">
						<div class="card-header">
							<h6>Reservas por cliente.</h6>
						</div>
						<div class="card-body">
							<form action="SvConsultas" method="POST">
								<div class="row">
									<div class="col">
										<div class="input-group input-group-sm mb-3">
											<label class="input-group-text">Fecha Check-In</label> 
											<input  class="form-control" type="Date" name="fechaConsultaIn" value="<%= Utilidades.dateAString(new Date())%>">
										</div>

									</div>
									<div class="col">
										<div class="input-group input-group-sm mb-3">
											<label class="input-group-text">Fecha Check-Out</label> 
											<input  class="form-control" type="Date" name="fechaConsultaOut" value="<%= Utilidades.dateAString(new Date())%>">
										</div>
									</div>

									<div class="col">
										<div class="input-group input-group-sm mb-3">
											<label class="input-group-text">Cliente</label> 
											<select class="form-control" name="idCliente" >
												<option value="0">-</option>
												<%
													int idCliente=0 ;
													for (Cliente clie : listaClientes) {
														idCliente = clie.getId();
												%>
												<option value="<%= clie.getId()%>"><%= clie.getNombre() + " " + clie.getApellido()%></option>	
												<% }%>
											</select>
											<input type="hidden" value="<%= idCliente%>" name="idCliente">
											<input type="hidden" value="consultaReservaCliente" name="consulta">
											<button class="btn btn-outline-primary" type="submit" >Consultar</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


			<section>
				<h1>Lista Reservas</h1>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Fecha In</th>
							<th scope="col">Fecha Out</th>
							<th scope="col">Fecha Reserva</th>
							<th scope="col">Cantidad Dias</th>
							<th scope="col">Huespedes</th>
							<th scope="col">Estilo Habitacion</th>
							<th scope="col">Tamaño Habitacion</th>
							<th scope="col">Monto Total</th>
							<th scope="col">Empleado</th>
							<th scope="col">Cliente</th>
							<th scope="col">Editar</th>
							<th scope="col">Borrar</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Reserva reserva : listaReservas) {
								int id = reserva.getId();
						%>
						<tr>
							<td> <i class="bi-emoji-neutral"></i></td>
							<td><p> <%= Utilidades.dateAString(reserva.getFechaCheckin())%> </p></td>
							<td><p> <%= Utilidades.dateAString(reserva.getFechaCheckout())%> </p></td>
							<td><p> <%= Utilidades.dateAString(reserva.getFechaReserva())%> </p></td>
							<td><p> <%= reserva.getCantDiasReserva()%> </p></td>
							<td><p> <%= reserva.getCantHuespedes()%> </p></td>
							<td><p> <%= reserva.getHabitacion().getTematica()%> </p></td>
							<td><p> <%= reserva.getHabitacion().getTipoHabitacion()%> </p></td>
							<td><p> <%= reserva.getMontoEstadia()%> </p></td>
							<td><p> <%= reserva.getEmpleado().getNombre()%> </p></td>
							<td><p> <%= reserva.getCliente().getApellido() %> </p></td>


							<td>
								<form action="<%= DatosServlets.RESERVAS_MOD%>" method="POST">
									<input type="hidden" value="<%= id%>" name="idReserva"/>
									<button type="submit" value="Editar" class="btn btn-warning btn-sm">Editar</button>
								</form>

							</td>
							<td>
								<form action="<%= DatosServlets.RESERVAS_BORRAR%>" method="POST">
									<input type="hidden" value="<%= id%>" name="idReserva"/>
									<button type="submit" value="Borrar" class="btn btn-danger btn-sm">Borrar</button>
								</form>

							</td>
						</tr>
						<%}%>
					</tbody>
				</table>


			</section>

			<%} else {%>
			<jsp:include page="<%= Paginas.ERROR%>" >
				<jsp:param name="error" value="Lista Reservas Vacia" />
				<jsp:param name="volver" value="SvReservasConsultas " />
			</jsp:include>
			<%}%>
		<%@include file="_footer.jspf" %>
	</body>
</html>
