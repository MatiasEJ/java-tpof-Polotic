/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidades.habitaciones.Habitacion;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author keta
 */
public class HabitacionPersistencia {
	HabitacionJpaController controller = new HabitacionJpaController();

	public void crearHabitacion(Habitacion habitacion){
		controller.create(habitacion);
	}

	public List<Habitacion> findAllHabitacion(){
		return controller.findHabitacionEntities();
	}
	
	public Habitacion findHabitacionById(int id){
		return controller.findHabitacion(id);
	}

	public void modificarHabitacion(Habitacion habitacion) {
		try {
			controller.edit(habitacion);
		} catch (Exception ex) {
			Logger.getLogger(HabitacionPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void borrarHabitacionById(int id) {
		try {
			controller.destroy(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(HabitacionPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
