/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.empleados;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraEmpleado;
import logica.controladoras.ControladoraUsuario;
import logica.entidades.personas.Usuario;
import logica.util.DatosPersona;
import logica.util.Paginas;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvUsuariosMod", urlPatterns = {"/SvUsuariosMod"})
public class SvUsuariosMod extends HttpServlet {

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

		String nombreUsuario = request.getParameter(DatosPersona.NOMBRE_USUARIO);
		String password = request.getParameter(DatosPersona.PASSWORD);
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		ControladoraUsuario cu = new ControladoraUsuario();
		Usuario usuario = cu.findUsuarioById(idUsuario);
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setPassword(password);
		
		cu.modificarUsuario(usuario);
		ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();
		//enviar datos
		request.getSession().setAttribute("listaEmpleados", controladoraEmpleado.findAllEmpleado());

		//Redirect		
		response.sendRedirect(Paginas.LISTA_EMPLEADOS);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Params
		int id = Integer.parseInt(request.getParameter("idUsuario"));
		//acciones
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		Usuario usuario = controladoraUsuario.findUsuarioById(id);
		//enviar Atributos
		HttpSession mySession = request.getSession();
		mySession.setAttribute(DatosPersona.PARAM_OBJ_USUARIO, usuario);

		//redirect	
		response.sendRedirect(Paginas.MOD_USUARIOS);

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
