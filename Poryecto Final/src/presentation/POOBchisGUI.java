/**
 * Paquete de presentacion
 */
package presentation;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
//import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
//import javax.swing.JFileChooser;

import domain.*;
import exceptions.BlockException;
import exceptions.FullCasException;
import exceptions.OutRangeException;
import jokerBoxes.DropHome;
import tiles.Advantageous;
import tiles.Tile;

/**
 * Clase POOBchisGUI.
 * </p>
 * Permite jugar POOBchis, inicialmente contra la maquina
 * 
 * @author Rocha y Rojas
 *
 */
public class POOBchisGUI extends JPanel {

	// Instancia correspondiente a la actual interfaz de POOBchis
	private static POOBchisGUI instance;

	// Tamano de la pantalla / ventana
	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (3 * screenSize.width) / 4;
	private int height = (3 * screenSize.height) / 4;

	// Coneccion con el Dominio
	private static POOBchis game;
	private static Player[] players;

	// Jugadores actuales (2 por el momento, maquina y jugador 1)
	public static Player player1;
	public static Player player2;
	public static Player player3;
	public static Player player4;

	// Colores de los jugadores definidos
	private Color colorPy1;
	private Color colorPy3;

	/*
	 * private JMenu configuracion; private JMenuItem jugadores; private JMenuItem
	 * colores;
	 */

	// División de paneles en la ventana principal
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel diceSection;

	// User info
	private JPanel playerInfo;
	private JPanel infoPlayer1;
	private JPanel infoPlayer3;
	private JLabel winTilesPy1;
	private JLabel winTilesPy3;
	private JLabel deadTilesPy1;
	private JLabel deadTilesPy3;
	private JLabel boxesToWinPy1;
	private JLabel boxesToWinPy3;

	// Zona y funcionalidad de los dados
	private DiceGUI dice1;
	private DiceGUI dice2;
	public static JButton rollDices;
	private JPanel turn;
	private JLabel colorToShow;
	private JPanel otherFunctions;
	private BoardGUI board;
	public static JButton terminar;

	// Numero de fichas en el tablero, inicialmente 4
	private int numTiles = 4;

	// Comodines
	// private Random random = new Random();
	public static DropHome jokerDropHome;

	// Instancia de POOBchisGUI
	/**
	 * Verifica si una instancia de POOBchisGUI existe, y si no, la crea. Luego, la
	 * retorna.
	 * 
	 * @return instance Instaancia de POOBchisGUI.
	 */
	public static POOBchisGUI getInstance() {
		if (instance == null) {
			instance = new POOBchisGUI();
		}
		return instance;
	}

