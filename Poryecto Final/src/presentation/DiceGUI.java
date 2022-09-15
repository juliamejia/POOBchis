package presentation;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import domain.*;

/**
 * Clase DiceGUI. Permite visualizar la zona de la pantala con los dados a
 * lanzar
 * 
 * @author Rocha y Rojas
 *
 */
@SuppressWarnings("serial")
public class DiceGUI extends JLabel {

	// Imagenes de los dados con sus correspondientes valores (1-6)
	private static HashMap<Integer, String> srcDice = new HashMap<Integer, String>();

	// Instancia de los dados (dominio)
	private Dice dice;
	private Image img;

	// Ancho y alto delimitados de la pantalla
	private int width;
	private int height;

	/**
	 * Constructor de la clase DiceGUI
	 * 
	 * @param width  Ancho de la pantalla delimitada
	 * @param height Alto de la pantalla delimitada
	 * @param dice   Instancia de dado a la que asignar visualizacion
	 */
	public DiceGUI(int width, int height, Dice dice) {
		setMap();
		this.width = width;
		this.height = height;
		this.dice = dice;
		img = new ImageIcon(this.getClass().getResource("/one.png")).getImage();
		Image newImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		setIcon(new ImageIcon(newImage));
	}

	// Asigna los valores a las imagenes de las caras de los dados,
	private void setMap() {
		srcDice.put(1, "/one.png");
		srcDice.put(2, "/two.png");
		srcDice.put(3, "/three.png");
		srcDice.put(4, "/four.png");
		srcDice.put(5, "/five.png");
		srcDice.put(6, "/six.png");
	}

	/**
	 * Lanza el dado, teniedo como resultado imagenes aleatorias de sus caras
	 * 
	 * @return newValue Nueva cara del dado, representada graficamente
	 */
	public int roll() {
		int newValue = 1;
		newValue = rollNormal();
		return newValue;
	}

	/**
	 * Retorna el dado del dominio correspondiente
	 * 
	 * @return dice Dado correspondiente
	 */
	public Dice getDice() {
		return dice;
	}

	// Lanza el dado normal, devuelve el valor de una cara aleatoria.
	private int rollNormal() {
		int newValue = dice.roll();
		String image = srcDice.get(newValue);
		if (image == null) {
			image = "/one.png";
		}
		img = new ImageIcon(this.getClass().getResource(image)).getImage();
		Image newImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		this.setIcon(new ImageIcon(newImage));
		return newValue;
	}

	/**
	 * Retorna la imagen correspondiente a la cara actual del dado, que se puede
	 * visualizar en la pantalla del juego
	 * 
	 * @return
	 */
	public Image getImage() {
		return img;
	}
}
