package domain;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exceptions.BlockException;
import exceptions.FullCasException;
import exceptions.OutRangeException;
import tiles.Advantageous;
import tiles.JumperTile;
import tiles.Tile;

import java.util.Arrays;

/**
 * Clase Player. Crea jugadores para el juego POOBchis.
 * 
 * @author Rocha y Rojas
 *
 */
public class Player {

	// Nombre, color, fichas, matriz de movimiento, y resultados de los dados para
	// el jugador.
	private String name;
	private Color color;
	private Tile[] myTiles;
	private int numTiles;
	private Box[] myBoard;
	public int value1;
	public int value2;
	public boolean again;
	public boolean winner = false;
	private boolean canMove;
	public boolean inHouse;

	// Mi base
	private House myHouse;
	// Mi final del juego
	private Box lastBox;

	/**
	 * Constructor de la Clase Player. Crea jugadores para el juego POOBchis.
	 * 
	 * @param name     Nombre del jugador.
	 * @param color    Color para identificar al jugador.
	 * @param numTiles Numero de fichas iniciales del juador.
	 */
	public Player(String name, Color color, int numTiles) {
		this.name = name;
		this.color = color;
		this.myTiles = new Tile[numTiles];
		this.numTiles = numTiles;
	}

	/**
	 * Retorna el color del jugador.
	 * 
	 * @return color Color del jugador.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Retorna el nombre del jugador.
	 * 
	 * @return name Nombre del jugador.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retorna las fichas que posee el jugador.
	 * 
	 * @return myTile Fichas que posee el jugador.
	 */
	public Tile[] getTiles() {
		return myTiles;
	}

	/**
	 * Retorna como String el nombre y el color del jugador.
	 * 
	 * @return String Nombre y el color del jugador.
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + ", color=" + color + "]";
	}

	/**
	 * Permite annadir fichas al jugador.
	 * 
	 * @param color    Color de la ficha a annadir.
	 * @param id       Identificador de la ficha.
	 * @param instance Instancia del boton correspondiente a la ficha.
	 * @param box      Casilla a la que pertenece la ficha.
	 * @param type     Tipo de ficha a crear para el jugador. Puede ser Normal,
	 *                 Advantageous o Jumper.
	 * @return tile Instancia de la ficha creada.
	 */
	public Tile addTile(Color color, int id, JButton instance, Box box, String type) {
		switch (type) {
		case "Normal":
			Tile normal = new Tile(color, this, id, instance, box);
			myTiles[id] = normal;
			return normal;
		case "Jumper":
			JumperTile jumpy = new JumperTile(color, this, id, instance, box);
			myTiles[id] = jumpy;
			return jumpy;
		case "Advantageous":
			Advantageous advantageous = new Advantageous(color, this, id, instance, box);
			myTiles[id] = advantageous;
			return advantageous;
		default:
			return null;
		}

	}

	/**
	 * Asigna una matriz de movimiento al jugador.
	 * 
	 * @param myBoard Matriz de movimiento a asignar al jugador
	 */
	public void setMovementMatrix(Box[] myBoard) {
		this.myBoard = myBoard;
		setLastBox();
	}

