package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;

import exceptions.POOBchisException;
import jokerBoxes.AdvanceFive;
import jokerBoxes.DropHome;
import jokerBoxes.ThrowHome;
import main.Main;
import tiles.Advantageous;
import tiles.Tile;

/**
 * Clase POOBchis. Juego POOBchis y todos los metodos para poder jugar (2 - 4
 * jugadores).
 * 
 * @author Rocha y Rojas
 *
 */
public class POOBchis {

	// Jugadores en el juego
	public static Player[] players;

	// Dados del juego
	public Dice dice1;
	public Dice dice2;

	// Casillas del juego
	public static HashMap<Integer, Box> board = new HashMap<Integer, Box>();

	// Jugador de la ronda actual
	private Player currentPlayer;
	private int index = 0;
	
	// Comodines
    private Random random = new Random();
    private ArrayList<Box> arrayJokers = new ArrayList<Box>();
    
    // Pares consecutivos
    //private int consecutivePairs = 0;
    
    
    //Matrices de movimiento para los jugadores, dependiendo de su numero identificador
    private int[] orderMatrix4 = { 
			48, 47, 46, 
			49, 401, 45, 
			50, 402, 44, 
			51, 403, 43, 
			52, 404, 42, 
			53, 405, 41, 
			54, 406, 40 
			};
	
	private int[] orderMatrix1 = { 
			63, 62, 61, 60, 59, 58, 57, 
			64, 101, 102, 103, 104, 105, 106,
			65, 66, 67, 68, 1, 2, 3 
			};
	
	private int[][] orderMatrix5 = { 
			{55, 56},
			{39, 38},
			{4, 5},
			{22, 21}
			};
	
	private int[] orderMatrix3 = { 
			37, 36, 35, 34, 33, 32, 31,
			306, 305 , 304, 303, 302, 301, 30,
			23, 24, 25, 26, 27, 28, 29
			};
	
	private int[] orderMatrix2 = { 
			6, 206, 20,
			7, 205, 19,
			8, 204, 18,
			9, 203, 17,
			10, 202, 16,
			11, 201, 15,
			12, 13, 14
			};

	/**
	 * Constructor de la clase POOBchis. Juego POOBchis y todos los metodos para
	 * poder jugar (2 - 4 jugadores).
	 * 
	 * @param players Jugadores en la partida.
	 */
	public POOBchis(Player[] players) {
		//Dados Normales
		dice1 = new Dice(1);
		dice2 = new Dice(1);
		POOBchis.players = players;
		currentPlayer = players[0];
	}

	/**
	 * Annadir casillas al tablero del juego POOBchis
	 * 
	 * @param number   Numero de la casilla.
	 * @param instance Intancia de la posicion en el tablero de la casilla.
	 * @param safe     Seguridad de la casilla, True si la casilla es segura, False
	 *                 si no lo es.
	 */
	public void addBox(int number, JPanel instance, boolean safe) {
		Box box = new Box(number, instance, safe);
		board.put(number, box);

	}

	/**
	 * Annadir bases / casas / carceles al juego
	 * 
	 * @param number   Numero de la base.
	 * @param instance Intancia de la posicion en el tablero de la base.
	 * @param safe     Seguridad de la base, True si la casilla es segura (siempre
	 *                 es True).
	 * @return
	 */
	public House addHouse(int number, JPanel instance, Player player, boolean safe) {
		House box = new House(number, instance, player, safe);
		board.put(number, box);
		return box;
	}

	/**
	 * Asigna una matriz de movimiento a un jugador especifico en el juego.
	 * 
	 * @param players   Jugador al que asignar la matriz de movimiento.
	 * @param numPlayer Numero del jugador al que asignar la matriz de movimiento.
	 */
	public void assignMovementMatrix(Player[] players, int numPlayer) {
		Box[] movesPlayer = new Box[64 + 8];
		movesPlayer[0] = board.get(500 + numPlayer);
		for (int i = 1; i < 65; i++) {
			if ((i + (numPlayer - 1) * 17) < 69) {
				movesPlayer[i] = board.get((i + (numPlayer - 1) * 17) % 69);
			} else {
				movesPlayer[i] = board.get((i + (numPlayer - 1) * 17) % 68);
			}

		}
		for (int i = 0; i < 6; i++) {
			movesPlayer[i + 65] = board.get(i + 101 + 100 * (numPlayer - 1));
		}
		movesPlayer[64 + 7] = board.get(107 + 100 * (numPlayer - 1));

		/*
		 * for (int i = 0; i < 64 + 8; i++) {
		 * System.out.println(movesPlayer[i].getNumber()); }
		 */

		// players[numPlayer - 1].setMovementMatrix(movesPlayer);

		if (numPlayer == 1) {
			players[0].setMovementMatrix(movesPlayer);
		} /*else if (numPlayer == 2) {
			players[1].setMovementMatrix(movesPlayer);
		} */else if (numPlayer == 3) {
			players[1/*2*/].setMovementMatrix(movesPlayer);
		} /*else if (numPlayer == 3) {
			players[3].setMovementMatrix(movesPlayer);
		}*/

	}

	/**
	 * Cambia de turno, cambia el jugador actual por el siguiente jugador (por la
	 * derecha).
	 */
	public void change() {
		index++;
		currentPlayer = players[(index) % players.length];
		//Repintar al cambiar el turno, revisar las fichas
		for (Player p: players) {
            for(int i=0;i<p.getNumTiles();i++) {
                if (p.getTiles()[i].getType()=="Advantageous") {
                    Advantageous a =(Advantageous)p.getTiles()[i];
                    a.increaseTurn();
                }
            }
        }
	}

