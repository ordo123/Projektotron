package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Skärm som visar uppnåd nivå när spelaren klarar en nivå 
 * och har huvudsyftet att ta spelaren vidare till nästa nivå.
 * 
 */

public class NextLevelState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage nextLevelImg;
	private int level;
	private int health;
	
	public NextLevelState(GameModel model, int level, int health) {
		super(model);
		this.model = model;
		nextLevelImg = loadImage("losescreen_skull.jpg");
		JLabel startscreen = new JLabel();
		startscreen.setIcon(new ImageIcon(nextLevelImg));
		this.add(startscreen);
		this.level = level;
		this.health = health;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		Image tmpNewLevelScreen = nextLevelImg.getScaledInstance(990, 765, Image.SCALE_SMOOTH);
		g.drawImage(tmpNewLevelScreen, 0, 0, null);
		g.setColor(Color.YELLOW);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
		g.drawString("CONGRATULATIONS", 990 / 4 - 140, 140);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("YOU HAVE REACHED STAGE " + this.level, 990 / 5 + 90, 620);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("PRESS TO CONTINUE", 990 / 2 - 85, 670);

	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ENTER)
			try {
				model.switchState(new PlayState(model, this.level, this.health));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void mousePressed(MouseEvent key) {

		try {
			model.switchState(new PlayState(model, this.level, this.health));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

	}

}