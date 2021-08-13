/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
public enum Cargo {
	GERENTE,
	ADMIN,
	DATAENTRY;

	@Override
	public String toString() {
		switch(this){
			case GERENTE: return "Gerente";
			case ADMIN: return "Administrador";
			case DATAENTRY: return "Data-Entry";
			default: return "";
		}
	}
	
}
