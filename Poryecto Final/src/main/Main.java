package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.Player;
import gui.GameMenus;
import presentation.POOBchisGUI;

/**
 * Clase principal para el programa.
 * </p>
 * Desde aquí se dan las instrucciones para crear POOBchis y POOBchisGUI.
 * @author Rocha y Rojas
 *
 */
public class Main {

	public static final JFrame MAIN_FRAME = new JFrame();
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int width = (3 * screenSize.width) / 4;
	private static int height = (3 * screenSize.height) / 4;

	/**
	 * Método principal del programa, desde aquí se dan las instrucciones para crear POOBchis y POOBchisGUI.
	 *
	 * @param args The arguments from the console when executing the program.
	 */

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			// @Override
			public void run() {
				JFrame frame = MAIN_FRAME;
				POOBchisGUI gui = POOBchisGUI.getInstance();
				gui.setVisible(true);
				gui.setOpaque(true);
				frame.setContentPane(gui);
				frame.setVisible(true);
				// Prepara la pantalla principal del juego.
				prepareWindow(frame);

				// prepareContent(gui);

			}
		});
	}

	// Prepara las acciones a realizar por la ventana en la que se encuentra el juego.
	private static void prepareWindow(JFrame frame) {
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setTitle("POOBchis");
		frame.setSize(width, height + 20);
		frame.setLocationRelativeTo(null);

		GameMenus menuBar = new GameMenus().getInstance();
		frame.setJMenuBar(menuBar);

		frame.setVisible(true);

		actionExit(frame);

	}

	// Pregunta antes de cerrar la ventana.
	private static void toCloseWindow(JFrame frame) {
		String[] opciones = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(null, "Esta seguro de que desea cerrar POOBchis?",
				"Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
				"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else {
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	// Accion de cerrar la ventana en la que se encuentra el juego.
	private static void actionExit(JFrame frame) {
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				toCloseWindow(frame);
			}
		});
	}

	public static void thereIsAWinner(Player player) {
		 JOptionPane.showMessageDialog(MAIN_FRAME, "Felicidades Jugador " + player.getName() + "!! Eres el/la ganador/ganadora!!!", "Felicidades al Ganador de POOBchis", 0);		
	}
}
