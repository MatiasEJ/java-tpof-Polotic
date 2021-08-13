/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidades.Empleado;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
public class EmpleadoPersistencia {
	EmpleadoJpaController empleadoJpaController = new EmpleadoJpaController();
	public List<Empleado> findAllEmpleado(){
		return empleadoJpaController.findEmpleadoEntities();
	}
	public void crearEmpleado(Empleado empleado){
		try {
			empleadoJpaController.create(empleado);
		} catch (Exception ex) {
			Logger.getLogger(EmpleadoPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
