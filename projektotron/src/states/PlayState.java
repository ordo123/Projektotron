package states;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import highscore.Manager;
import objects.Bullet;
import objects.Enemy;
import objects.EnemyBullet;
import objects.ExtraLife;
import objects.House;
import objects.Objects;
import objects.Obstacle;
import objects.Player;
import objects.PowerUp;
import objects.PowerWeapon;

/**
 * 
 * Klassen som hanterar själva spelandet. Den skapar alla objekten och kontrollerar kollision och 
 * den anropar funktioner i respektive objektklass. Den ritar ut och tar bort alla objekt (när det passar).
 * Klassen initerar nya nivåer varteftersom spelar avancerar i spelet.
 * 
 * If you're reading this blizzard entertainment. This is a unique piece of code, if you're interested we could settle for 2 logs snus and 1 keg of monster energy drink.
 */


public class PlayState extends GameState implements KeyListener {
/*
 * 
 * 
 */
	private static final long serialVersionUID = 8360417946870731449L;
	private Player player;
	private House house;
	private HUD hud = new HUD();
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Bullet> bulletList = new ArrayList<>();
	private ArrayList<Obstacle> obstacleList = new ArrayList<>();
	private ArrayList<PowerUp> powerUpList = new ArrayList<>();
	private int level = 1;
	private Manager highscore = new Manager();

	public PlayState(GameModel model, int level, int health) throws IOException {

		super(model);
		player = new Player(model, 490, 40, health);
		house = new House(model, 472, 0);
		this.level = level;
		this.initializeLevel(level);

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 990, 770);
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Stage: " + this.level, 900, 20);
		player.delegate(g);
		house.delegate(g);

		for (Enemy enemy : enemyList) {
			if (enemy != null) {
				enemy.delegate(g);
			}
		}
		for (Obstacle obstacle : obstacleList) {
			if (obstacle != null) {
				obstacle.delegate(g);
			}
		}
		for (Bullet bullet : bulletList) {
			if (bullet != null) {
				bullet.delegate(g);
			}
		}
		for (PowerUp powerup : powerUpList) {
			powerup.delegate(g);
		}

