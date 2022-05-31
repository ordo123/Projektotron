package main;

import states.GameModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * This Class is the entry point of the application.
 * <p>
 * This Class has the following primary responsibilities:
 * 1. Serve as the entry for the Application
 * <p>
 * 2. Create the GameModel
 * (For more information about the GameModel see /src/states/GameModel)
 * <p>
 * 3. Create the GameFrame (A JFrame Wrapper):
 * (For more information about the GameFrame see /src/main/GameFrame)...
 * <p>
 * 4. Create the GamePanel (A JPanel Wrapper):
 * (For more information about the GamePanel see /src/main/GamePanel)...
 * <p>
 * 5. Run the main loop of the game. 
 * <p>
 * https://gitlab.ida.liu.se/alomi60/ExempelProjekt-TDDE10
 */
public class Main {
	
	public static Clip playASound(String path, boolean repeat) {
		AudioInputStream audioIn;

		try {
			audioIn = AudioSystem.getAudioInputStream(new File(path));
			
			DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioIn);
			if (repeat) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
	        return clip;
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Main() {
	
		playASound("gamesong.wav", true);
		
	}
	
    public static void main(String[] args) {

        GameModel model = new GameModel();
        GameFrame frame = new GameFrame("PROJEKTOTRON 2084", model);

        //Starts the music
		new Main();

        //Set the number of frames
        final double fps = 60;

        //Calculate frequency
        double ms = 1000.0 / fps;

        while (true) {
            // Variable that will help us decide how long rendering and update took
            long lastTime = System.currentTimeMillis();

            // Perform game update and game rendering.
            model.update();
            frame.repaint();

            // Calculate the time it took to update and render
            long timer = System.currentTimeMillis() - lastTime;

            Toolkit.getDefaultToolkit().sync(); // Sync the graphics state (see java docs for more info)

            try {
                // The difference between ms and timer is how much faster/slower we are then 60 fps
                // if we are faster, sleep that amount else sleep 0 seconds
                Thread.sleep((long) Math.max(ms - timer, 0));
            } catch (InterruptedException e) {
                // Write to to log here
            }
        }
    }
}