	/**
	 * Asigna los movimientos disponibles al jugador.
	 * 
	 * @param value1 Cantidad de casillas a mover con 1 dado.
	 * @param value2 Cantidad de casillas a mover con el otro dado.
	 */
	public void availableMoves(int value1, int value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	/**
	 * Movimientos disponibles por un jugador.
	 * 
	 * @return moves Movimientos disponibles por un jugador.
	 */
	public int[] toMove() {
		int[] moves = new int[2];
		if (value1 < 0) {
			moves[0] = 0;
		} else {
			moves[0] = value1;
		}
		if (value2 < 0) {
			moves[1] = 0;
		} else {
			moves[1] = value2;
		}
		return moves;
	}

	/**
	 * Determina cuantas casillas tiene disponibles un jugador para hacer avanzar
	 * sus fichas.
	 * 
	 * @param toMove Numero de casillas a mover por ficha
	 */
	public void setCanMove(int toMove) {
		canMove = false;
		for (Tile t : myTiles) {
			int getIndexT = Arrays.asList(myBoard).indexOf(t.getBox());
			if (getIndexT + toMove < myBoard.length) {
				canMove = true;
			}
		}
	}

	/**
	 * Determina si el jugador tiene todas sus fillas en la base.
	 */
	public void setInHouse() {
		inHouse = false;
		for (Tile t : myTiles) {
			int getIndexT = Arrays.asList(myBoard).indexOf(t.getBox());
			if (getIndexT != 0) {
				inHouse = true;
			}
		}
	}

	/**
	 * Permite mover una ficha seleccionada, mientras pertenezca al jugador.
	 * 
	 * @param tile   Ficha a mover.
	 * @param toMove Cantidad de casillas a mover.
	 * @return Box Casilla a la que se mueve la ficha.
	 */
	public Box moveTile(Tile tile, int toMove) throws FullCasException, BlockException, OutRangeException {
		int getIndex = Arrays.asList(myBoard).indexOf(tile.getBox());
		setCanMove(toMove);
		if (getIndex + toMove < myBoard.length && canMove) {
			if (myBoard[getIndex + toMove].getTilesOver() >= 2 && getIndex + toMove != myBoard.length) {
				throw new FullCasException(FullCasException.msgLocal);
			}
			for (int i = (getIndex + 1); i < (getIndex + toMove); i++) {
				if (myBoard[i].getTilesOver() >= 2 && tile.getType() != "Jumper") {
					throw new BlockException(BlockException.msgLocal);
				}
			}
			if (myBoard[getIndex + toMove].getTilesOver() == 1 && !myBoard[getIndex + toMove].safe
					&& !myBoard[getIndex + toMove].getTiles().get(0).getOwner().equals(tile.getOwner())) {
				moveToHouse(myBoard[getIndex + toMove].getTiles().get(0));
			}
			JPanel panel = tile.getBox().getInstance();
			tile.getBox().removeTile(tile);

			myBoard[getIndex + toMove].addTile(tile);
			tile.changeBox(myBoard[getIndex + toMove]);

			panel.repaint();
			myBoard[getIndex + toMove].getInstance().repaint();

			JButton itsButton = tile.getInstance();
			itsButton.setBounds(5, 5, 15, 15);
			if (getIndex + toMove == myBoard.length) {
				tile.setEnabled(false);
			}
			itsButton.repaint();
			isWinner();
		} else {
			throw new OutRangeException(OutRangeException.msgLocal);
		}
		if (isWinner()) {
			this.winner = true;
			POOBchis.thereIsAWinner(this);
		}
		
		return myBoard[getIndex + toMove];
	}

	/**
	 * Mueve una ficha perteneciente al jugador de regreso a su base.
	 * 
	 * @param tile Ficha a regresar a la base del jugador.
	 */
	public void moveToHouse(Tile tile) {
		Box[] itsBoard = myBoard;
		for (Player p : POOBchis.players) {
			if (p.equals(tile.getOwner())) {
				itsBoard = p.myBoard;
			}
		}
		JPanel panel = tile.getBox().getInstance();
		tile.getBox().removeTile(tile);
		itsBoard[0].addTile(tile);
		tile.changeBox(itsBoard[0]);

		panel.repaint();
		itsBoard[0].getInstance().repaint();
	}

	/**
	 * Actualiza el estado del jugador 'winner' de false a true.
	 * @return winner True si el usuario llevo todas sus fichas hasta el final, false de otra forma
	 */
	public boolean isWinner() {
		boolean winner;
		if (myBoard[myBoard.length - 1].getTilesOver() == 4) {
			winner = true;
		} else {
			winner = false;
		}
		return winner;
	}

	/**
	 * Retorna el numero de fichas que posee el jugador.
	 * 
	 * @return numTiles El numero de fichas que posee el jugador.
	 */
	public int getNumTiles() {
		return numTiles;
	}

	/**
	 * Asigna al jugador la base que le pertenece en el tablero.
	 * 
	 * @param box Base que pertenece al jugador en el tablero.
	 */
	public void assignHouse(House box) {
		this.myHouse = box;
	}

	/**
	 * Retorna la base que le pertenece al jugador en el tablero.
	 * 
	 * @return myHouse Base que le pertenece al jugador en el tablero.
	 */
	public House getHouse() {
		return myHouse;
	}

	/**
	 * Retorna la matriz de movimiento del jugador.
	 * 
	 * @return myBoard Es tablero por el que el jugador puede jugar y mover fichas
	 */
	public Box[] getMovementMatrix() {
		return myBoard;
	}

	/**
	 * Pernite sacar 2 fichas de la carcel siempre que la carcel se encuentre
	 * completamente llena.
	 */
	public void outWhenFull() {
		for (int i = 0; i < 2; i++) {
			try {
				moveTile(getTiles()[i], 1);
				for (int j = 0; j < getHouse().getTilesOver(); j++) {
					getHouse().getTiles().get(i).getInstance().setEnabled(false);
				}
			} catch (FullCasException fe) {
				JOptionPane.showMessageDialog(null, fe.getMessage());
				Log.record(fe);
			} catch (BlockException be) {
				JOptionPane.showMessageDialog(null, be.getMessage());
				Log.record(be);
			} catch (OutRangeException oe) {
				JOptionPane.showMessageDialog(null, oe.getMessage());
				Log.record(oe);
			}
		}
	}

	// Setea la última casilla del jugador, la meta, a donde deben llegar las
	// fichas.
	private void setLastBox() {
		int lastIdx = myBoard.length - 1;
		Box lastElement = myBoard[lastIdx];
		this.lastBox = lastElement;
	}

	/**
	 * Retorna la ultima casilla del jugador, a donde deben llegar las fichas.
	 * 
	 * @return lastBox Ultima casilla del jugador.
	 */
	public Box getLastBox() {
		return lastBox;
	}
}
