package states;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * Hanterar hur spelarens liv blir utskrivet på skärmen.
 *
 */

public class HUD extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage heartImg = loadImage("HEART.png");

	public HUD() {

	}

	public BufferedImage loadImage(String imgPath) {
		try {
			return ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void draw(Graphics g, int hp) {

		Image tmpheart;

		tmpheart = heartImg.getScaledInstance(25, 20, Image.SCALE_SMOOTH);
		for (int i = 0; i < hp; i++) {
			g.drawImage(tmpheart, i * 35 + 6, 5, this);

		}
	}

}
