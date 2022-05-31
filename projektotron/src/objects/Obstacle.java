package objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import states.GameModel;

	/**
	 *  Alla hinder som är med i spelet.
	 */

public class Obstacle extends Objects {

	private static final long serialVersionUID = 323944096705912932L;
	static BufferedImage obstacleImg;

	public Obstacle(GameModel model, int x, int y) {
		super(model, x, y);
		obstacleImg = loadImage("PixelArt (1).png");
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 50, 50);

	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(obstacleImg, (int) getPosition().getX(), (int) getPosition().getY(), 50, 50, null);
	}

	public void collision(Objects object) {
	}
}
