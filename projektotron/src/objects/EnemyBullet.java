package objects;

import states.GameModel;
/**
 * 
 * Fiendens kulor, reglerar hur de reagerar med olika objekt.
 *
 */

public class EnemyBullet extends Bullet {

	private static final long serialVersionUID = 717142091947242264L;

	public EnemyBullet(GameModel model, double x, double y, double velX, double velY) {
		super(model, x, y, velX, velY);
		this.setObjectImg(loadImage("enemybullet.png"));
	}

	@Override
	public void collision(Objects object) {
		if (object instanceof Player || object instanceof Obstacle || object instanceof House
				|| object instanceof PlayerBullet) {
			this.setHit(true);
		}
	}

}
