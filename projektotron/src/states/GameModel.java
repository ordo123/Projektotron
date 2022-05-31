package states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * This class represents the current state of the game.
 *
 * This implementation of a state machine keeps a reference to the current state
 * (see /src/states/GameState).
 *
 * Please note: This is just one way to do it, there are several other ways and
 * none of them work for every case, so if you want to implement your own state
 * machine make sure that it can do all the operations that you need for your
 * project.
 *
 * To change state simply call the switchState(GameState nextState) function
 * passing a reference to some other gameState.
 *
 * Initial State: MenuState
 *
 */

public class GameModel {

	private GameState currentState;

	public GameModel() {
		this.currentState = new MenuState(this);
	}

	public void switchState(GameState nextState) {
		currentState.deactivate();
		currentState = nextState;
		currentState.activate();
	}

	public void switchState(GameState nextState, int level) {
		currentState.deactivate();
		currentState = nextState;
		currentState.activate();
	}

	public void keyPressed(KeyEvent key) {
		currentState.keyPressed(key);
	}

	public void keyReleased(KeyEvent key) {
		currentState.keyReleased(key);
	}

	public void mousePressed(MouseEvent key) throws IOException {
		currentState.mousePressed(key);
	}

	public void update() {
		currentState.update();
	}

	public void draw(Graphics g) {
		currentState.draw(g);
	}

}
