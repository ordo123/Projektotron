package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import states.GameModel;

/**
 *  Superklass till alla spelets kulor, dvs spelaren och fiendens.
 */

public abstract class Bullet extends Objects {

	private static final long serialVersionUID = -510946143016887456L;
	private boolean hit;
	private boolean isFired;

	public Bullet(GameModel model, double x, double y, double velX, double velY) {
		super(model, (int) x, (int) y);
		this.setVelX(velX);
		this.setVelY(velY);
		hit = false;
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 8, 8);
	}

	@Override
	public void update() {
		getPosition().setX(getPosition().getX() + getVelX());
		getPosition().setY(getPosition().getY() + getVelY());
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getObjectImg(), (int) getPosition().getX(), (int) getPosition().getY(), 7, 11, null);
	}

	/*
	 * Getters och setters
	 */

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isFired() {
		return isFired;
	}

	public void setFired(boolean isFired) {
		this.isFired = isFired;
	}

}
