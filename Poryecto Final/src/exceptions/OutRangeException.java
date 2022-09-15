package exceptions;

/**
 * Clase OutRangeException. Error cuando una ficha intenta moverse a fuera de
 * los límites del tablero.
 * 
 * @author Rocha y Rojas
 *
 */
@SuppressWarnings("serial")
public class OutRangeException extends POOBchisException {
	private static final String localMsg = "Intenta con otra, no puedes salir del tablero";

	/**
	 * Constructor de la Clase FullCasException. Arroja un error cuando una ficha
	 * intenta moverse a fuera de los límites del tablero.
	 * 
	 * @param msg Mensaje a arrojar cuando ocurra un error.
	 */
	public OutRangeException(String msg) {
		super(localMsg);
	}

}