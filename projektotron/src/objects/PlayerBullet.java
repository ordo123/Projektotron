package objects;

import states.GameModel;
/**
 * 
 *  Spelarens kulor, reglerar hur de reagerar med olika objekt.
 *
 */

public class PlayerBullet extends Bullet {

	private static final long serialVersionUID = -2053876310205670445L;

	public PlayerBullet(GameModel model, double x, double y, double velX, double velY) {
		super(model, x, y, velX, velY);
		this.setObjectImg(loadImage("playerbullet.png"));

	}

	@Override
	public void collision(Objects object) {
		if (object instanceof Enemy || object instanceof Obstacle || object instanceof House
				|| object instanceof EnemyBullet) {
			this.setHit(true);
		}

	}
}
