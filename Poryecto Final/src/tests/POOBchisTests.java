package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import javax.swing.JButton;
//import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.Box;
import domain.Dice;
import domain.House;
import domain.POOBchis;
import domain.Player;
import exceptions.BlockException;
import exceptions.FullCasException;
import exceptions.OutRangeException;
//import presentation.BoardGUI;
import presentation.POOBchisGUI;
import tiles.Tile;

class POOBchisTests {
	
	private static POOBchisGUI total;
	private static Player player1;
	private static Player player2;
	private static Player player3;
	private static Player[] players;
	private static POOBchis testGame;
	private static POOBchis testGame2;
	private static POOBchis testGame3;
	private static House box1;
	private static House box2;
	private static Dice dice1;
	private static Dice dice2;
	//private static BoardGUI boardG;
	
	
	@BeforeAll
	static void prepareTest() {
		player1 = new Player("testMan", Color.CYAN, 4);
		player2 = new Player("testWoman", Color.GREEN, 4);
		players = new Player[2];
		players[0] = player1;
		players[1] = player2;
		//JPanel rightPanel = new JPanel();
		testGame = new POOBchis(players);
		//boardG = new BoardGUI(rightPanel, testGame, 4);
		box1 = testGame.addHouse(501, null, player1, true);
		box2 = testGame.addHouse(503, null, player2, true);
		player1.assignHouse(box1);
		player2.assignHouse(box2);
		testGame.assignMovementMatrix(players, 1);
		testGame.assignMovementMatrix(players, 3);
		for (int i = 0;i<4;i++) {
			JButton testButton = new JButton();
			player1.addTile(Color.CYAN, i, testButton, box1, "Normal");
			player2.addTile(Color.GREEN, i, testButton, box2, "Jumper");
		}
		
		total = new POOBchisGUI();
		testGame2 = POOBchisGUI.getGame();
		
		
		
	}
	
	@Test
	/**
	 * Test que verifica que se pueda crear jugadores para el juego POOBchis
	 */
	void shouldCreatePlayers() {
		assertEquals(player1.getName(), "testMan");
		assertEquals(player1.getColor(), Color.CYAN);
		
	}
	
	@Test
	/**
	 * Test que verifica que los usuarios puedan escoger sus datos de identificacion (nombre y color)
	 */
	void shouldAllowNameAndColorPlayers() {
		player3 = testGame2.getCurrentPlayer();
		assertNotEquals(null,player3.getName());
		assertNotEquals(null,player3.getColor());
		testGame2.change();
		player3 = testGame2.getCurrentPlayer();
		assertNotEquals(null,player3.getName());
		assertNotEquals(null,player3.getColor());
	}
	
	@Test
	/**
	 * Test que verifica que se pueda crear fichas de tipo Jumper para el juego POOBchis
	 */
	void shouldCreateNormalTiles() {
		for (Tile tile : player1.getTiles()) {
			assertEquals(tile.getType(), "Normal");
		}
		
	}
	
	@Test
	/**
	 * Test que verifica que se pueda crear fichas de tipo Jumper para el juego POOBchis
	 */
	void shouldCreateJumperTiles() {
		for (Tile tile : player2.getTiles()) {
			assertEquals(tile.getType(), "Jumper");
		}
		
	}
	
	@Test
	/**
	 * Test que verifica que todas las fichas de los jugadores inicialmente estan en sus respectivas bases
	 */
	void shouldHaveTilesOnHouse() {
		for (Tile tile : player1.getTiles()) {
			assertEquals(tile.getBox(), player1.getHouse());
		}
	}
	
	@Test
	/**
	 * Test que verifica que un jugador con todas sus fichas en la carce, al sacar pares, sacará 2 de sus fichas al tablero
	 */
	void shouldFreeTwoTiles() {
		player3 = testGame2.getCurrentPlayer();
		player3.availableMoves(1, 1);
		player3.outWhenFull();
		assertEquals(2, player3.getHouse().getTilesOver());
	}
	
