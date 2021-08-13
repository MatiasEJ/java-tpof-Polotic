/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.errores;


/**
 *
 * @author Matias Ezequiel Juncos.
 */
public class ErrorServlet extends Exception{

	public ErrorServlet(String message) {
		super(message);
	}


}
