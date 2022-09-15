package presentation;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.House;
import domain.POOBchis;
import listeners.TileListener;
import tiles.Tile;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

@SuppressWarnings("serial")
/**
 * Clase BoardGUI. Permite crear el tableto de PPOBchis para poder jugar
 * 
 * @author Rocha y Rojas
 *
 */
public class BoardGUI extends JPanel {

	// Para el tablero
	private JPanel tablero;
	private POOBchis game;
	private int numTiles;
	private String[] tileTypes;

	// Matrices para asignar el movimiento correcto
	// para los jugadores en la partida

	/**
	 * Constructor de BoardGui, para visualizar el tablero y las fichas de los
	 * jugadores
	 * 
	 * @param rightPanel Panel en el que se encuentra el tablero a visualizar
	 * @param game       Instancia del juego POOBchis
	 * @param numTiles   Numero de fichas por jugador en el juego
	 */
	public BoardGUI(JPanel rightPanel, POOBchis game, int numTiles) {
		this.tablero = rightPanel;
		this.game = game;
		this.numTiles = numTiles;
	}

	/**
	 * Prepara el tablero del juego, lo construlle y permite visualizarlo
	 * 
	 * @param colorPy1 Color del Jugador 1, seleccionado al comienzo de la
	 *                 ejecucuion del programa.
	 * @param colorPy2 Color del Jugador 2, seleccionado al comienzo de la
	 *                 ejecucuion del programa.
	 * @param colorPy3 Color del Jugador 3, seleccionado al comienzo de la
	 *                 ejecucuion del programa.
	 * @param colorPy4 Color del Jugador 4, seleccionado al comienzo de la
	 *                 ejecucuion del programa.
	 */
	public void prepareBoard(Color colorPy1, Color colorPy2, Color colorPy3, Color colorPy4) {
		tileTypes = askTileTypes();
		prepareBase4(colorPy4);
		prepareUpperCentralZone(colorPy4);
		prepareBase3(colorPy3);
		prepareCentralLeftZone(colorPy1);
		prepareCentralCentralZone(colorPy1, colorPy2, colorPy3, colorPy4);
		prepareCentralRightlZone(colorPy3);
		prepareBase1(colorPy1);
		prepareLowerCentralZone(colorPy2);
		prepareBase2(colorPy2);
	}

	// Pregunta por tipos de fichas
	private String[] askTileTypes() {
		// Pregunta por tipos de fichas
		String[] tileTypes = { "Normal", /* "Rocket", */ "Jumper", "Advantageous"/* , "Engineer" */ };
		String[] selectedTypes = new String[4];
		for (int i = 1; i < 5; i++) {
			int index = JOptionPane.showOptionDialog(null, "Selecione el tipo de la ficha " + i + " para el juego.",
					"Tipo para la ficha " + i, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					tileTypes, tileTypes[0]);
			selectedTypes[i - 1] = tileTypes[index];
		}
		return selectedTypes;
	}

	// Base 4, esquina superior izquierda
	private void prepareBase4(Color colorPy4) {
		JPanel spot1 = new JPanel();
		/* House box4 = */game.addHouse(504, spot1, POOBchisGUI.player4, true);
		// Extensible cuando se pida más jugadores
		// POOBchisGUI.player4.assignHouse(box4);
		spot1.setBorder(BorderFactory.createLineBorder(colorPy4, 5, false));
		/*
		 * for (int i = 0; i < numTiles; i++) { JButton tilePlayer4 = new JButton();
		 * spot3.add(tilePlayer4); tilePlayer4.addActionListener(new
		 * TileListener(tilePlayer4, game, POOBchisGUI.player4));
		 * tilePlayer4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
		 * tilePlayer4.setBackground(colorPy3); Tile tile4 =
		 * POOBchisGUI.player3.addTile(POOBchisGUI.player4.getColor(), i, tilePlayer4,
		 * box4); tile4.assignHouse(box4); box4.addTile(tile3); }
		 */
		tablero.add(spot1);
	}

