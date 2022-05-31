package objects;

import java.awt.Graphics; 
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import states.GameModel;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

/**
 * 
 * Spelarklassen som sköter allt som har med spelaren att göra.
 * Hur spelaren rör sig, skjuter och tar skada.
 *
 */

public class Player extends Objects implements KeyListener {

	private static final long serialVersionUID = 5916770117843592565L;
	private int health;
	private boolean powerWeapon = false;


	public Player(GameModel model, int x, int y, int health) throws IOException {
		super(model, x, y);
		this.health = health;
		this.setVelX(0);
		this.setVelY(0);
		setObjectImg(loadImage("robotPlayer.png"));
		
	}



	@Override
	public void draw(Graphics g) {
		if (this.getPosition().getX() < SCREEN_WIDTH && this.getPosition().getY() < SCREEN_HEIGHT) {
			g.drawImage(this.getObjectImg(), (int) this.getPosition().getX(), 
					(int) this.getPosition().getY(), 25, 25, null);
		}
	}

	public void reSpawn(int x, int y) {
		this.setPosition(new Point(x, y));
	}

	@Override
	public synchronized void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_S)
			this.setVelY(0);
		if (key.getKeyCode() == KeyEvent.VK_D)

			this.setVelX(0);
		if (key.getKeyCode() == KeyEvent.VK_A)
			this.setVelX(0);
		if (key.getKeyCode() == KeyEvent.VK_W)
			this.setVelY(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void update() {
		checkOOB();
		this.getPosition().setX(getPosition().getX() + getVelX());
		this.getPosition().setY(getPosition().getY() + getVelY());
	}

	public void checkOOB() {
		if (getPosition().getX() < 0)
			getPosition().setX(0);
		if (getPosition().getX() > 960)
			getPosition().setX(960);
		if (getPosition().getY() < 1)
			getPosition().setY(1);
		if (getPosition().getY() > 735)
			getPosition().setY(735);
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S)
			this.setVelY(5);

		if (e.getKeyCode() == KeyEvent.VK_D)
			this.setVelX(5);
		if (e.getKeyCode() == KeyEvent.VK_A)
			this.setVelX(-5);

		if (e.getKeyCode() == KeyEvent.VK_W)
			this.setVelY(-5);
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 25, 25);

	}

	public void collision(Objects object) {
		if (object instanceof Enemy || object instanceof Obstacle || object instanceof EnemyBullet) {
			this.changeHealth(-1);
			this.reSpawn(495, 40);
		}
	}

	public Bullet createBullet(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		Double angle = Math.atan2(my - this.getPosition().getY(), mx - this.getPosition().getX());

		Integer speed = 20;
		Double velX = speed * Math.cos(angle);
		Double velY = speed * Math.sin(angle);

		PlayerBullet tempBullet = new PlayerBullet(model, this.getPosition().getX() + 10,
				this.getPosition().getY() + 10, velX, velY);
		return tempBullet;
	}

	public int getHealth() {
		return health;
	}

	public void changeHealth(int health) {
		this.health = (this.health + health);

	}

	public boolean isPowerWeapon() {
		return powerWeapon;
	}

	public void setPowerWeapon(boolean powerWeapon) {
		this.powerWeapon = powerWeapon;
	}
}
