<%@page import="logica.util.TematicaHabitacion"%>
<%@page import="logica.util.DatosHabitacion"%>
<%@page import="logica.util.TipoHabitacion"%>
<%@page import="logica.util.DatosReservas"%>
<!DOCTYPE html>
<html>
	<% String titulo = DatosHabitacion.TITULO;%>
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
								<form action="SvHabitacionesCrear" method="POST">
									<div class="form-group">
										<div class="input-group mb-3">
											<label class="input-group-text">Tematica Habitación</label> 
											<select class="form-control" name="<%= DatosHabitacion.TEMATICA_HAB%>" >
												<option>-</option>
												<%
											for (TematicaHabitacion estilo : TematicaHabitacion.values()) {%>
												<option><%= estilo.toString()%></option>	
												<% }%>
											</select>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Tamaño Habitación</label> 
											<select class="form-control" name="<%= DatosHabitacion.TIPO_HAB%>" >
												<option>-</option>
												<% for (TipoHabitacion tipo : TipoHabitacion.values()) {%>
												<option><%= tipo.toString()%></option>	
												<% }%>
											</select>

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Descripción</label> 
											<input  class="form-control" type="text" name="<%= DatosHabitacion.DESCRIPCION%>">

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Piso</label> 
											<input  class="form-control" type="text" name="<%= DatosHabitacion.PISO%>">

										</div>
										<div class="input-group mb-3">
											<label class="input-group-text">Precio Noche</label> 
											<input  class="form-control" type="text" name="<%= DatosHabitacion.PRECIO_NOCHE%>" >

										</div>
										<div class="input-group mb-3">
											<input class="form-control" type="submit" value="Crear Habitación">
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
