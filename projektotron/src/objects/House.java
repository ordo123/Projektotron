package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import states.GameModel;

/**
 *  Platsen där spelaren startar spelet och där han respawnar.
 *  Syftet är att spelaren innanför House hitBox() ska vara säker.
 */

public class House extends Objects {

	private static final long serialVersionUID = -153082073499490159L;

	public House(GameModel model, int x, int y) throws IOException {
		super(model, x, y); 
		this.setObjectImg(loadImage("ufo.png"));  
	}

	@Override
	public Rectangle hitBox() {
		return new Rectangle((int)getPosition().getX(), (int)getPosition().getY(), 70, 70);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.getObjectImg(), (int)getPosition().getX(), (int)getPosition().getY(), 70, 70, null);
	}

	@Override
	public void collision(Objects object) {		
	}

}
