/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladoras;

import java.util.Date;
import java.util.List;
import logica.modelos.personas.Empleado;
import logica.modelos.personas.Usuario;
import persistencia.EmpleadoPersistencia;
import persistencia.UsuarioPersistencia;

/**
 *
 * @author keta
 */
public class ControladoraEmpleado {

	UsuarioPersistencia persistenciaUsuario = new UsuarioPersistencia();
	EmpleadoPersistencia persistenciaEmpleado = new EmpleadoPersistencia();

	public void crearEmpleado(String nombre,
			String apellido,
			String dni,
			String direccion,
			String cargo,
			Date fechaNac,
			Usuario usuario
	) {
		Empleado empleado = new Empleado();
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDireccion(direccion);
		empleado.setDni(dni);
		empleado.setCargo(cargo);
		empleado.setFechaNacimiento(fechaNac);
		empleado.setUsuario(usuario);

		persistenciaEmpleado.crearEmpleado(empleado);
	}

	public Empleado findEmpleadoById(int id) {
		return persistenciaEmpleado.findEmpleadoById(id);
	}

	public void modicarEmpleado(Empleado empleado) {
		persistenciaEmpleado.modificarEmpleado(empleado);
	}

	public List<Empleado> findAllEmpleado() {
		return persistenciaEmpleado.findAllEmpleado();
	}

	public boolean checkEmpleadoCredentials(String nombreUsuario, String password) {
		List<Usuario> listaUsuarios = persistenciaUsuario.findAllUsuario();
		if (listaUsuarios != null) {
			for (Usuario usuario : listaUsuarios) {
				if (usuario.getNombreUsuario().equals(nombreUsuario)
						&& usuario.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	public Empleado findEmpleadoByUsername(String username) {
		for (Empleado emp: persistenciaEmpleado.findAllEmpleado()) {
			if( emp.getUsuario().getNombreUsuario().equals(username)){
			return emp;}
		}
		return null;
	}

	public void borrarEmpleadoById(int id) {
		persistenciaEmpleado.borrarEmpleadoById(id);
	}

	public void crearEmpleadoPrueba(){
		Usuario usuario = new Usuario("admin", "admin");
		persistenciaUsuario.crearUsuario(usuario);
		Empleado empleado = new Empleado(usuario, "Juan");
		empleado.setApellido("juan");
		empleado.setCargo("Admin");
		empleado.setDireccion("Calle falsa 123");
		empleado.setDni("123123123");
		empleado.setFechaNacimiento(new Date());
		persistenciaEmpleado.crearEmpleado(empleado);
	}

}
