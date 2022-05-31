package states;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class represents a state of the game.
 * These states are different views in the game,
 * which contain their own separate logic.
 *
 * Examples of states are: menu, playing,
 * paused, game over and many more
 *
 * This Class is an abstract class that defines
 * a couple of general functions that are used by
 * the state machine to delegate work to the state.
 *
 * The responsibilities of a state are roughly speaking
 * separated into three categories:
 * - Input from the user (keypresses, mouse clicks, etc.)
 * - Internal logic (move sprites, check for collision etc.)
 * - Rendering (drawing objects to the screen)
 *
 * These steps are represented by the functions:
 * - keyPressed (input step)
 * - update (logic step)
 * - draw (rendering step)
 *
 * To define a valid state, that state has to be derived
 * from this class, and needs to override the three functions
 * mentioned above.
 * @param <E>
 */

public abstract class GameState extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameModel model;

    public GameState(GameModel model) {
        this.model = model;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void keyPressed(KeyEvent key);
    
    public void mousePressed(MouseEvent key) {
    	this.mousePressed(key);
    }

	public BufferedImage loadImage(String imgPath) {
		try {
			return ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    public void activate() { }

    public void deactivate() { }

	protected abstract void keyReleased(KeyEvent key);
}
