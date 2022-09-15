package domain;

import java.util.ArrayList;

import javax.swing.JPanel;

import jokerBoxes.JokerBox;
import tiles.Tile;

/**
 * Clase Box. Cada box corresponde a una casilla en el tablero.
 * 
 * @author Rocha y Rojas
 *
 */
public class Box {

	// Numero de la casilla, instancia de casilla en presentacion
	protected int number;
	private JPanel instance;
	private JokerBox joker = null;

	// Seguridad de la casilla
	public boolean safe;

	// Fichas alojadas en la caslla
	protected ArrayList<Tile> tiles = new ArrayList<Tile>();

	/**
	 * Constructor de la clase Box. Cada box corresponde a una casilla en el
	 * tablero.
	 * 
	 * @param number   Numero de la casilla.
	 * @param instance Intancia de posicion en el tablero, presentacion.
	 * @param safe     Seguridad de la casilla, True si la casilla es segura, False
	 *                 si no.
	 */
	public Box(int number, JPanel instance, boolean safe) {
		this.number = number;
		this.instance = instance;
		this.safe = safe;
	}

	/**
	 * Retorna el numero de la casilla
	 * 
	 * @return number Numero de la casilla.
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Annade fichas a la casilla
	 * 
	 * @param tile Ficha a annadir a la casilla actual
	 */
	public void addTile(Tile tile) {
		tiles.add(tile);
		instance.add(tile.getInstance());
		instance.repaint();
	}

	/**
	 * Retorna una lista con la fichas contenidas en la casilla.
	 * 
	 * @return tiles Lista con la fichas contenidas en la casilla.
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	/**
	 * Retorna la instancia de la posicion en el tablero a la que pertenece la
	 * casilla.
	 * 
	 * @return instance Posicion en el tablero a la que pertenece la casilla.
	 */
	public JPanel getInstance() {
		return instance;
	}

	/**
	 * Retorna si la casilla es segura o no.
	 * 
	 * @return safe True si la casilla es segura, False si no lo es
	 */
	public boolean isSafe() {
		return safe;
	}

	/**
	 * Remueve una ficha de la casilla, la quita de encima.
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		tile.getBox().getInstance().remove(tile.getInstance());
		tiles.remove(tile);
	}

	/**
	 * Retorna el numero de fichas que hay presentes encima de la casilla.
	 * @return tiles.size() Numero de fichas que hay presentes encima de la casilla.
	 */
	public int getTilesOver() {
		return tiles.size();
	}

	/**
	 * Coloca un tipo especificado de comodin qpara ponerlo encima de la casilla.
	 * @param joker Comodin a poner encima de la casilla actual.
	 */
	public void setJoker(JokerBox joker) {
		this.joker = joker;
	}

	/**
	 * Retorna el tipo de comodin que hay presente encima de la casilla, en caso de haberlo.
	 * @return joker Comodin presente en la casilla
	 */
	public JokerBox getJoker() {
		return joker;
	}
}