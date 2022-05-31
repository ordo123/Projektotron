package highscore;

import java.util.*;
import java.io.*;

/**
 * 
 * Klassen hanterar highscore. Den skapar en fil innehållande highscore på hårddisken om en sådan inte finns, 
 * den sparar sedan highscore i den filen med ett kommando.
 *
 */

public class Manager {

	private ArrayList<Score> scores;

	private static final String HIGHSCORE_FILE = "scores.dat";

	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public Manager() {
		scores = new ArrayList<Score>();
	}

	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}
	
	private void sort() {
        Comparer comparator = new Comparer();
        Collections.sort(scores, comparator);
}

	public void addScore(int score) {
		loadScoreFile();
		scores.add(new Score(score));
		updateScoreFile();
	}

	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	
	public String getHighscoreString() {
		String highscoreString = "";

		ArrayList<Score> scores;
		scores = getScores();
		highscoreString += scores.get(0).getScore();

		return highscoreString;
	}

}
