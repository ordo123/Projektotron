package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import states.GameModel;

/**
 *  Hanterar PowerUpen 'Nuke' och vad som händer när spelaren tar ett sådant.
 */

public class PowerWeapon extends PowerUp {

	private static final long serialVersionUID = 1L;
	private Player player;
	static BufferedImage nuke;

	public PowerWeapon(GameModel model, int x, int y, Player player) {
		super(model, x, y, player);
		this.player = player;
		nuke = loadImage("nuke.jpg");
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 25, 25);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(nuke, (int) getPosition().getX(), (int) getPosition().getY(), 25, 25, null);
	}

	@Override
	public void collision(Objects object) {
		if (object instanceof Player) {
			if (object.hitBox().intersects(this.hitBox())) {
				player.setPowerWeapon(true);
				setTaken(true);
			}

		}

	}

}
