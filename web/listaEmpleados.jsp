<%@page import="logica.modelos.personas.Empleado"%>
<%@page import="logica.controladoras.ControladoraEmpleado"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="java.util.List"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.controladoras.ControladoraCliente"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Lista empleados" />
	</jsp:include>
	<body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>
		<%			List<Empleado> listaEmpleados = (List) request.getSession().getAttribute("listaEmpleados");
			if (listaEmpleados != null || !listaEmpleados.isEmpty()) {

		%>
		<jsp:include page="_banner.jsp" >
			<jsp:param name="titulo" value="Empleados" />
		</jsp:include>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellido</th>
					<th scope="col">DNI</th>
					<th scope="col">Direccion</th>
					<th scope="col">Cargo</th>
					<th scope="col">Fecha Nacimiento</th>
					<th scope="col">Editar Usuario</th>
					<th scope="col">Editar</th>
					<th scope="col">Borrar</th>
				</tr>
			</thead>
			<tbody>
				<%					for (Empleado emp : listaEmpleados) {
				%>
				<tr>
					<td> <i class="bi-emoji-neutral"></i></td>
						<% int id = emp.getId();%>
					<td><p> <%= emp.getNombre()%> </p></td>
					<td><p> <%= emp.getApellido()%> </p></td>
					<td><p> <%= emp.getDni()%> </p></td>
					<td><p> <%= emp.getDireccion()%> </p></td>
					<td><p> <%= emp.getCargo()%> </p></td>
					<td><p> <%= emp.getFechaNacToString()%> </p></td>
					<td>
						<form action="SvUsuariosMod" method="POST">
							<input type="hidden" value="<%= emp.getUsuario().getId()%>" name="idUsuario"/>
							<button type="submit" value="Editar" class="btn btn-warning btn-sm">Editar Usuario</button>
						</form>

					</td>
					<td>
						<form action="SvEmpleadosMod" method="POST">
							<input type="hidden" value="<%= id%>" name="idEmpleado"/>
							<button type="submit" value="Editar" class="btn btn-warning btn-sm">Editar</button>
						</form>

					</td>
					<td>
						<form action="SvEmpleadosBorrar" method="POST">
							<input type="hidden" value="<%= id%>" name="idEmpleado"/>
							<button type="submit" value="Borrar" class="btn btn-danger btn-sm">Borrar</button>
						</form>

					</td>

				</tr>

				<%}%>
			</tbody>
		</table>
		<%} else {%>
		<jsp:include page="<%=Paginas.ERROR%>" >
			<jsp:param name="error" value="Lista Empleados Vacia." />
			<jsp:param name="volver" value="<%= Paginas.ALTA_EMPLEADOS%>" />
		</jsp:include>

		<%}%>
		<%@include file="_footer.jspf" %>
	</body>
</html>
