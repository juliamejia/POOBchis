package jokerBoxes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import domain.Box;
import exceptions.BlockException;
import exceptions.FullCasException;
import exceptions.OutRangeException;
import tiles.Tile;

/**
 * 
 * Clase correspondiente al comodin con requerimiento: La ficha que obtenga este
 * comodín avanza 5 casillas automáticamente.
 * 
 * @author Rocha y Rojas
 *
 */
public class AdvanceFive extends JokerBox {

	/**
	 * Costructor de la clase AdvanceFive
	 * 
	 * @param selectedBox referencia de la casilla en la que se aplica el comodin
	 */
	public AdvanceFive(Box selectedBox) {
		super(selectedBox);
		selectedBox.getInstance().add(new JLabel("A.F"));
	}

	/**
	 * Toda ficha que caiga este comodín avanza 5 espacios.
	 */
	@Override
	public void use() {
		if (available) {
			Tile t = selectedBox.getTiles().get(0);
			try {
				t.getOwner().moveTile(t, 5);
				selectedBox.getInstance().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			} catch (FullCasException | BlockException | OutRangeException e) {
				e.printStackTrace();
			}
		}
	}

}