	/**
	 * Retorna el jugador actual, el jugador al que corresponde el turno actual.
	 * 
	 * @return currentPlayer Jugador con el actual turno.
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Retorna el tablero del juego actual.
	 * @return board Tablero del juego actual.
	 */
	public HashMap<Integer, Box> getBoard() {
		return board;
	}
	
	/**
     * Metodo para aplicar el comodin DropHome a una casilla en el tablero
     * @param jokerDropHome Instancia del comodin a aplicar.
     * @param selected Box en donde se encuentra el comodin.
     * @param target Ficha que sera objetivo del comodin.
     * @return int Indice del la casilla con comodin.
     */
    public int applyDropHome(DropHome jokerDropHome, Box selected,Tile target) {
        if(jokerDropHome.available && board.get(index).getTilesOver() > 0) {
            jokerDropHome.pickTarget(target);
            jokerDropHome.use();
            jokerDropHome.available = false;
        }
        return index;
    }
    
    /**
     * Metodo que escoge aleatoriamente las cajas donde estaran los jokers
     */
    public void spreadJokers(String[] selectedJokers) {
    	int i = random.nextInt(68) + 1;
        int j = random.nextInt(68) + 1;
        int k = random.nextInt(68) + 1;
        int l = random.nextInt(68) + 1;
        while (i == j) {
            j = random.nextInt(68) + 1;
        }
        while (i == k || k == j) {
            k = random.nextInt(68) + 1;
        }
        while (i == l || l == k) {
            l = random.nextInt(68) + 1;
        }
        
        int[] randomBoxes = {i, j, k, l};
        
        for (int m = 0; m < 4; m++) {
        	if (selectedJokers[m] == "ThrowHome") {
    			Box selectedBox = board.get(randomBoxes[m]);
    	        arrayJokers.add(selectedBox);
    	        addThrowHome(selectedBox);
    		} else if (selectedJokers[m] == "AdvanceFive") {
    			Box selectedBox1 = board.get(randomBoxes[m]);
    	        arrayJokers.add(selectedBox1);
    	        addAdvanceFive(selectedBox1);
    		}	
        }
    }
    
    /**
     * Agrega una instacia de ThrowHome a una casilla
     * @param selectedBox casilla a la que se le agregara el comodin
     */
    public void addThrowHome(Box selectedBox) {
        selectedBox.setJoker(new ThrowHome(selectedBox));
    }
 
    /**
     * Agrega una instacia de AdvanceFive a una casilla
     * @param selectedBox casilla a la que se le agregara el comodin
     */
    public void addAdvanceFive(Box selectedBox) {
        selectedBox.setJoker(new AdvanceFive(selectedBox));
    }
    
    /**
     * Retorna el primer dado del tablero, un dado Normal.
     * @return dice1 El primer dado del tablero, un dado Normal.
     */
    public Dice getDice1() {
    	return dice1;
    }
    
    /**
     * Retorna el segundo dado del tablero, un dado Normal.
     * @return dice2 El primer dado del tablero, un dado Normal.
     */
    public Dice getDice2() {
    	return dice2;
    }
    
    /**
     * Retorna un array con todos los jokers presentes en el juego.
     * @return arrayJokers Todos los jokers presentes en el juego.
     */
    public ArrayList<Box> getJokers(){
    	return arrayJokers;
    }
    
    //Getters para las matrices de movimiento
    /**
     * Retorna la matriz de movimiento correspondiente al jugador ubicado en la esquina inferior izquierda del tablero.
     * @return orderMatrix1 Matriz de movimiento correspondiente al jugador ubicado en la esquina inferior izquierda del tablero.
     */
    public int[] getMatrix1(){
    	return orderMatrix1;
    }
    
    /**
     * Retorna la matriz de movimiento correspondiente al jugador ubicado en la esquina inferior derecha del tablero.
     * @return orderMatrix2 Matriz de movimiento correspondiente al jugador ubicado en la esquina inferior derecha del tablero.
     */
    public int[] getMatrix2(){
    	return orderMatrix2;
    }
    
    /**
     * Retorna la matriz de movimiento correspondiente al jugador ubicado en la esquina superior derecha del tablero.
     * @return orderMatrix3 Matriz de movimiento correspondiente al jugador ubicado en la esquina superior derecha del tablero.
     */
    public int[] getMatrix3(){
    	return orderMatrix3;
    }
    
    /**
     * Retorna la matriz de movimiento correspondiente al jugador ubicado en la esquina superior izquierda del tablero.
     * @return orderMatrix4 Matriz de movimiento correspondiente al jugador ubicado en la esquina superior izquierda del tablero.
     */
    public int[] getMatrix4(){
    	return orderMatrix4;
    }
    
    /**
     * Retorna la matriz de movimiento correspondiente a todos los jugadores que pasen por la zona central del tablero.
     * @return orderMatrix5 Matriz de movimiento correspondiente a todos los jugadores que pasen por la zona central del tablero.
     */
    public int[][] getMatrix5(){
    	return orderMatrix5;
    }
    
    /**
     * Llama al la clase principal del juego para indicarle que hay un ganador.
     * @param player Ganador del juego POOBchis
     */
    public static void thereIsAWinner(Player player){
    	Main.thereIsAWinner(player);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public void nuevo() throws POOBchisException {
		throw new POOBchisException(POOBchisException.msgLocal);
	}

	public void abrir() throws POOBchisException {
		throw new POOBchisException(POOBchisException.msgLocal);
	}

	public void guardar() throws POOBchisException {
		throw new POOBchisException(POOBchisException.msgLocal);
	}

	public void importar() throws POOBchisException {
		throw new POOBchisException(POOBchisException.msgLocal);
	}

	public void exportar() throws POOBchisException {
		throw new POOBchisException(POOBchisException.msgLocal);
	}
}
