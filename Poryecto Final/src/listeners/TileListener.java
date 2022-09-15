package listeners;

import java.awt.event.ActionListener;
//import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import domain.POOBchis;
import domain.Player;
import exceptions.BlockException;
import exceptions.FullCasException;
import exceptions.OutRangeException;
import presentation.POOBchisGUI;
import tiles.Tile;

import java.awt.event.ActionEvent;

import domain.Box;
import domain.Log;

/**
 * Clase TileListener Encargada de dar acciones a las fichas de los jugadores,
 * cuando son presionadas
 * 
 * @author Rocha y Rojas
 *
 */
public class TileListener implements ActionListener {

	// Juego y representacion visual
	private POOBchis game;
	private JButton instance;
	private Player owner;
	private static int numShots = 0;

	/**
	 * Constructor de la clase TileListener Asigna funcionalidades al presionar las
	 * fichas de los jugadores
	 * 
	 * @param instance Intancia del boton correspondiente a la ficha de un jugador
	 * @param game     Instancia del juego POOBchis al que pertenecen las fichas
	 * @param owner    Instancia de Player, correspondiente a quien posee la ficha
	 *                 seleccionada
	 */
	public TileListener(JButton instance, POOBchis game, Player owner) {
		this.instance = instance;
		this.game = game;
		this.owner = owner;
	}

