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
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraHabitacion;
import logica.modelos.habitaciones.Habitacion;
import logica.util.DatosHabitacion;
import logica.util.Paginas;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvHabitacionesMod", urlPatterns = {"/SvHabitacionesMod"})
public class SvHabitacionesMod extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descripcion = request.getParameter(DatosHabitacion.DESCRIPCION);
		String piso = request.getParameter(DatosHabitacion.PISO);
		double precioNoche = Double.parseDouble(request.getParameter(DatosHabitacion.PRECIO_NOCHE));
		String tipoHabitacion = request.getParameter(DatosHabitacion.TIPO_HAB);
		String tematica = request.getParameter(DatosHabitacion.TEMATICA_HAB);
		boolean isRes = Boolean.getBoolean(request.getParameter("isReservada"));

		ControladoraHabitacion controladoraHabitacion = new ControladoraHabitacion();	
		int idHabitacion = Integer.parseInt(request.getParameter(DatosHabitacion.PARAM_ID));
		Habitacion habitacion = controladoraHabitacion.findHabitacionById(idHabitacion);
		habitacion.setTematica(tematica);
		habitacion.setTipoHabitacion(tipoHabitacion);
		habitacion.setCantMaxPorTipoHab(tipoHabitacion);
		habitacion.setPrecioNoche(precioNoche);
		habitacion.setPiso(piso);
		habitacion.setDescripcion(descripcion);
		habitacion.setIsReservada(isRes);
		
		controladoraHabitacion.modificarHabitacion(habitacion);

		//enviar datos
		request.getSession().setAttribute(DatosHabitacion.PARAM_LISTAHAB, controladoraHabitacion.findAllHabitacion());

		//Redirect		
		response.sendRedirect(Paginas.LISTA_HABITACIONES);

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Params
		int id = Integer.parseInt(request.getParameter(DatosHabitacion.PARAM_ID));
		//acciones
		ControladoraHabitacion controladorHabitacion = new ControladoraHabitacion();
		Habitacion habitacion = controladorHabitacion.findHabitacionById(id);
		//enviar Atributos
		HttpSession mySession = request.getSession();
		mySession.setAttribute(DatosHabitacion.PARAM_OBJ_HAB, habitacion);

		//redirect	
		response.sendRedirect(Paginas.MOD_HABITACIONES);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
