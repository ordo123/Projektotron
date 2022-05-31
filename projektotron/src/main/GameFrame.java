package main;

import states.GameModel;
import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;
import java.awt.Dimension;

import javax.swing.*;

/**
 * This class Wraps a JFrame:
 *      A JFrame is a top level Swing container
 *      used to show a Window on the screen.
 *      The JFrame serves as the root of the
 *      containment hierarchy and contains all
 *      other Swing components that appear inside
 *      the top level container.
 *
 *      A JFrame is used for wrapping other components
 *      like buttons, bars, panels etc.
 *
 * The GameFrame is responsible for creating the Window
 * with the desired properties. For more information see Comments below.
 *
 */
public class GameFrame extends JFrame {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameFrame(String gameName, GameModel model){
    	super("PROJEKTOTRON 2084");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates the program when window closes
        this.setResizable(false);
        this.add(new GamePanel(model)); // Create a new GamePanel and add's it to the frame
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null); // Sets location of frame to mid point of screen
        this.setName(gameName);
        this.setVisible(true); // Shows the frame
    }
}