	/**
	 * Accion que va a realizar la ficha selecconada. Permite mover las fichas
	 * seleccionadas mientras el jugador sea el dueño y mientras su lanzamiento de
	 * dados lo permita.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Player player = game.getCurrentPlayer();
		boolean canPlay = false;
		int[] moves = player.toMove();
		if (player.getHouse().isFull() || !(player.getHouse().isEmpty())) {
			canPlay = getTilesOut(player, moves);
		} else {
			canPlay = true;
		}
		if (player.equals(owner) && canPlay) {
			startPlayerTurn(player, moves);
			if (player.again) {
				if (player.value1 == 0 && player.value2 == 0) {
					POOBchisGUI.rollDices.setEnabled(true);
					POOBchisGUI.terminar.setEnabled(false);
				}
			}
		} else if (!(player.equals(owner))) {
			// No deberia darse este caso
			JOptionPane.showMessageDialog(null, "No es tu ficha, no puedes jugar", "Ficha equivocada", 0);
		}

		boolean blocked = false;

		for (Tile tile : player.getTiles()) {
			if (!tile.getAvailability()) {
				blocked = true;
			} else {
				blocked = false;
			}
		}

		if (blocked) {
			POOBchisGUI.terminar.setEnabled(true);
			POOBchisGUI.rollDices.setEnabled(false);
		}

	}

	// Si hay fichas en la base del jugador, se debe actuar de forma especial
	private boolean getTilesOut(Player player, int[] moves) {
		if (player.getHouse().isFull() && moves[0] == moves[1]) {
			numShots = 0;
			player.outWhenFull();
			for (Tile t : player.getTiles()) {
				t.setEnabled(false);
			}
			POOBchisGUI.rollDices.setEnabled(false);
			POOBchisGUI.terminar.setEnabled(true);
			return false;

		} else if (!(player.getHouse().isFull()) && !(player.getHouse().isEmpty()) && moves[0] == moves[1]) {
			Tile t = player.getHouse().getTiles().get(0);
			for (Tile t2 : player.getTiles()) {
				if (t2.getInstance().equals(instance)) {
					try {
						player.moveTile(t2, moves[0]);
						for (Tile tile : player.getTiles()) {
							tile.setEnabled(false);
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
			try {
				player.moveTile(t, 1);
				t.setEnabled(false);
			} catch (FullCasException fe) {
				JOptionPane.showMessageDialog(null, fe.getMessage());
				t.setAvailability(false);
				Log.record(fe);
			} catch (BlockException be) {
				JOptionPane.showMessageDialog(null, be.getMessage());
				t.setAvailability(false);
				Log.record(be);
			} catch (OutRangeException oe) {
				JOptionPane.showMessageDialog(null, oe.getMessage());
				t.setAvailability(false);
				Log.record(oe);
			}

			eliminateRepeated(player);

			POOBchisGUI.rollDices.setEnabled(false);
			POOBchisGUI.terminar.setEnabled(true);
			player.again = false;
			return false;

		} else if (!(player.getHouse().isFull()) && !(player.getHouse().isEmpty()) && moves[0] != moves[1]) {
			return true;

		} else {
			numShots++;
			if (numShots < 3) {
				JOptionPane.showMessageDialog(null, "Lanze de nuevo los dados.", "No sacaste pares.", 0);
				for (Tile t : player.getTiles()) {
					t.setEnabled(false);
				}
				POOBchisGUI.rollDices.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "No pudiste sacar tus fichas de la carcel.", "No sacaste fichas.",
						0);
				POOBchisGUI.rollDices.setEnabled(false);
				POOBchisGUI.terminar.setEnabled(true);
				for (Tile t : player.getTiles()) {
					t.setEnabled(false);
				}
				numShots = 0;
			}
			return false;
		}
	}

	// Turno normal de un jugador, sin fichas en la carcel
	private void startPlayerTurn(Player player, int[] moves) {
		Object[] options = { moves[0], moves[1], moves[0] + moves[1] };
		int ans = JOptionPane.showOptionDialog(null, "Cuanto desea mover?", "Mover Ficha", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);

		for (Tile tile : player.getTiles()) {
			if (tile.getInstance().equals(instance)) {
				try {
					try {
						try {
							Box previousBox = tile.getBox();
							Box newBox = player.moveTile(tile, (int) options[ans]);
							if (!previousBox.equals(newBox)) {
								tile.setEnabled(false);
								tile.setAvailability(false);
							}
							extractDropped(player, (int) options[ans]);
							if (!newBox.getJoker().equals(null)) {
								newBox.getJoker().use();
								newBox.getJoker().available = false;
								newBox.getInstance().setOpaque(false);
							}
						} catch (NullPointerException ne) {
							Log.record(ne);
						}
						if (player.value1 <= 0 && player.value2 <= 0 && player.again == false) {
							for (Tile t : player.getTiles()) {
								t.setEnabled(false);
							}
							POOBchisGUI.terminar.setEnabled(true);
						} else if (player.value1 <= 0 && player.value2 <= 0 && player.again == true) {
							POOBchisGUI.rollDices.setEnabled(true);
							for (Tile t : player.getTiles()) {
								t.setEnabled(false);
							}
						}
						for (Player p : POOBchis.players) {
							if (p.winner == true) {
								String[] optionEnd = { "Nuevo juego", "Cancel" };
								ans = 1;
								ans = JOptionPane.showOptionDialog(null, "El ganador es " + p.getName(),
										"Fin del juego", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
										null, optionEnd, options[0]);
							}
						}
					} catch (ArrayIndexOutOfBoundsException ae) {
						JOptionPane.showMessageDialog(null, "Es necesario que selecciones una de las respuestas.");
						POOBchisGUI.rollDices.setEnabled(false);
						Log.record(ae);
					}
				} catch (FullCasException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage());
					tile.setAvailability(false);
					Log.record(fe);
				} catch (BlockException be) {
					JOptionPane.showMessageDialog(null, be.getMessage());
					tile.setAvailability(false);
					Log.record(be);
				} catch (OutRangeException oe) {
					JOptionPane.showMessageDialog(null, oe.getMessage());
					tile.setAvailability(false);
					Log.record(oe);
				}
			}
		}

	}

	// Elimina los jugadores repetidos
	private void eliminateRepeated(Player player) {
		if (player.getMovementMatrix()[1].getTilesOver() > 1) {
			for (Tile tile : player.getMovementMatrix()[1].getTiles()) {
				if (!(tile.getColor().equals(player.getColor()))) {
					tile.getOwner().moveToHouse(tile);
					break;
				}
			}
		}
	}

	/**
	 * Evalua los movimientos de los jugadores y determina que jugadas pueden hacer.
	 * 
	 * @param player Jugador que movio la ficha.
	 * @param value  Cantidad de casillas que movio.
	 */
	public void extractDropped(Player player, int value) {
		if (player.value1 == value) {
			player.value1 = player.value1 - value;
		} else if (player.value2 == value) {
			player.value2 = player.value2 - value;
		} else {
			player.value1 = player.value1 - value;
			player.value2 = player.value2 - value;
		}
	}
}
