package states;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

import highscore.Manager;

/**
 * This state represents the menu of the Game The main responsibility of this
 * class is to allow the user to swap state to the PlayState
 */
public class MenuState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage menuImg;
	private Manager highscore = new Manager();
	private int x;

	public MenuState(GameModel model) {
		super(model);
		this.model = model;
		menuImg = loadImage("menuscreen1.jpg");
	}

	@Override
	public void draw(Graphics g) {
		Image tmpmenuscreen = menuImg.getScaledInstance(990, 765, Image.SCALE_SMOOTH);
		g.drawImage(tmpmenuscreen, 0, 0, null);
		
		g.setColor(Color.YELLOW);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		g.drawString("PROJEKTOTRON 2084", this.x, 350);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("PRESS TO PLAY", 990 / 2 - 100, 450);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("PRESS ESC TO QUIT", 800, 755);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("HIGHSCORE: STAGE " + highscore.getHighscoreString(), 5, 755);
	}

	@Override
	public void keyPressed(KeyEvent key) {

		if (key.getKeyCode() == KeyEvent.VK_ENTER)
			try {
				model.switchState(new PlayState(model, 1, 5));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	@Override
	public void mousePressed(MouseEvent key) {

		try {
			model.switchState(new PlayState(model, 1, 5));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update() {
		x += 1;
		if (x > 970)
			x = -600;
	}

	@Override
	protected void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

	}
}
