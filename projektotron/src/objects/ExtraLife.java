package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import states.GameModel;

/**
 *  Hanterar PowerUpen extraliv och vad som händer när spelaren tar ett sådant.  
 */

public class ExtraLife extends PowerUp {

	private static final long serialVersionUID = 1L;
	private Player player;
	static BufferedImage lifeImg;

	public ExtraLife(GameModel model, int x, int y, Player player) {
		super(model, x, y, player);
		this.player = player;
		lifeImg = loadImage("HEART.png");
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 15, 10);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(lifeImg, (int) getPosition().getX(), (int) getPosition().getY(), 18, 14, null);
	}

	@Override
	public void collision(Objects object) {
		if (object instanceof Player) {
			if (object.hitBox().intersects(this.hitBox())) {
				player.changeHealth(1);
				setTaken(true);
			}

		}
	}

}
