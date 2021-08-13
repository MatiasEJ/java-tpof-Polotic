/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.habitaciones;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.controladoras.ControladoraHabitacion;
import logica.util.DatosHabitacion;
import logica.util.Paginas;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvHabitacionesBaja", urlPatterns = {"/SvHabitacionesBaja"})
public class SvHabitacionesBaja extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//params
		int idHabitacion =Integer.parseInt(request.getParameter("idHabitacion"));
		//acciones
		ControladoraHabitacion controladora = new ControladoraHabitacion();
		controladora.borrarHabitacionById(idHabitacion);
		//enviar atributos
		request.getSession().setAttribute(DatosHabitacion.LISTA_HAB, controladora.findAllHabitacion());

		response.sendRedirect(Paginas.LISTA_HABITACIONES);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
