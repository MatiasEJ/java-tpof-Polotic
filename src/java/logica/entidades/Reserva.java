/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;

import logica.entidades.habitacion.Habitacion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
class Reserva {
	long id;
	Date fechaCheckIn;
	Date fechaCheckOut;
	double montoEstadia;
	int cantHuespedes;
	List<Huesped> listaHuespedes;	
	Habitacion habitacion;
}
