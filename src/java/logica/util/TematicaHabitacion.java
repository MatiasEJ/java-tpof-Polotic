/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.util;

/**
 *
 * @author keta
 */
public enum TematicaHabitacion {
	CHINA,
	JAPON,
	AMAZONAS,
	MONTANIA;

	@Override
	public String toString() {
		switch(this){
			case CHINA: return "China";
			case JAPON: return "Japon";
			case AMAZONAS: return "Amazonas";
			case MONTANIA: return "Montania";

			default: return "";
		}
	}


	
}
