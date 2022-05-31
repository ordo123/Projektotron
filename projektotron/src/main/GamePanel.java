package main;

import states.GameModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

/**
 * The GamePanel wraps a JPanel
 *
 * The main responsibilities of the GamePanel are: - Handle events (e.g. key
 * events and mouse events) - Supplying the game with a Graphics object for
 * painting different states
 */
public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameModel model;

	public GamePanel(final GameModel model) {
		this.model = model;

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				super.keyPressed(e);
				model.keyPressed(e);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				model.keyReleased(e);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				super.mousePressed(e);
				try {
					model.mousePressed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

	public void paintComponent(Graphics g) {
		model.draw(g);
	}
}
