<%@page import="logica.util.DatosPersona"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="logica.util.DatosServlets"%>
<%@page import="logica.util.Utilidades"%>
<%@page import="logica.modelos.personas.Empleado"%>
<!DOCTYPE html>
<html>
	<%String title = "Modificar Empleado";%>	
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="<%= title%>" />
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
								<h1><%= title%></h1> 
							</div>
							<div class="card-body">
								<form action="SvEmpleadosMod" method="get">

									<%
										HttpSession mySession = request.getSession();
										Empleado empleado = (Empleado) mySession.getAttribute(DatosPersona.PARAM_OBJ_EMPLEADO);
									%>
									<div class="form-group">
										<label class="form-label">Nombre</label> 
										<input class="form-control" type="text" name="nombre" value="<%= empleado.getNombre()%>">
										<label class="form-label">Apellido</label> 
										<input  class="form-control" type="text" name="apellido" value="<%= empleado.getApellido()%>">
										<label class="form-label">DNI</label> 
										<input  class="form-control" type="text" name="dni" value="<%= empleado.getDni()%>">
										<label class="form-label">Direccion</label> 
										<input  class="form-control" type="text" name="direccion" value="<%= empleado.getDireccion()%>">
										<label class="form-label">Cargo</label> 
										<input  class="form-control" type="text" name="cargo" value="<%= empleado.getCargo()%>">
										<label class="form-label">Fecha de nacimiento</label> 
										<input  class="form-control" type="Date" name="fechaNacimiento" value="<%= empleado.getFechaNacToString()%>">
									</div>
									<div class="py-4">
										<input type="hidden" value="<%= empleado.getId()%>" name="idEmpleado"/>
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
