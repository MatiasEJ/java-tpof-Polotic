/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.reservas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.controladoras.ControladoraHabitacion;
import logica.util.Paginas;
import logica.controladoras.ControladoraReservas;
import logica.modelos.habitaciones.Habitacion;
import logica.modelos.reserva.Reserva;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvReservasBorrar", urlPatterns = {"/SvReservasBorrar"})
public class SvReservasBorrar extends HttpServlet {

	ControladoraReservas controladoraReservas = new ControladoraReservas(); 
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

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
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
		//params
		int id =Integer.parseInt(request.getParameter("idReserva"));
		//acciones
		ControladoraHabitacion controladoraHabitacion = new ControladoraHabitacion();
		Reserva reserva = controladoraReservas.findReservaById(id);
		Habitacion habitacion = controladoraHabitacion.findHabitacionById(reserva.getHabitacion().getId());
		habitacion.setIsReservada(false);
		controladoraHabitacion.modificarHabitacion(habitacion);
		controladoraReservas.borrarReservaById(id);
		//enviar atributos
		request.getSession().setAttribute("listaReservas", controladoraReservas.findAllReserva());

		response.sendRedirect(Paginas.LISTA_RESERVAS);
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
