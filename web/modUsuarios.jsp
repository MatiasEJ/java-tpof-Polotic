<%@page import="logica.modelos.personas.Usuario"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="logica.modelos.personas.Empleado"%>
<!DOCTYPE html>
<html>

	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Control Clientes" />
	</jsp:include>
    <body>
		<jsp:include page="_checkUser.jsp"/>
		<jsp:include page="_nav.jsp" />

		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="card">
							<div class="card-header">
								<h1>Modificar Usuario</h1> 
							</div>
							<div class="card-body">
								<form action="SvUsuariosMod" method="get">

									<%
										HttpSession mySession = request.getSession();
										Usuario usuario = (Usuario) mySession.getAttribute("objUsuario");
									%>
									<div class="form-group">
										<label class="form-label">nombre Usuario</label> 
										<input class="form-control" type="text" name="nombreUsuario" value="<%= usuario.getNombreUsuario() %>">
										<label class="form-label">Password</label> 
										<input  class="form-control" type="text" name="password" value="<%= usuario.getPassword() %>">
									</div>
									<div class="py-4">
										<input type="hidden" value="<%= usuario.getId()%>" name="idUsuario"/>
										<input class="form-control" type="submit" value="Modificar">
									</div>
								</form>
							</div>
						</div> 
					</div>
				</div>
			</div>
		</section>
		<%@include file="_footer.jspf" %>
    </body>
	
</html>