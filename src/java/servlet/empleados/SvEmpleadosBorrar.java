package servlet.empleados;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.controladoras.ControladoraEmpleado;
import logica.util.Paginas;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvEmpleadosBorrar", urlPatterns = {"/SvEmpleadosBorrar"})
public class SvEmpleadosBorrar extends HttpServlet {
	ControladoraEmpleado controladora = new ControladoraEmpleado();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//params
		int idEmpleado =Integer.parseInt(request.getParameter("idEmpleado"));
		//acciones
		controladora.borrarEmpleadoById(idEmpleado);
		//enviar atributos
		request.getSession().setAttribute("listaEmpleados", controladora.findAllEmpleado());

		response.sendRedirect(Paginas.LISTA_EMPLEADOS);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
