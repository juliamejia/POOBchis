package tiles;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import domain.Box;
import domain.Player;

public class JumperTile extends Tile {

	// Tipo de ficha, Jumper
	private final String type = "Jumper";

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
	public JumperTile(Color color, Player owner, int id, JButton instance, Box box) {
		super(color, owner, id, instance, box);
		instance.setText("J");
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
}
