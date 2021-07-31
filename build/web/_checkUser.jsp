<%@page import="logica.Paginas"%>
<%@page import="logica.Datos"%>
<%
	String nombreUsuario = (String) request.getSession().getAttribute(Datos.NOMBRE_USUARIO);
	if (nombreUsuario == null) {
		response.sendRedirect(Paginas.LOGIN);
	}
%>