	/**
	 * Constructor de la Interface Grafica de POOBchis
	 * 
	 * @param title Titulo para la ventana del juego, predeterminada a POOBchis
	 */
	public POOBchisGUI() {
		setLayout(null);
		preparePlayers();
		prepareElements();
		prepareActions();
		getGame().assignMovementMatrix(players, 1);
		getGame().assignMovementMatrix(players, 3);
		preparePlayerZone();

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// ELEMENTS//ELEMENTS///ELEMENTS////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Prepara todos los elementos de la interface: el menu emergente,
	// el panel izquierdo y el panel derecho
	private void prepareElements() {

		prepareleftPanel();
		prepareRightPanel();
		prepareJokers();

	}

	// Prepara los jugadores del juego,
	// crea la instancia de POOBchis con la lista de jugadores
	private void preparePlayers() {
		String name1 = null;
		while (name1 == null || name1.equals("")) {
			name1 = JOptionPane.showInputDialog(null, "Ingrese el nombre del Jugador 1", "Nombre del Jugador", 1);
			if (name1 == null || name1.equals("")) {
				JOptionPane.showMessageDialog(null, "Es necesario escoger un nombre para el Jugador 1 .",
						"Nombre requerido", 1);
			}
		}
		String name2 = null;
		while (name2 == null || name2.equals("") || name2.equals(name1)) {
			name2 = JOptionPane.showInputDialog(null, "Ingrese el nombre del Jugador 2", "Nombre del Jugador", 1);
			if (name2 == null || name2.equals("")) {
				JOptionPane.showMessageDialog(null, "Es necesario escoger un nombre para el Jugador 2 .",
						"Nombre requerido", 1);
			}
		}

		boolean different = false;
		while (!different) {
			while (colorPy1 == null || colorPy1 == Color.RED || colorPy1 == Color.BLACK) {
				colorPy1 = JColorChooser.showDialog(null, "Seleccione un Color para el jugador " + name1 + ".",
						Color.BLUE);
				if (colorPy1 == null) {
					JOptionPane.showMessageDialog(null, "Es necesario escoger un color para el jugador " + name1 + ".",
							"Color requerido", 1);
				}
			}

			while (colorPy3 == null || colorPy3 == Color.RED || colorPy3 == Color.BLACK) {
				colorPy3 = JColorChooser.showDialog(null, "Seleccione un Color para el jugador " + name2, Color.BLUE);
				if (colorPy3 == null) {
					JOptionPane.showMessageDialog(null, "Es necesario escoger un color para el jugador " + name2,
							"Color requerido", 1);
				}
			}

			if (colorPy1.equals(colorPy3)) {
				JOptionPane.showMessageDialog(null, "Se repitio algun color, intente de nuevo.", "Color repetido", 1);
				colorPy1 = null;
				colorPy3 = null;
			} else {
				different = true;
			}
		}

		player1 = new Player(name1, colorPy1, numTiles);
		player3 = new Player(name2, colorPy3, numTiles);
		players = new Player[2];
		players[0] = player1;
		players[1] = player3;
		setGame(new POOBchis(players));
	}

	// Prepara la parte izquierda de la pantalla del juego,
	// permite visualizar dados, turnos, poderes, y botones para jugar y terminar de
	// jugar
	private void prepareleftPanel() {

		leftPanel = new JPanel();
		// leftPanel.setBackground(Color.CYAN);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));

		leftPanel.setSize(width - height, height);
		leftPanel.setBounds(0, 0, width - height - 20, 24 * height / 25 - 10);
		leftPanel.setLayout(new BorderLayout());

		//////////////////////////////
		/// Quitar el borderlayout/////
		//////////////////////////////

		// Dices //
		diceSection = new JPanel();
		diceSection.setLayout(new FlowLayout());
		dice1 = new DiceGUI(width / 10, width / 10, getGame().getDice1());
		dice2 = new DiceGUI(width / 10, width / 10, getGame().getDice2());

		diceSection.add(dice1);
		diceSection.add(dice2);
		JLabel diceTypeLabel = new JLabel("Tipo de dado: Dado Normal");
		diceTypeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
		rollDices = new JButton("Lanzar dados");

		diceSection.add(rollDices);
		diceSection.add(diceTypeLabel);
		leftPanel.add(diceSection, BorderLayout.NORTH);

		// Turn //
		turn = new JPanel();
		turn.setLayout(new GridLayout(2, 1));

		colorToShow = new JLabel("Bienvenido!!", SwingConstants.CENTER);
		colorToShow.setFont(new Font("Serif", Font.BOLD, 20));
		colorToShow.setForeground(Color.BLACK);
		colorToShow.setBackground(Color.CYAN);
		colorToShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
		colorToShow.setBackground(Color.LIGHT_GRAY);
		colorToShow.setOpaque(true);

		turn.add(colorToShow);

		leftPanel.add(turn, BorderLayout.CENTER);

		// Other Functions //
		otherFunctions = new JPanel();
		otherFunctions.setLayout(new FlowLayout());
		JButton terminarBut = new JButton("Useless Button");
		otherFunctions.add(terminarBut);
		terminar = terminarBut;
		terminar.setText("Terminar turno");
		terminar.setEnabled(false);

		// prepareUserinfo();
		// otherFunctions.add(userInfo);

		leftPanel.add(otherFunctions, BorderLayout.SOUTH);

		//////////////////////////////
		/////// Falta setBounds()//////
		//////////////////////////////

		// topFrame.add(leftPanel, 1);
		add(leftPanel);

	}

	/*
	 * private void prepareUserinfo() { userInfo = new JPanel();
	 * userInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
	 * name = new JLabel("Nombre del Jugador: " + game.currentPlayer.getName());
	 * currentTiles = new JLabel("Numero de fichas en juego: " +
	 * game.currentPlayer.getNumTiles()); results = new JLabel("Dado 1: " +
	 * dice1.getDice().getValue() + "\n Dado 2: " + dice2.getDice().getValue());
	 * userInfo.add(name); userInfo.add(currentTiles); userInfo.add(results);
	 * 
	 * }
	 */

	// Prepara la parte derecha de la pantalla del juego,
	// permite visualizar el tablero y las fichas presentes
	private void prepareRightPanel() {
		rightPanel = new JPanel();
		// rightPanel.setBackground(Color.ORANGE);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
		rightPanel.setSize(height, height);
		rightPanel.setBounds(width - height - 20, 0, height - 10, 24 * height / 25 - 10);
		rightPanel.setLayout(new GridLayout(3, 3));
		board = new BoardGUI(rightPanel, getGame(), numTiles);

		boolean different = false;

		while (!different) {

			Color colorPy2 = null;
			Color colorPy4 = null;

			while (colorPy2 == null || colorPy2 == Color.RED || colorPy2 == Color.BLACK) {
				colorPy2 = JColorChooser.showDialog(null, "Seleccione un Color para el jugador 2", Color.BLUE);
				if (colorPy2 == null) {
					JOptionPane.showMessageDialog(null, "Es necesario escoger un color para el jugador 2.",
							"Color requerido", 1);
				}
			}

			while (colorPy4 == null || colorPy4 == Color.RED || colorPy4 == Color.BLACK) {
				colorPy4 = JColorChooser.showDialog(null, "Seleccione un Color para el jugador 4", Color.BLUE);
				if (colorPy4 == null) {
					JOptionPane.showMessageDialog(null, "Es necesario escoger un color para el jugador 4.",
							"Color requerido", 1);
				}
			}

			if (colorPy1.equals(colorPy2) || colorPy1.equals(colorPy3) || colorPy1.equals(colorPy4)
					|| colorPy2.equals(colorPy3) || colorPy2.equals(colorPy4) || colorPy3.equals(colorPy4)) {
				different = false;
				JOptionPane.showMessageDialog(null, "Se repitio algun color, intente de nuevo.", "Color repetido", 1);
			} else {
				different = true;
			}
			if (different) {
				board.prepareBoard(colorPy1, colorPy2, colorPy3, colorPy4);
				// topFrame.add(rightPanel);
				add(rightPanel);
			}
		}

		// colorToShow.setBackground(getGame().getCurrentPlayer().getColor());

		for (Player p : POOBchis.players) {
			for (Tile t : p.getTiles()) {
				t.setEnabled(false);
			}
		}
	}

	// Prepara la zona donde se encuentra toda la informacion con respecto a los jugadores actuales en la partida
	private void preparePlayerZone() {
		playerInfo = new JPanel();
		playerInfo.setLayout(new GridLayout(2, 1));
		infoPlayer1 = new JPanel();
		infoPlayer3 = new JPanel();
		infoPlayer1.setLayout(new GridLayout(4, 1));
		infoPlayer3.setLayout(new GridLayout(4, 1));
		infoPlayer1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
		infoPlayer3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
		infoPlayer1.setBackground(player1.getColor().brighter());
		infoPlayer3.setBackground(player3.getColor().brighter());

		JLabel namePy1 = new JLabel("Nombre: " + player1.getName(), SwingConstants.CENTER);
		namePy1.setFont(new Font("Serif", Font.BOLD, 20));
		namePy1.setForeground(Color.BLACK);

		JLabel namePy3 = new JLabel("Nombre: " + player3.getName(), SwingConstants.CENTER);
		namePy3.setFont(new Font("Serif", Font.BOLD, 20));
		namePy3.setForeground(Color.BLACK);

		winTilesPy1 = new JLabel("Fichas Coronadas: " + player1.getLastBox().getTilesOver(), SwingConstants.CENTER);
		winTilesPy1.setFont(new Font("Serif", Font.BOLD, 20));
		winTilesPy1.setForeground(Color.BLACK);

		winTilesPy3 = new JLabel("Fichas Coronadas: " + player3.getLastBox().getTilesOver(), SwingConstants.CENTER);
		winTilesPy3.setFont(new Font("Serif", Font.BOLD, 20));
		winTilesPy3.setForeground(Color.BLACK);

		deadTilesPy1 = new JLabel("Fichas en la Carcel: " + player1.getHouse().getTilesOver(), SwingConstants.CENTER);
		deadTilesPy1.setFont(new Font("Serif", Font.BOLD, 20));
		deadTilesPy1.setForeground(Color.BLACK);

		deadTilesPy3 = new JLabel("Fichas en la Carcel: " + player3.getHouse().getTilesOver(), SwingConstants.CENTER);
		deadTilesPy3.setFont(new Font("Serif", Font.BOLD, 20));
		deadTilesPy3.setForeground(Color.BLACK);

		boxesToWinPy1 = new JLabel("Casillas para Ganar: " + (64 + 7) * 4, SwingConstants.CENTER);
		boxesToWinPy1.setFont(new Font("Serif", Font.BOLD, 20));
		boxesToWinPy1.setForeground(Color.BLACK);

		boxesToWinPy3 = new JLabel("Casillas para Ganar: " + (64 + 7) * 4, SwingConstants.CENTER);
		boxesToWinPy3.setFont(new Font("Serif", Font.BOLD, 20));
		boxesToWinPy3.setForeground(Color.BLACK);

		infoPlayer1.add(namePy1);
		infoPlayer1.add(winTilesPy1);
		infoPlayer1.add(deadTilesPy1);
		infoPlayer1.add(boxesToWinPy1);

		infoPlayer3.add(namePy3);
		infoPlayer3.add(winTilesPy3);
		infoPlayer3.add(deadTilesPy3);
		infoPlayer3.add(boxesToWinPy3);

		playerInfo.add(infoPlayer1);
		playerInfo.add(infoPlayer3);

		turn.add(playerInfo);
	}

	// Prepara las casillas de comodines en el tablero del juego
	private void prepareJokers() {
		String[] selectedJokers = askJokerTypes();
		getGame().spreadJokers(selectedJokers);
		paintJokers(getGame());
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////// ACTIONS//ACTIONS///ACTIONS////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Prepara las acciones correspondientes a presionar botones en
	// la interface
	private void prepareActions() {
		prepareActionsDice();
	}

	// Prepara las acciones correspondientes para el lanzamiento de dados, junto
	// con el boton de terminar turno
	private void prepareActionsDice() {
		rollDices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int firstValue = dice1.roll();
				int secondValue = dice2.roll();
				rollDices.setEnabled(false);
				getGame().getCurrentPlayer().availableMoves(firstValue, secondValue);
				for (Player p : POOBchis.players) {
					for (int i = 0; i < p.getNumTiles(); i++) {
						if (p.getTiles()[i].getType() == "Advantageous") {
							Advantageous a = (Advantageous) p.getTiles()[i];
							if (a.getTurns() == 0 && !a.isInHouse()) {
								try {
									p.moveTile(a, 3);
								} catch (FullCasException | BlockException | OutRangeException e1) {
									Log.record(e1);
								}
							}
						}
					}
				}
				if (firstValue == secondValue) {
					rollDices.setEnabled(false);
					getGame().getCurrentPlayer().again = true;
				} else {
					getGame().getCurrentPlayer().setInHouse();
					getGame().getCurrentPlayer().again = false;
				}
				for (Tile t : getGame().getCurrentPlayer().getTiles()) {
					if (getGame().getCurrentPlayer().getHouse().isFull()) {
						t.setEnabled(true);
					} else {
						if (t.isInHouse()) {
							t.setEnabled(false);
						} else {
							t.setEnabled(true);
						}
						/*
						 * if (game.currentPlayer.again == true) { rollDices.setEnabled(true);
						 * terminar.setEnabled(false); }
						 */
					}
				}
			}
		});

		terminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				getGame().change();
				colorToShow.setBackground(getGame().getCurrentPlayer().getColor());
				colorToShow.setText("Turno de: " + getGame().getCurrentPlayer().getName());
				rollDices.setEnabled(true);
				terminar.setEnabled(false);

				updatePlayerGUI();

			}
		});
	}

	// Actualiza el estado de las estadísticas de los jugadores
	private void updatePlayerGUI() {
		int toStillMove1 = 0;
		int counter1 = 0;
		int[] boxIndexes1 = new int[4];
		for (Tile tile : player1.getTiles()) {
			Box[] rest1 = player1.getMovementMatrix();
			for (int i = 0; i < rest1.length; i++) {
				if (rest1[i].equals(tile.myBox)) {
					boxIndexes1[counter1] = i;
					counter1++;
				}
			}
		}
		for (int i = 0; i < boxIndexes1.length; i++) {
			toStillMove1 += player1.getMovementMatrix().length - boxIndexes1[i];
		}

		int toStillMove2 = 0;
		int counter2 = 0;
		int[] boxIndexes2 = new int[4];
		for (Tile tile : player3.getTiles()) {
			Box[] rest2 = player3.getMovementMatrix();
			for (int i = 0; i < rest2.length; i++) {
				if (rest2[i].equals(tile.myBox)) {
					boxIndexes2[counter2] = i;
					counter2++;
				}
			}
		}
		for (int i = 0; i < boxIndexes2.length; i++) {
			toStillMove2 += player3.getMovementMatrix().length - boxIndexes2[i];
		}

		winTilesPy1.setText("Fichas Coronadas: " + player1.getLastBox().getTilesOver());
		deadTilesPy1.setText("Fichas en la Carcel: " + player1.getHouse().getTilesOver());
		boxesToWinPy1.setText("Casillas para Ganar: " + toStillMove1);
		winTilesPy3.setText("Fichas Coronadas: " + player3.getLastBox().getTilesOver());
		deadTilesPy3.setText("Fichas en la Carcel: " + player3.getHouse().getTilesOver());
		boxesToWinPy3.setText("Casillas para Ganar: " + toStillMove2);
	}

	/**
	 * Pinta cada casilla con joker del tablero
	 * 
	 * @param game juego al que le pertenece el tablero
	 */
	public void paintJokers(POOBchis game) {
		for (Box b : game.getJokers()) {
			if (!b.equals(null)) {
				b.getInstance().setBackground(Color.RED);
			}
		}
	}

	// Pregunta por los tipos de joker a los jugadores
	private String[] askJokerTypes() {
		// Pregunta por tipos de fichas
		String[] jokerTypes = { "ThrowHome", /* "DropHome", */ "AdvanceFive" };
		String[] selectedTypes = new String[4];
		for (int i = 1; i < 5; i++) {
			int index = JOptionPane.showOptionDialog(null, "Selecione el tipo del comodin " + i + " para el juego.",
					"Tipo para el comodin " + i, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					jokerTypes, jokerTypes[0]);
			selectedTypes[i - 1] = jokerTypes[index];
		}
		return selectedTypes;
	}

	/**
	 * Retorna el juego POOBchis ligado a la interfaz de POOBchisGUI.
	 * 
	 * @return game Instancia de POOBchis ligada a POOBchisGUI.
	 */
	public static POOBchis getGame() {
		return game;
	}

	/**
	 * Asigna a la interfaz Gráfica POOBchisGUI una nueva instancia de POOBchis.
	 * 
	 * @param game Nueva instancia de POOBchis a asignar a POOBchisGUI.
	 */
	public static void setGame(POOBchis game) {
		POOBchisGUI.game = game;
	}

	/**
	 * Retorna la instancia del primer dado que se visualiza en la pantalla
	 * 
	 * @return dice1 La instancia del primer dado que se visualiza en la pantalla
	 *         del juego.
	 */
	public DiceGUI getVisualDice1() {
		return dice1;
	}

	/**
	 * Retorna la instancia del segundo dado que se visualiza en la pantalla
	 * 
	 * @return dice1 La instancia del segundo dado que se visualiza en la pantalla
	 *         del juego.
	 */
	public DiceGUI getVisualDice2() {
		return dice2;
	}
}
