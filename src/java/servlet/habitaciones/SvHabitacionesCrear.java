package servlet.habitaciones;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.util.DatosHabitacion;
import logica.util.Paginas;
import logica.controladoras.ControladoraHabitacion;

@WebServlet(name = "SvHabitacionesCrear", urlPatterns = {"/SvHabitacionesCrear"})
public class SvHabitacionesCrear extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Paginas.ALTA_HABITACIONES);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descripcion = request.getParameter(DatosHabitacion.DESCRIPCION);
		String piso = request.getParameter(DatosHabitacion.PISO);
		String precioNocheSt = request.getParameter(DatosHabitacion.PRECIO_NOCHE);
		int precioNoche = Integer.parseInt(precioNocheSt);
		String tipoHabitacion = request.getParameter(DatosHabitacion.TIPO_HAB);
		String tematica = request.getParameter(DatosHabitacion.TEMATICA_HAB);

		//Save
		ControladoraHabitacion controladoraHabitacion =new ControladoraHabitacion();
		controladoraHabitacion.crearHabitacion(descripcion, piso, precioNoche,
				tematica, tipoHabitacion);

		request.getSession().setAttribute("listaHabitaciones", controladoraHabitacion.findAllHabitacion());

		response.sendRedirect(Paginas.LISTA_HABITACIONES);
		

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
