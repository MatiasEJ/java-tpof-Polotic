/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidades.reserva.Reserva;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author keta
 */
public class ReservaPersistencia {
	ReservaJpaController controller = new ReservaJpaController();

	public void crearReserva(Reserva reserva){
		controller.create(reserva);
	}

	public List<Reserva> findAllReserva(){
		return controller.findReservaEntities();
	}

	public void borrarReservaById(int id) throws NonexistentEntityException {
		controller.destroy(id);
	}

	public Reserva findReservaById(int idReserva) {
		return controller.findReserva(idReserva);
	}

	public void modifcarReserva(Reserva reservaTmb) {
		try {
			controller.edit(reservaTmb);
		} catch (Exception ex) {
			Logger.getLogger(ReservaPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
