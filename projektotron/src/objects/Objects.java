package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import states.GameModel;
import states.GameState;

/**
 *  Denna klass är superklass till alla objekt och 
 *  tillhandahåller vart objekten befinner sig och rör sig i spelet.
 *  
 */

public abstract class Objects extends GameState {

	private static final long serialVersionUID = 363057172121041502L;
	private double velX;
	private double velY;
	private Point position;
	private BufferedImage objectImg;

	
	public Objects(GameModel model, int x, int y) {
		super(model);
		this.position = new Point(x, y);
	}
	
	public class Point {
		private double x;
		private double y;

		public Point(double d, double e) {
			this.x = d;
			this.y = e;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

	}

// Abstrakta metoder & Getters setters
	public abstract Rectangle hitBox();

	public abstract void collision(Objects object);

	@Override
	public void update() {
	}

	public BufferedImage loadImage(String imgPath) {
		try {
			return ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delegate(Graphics g) {
		if (g != null)
			draw(g);
		else
			update();
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void keyPressed(KeyEvent key) {
	}

	@Override
	protected void keyReleased(KeyEvent key) {
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public BufferedImage getObjectImg() {
		return objectImg;
	}

	public void setObjectImg(BufferedImage objectImg) {
		this.objectImg = objectImg;
	}

}
