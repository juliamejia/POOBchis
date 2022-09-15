package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import domain.POOBchis;
import exceptions.POOBchisException;
import presentation.POOBchisGUI;

/**
 * Clase GameMenus, encargada de crear un menu desplegable para una pantalla de juego para POOBchis.
 * @author Rocha y Rojas
 *
 */
public class GameMenus extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private GameMenus instance;
	private POOBchis game;
	
	/**
	 * Retorna la instancia del GameMenu creado
	 * @return instance Instancia del GameMenu creado
	 */
	public GameMenus getInstance() {
		if (instance == null) {
			instance = new GameMenus();
		}
		return instance;
	}
	
	/**
	 * Constructor de la clase GameMenus.</p>
	 * Crea una barra de menu para un JFrame con opciones como Nuevo, Abrir o Guardar el juego.
	 */
	public GameMenus() {
		this.game = POOBchisGUI.getGame();
		JMenu archivo = new JMenu("Archivo");
		JMenuItem nuevo = new JMenuItem("Nuevo");
		JMenuItem abrir = new JMenuItem("Abrir");
		//JMenuItem exportar = new JMenuItem("Exportar como");
		JMenuItem guardar = new JMenuItem("Guardar como");
		//JMenuItem importar = new JMenuItem("Importar");
		JMenuItem salir = new JMenuItem("Salir");
		/*
		configuracion = new JMenu("Configuraci\u00f3n");
		jugadores = new JMenuItem("Jugadores");
		colores = new JMenuItem("Colores");
		*/
		add(archivo);
		archivo.add(nuevo);
		archivo.add(abrir);
		archivo.add(guardar);
		//archivo.add(importar);
		//archivo.add(exportar);
		archivo.add(salir);
		/*
		menuBar.add(configuracion);
		configuracion.add(jugadores);
		configuracion.add(colores);
		*/
		prepareActionArchivo(nuevo, abrir, guardar, salir);
	}
	
	// Prepara las acciones para la persistencia del programa
	private void prepareActionArchivo(JMenuItem nuevo, JMenuItem abrir, JMenuItem guardar, JMenuItem salir) {
		nuevo.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String[] opciones = { "Crear nuevo", "Cancelar" };
				int eleccion = JOptionPane.showOptionDialog(getRootPane(), "Esta seguro de que desea cerrar POOBChis?",
						"Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						opciones, "Aceptar");
				if (eleccion == JOptionPane.YES_OPTION) {
					try {
						game.nuevo();
					} catch (POOBchisException pe) {
						JOptionPane.showMessageDialog(null, pe.msgLocal);
					}
				}
			}
		});

		abrir.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int seleccion = file.showOpenDialog(null);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File selected = file.getSelectedFile();
					selected.canRead();
					try {
						game.abrir();
					} catch (POOBchisException pe) {
						JOptionPane.showMessageDialog(null, pe.msgLocal);
					}
				}
			}
		});

		guardar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int seleccion = file.showOpenDialog(null);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File selected = file.getSelectedFile();
					selected.canRead();
					try {
						game.guardar();
					} catch (POOBchisException pe) {
						JOptionPane.showMessageDialog(null, pe.msgLocal);
					}
				}
			}
		});

		/*
		importar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int seleccion = file.showOpenDialog(null);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File selected = file.getSelectedFile();
					selected.canRead();
					try {
						game.importar();
					} catch (POOBchisException pe) {
						JOptionPane.showMessageDialog(null, pe.msgLocal);
					}
				}
			}
		});

		exportar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int seleccion = file.showOpenDialog(null);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File selected = file.getSelectedFile();
					selected.canRead();
					try {
						game.exportar();
					} catch (POOBchisException pe) {
						JOptionPane.showMessageDialog(null, pe.msgLocal);
					}
				}
			}
		});
		*/

		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
