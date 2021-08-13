<%-- 
    Document   : controlClientes
    Created on : 24 jul 2021, 19:18:33
    Author     : keta
--%>

<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Control Clientes" />
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
								<h1><i class="bi-file-person"></i> Control Clientes</h1> 
							</div>
							<div class="card-body">
								<form action="svClientes" method="POST" >
									<div class="input-group mb-3">

										<label class="input-group-text">Nombre</label> 
										<input  class="form-control" type="text" name="nombre" required>
									</div>
									<div class="input-group mb-3">

										<label class="input-group-text">Apellido</label> 
										<input  class="form-control" type="text" name="apellido" required>
									</div>
									<div class="input-group mb-3">

										<label class="input-group-text">Dni</label> 
										<input  class="form-control" type="text" name="dni" required>
										<span id="passwordHelpInline" class="input-group-text">
											Sin puntos. Ej: 20200201	
										</span>
									</div>
									<div class="input-group mb-3">
										<label class="input-group-text">Dirección</label> 
										<input  class="form-control" type="text" name="direccion" required>

									</div>
									<div class="input-group mb-3">
										<label class="input-group-text">Profesión</label> 
										<input  class="form-control" type="text" name="profesion" required>

									</div>
									<div class="input-group mb-3">
										<label class="input-group-text">Fecha de Nacimiento</label> 
										<input  class="form-control" type="Date" name="fechaNacimiento" required>

									</div>
									<div class="input-group mb-3">
										<input class="form-control" type="submit" value="Guardar">

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
