/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.empleados;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraEmpleado;
import logica.entidades.personas.Empleado;
import logica.util.Paginas;

/**
 *
 * @author keta
 */
@WebServlet(name = "SvEmpleadosConsultas", urlPatterns = {"/SvEmpleadosConsultas"})
public class SvEmpleadosConsultas extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControladoraEmpleado controladora = new ControladoraEmpleado();
		List<Empleado> listaEmpleados = controladora.findAllEmpleado();
		HttpSession mySession = request.getSession();
		if (listaEmpleados != null) {
			mySession.setAttribute("listaEmpleados", listaEmpleados);
			response.sendRedirect(Paginas.LISTA_EMPLEADOS);
		} 

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
