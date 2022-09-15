package domain;

import java.util.Random;

/**
 * Clase Dice. Crea dados y permite lanzarlos para conseguir un valor aleatorio
 * (1-6)
 * 
 * @author Rocha y Rojas
 *
 */
public class Dice {

	// Valor de la cara actual del dado
	protected int value;
	private Random random;
	private final String type = "Normal";

	/**
	 * Constructor de la Clase Dice. Crea dados y permite lanzarlos para conseguir
	 * un valor aleatorio (1-6)
	 * 
	 * @param value Valor inicial de la cara inicial del dado.
	 */
	public Dice(int value) {
		this.value = value;
		this.random = new Random();
	}

	/**
	 * Permita lanzar un dado y conseguir un valor aleatorio (1-6)
	 * 
	 * @return value Valor de la cara del dado tras ser lanzado.
	 */
	public int roll() {
		value = random.nextInt(6) + 1;
		return value;
	}

	/**
	 * Retorna el valor del dado actualmente.
	 * 
	 * @return value Valor del dado actualmente
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Retorna el tipo de dado correspondiente al dado actual.
	 * 
	 * @return type tipo del dado seleccionado actual.
	 */
	public String getType() {
		return type;
	}

}
