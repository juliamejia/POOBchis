package domain;

import javax.swing.JPanel;

/**
 * Clase House, extiende la Clase Box. Cada House representa la base de un
 * jugador en la partida.
 * 
 * @author Rocha y Rojas
 *
 */
public class House extends Box {

	private Player owner;

	/**
	 * Constructor de la Clase House. Cada House representa la base de un jugador en
	 * la partida.
	 * 
	 * @param number   Numero de la casilla
	 * @param instance Instancia de la posicion en el tablero correspondiente en
	 *                 presentacion
	 * @param safe     Seguridad de la casilla, True para indicar que es segura
	 *                 (siempre debe ser True)
	 */
	public House(int number, JPanel instance, Player player, boolean safe) {
		super(number, instance, safe);
		this.owner = player;
	}

	/**
	 * Retorna si la casa esta llena o no. True si esta llena, False en otro caso.
	 * 
	 * @return boolean True si esta llena, False en otro caso.
	 */
	public boolean isFull() {
		if (this.getTilesOver() == owner.getNumTiles()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Pregunta si la base de un jugador está vacia o no
	 * 
	 * @return boolean True si esta vacia, False de lo contrario
	 */
	public boolean isEmpty() {
		if (this.getTilesOver() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
