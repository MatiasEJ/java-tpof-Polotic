<%-- 
    Document   : controlClientes
    Created on : 24 jul 2021, 19:18:33
    Author     : keta
--%>

<%@page import="logica.modelos.personas.Cliente"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Control Clientes" />
	</jsp:include>
    <body>
		<%@include file="_nav.jsp" %>
		<%@include file="_checkUser.jsp"%>
		<%							
			Cliente cliente = (Cliente) mySession.getAttribute("objCliente");
		%>

		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="card">
							<div class="card-header">
								<h1><i class="bi-file-person"></i> Control Clientes</h1> 
							</div>
							<div class="card-body">
								<form action="SvClientesMod" method="get" >
									<div class="input-group mb-3">
										<label class="input-group-text">Nombre</label> 
										<input  class="form-control" type="text" name="nombre" value="<%= cliente.getNombre() %>" required>
									</div>
									<div class="input-group mb-3">

										<label class="input-group-text">Apellido</label> 
										<input  class="form-control" type="text" name="apellido" value="<%= cliente.getApellido()%>" required>
									</div>
									<div class="input-group mb-3">

										<label class="input-group-text">Dni</label> 
										<input  class="form-control" type="text" name="dni" value="<%= cliente.getDni()%>" required>
										<span id="passwordHelpInline" class="input-group-text">
											Sin puntos. Ej: 20200201	
										</span>
									</div>
									<div class="input-group mb-3">
										<label class="input-group-text">Dirección</label> 
										<input  class="form-control" type="text" name="direccion" value="<%= cliente.getDireccion()%>" required>

									</div>
									<div class="input-group mb-3">
										<label class="input-group-text">Profesión</label> 
										<input  class="form-control" type="text" name="profesion" value="<%= cliente.getProfesion()%>" required>

									</div>
									<div class="input-group mb-3">
										<label class="input-group-text">Fecha de Nacimiento</label> 
										<input  class="form-control" type="Date" name="fechaNacimiento" value="<%= cliente.getFechaNacToString()%>" required>

									</div>
									<div class="input-group mb-3">
										<input type="hidden" value="<%= cliente.getId()%>" name="idCliente"/>
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
