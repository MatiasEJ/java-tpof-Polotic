<%@page import="logica.modelos.habitaciones.Habitacion"%>
<%@page import="logica.util.TematicaHabitacion"%>
<%@page import="logica.util.DatosHabitacion"%>
<%@page import="logica.util.TipoHabitacion"%>
<%@page import="logica.util.DatosReservas"%>
<!DOCTYPE html>
<html>
	<% String titulo = "Modificar Habitacion";%>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="<%=titulo%>" />
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
								<form action="SvHabitacionesMod" method="GET">
									<%
										Habitacion habitacion = (Habitacion) mySession.getAttribute(DatosHabitacion.PARAM_OBJ_HAB);
									%>
									<div class="form-group">
										<div class="input-group mb-3">
											<label class="input-group-text">Tematica Habitación</label> 
											<select class="form-control" name="<%= DatosHabitacion.TEMATICA_HAB%>" >
												<option  selected disabled hidden><%= habitacion.getTematica() %></option>
												<%
											for (TematicaHabitacion estilo : TematicaHabitacion.values()) {%>
												<option><%= estilo.toString()%></option>	
												<% }%>
											</select>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Tamaño Habitación</label> 
											<select class="form-control" name="<%= DatosHabitacion.TIPO_HAB%>" >
												<option  disabled hidden><%= habitacion.getTipoHabitacion() %></option>
												<% for (TipoHabitacion tipo : TipoHabitacion.values()) {%>
												<option><%= tipo.toString()%></option>	
												<% }%>
											</select>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Descripción</label> 
											<input  class="form-control" type="text" name="<%= DatosHabitacion.DESCRIPCION%>" value="<%= habitacion.getDescripcion() %>">

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Piso</label> 
											<input  class="form-control" type="text" name="<%= DatosHabitacion.PISO%>" value="<%= habitacion.getPiso() %>">

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Reservada</label> 
											<input  class="form-control" type="text" name="isReservada" value="<%= habitacion.isIsReservada() %>">

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Precio Noche</label> 
											<input  class="form-control" type="number" name="<%= DatosHabitacion.PRECIO_NOCHE%>" value="<%= habitacion.getPrecioNoche()%>">

										</div>
										<div class="input-group mb-3">

											<input hidden value="<%= habitacion.getId() %>" name="idHabitacion">
											<input class="form-control" type="submit" value="Modificar Habitación">
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
