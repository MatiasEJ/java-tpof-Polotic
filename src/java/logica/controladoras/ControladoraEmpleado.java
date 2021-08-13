/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladoras;

import java.util.Date;
import java.util.List;
import logica.entidades.Cargo;
import logica.entidades.Empleado;
import logica.entidades.Usuario;
import persistencia.EmpleadoPersistencia;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
public class ControladoraEmpleado {

	EmpleadoPersistencia empleadoPersistencia = new EmpleadoPersistencia();

	public List<Empleado> findAllEmpleado() {
		return empleadoPersistencia.findAllEmpleado();
	}

	public void crearEmpleado(String nombre, String apellido, String dni, String direccion, Cargo cargo, Date fechaNacimiento) {
		Empleado empleado = new Empleado();
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDni(dni);
		empleado.setDireccion(direccion);
		empleado.setCargo(cargo);
		empleado.setFechaNacimiento(fechaNacimiento);

	}

	public void crearEmpleadoPrueba(){
		Empleado empleado = new Empleado();
		empleado.setNombre("Juan");
		empleado.setApellido("Juanes");
		empleado.setCargo(Cargo.ADMIN);
		empleado.setDireccion("Calle falsa 123");
		empleado.setDni("123123123");
		empleado.setFechaNacimiento(new Date());
		empleado.setUsuario(new Usuario("admin","admin"));

		empleadoPersistencia.crearEmpleado(empleado);
	}

}