	// Zona superior central del tablero
	private void prepareUpperCentralZone(Color colorPy4) {
		JPanel spot2 = new JPanel();
		spot2.setLayout(new GridLayout(7, 3));
		for (int i = 0; i < 21; i++) {
			JPanel label = new JPanel();
			if (game.getMatrix4()[i] != 52 && game.getMatrix4()[i] != 47 && game.getMatrix4()[i] != 42
					&& game.getMatrix4()[i] < 100) {
				game.addBox(game.getMatrix4()[i], label, false);
			} else {
				game.addBox(game.getMatrix4()[i], label, true);
			}
			spot2.add(label);
			if (i % 3 == 1) {
				label.setOpaque(true);
				label.setBackground(colorPy4);
			} else if (i == 12 || i == 14) {
				label.setOpaque(true);
				label.setBackground(colorPy4);
			}
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		tablero.add(spot2);
	}

	// Base 3, esquina superior derecha
	private void prepareBase3(Color colorPy3) {
		JPanel spot3 = new JPanel();
		spot3.setLayout(new GridLayout(2, 2));
		House box3 = game.addHouse(503, spot3, POOBchisGUI.player3, true);
		POOBchisGUI.player3.assignHouse(box3);
		spot3.setBorder(BorderFactory.createLineBorder(colorPy3, 5, false));
		tablero.add(spot3);
		for (int i = 0; i < numTiles; i++) {
			JButton tilePlayer3 = new JButton();
			spot3.add(tilePlayer3);
			tilePlayer3.addActionListener(new TileListener(tilePlayer3, game, POOBchisGUI.player3));
			tilePlayer3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
			tilePlayer3.setBackground(colorPy3);
			Tile tile3 = POOBchisGUI.player3.addTile(POOBchisGUI.player3.getColor(), i, tilePlayer3, box3,
					tileTypes[i]);
			tile3.assignHouse(box3);
			box3.addTile(tile3);
		}
	}

	// Zona central izquierda del tablero
	private void prepareCentralLeftZone(Color colorPy1) {
		JPanel spot4 = new JPanel();
		spot4.setLayout(new GridLayout(3, 7));
		for (int i = 0; i < 21; i++) {
			JPanel label = new JPanel();
			if (game.getMatrix1()[i] != 59 && game.getMatrix1()[i] != 64 && game.getMatrix1()[i] != 1
					&& game.getMatrix1()[i] < 100) {
				game.addBox(game.getMatrix1()[i], label, false);
			} else {
				game.addBox(game.getMatrix1()[i], label, true);
			}
			spot4.add(label);
			if (i > 6 && i < 14) {
				label.setOpaque(true);
				label.setBackground(colorPy1);
			} else if (i == 4 || i == 18) {
				label.setOpaque(true);
				label.setBackground(colorPy1);
			}
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		tablero.add(spot4);
	}

	// Zona central central del tablero
	private void prepareCentralCentralZone(Color colorPy1, Color colorPy2, Color colorPy3, Color colorPy4) {
		JPanel spot5 = new JPanel();
		spot5.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 9; i++) {
			JPanel panel = new JPanel();
			if (i != 3 && i != 5 && i != 4) {
				if (i == 1 || i == 7) {
					panel.setLayout(new GridLayout(1, 1));
					spot5.add(panel);
					JPanel label = new JPanel();
					panel.add(label);
					if (i == 1) {
						game.addBox(407, label, true);
						label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						label.setOpaque(true);
						label.setBackground(colorPy4);
					} else if (i == 7) {
						game.addBox(207, label, true);
						label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						label.setOpaque(true);
						label.setBackground(colorPy2);
					}
				} else {
					panel.setLayout(new GridLayout(2, 1));
					spot5.add(panel);
					for (int j = 0; j < 2; j++) {
						JPanel label = new JPanel();
						panel.add(label);
						if (i == 0) {
							game.addBox(game.getMatrix5()[0][j], label, false);
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						} else if (i == 2) {
							game.addBox(game.getMatrix5()[1][j], label, false);
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						} else if (i == 6) {
							game.addBox(game.getMatrix5()[2][j], label, false);
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						} else if (i == 8) {
							game.addBox(game.getMatrix5()[3][j], label, false);
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						}
					}
				}
			} else if (i == 3 || i == 5) {
				spot5.add(panel);
				panel.setLayout(new GridLayout(1, 1));
				JPanel label = new JPanel();
				panel.add(label);
				if (i == 3) {
					game.addBox(107, label, true);
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setOpaque(true);
					label.setBackground(colorPy1);
				} else if (i == 5) {
					game.addBox(307, label, true);
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setOpaque(true);
					label.setBackground(colorPy3);
				}
			} else {
				// Centro central total del tablero
				spot5.add(panel);
				JLabel innerIcon = new JLabel();
				Image img = new ImageIcon(this.getClass().getResource("/center.png")).getImage();
				Image newImage = img.getScaledInstance(tablero.getWidth() / 11, tablero.getHeight() / 11,
						Image.SCALE_DEFAULT);
				innerIcon.setIcon(new ImageIcon(newImage));
				panel.add(innerIcon);
			}
		}
		tablero.add(spot5);
	}

	// Zona central derecha del tablero
	private void prepareCentralRightlZone(Color colorPy3) {
		JPanel spot6 = new JPanel();
		spot6.setLayout(new GridLayout(3, 7));
		for (int i = 0; i < 21; i++) {
			JPanel label = new JPanel();
			if (game.getMatrix3()[i] != 35 && game.getMatrix3()[i] != 30 && game.getMatrix3()[i] != 25
					&& game.getMatrix3()[i] < 100) {
				game.addBox(game.getMatrix3()[i], label, false);
			} else {
				game.addBox(game.getMatrix3()[i], label, true);
			}
			spot6.add(label);
			if (i > 6 && i < 14) {
				label.setOpaque(true);
				label.setBackground(colorPy3);
			} else if (i == 2 || i == 16) {
				label.setOpaque(true);
				label.setBackground(colorPy3);
			}
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		tablero.add(spot6);
	}

	// Base 1, esquina inferior izquierda
	private void prepareBase1(Color colorPy1) {
		JPanel spot7 = new JPanel();
		spot7.setLayout(new GridLayout(2, 2));
		House box1 = game.addHouse(501, spot7, POOBchisGUI.player1, true);
		POOBchisGUI.player1.assignHouse(box1);
		spot7.setBorder(BorderFactory.createLineBorder(colorPy1, 5, false));
		tablero.add(spot7);
		for (int i = 0; i < numTiles; i++) {
			JButton tilePlayer1 = new JButton();
			// tilePlayer2.setSize(new Dimension(generalWidth/45,generalWidth/45));
			spot7.add(tilePlayer1);
			tilePlayer1.addActionListener(new TileListener(tilePlayer1, game, POOBchisGUI.player1));
			tilePlayer1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
			tilePlayer1.setBackground(colorPy1);
			Tile tile1 = POOBchisGUI.player1.addTile(POOBchisGUI.player1.getColor(), i, tilePlayer1, box1,
					tileTypes[i]);
			tile1.assignHouse(box1);
			box1.addTile(tile1);
		}
	}

	// Zona inferior central del tablero
	private void prepareLowerCentralZone(Color colorPy2) {
		JPanel spot8 = new JPanel();
		spot8.setLayout(new GridLayout(7, 3));
		for (int i = 0; i < 21; i++) {
			JPanel label = new JPanel();
			if (game.getMatrix2()[i] != 8 && game.getMatrix2()[i] != 13 && game.getMatrix2()[i] != 18
					&& game.getMatrix2()[i] < 100) {
				game.addBox(game.getMatrix2()[i], label, false);
			} else {
				game.addBox(game.getMatrix2()[i], label, true);
			}
			spot8.add(label);
			if (i % 3 == 1) {
				label.setOpaque(true);
				label.setBackground(colorPy2);
			} else if (i == 6 || i == 8) {
				label.setOpaque(true);
				label.setBackground(colorPy2);
			}
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		tablero.add(spot8);
	}

	// Base 2, esquina inferior derecha
	private void prepareBase2(Color colorPy2) {
		JPanel spot9 = new JPanel();
		/* House box2 = */ game.addHouse(502, spot9, POOBchisGUI.player2, true);
		// Extensible cuando se pida más jugadores
		// POOBchisGUI.player2.assignHouse(box2);
		spot9.setBorder(BorderFactory.createLineBorder(colorPy2, 5, false));
		tablero.add(spot9);
	}
}