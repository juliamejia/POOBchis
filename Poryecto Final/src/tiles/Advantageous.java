package tiles;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import domain.Box;
import domain.Player;

/**
 * Clase para las fichas de tipo Advantageous, estas fichas avanzan 3 casillas
 * cada 3 turnos en el juego POOBchis.
 * 
 * @author Dell
 *
 */
public class Advantageous extends Tile {

	// Tipo de ficha, Jumper
	private final String type = "Advantageous";

	private int turns = 0;

	/**
	 * Constructor de la clase JumperTile, una ficha que puede saltarse los bloqueos
	 * presentes en tablero.
	 * 
	 * @param color    Color de la fila Jumper (Saltarina).
	 * @param owner    Jugador al que pertenece la ficha Jumper (Saltarina).
	 * @param id       Identificador de la ficha Jumper (Saltarina).
	 * @param instance Instancia del boton que representa la ficha Jumper
	 *                 (Saltarina).
	 * @param box      Casilla en donde se encuentra inicialmente la ficha Jumper
	 *                 (Saltarina).
	 */
	public Advantageous(Color color, Player owner, int id, JButton instance, Box box) {
		super(color, owner, id, instance, box);
		instance.setText("A");
		instance.setForeground(Color.BLACK);
		instance.setFont(new Font("Serif", Font.BOLD, 10));
	}

	/**
	 * Retorna el tipo de ficha que es la instancia de la ficha, en este caso, un
	 * ficha Jumper (Saltarina).
	 * 
	 * @return type Tipo de ficha de la ficha Jumper (Saltarina).
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * Incrementa el numero de turnos que recuerda la ficha Advantageous, para saber
	 * como actuar en casos especiales (cada 3 turnos)
	 */
	public void increaseTurn() {
		turns = ((turns + 1) % 3);
	}

	/**
	 * Retorna los tuenos que recuerda la ficha Advantageous, para saber como actuar
	 * en casos especiales (cada 3 turnos)
	 * 
	 * @return turns Turnos en los que la ficha ha estado presente en el juego
	 *         POOBchis.
	 */
	public int getTurns() {
		return turns;
	}
}