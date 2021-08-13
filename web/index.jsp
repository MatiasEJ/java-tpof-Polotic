<%@page import="logica.modelos.personas.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="logica.controladoras.ControladoraEmpleado"%>
<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Login" />
	</jsp:include>
    <body  class="text-center">
		<%
			ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();
			List<Empleado> listaEmpleados = controladoraEmpleado.findAllEmpleado();
			if (listaEmpleados.isEmpty()) {
				controladoraEmpleado.crearEmpleadoPrueba();
			}
		%>

		<section class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
			<div class="container">
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="card">
							<div class="card-header">
								<h1>Bienvenido</h1>
								<form class="d-flex" action="SvLogin" method="POST">
									<input class="form-control mr-sm-2 mx-2" type="text" placeholder="Nombre Usuario" name="nombreUsuario" aria-label="nombreUsuario">
									<input class="form-control mr-sm-2 mx-2" type="text" placeholder="Password" name="password" aria-label="Password">
									<button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Login">Login</button>
								</form>
							</div>
							<div class="card-body">
							</div>
						</div> 
					</div>
				</div>
			</div>
		</section>
    </body>

</html>
