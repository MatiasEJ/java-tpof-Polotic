<%@page import="logica.controladoras.ControladoraCliente"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.modelos.personas.Empleado"%>
<%@page import="logica.controladoras.ControladoraEmpleado"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="java.util.Date"%>
<%@page import="logica.modelos.reserva.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Consultas" />
	</jsp:include>
	<body>

		<jsp:include page="_nav.jsp" />
		<jsp:include page="_checkUser.jsp"/>

		<%
			ControladoraEmpleado ce = new ControladoraEmpleado();
			ControladoraCliente cc = new ControladoraCliente();
		%>	
		<h1>Consultas</h1>
		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 mx-auto">

						<div class="card">
							<div class="card-header">
								<h2>Consulta Fecha Reserva</h2>
							</div>
							<div class="card-body">
								<form action="SvConsultas" method="POST">
									<div class="form-group">
										<label class="form-label">fechaReserva</label> 
										<input  class="form-control" type="Date" name="fechaReserva" value="<%= Utilidades.dateAString(new Date())%>">
										<div class="py-4">
											<input type="hidden" value="a" name="consulta">
											<button class="form-control" type="submit" >submit</button>
										</div>
								</form>

							</div>
						</div>

						<div class="card">
							<div class="card-header">
								<h2>Consulta Empleados</h2>
							</div>
							<div class="card-body">
								<form action="SvConsultas" method="POST">
									<div class="form-group">
										<label class="form-label">Empleado</label> 
										<select class="form-control" name="idEmpleado" >
											<option value="0">-</option>
											<%
												for (Empleado emp : ce.findAllEmpleado()) {%>
											<option value="<%= emp.getId()%>"><%= emp.getNombre() + " " + emp.getApellido()%></option>	
											<% }%>
										</select>
										<div class="py-4">
											<input type="hidden" value="b" name="consulta">
											<button class="form-control" type="submit" >submit</button>
										</div>
									</div>
								</form>

							</div>
						</div>

						<div class="card">
							<div class="card-header">
								<h2>Consulta reservas por huesped</h2>
							</div>
							<div class="card-body">
								<form action="SvConsultas" method="POST">
									<div class="form-group">

										<label class="form-label">Cliente</label> 
										<select class="form-control" name="idCliente" >
											<option value="0">-</option>
											<%
												for (Cliente clie : cc.findAllClientes()) {%>
											<option value="<%= clie.getId()%>"><%= clie.getNombre() + " " + clie.getApellido()%></option>	
											<% }%>
										</select>

										<label class="form-label">fechaReserva in</label> 
										<input  class="form-control" type="Date" name="fechaConsultaIn" value="<%= Utilidades.dateAString(new Date())%>">

										<label class="form-label">fechaReserva Out</label> 
										<input  class="form-control" type="Date" name="fechaConsultaOut" value="<%= Utilidades.dateAString(new Date())%>">

										<div class="py-4">
											<input type="hidden" value="c" name="consulta">
											<button class="form-control" type="submit" >submit</button>
										</div>
									</div>
								</form>

							</div>
						</div>

					</div>
				</div>
			</div>
		</section>

	</body>
</html>
