package objects;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.Graphics;
import java.awt.Rectangle;

import java.io.IOException;
import java.util.Random;

import states.GameModel;
/**
 * 
 * Fiende klassen som sköter hur fienderna rör sig, NÄR de skjuter och vad som händer vid kollision med andra objekt.
 * 
 */
public class Enemy extends Objects {

	private static final long serialVersionUID = -1129493691790163940L;

	private boolean dead = false;
	private int updateCount;

	public Enemy(GameModel model, int x, int y, int velX, int velY) throws IOException {
		super(model, x, y);
		this.setVelX(velX);
		this.setVelY(velY);
		setObjectImg(loadImage("player.jpg"));
		Random random = new Random();
		this.updateCount = random.nextInt(120);
	}

	@Override
	public void draw(Graphics g) {
		if (getPosition().getX() < SCREEN_WIDTH && getPosition().getY() < SCREEN_HEIGHT) {
			g.drawImage(this.getObjectImg(), (int) getPosition().getX(), (int) getPosition().getY(), 25, 25, null);
		}
	}

	@Override
	public void update() {
		if (getPosition().getY() <= 0 || getPosition().getY() >= SCREEN_HEIGHT - 70)
			this.setVelY(this.getVelY() * -1);
		if (getPosition().getX() <= 0 || getPosition().getX() >= SCREEN_WIDTH - 50)
			this.setVelX(this.getVelX() * -1);

		getPosition().setX(getPosition().getX() + getVelX());
		getPosition().setY(getPosition().getY() + getVelY());

		this.updateCount += 1;
		if (updateCount > 120) {
			this.updateCount = 0;
		}
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 20, 20);
	}

	public void collision(Objects object) {
		if (object instanceof PlayerBullet) {
			this.setDead(true);
		} else if (object instanceof House || object instanceof Obstacle) {
			this.setVelX(this.getVelX() * -1);
			this.setVelY(this.getVelY() * -1);
		}

	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getUpdateCount() {
		return updateCount;
	}
}
