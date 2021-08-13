/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladoras;

import java.util.ArrayList;
import java.util.List;
import logica.modelos.habitaciones.Habitacion;
import persistencia.HabitacionPersistencia;

/**
 *
 * @author keta
 */
public class ControladoraHabitacion {

	HabitacionPersistencia persistencia = new HabitacionPersistencia();

	public void crearHabitacion(String descripcion, String piso, double precioNoche, 
String tematica, String tipoHabitacion) {
		Habitacion habitacion = new Habitacion();
		habitacion.setTematica(tematica);
		habitacion.setTipoHabitacion(tipoHabitacion);
		habitacion.setPiso(piso);
		habitacion.setCantMaxPorTipoHab(tipoHabitacion);
		habitacion.setPrecioNoche(precioNoche);
		habitacion.setDescripcion(descripcion);
		persistencia.crearHabitacion(habitacion);
	}


	public List<Habitacion> getAllHabitacionByTipo(String tipoHabitacion) {
		List<Habitacion> listaHabitaciones = new ArrayList<>();
		for (Habitacion habitacion : findAllHabitacion()) {
			if (habitacion.getTipoHabitacion().toString().equals(tipoHabitacion)) {
				listaHabitaciones.add(habitacion);
			}
		}
		return listaHabitaciones;
	}


	public List<Habitacion> findAllHabitacion() {
		return persistencia.findAllHabitacion();
	}

	public Habitacion findHabitacionById(int id){
		return persistencia.findHabitacionById(id);
	}
    
	public boolean cantidadCorrectaHuespedes(int cantHuespedes, int idHabitacion){
		Habitacion hab = persistencia.findHabitacionById(idHabitacion);
		boolean rta = cantHuespedes <= hab.getCantMaxHuespedes() && cantHuespedes >= 1;
		return rta;

	}

	public void modificarHabitacion(Habitacion habitacion) {
		persistencia.modificarHabitacion(habitacion);
	}

	public void borrarHabitacionById(int id) {
		persistencia.borrarHabitacionById(id);
	}

}
