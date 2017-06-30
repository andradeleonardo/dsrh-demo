/**
 * 
 */
package br.com.dsrh.demo.web.exception;


public class DAOException extends Exception {

	private static final long serialVersionUID = -6588865631134722708L;
	
	public DAOException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
