package exceptions;

/**
 * Clase BlockException. Error cuando una ficha intenta moverse a traves de un
 * bloqueo.
 * 
 * @author Rocha y Rojas
 *
 */
@SuppressWarnings("serial")
public class BlockException extends POOBchisException {
	private static final String localMsg = "Se presenta un bloqueo";

	/**
	 * Constructor de la Clase FullCasException. Arroja un error cuando una ficha
	 * intenta moverse a traves de un bloqueo.
	 * 
	 * @param msg Mensaje a arrojar cuando ocurra un error.
	 */
	public BlockException(String msg) {
		super(localMsg);
	}
}