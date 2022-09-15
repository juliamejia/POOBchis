package tiles;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import domain.Box;
import domain.House;
import domain.Player;

/**
 * Constructor de la clase Tile. Crea una ficha que pertenece a un jugador de
 * POOBchis.
 * 
 * @author Rocha y Rojas
 *
 */
public class Tile {

	// Tipo de Ficha
	private final String type = "Normal";

	// Color, duenno, id, boton al que pertenece y casilla del tablero en la que se
	// encuentra.
	protected Color color;
	private Player owner;
	private int id;
	protected JButton instance;
	public Box myBox;
	public House myHouse;
	//
	public boolean targetDropHome = false;

	// Determina si se puede mover la ficha
	private boolean availableToMove = true;

	/**
	 * Constructor de la clase Tile. Crea una ficha que pertenece a un jugador de
	 * POOBchis.
	 * 
	 * @param color    Color de la fila.
	 * @param owner    Jugador al que pertenece la ficha.
	 * @param id       Identificador de la ficha.
	 * @param instance Instancia del boton que representa la ficha.
	 * @param box      Casilla en donde se encuentra inicialmente la ficha.
	 */
	public Tile(Color color, Player owner, int id, JButton instance, Box box) {
		this.color = color;
		this.owner = owner;
		this.id = id;
		this.instance = instance;
		instance.setText("N");
		instance.setForeground(Color.BLACK);
		instance.setFont(new Font("Serif", Font.BOLD, 10));
		this.myBox = box;
	}

	/**
	 * Retorna la instancia del boton que representa a la ficha.
	 * 
	 * @return instance Instancia del boton que representa a la ficha.
	 */
	public JButton getInstance() {
		return instance;
	}

	/**
	 * Retorna la casilla en la que se encuentra actualmente la ficha.
	 * 
	 * @return myBox Casilla en la que se encuentra actualmente la ficha.
	 */
	public Box getBox() {
		return myBox;
	}

	/**
	 * Cambia y actualiza la casilla en la que se encuentra la ficha.
	 * 
	 * @param newBox Nueva casilla en la que se encuentra la ficha.
	 */
	public void changeBox(Box newBox) {
		this.myBox = newBox;
	}

	/**
	 * Retorna el duenno de la ficha.
	 * 
	 * @return owner Duenno de la ficha
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Retorna el color de la ficha.
	 * 
	 * @return color Color de la ficha.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Retorna el identificador de la ficha.
	 * 
	 * @return id Identificador de la ficha.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Habilita o desabilita el boton correspondiente a la ficha.
	 * 
	 * @param value True para habilitar, False para desabilitar.
	 */
	public void setEnabled(boolean value) {
		instance.setEnabled(value);
	}

	/**
	 * Metodo para asignar a la ficha una base a la que pertenece.
	 * 
	 * @param box Base a la que pertenecen la ficha.
	 */
	public void assignHouse(House box) {
		this.myHouse = box;
	}

	/**
	 * Retorna la
	 * 
	 * @return myHouse Base a la que pertenece la ficha.
	 */
	public House getHouse() {
		return myHouse;
	}

	/**
	 * Pregunta si una ficha se encuentra en la carcel o no.
	 * 
	 * @return boolean True si se encuentra en una base, False de lo contrario.
	 */
	public boolean isInHouse() {
		if (myBox.equals(myHouse)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retorna el tipo de ficha que es la instancia de la ficha, en este caso, un
	 * ficha Normal.
	 * 
	 * @return type Tipo de ficha de la ficha Normal.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Retorna la disponivilidad de una ficha para moverse.
	 * 
	 * @return True si la ficha puede moverse, false en cualquier otro caso.
	 */
	public boolean getAvailability() {
		return availableToMove;
	}

	/**
	 * Asigna la disponivilidad de una ficha para moversa.
	 * 
	 * @param availability True si la ficha puede moverse, false en cualquier otro
	 *                     caso
	 */
	public void setAvailability(Boolean availability) {
		this.availableToMove = availability;
	}

	/**
	 * Si la ficha se encuentra en la última casilla del jugador, esta se corona y
	 * no se puede jugar más. Su estado pasa de false a true.
	 * 
	 * @return True si la ficha está coronada (en la última casilla del jugador),
	 *         false en cualquier otro caso.
	 */
	public boolean winnerTile() {
		if (getBox() == getOwner().getLastBox()) {
			return true;
		} else {
			return false;
		}
	}
}
