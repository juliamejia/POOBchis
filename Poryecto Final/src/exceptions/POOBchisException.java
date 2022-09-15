package exceptions;

/**
 * Clase POOBchisException. Establece los posibles errores presentes en el
 * juego. Particularmente, cuando las opciones presentes no están implementadas
 * todavia.
 * 
 * @author Rocha y Rojas
 *
 */
@SuppressWarnings("serial")
public class POOBchisException extends Exception {

	// Error cuando la funcionalidad no este implementada aun
	public static final String msgLocal = "Opcion en construccion";

	/**
	 * Constructor de la Clase POOBchisException. Establece los posibles errores
	 * presentes en el juego. Particularmente, cuando una opcion no esta
	 * implementada todavia.
	 * 
	 * @param msg Mensaje a arrojar cuando ocurra un error.
	 */
	public POOBchisException(String msg) {
		super(msg);
	}
}
