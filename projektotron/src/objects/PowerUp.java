package objects;

import java.awt.Rectangle;


import states.GameModel;
/**
 *  Superklass till alla PowerUps.
 *  
 */
public abstract class PowerUp extends Objects {


	private static final long serialVersionUID = 1L;
	private boolean isTaken;

	public PowerUp(GameModel model, int x, int y, Player player) {
		super(model, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract Rectangle hitBox();

	@Override
	public abstract void collision(Objects object);

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

}
