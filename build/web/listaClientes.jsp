<%@page import="logica.util.DatosPersona"%>
<%@page import="logica.util.Paginas"%>
<%@page import="java.util.List"%>
<%@page import="logica.modelos.personas.Cliente"%>
<%@page import="logica.controladoras.ControladoraCliente"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Control Clientes" />
	</jsp:include>
	<body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>
		<jsp:include page="_banner.jsp" >
			<jsp:param name="titulo" value="Clientes" />
		</jsp:include>
		<%			List<Cliente> listaClientes = (List) mySession.getAttribute("listaClientes");
			if (listaClientes != null && !listaClientes.isEmpty()) {
		%>
		<table class="table">
			<thead>
				<tr>
					<th scope="col"># Estado</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellido</th>
					<th scope="col">Dni</th>
					<th scope="col">Direccion</th>
					<th scope="col">Profesion</th>
					<th scope="col">Fecha Nacimiento</th>
					<th scope="col">Editar</th>
					<th scope="col">Borrar</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Cliente cliente : listaClientes) {
				%>
				<tr>
					<td> <i class="bi-emoji-neutral"> </i></td>
					<td><p> <%= cliente.getNombre()%> </p></td>
					<td><p> <%= cliente.getApellido()%> </p></td>
					<td><p> <%= cliente.getDni()%> </p></td>
					<td><p> <%= cliente.getDireccion()%> </p></td>
					<td><p> <%= cliente.getProfesion()%> </p></td>
					<td><p> <%= cliente.getFechaNacToString()%> </p></td>
					<td>
						<form action="SvClientesMod" method="POST">
							<input type="hidden" value="<%= cliente.getId()%>" name="idCliente"/>
							<button type="submit" value="Editar" class="btn btn-warning btn-sm">Editar</button>
						</form>

					</td>
					<td>
						<form action="SvClientesBaja" method="POST">
							<input type="hidden" value="<%= cliente.getId()%>" name="idCliente"/>
							<button type="submit" value="Editar" class="btn btn-danger btn-sm">Borrar</button>
						</form>

					</td>

				</tr>

				<% }%>
			</tbody>
		</table>
		<%} else {%>

		<jsp:include page="<%= Paginas.ERROR%>" >
			<jsp:param name="error" value="Lista Clientes Vacia" />
			<jsp:param name="volver" value="<%= Paginas.ALTA_CLIENTES%>" />
		</jsp:include>

		<%}%>
		<%@include file="_footer.jspf" %>
	</body>
</html>
