package jokerBoxes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import domain.Box;
import tiles.Tile;

/**
 * 
 * Clase correspondiente al comodin con requerimiento: La ficha que obtenga este
 * comodín se irá directamente a la cárcel.
 * 
 * @author Rocha y Rojas
 *
 */
public class ThrowHome extends JokerBox {
	/**
	 * Costructor de la clase throwHome
	 * 
	 * @param selectedBox referencia de la casilla en la que se aplica el comodin
	 */
	public ThrowHome(Box selectedBox) {
		super(selectedBox);
		selectedBox.getInstance().add(new JLabel("T.H"));
		// showOption = false;
	}

	/**
	 * Toda ficha que caiga este comodín se irá directamente a la cárcel.
	 */
	@Override
	public void use() {
		if (available) {
			Tile t = selectedBox.getTiles().get(0);
			t.getOwner().moveToHouse(t);
			selectedBox.getInstance().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}
}