	@Test
	/**
	 * Test que verifica que un jugador con al menos una ficha en la carcel, al sacar pares, sacará esa ficha de la carcel y correra un dado con otra ficha
	 */
	void shouldFreeOneTiles() {
		assertEquals(2, player3.getHouse().getTilesOver());
		assertEquals(2, player3.getMovementMatrix()[1].getTilesOver());
		player3.availableMoves(1, 1);
		try {
			player3.moveTile(player3.getMovementMatrix()[1].getTiles().get(0), 1);
			player3.moveTile(player3.getHouse().getTiles().get(0), 1);
		} catch (FullCasException | BlockException | OutRangeException e) {
		}
		assertEquals(2, player3.getMovementMatrix()[1].getTilesOver());
		assertEquals(1, player3.getMovementMatrix()[2].getTilesOver());
	}
	
	@Test
	/**
	 * Test que verifica que si una ficha intenta pasar por un bloqueo, no deberia moverse si es Normal, y debería pasar si es de tipo Jumper
	 */
	void shouldNotPassBlock() {
		assertEquals(1, player3.getHouse().getTilesOver());
		assertEquals(2, player3.getMovementMatrix()[1].getTilesOver());
		player3.availableMoves(1, 1);
		String tileType = player3.getHouse().getTiles().get(0).getType();
		String fail;
		try {
			player3.moveTile(player3.getHouse().getTiles().get(0), 2);
			fail = "Pasa el bloqueo";
		} catch (BlockException e) {
			fail = "No pasa el bloqueo";
		} catch (FullCasException | OutRangeException e) {
			fail = "Imposible";
		}
		if (tileType != "Jumper") {
			assertEquals("No pasa el bloqueo", fail);
		}
	}
	
	@Test
	/**
	 * Test que verifica que si una ficha intenta caer en una casilla con 2 ficha, arrojará error
	 */
	void shouldNotArriveToFullBox() {
		player3.availableMoves(1, 1);
		for (Tile tile : player3.getTiles()) {
			player3.moveToHouse(tile);
		}
		String fail;
		try {
			player3.outWhenFull();
			player3.moveTile(player3.getHouse().getTiles().get(0), 1);
			fail = "Pudo entrar";
		} catch (FullCasException e) {
			fail = "No pudo entrar";
		} catch (BlockException | OutRangeException e) {
			fail = "Imposible";
		}
		assertEquals("No pudo entrar", fail);
	}
	
	@Test
	/**
	 * Test que verifica que se puedan escoger distintos tipos de fichas para la partida
	 */
	void shouldAllowPickDifferentTiles() {
		assertNotEquals(player1.getTiles()[0].getType(), player2.getTiles()[0].getType());
	}
	
	@Test
	/**
	 * Test que verifica que se puedan escoger distintos tipos de comodines para la partida
	 */
	void shouldAllowPickDifferentJokerBoxes() {
		player3 = testGame2.getCurrentPlayer();
		testGame2.getJokers().add(player3.getMovementMatrix()[4]);
		testGame2.getJokers().add(player3.getMovementMatrix()[5]);
		testGame2.addThrowHome(player3.getMovementMatrix()[4]);
		testGame2.addAdvanceFive(player3.getMovementMatrix()[5]);
		assertNotEquals(testGame2.getJokers().get(4).getJoker(), testGame2.getJokers().get(5).getJoker());
		assertNotEquals(player3.getMovementMatrix()[4].getJoker(), player3.getMovementMatrix()[5].getJoker());
	}
	
	@Test
	/**
	 * Test que verifica que se puedan escoger distintos tipos de comodines para la partida
	 */
	void shouldShowCorrectDiceFaces() {
		player3 = testGame2.getCurrentPlayer();
		dice1 = testGame2.getDice1();
		dice2 = testGame2.getDice2();
		assertEquals(1, dice1.getValue());
		assertEquals(1, dice2.getValue());
		total.getVisualDice1();
		total.getVisualDice2();
	}
	
	@Test
	/**
	 * Test que verifica que se puedan escoger distintos tipos de comodines para la partida
	 */
	void shouldLetAPlayerWin() {
		total = new POOBchisGUI();
		testGame3 = POOBchisGUI.getGame();
		player3 = testGame3.getCurrentPlayer();
		Box lastBox = player3.getLastBox();
		String fail = "No gana el juego";
		for (Tile tile:player3.getTiles()) {
			tile.changeBox(lastBox);
		}
		for (Tile tile:player3.getTiles()) {
			try {
				player3.moveTile(tile, 1);
			} catch (FullCasException | BlockException | OutRangeException e) {
				fail = "Gana el juego";
			}
		}
		assertEquals("Gana el juego", fail);
		assertEquals(player3.isWinner(), false);
	}
	
}
