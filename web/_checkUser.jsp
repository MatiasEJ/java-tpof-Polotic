<%@page import="logica.util.DatosPersona"%>
<%@page import="logica.util.Paginas"%>
<%
	String nombreUsuario = (String) request.getSession().getAttribute(DatosPersona.NOMBRE_USUARIO);
	if (nombreUsuario == null ) {
		response.sendRedirect(Paginas.LOGIN);
	}
%>