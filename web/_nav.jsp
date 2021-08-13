<%@page import="logica.modelos.personas.Empleado"%>
<%@page import="logica.modelos.personas.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="logica.controladoras.ControladoraUsuario"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<i class="bi-"></i>
			<a class="navbar-brand" href="home.jsp">Home</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Clientes
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<form action="svClientes" method="GET">
								<li><a class="dropdown-item" href="svClientes">Alta Cliente</a></li>
							</form>
							<form action="svClientesConsultas" method="GET">
								<li><a class="dropdown-item" href="svClientesConsultas">Lista Clientes</a></li>
							</form>
							<li><hr class="dropdown-divider"></li>
						</ul>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Empleados
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<form action="SvEmpleadosCrear" method="GET">
								<li><a class="dropdown-item" href="SvEmpleadosCrear">Crear Empeados</a></li>
							</form>
							<form action="SvEmpleadosConsultas" method="GET">
								<li><a class="dropdown-item" href="SvEmpleadosConsultas">Lista empleados</a></li>
							</form>
							<li><hr class="dropdown-divider"></li>
						</ul>
					</li>

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Habitaciones
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<form action="SvHabitacionesCrear" method="GET">
								<li><a class="dropdown-item" href="SvHabitacionesCrear">Crear Habitaciones</a></li>
							</form>
							<form action="SvHabitacionesConsultas" method="GET">
								<li><a class="dropdown-item" href="SvHabitacionesConsultas">Lista Habitaciones</a></li>
							</form>
							<li><hr class="dropdown-divider"></li>
						</ul>
					</li>

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Reservas
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<form action="SvReservasCrear" method="GET">
								<li><a class="dropdown-item" href="SvReservasCrear">Hacer Reserva</a></li>
							</form>
							<form action="SvReservasConsultas" method="GET">
								<li><a class="dropdown-item" href="SvReservasConsultas">Lista Reservas</a></li>
							</form>
							<li><hr class="dropdown-divider"></li>
						</ul>
					</li>
				</ul>
				<%
					HttpSession mySession = request.getSession();
					Empleado empleado = (Empleado) mySession.getAttribute("empleado");
					String nombre = (String) mySession.getAttribute("nombreUsuario");
					if (mySession != null && nombre != null) {

				%>
				<form class="d-flex" action="SvLogout" method="GET">
					<input class="form-control mr-sm-2 mx-2" type="text" value="Bienvenido: <%= empleado.getNombre()%> " aria-label="Password" disabled="true" draggable="false" >
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="logout">Logout</button>
				</form>
				<%} else {%>
				<form class="d-flex" action="SvLogin" method="POST">
					<input class="form-control mr-sm-2 mx-2" type="text" placeholder="Nombre Usuario" name="nombreUsuario" aria-label="nombreUsuario">
					<input class="form-control mr-sm-2 mx-2" type="text" placeholder="Password" name="password" aria-label="Password">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Login">Login</button>
				</form>

				<% }%>
			</div>
		</div>
	</nav>
</header>