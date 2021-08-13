<%@page import="logica.util.DatosPersona"%>
<%@page import="logica.util.Cargo"%>
<%@page import="logica.util.DatosHabitacion"%>
<%@page import="logica.util.TematicaHabitacion"%>
<%@page import="logica.util.DatosServlets"%>
<!DOCTYPE html>
<html>
	<%String titulo= DatosPersona.ALTA_EMPLEADO_TITULO;%>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="<%= titulo%>" />
	</jsp:include>
    <body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>

		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="card">
							<div class="card-header">
								<h1><%= titulo%></h1> 
							</div>
							<div class="card-body">
								<form action="<%= DatosServlets.EMPLEADO_CREAR%>" method="POST">
									<div class="form-group">
										<div class="input-group mb-3">
											<label class="input-group-text">Nombre</label> 
											<input class="form-control" type="text" name="nombre" required>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Apellido</label> 
											<input  class="form-control" type="text" name="apellido" required>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">DNI</label> 
											<input  class="form-control" type="text" name="dni" required>
											<span class="input-group-text">
												Sin puntos. Ej: 20200201	
											</span>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Dirección</label> 
											<input  class="form-control" type="text" name="direccion" required>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Cargo</label> 
									<select class="form-control" name="cargo"  required>
										<option>-</option>
										<%
											for (Cargo cargo : Cargo.values()) {%>
										<option><%= cargo.toString()%></option>	
										<% }%>
									</select>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Nombre de Usuario</label> 
											<input  class="form-control" type="text" name="nombreUsuario" required>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Contraseña</label> 
											<input  class="form-control" type="text" name="password" required>
										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Fecha de Nacimiento</label> 
											<input  class="form-control" type="Date" name="fechaNacimiento" name="fechaNacimiento" required>
										</div>
									</div>
									<div class="py-4">
										<input class="form-control" type="submit" value="submit">
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
