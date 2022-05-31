package states;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import highscore.Manager;

/**
 * 
 * En skärm som visar highscore och uppnått poäng när spelaren dör.
 *
 */

public class LoseState extends GameState {

	private static final long serialVersionUID = -7486888232419994470L;
	private BufferedImage loseScreenImg;
	private Manager highscore = new Manager();
	private int level;

	public LoseState(GameModel model, int level) {
		super(model);
		this.level = level;
		loseScreenImg = loadImage("nextlevelscreen.jpg"); // TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		Image tmpLoseScreen = loseScreenImg.getScaledInstance(990, 770, Image.SCALE_SMOOTH);
		g.drawImage(tmpLoseScreen, 0, 0, null);
		g.setColor(Color.YELLOW);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		g.drawString("YOU HAVE DIED", 990 / 4 - 140, 380);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("YOU'VE REACHED STAGE " + this.level, 990 / 4 + 55, 500);
		g.drawString("THE HIGHSCORE IS STAGE " + highscore.getHighscoreString(), 990 / 4 + 40, 580);
	}

//	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			model.switchState(new MenuState(model));
		}
	}

	@Override
	public void mousePressed(MouseEvent key) {
		model.switchState(new MenuState(model));

	}

	@Override
	protected void keyReleased(KeyEvent key) {
	}
}
