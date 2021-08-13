/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidades.personas.Empleado;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author keta
 */
public class EmpleadoPersistencia {

	EmpleadoJpaController jpaController = new EmpleadoJpaController();

	public void crearEmpleado(Empleado empleado) {
		jpaController.create(empleado);
	}
	
	public List<Empleado> findAllEmpleado(){
		return jpaController.findEmpleadoEntities();
	}

	public Empleado findEmpleadoById(int id){
		return jpaController.findEmpleado(id);
	}

	public void borrarEmpleadoById(int id) {
		try {
			jpaController.destroy(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(EmpleadoPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void modificarEmpleado(Empleado empleado) {
		try {
			jpaController.edit(empleado);
		} catch (Exception ex) {
			Logger.getLogger(EmpleadoPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
}
