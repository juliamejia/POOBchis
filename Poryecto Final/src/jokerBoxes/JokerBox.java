/**
 * Paquete jokerBoxes, contiene todos los tipos de comodines implementados para el juego POOBchis
 */
package jokerBoxes;

import java.awt.Color;

import javax.swing.BorderFactory;

import domain.Box;

/**
 * 
 * Clase abstracta de la cual heredaran los comodines
 * 
 * @author Rocha y Rojas
 *
 */
public abstract class JokerBox {
	public Box selectedBox;
	public boolean available = true;

	/**
	 * Constructor de JokerBox
	 * 
	 * @param selectedBox box referencia de la casilla en la que se aplica el
	 *                    comodin
	 */
	public JokerBox(Box selectedBox) {
		this.selectedBox = selectedBox;
		selectedBox.getInstance().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));
	}

	/**
	 * Aplica el truco que guarda el comodin
	 */
	public abstract void use();
}