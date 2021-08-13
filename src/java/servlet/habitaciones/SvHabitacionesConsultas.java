/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.habitaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.util.Paginas;
import logica.controladoras.ControladoraHabitacion;
import logica.modelos.habitaciones.Habitacion;

/**
 *
 * @author keta
 */
@WebServlet(name = "SvHabitacionesConsultas", urlPatterns = {"/SvHabitacionesConsultas"})
public class SvHabitacionesConsultas extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControladoraHabitacion controladora = new ControladoraHabitacion();
		List<Habitacion> listaHabitaciones = controladora.findAllHabitacion();
		if (listaHabitaciones != null) {
			HttpSession mySession = request.getSession();
			mySession.setAttribute("listaHabitaciones", listaHabitaciones);
			response.sendRedirect(Paginas.LISTA_HABITACIONES);
		}else{
		response.sendRedirect(Paginas.HOME);
		}
		


	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