		hud.draw(g, player.getHealth());
	}

	@Override
	public void update() {
		player.update();
		for (Enemy enemy : enemyList) {
			enemy.update();
			if (enemy.getUpdateCount() > 119) {
				bulletList.add(createBullet(enemy));
			}
		}
		for (Bullet bullet : bulletList) {
			bullet.update();

		}
		for (Obstacle obstacle : obstacleList) {
			obstacle.update();
		}
		collision();
		checkDeadEnemy();
		checkDeadObstacle();
		checkWinCondition();
		checkBullet();
		checkTakenPowerUp();
	}

	/*
	 * Create objects
	 */
	public Obstacle makeNewObstacle(ArrayList<Obstacle> obstacleList) {
		Random random = new Random();
		int randomNumberSpawnX = random.nextInt(900);
		int randomNumberSpawnY = random.nextInt(700);
		Obstacle tempObstacle = null;
		tempObstacle = new Obstacle(model, (randomNumberSpawnX), (randomNumberSpawnY));
		for (Obstacle obstacle : obstacleList) {
			if (tempObstacle.hitBox().intersects(obstacle.hitBox())
					|| tempObstacle.hitBox().intersects(house.hitBox())) {
				tempObstacle = makeNewObstacle(obstacleList);
			}
		}
		return tempObstacle;
	}

	public PowerUp makeNewPowerUp(ArrayList<Obstacle> obstacleList) {
		Random random = new Random();
		int randomNumberSpawnX = random.nextInt(900);
		int randomNumberSpawnY = random.nextInt(700);
		PowerUp tempPowerExtraLife = null;
		tempPowerExtraLife = new ExtraLife(model, (randomNumberSpawnX), (randomNumberSpawnY), player);
		for (Obstacle obstacle : obstacleList) {
			if (tempPowerExtraLife.hitBox().intersects(obstacle.hitBox()) || tempPowerExtraLife.hitBox().intersects(house.hitBox())) {
				tempPowerExtraLife = makeNewPowerUp(obstacleList);
			}
			
		}
		return tempPowerExtraLife;
	}
	
	public PowerUp makeNewPowerWeapon(ArrayList<Obstacle> obstacleList) {
		Random random = new Random();
		int randomNumberSpawnX = random.nextInt(900);
		int randomNumberSpawnY = random.nextInt(700);
		PowerUp tempPowerWeapon = null;

		tempPowerWeapon = new PowerWeapon(model, (randomNumberSpawnX), (randomNumberSpawnY), player);
		for (Obstacle obstacle : obstacleList) {
			if (tempPowerWeapon.hitBox().intersects(obstacle.hitBox()) || tempPowerWeapon.hitBox().intersects(house.hitBox())) {
				tempPowerWeapon = makeNewPowerWeapon(obstacleList);
			}
			
		}
		return tempPowerWeapon;
	}


	public Enemy makeNewEnemy(ArrayList<Obstacle> obstacleList) {
		Random random = new Random();
		int randomNumberSpawnX = random.nextInt(900);
		int randomNumberSpawnY = random.nextInt(700);
		Enemy tempEnemy = null;
		try {
			tempEnemy = new Enemy(model, (randomNumberSpawnX), (randomNumberSpawnY), 3, -3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Obstacle obstacle : obstacleList) {
			if (tempEnemy.hitBox().intersects(obstacle.hitBox()) || tempEnemy.hitBox().intersects(house.hitBox())) {
				tempEnemy = makeNewEnemy(obstacleList);
			}
		}
		return tempEnemy;
	}

	public Bullet createBullet(Enemy enemy) {
		Double angle = Math.atan2(player.getPosition().getY() - enemy.getPosition().getY(),
				player.getPosition().getX() - enemy.getPosition().getX());

		Integer speed = 3;
		if (level > 9) {
			speed = level - 2;
		}
		Double velX = speed * Math.cos(angle);
		Double velY = speed * Math.sin(angle);

		EnemyBullet tempBullet = new EnemyBullet(model, enemy.getPosition().getX() + 10,
				enemy.getPosition().getY() + 20, velX, velY);
		return tempBullet;
	}

	/*
	 * Controls and conditions
	 */
	public void collision() {
		ArrayList<Objects> objectList = new ArrayList<>();
		objectList.addAll(enemyList);
		objectList.addAll(bulletList);
		objectList.addAll(obstacleList);
		objectList.addAll(powerUpList);
		objectList.add(house);
		objectList.add(player);

		for (Objects object : objectList) {
			for (int i = 0; i < objectList.size(); i++) {
				Objects tempObject = objectList.get(i);
				if (tempObject.hitBox().intersects(object.hitBox())) {
					tempObject.collision(object);
				}
			}
			if (player.getHealth() == 0) {
				model.switchState(new LoseState(model, this.level));		
				highscore.addScore(this.level);
			}
		}
		objectList.clear();
	}

	public void checkWinCondition() {
		if (enemyList.size() == 0) {
			model.switchState(new NextLevelState(model, nextLevel(), player.getHealth()));
		}
	}

	public void checkDeadObstacle() {
		Iterator<Obstacle> ite = obstacleList.iterator();
		while (ite.hasNext()) {
			@SuppressWarnings("unused")
			Obstacle obstacle = ite.next();
			if (player.isPowerWeapon() == true) {
				ite.remove();
			}
		}
	}

	public void checkDeadEnemy() {
		Iterator<Enemy> ite = enemyList.iterator();
		while (ite.hasNext()) {
			Enemy enemy = ite.next();
			if (enemy.isDead() == true) {

				ite.remove();
			}
		}
	}

	public void checkTakenPowerUp() {
		Iterator<PowerUp> ite = powerUpList.iterator();
		while (ite.hasNext()) {
			PowerUp powerup = ite.next();
			if (powerup.isTaken() == true) {

				ite.remove();
			}

		}
	}

	public void checkBullet() {
		Iterator<Bullet> ite = bulletList.iterator();
		while (ite.hasNext()) {
			Bullet bullet = ite.next();
			if ((bullet.getPosition().getX() < 0 || bullet.getPosition().getX() > 1000
					|| bullet.getPosition().getY() < 1 || bullet.getPosition().getY() > 800 || bullet.isHit())) {
				ite.remove();
			}
		}
	}

	/*
	 * User input-handling
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			model.switchState(new MenuState(model));
		} else {
			player.keyPressed(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		bulletList.add(player.createBullet(e));
	}

	public void initializeLevel(int level) {
		
		for (int i = 0; i < level && i < 11; i++) {
			obstacleList.add(makeNewObstacle(obstacleList));
		}
		
		if (level > 10) {
			level = 10;
			for (int i = 0; i < 1; i++) {
				powerUpList.add(makeNewPowerWeapon(obstacleList));
			}
		}
		
		for (int i = 0; i < 1; i++) {
			powerUpList.add(makeNewPowerUp(obstacleList));
		}
		
		for (int i = 0; i < level; i++) {
			enemyList.add(makeNewEnemy(obstacleList));
		}

	}

	public int nextLevel() {
		return this.level + 1;
	}
}
