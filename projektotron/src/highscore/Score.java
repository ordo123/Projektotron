package highscore;

import java.io.Serializable;
/**
 * 
 * Sj�lva po�ngm�tningen, i detta fall en integer.
 *
 */
public class Score implements Serializable {

	private int score;

	public Score(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

}
