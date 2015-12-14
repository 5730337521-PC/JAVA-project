
package Graphic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class DrawingUtility {

	protected static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	protected static final Font smallFont = new Font("Tahoma", Font.PLAIN, 9);

	public static BufferedImage getImage(String directory) {
		try {
			ClassLoader load = DrawingUtility.class.getClassLoader();
			return ImageIO.read(load.getResource(directory));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	protected static final BufferedImage bg = getImage("res/img/bg/bg.jpg");
	protected static final BufferedImage explosion = getImage("res/img/sprite/Explosion-Sprite-Sheet.png");
	protected static final BufferedImage firework = getImage("res/img/sprite/firework.png");

	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);

	public static BufferedImage getExplosion() {
		return explosion;
	}
	
	public static BufferedImage getFirework() {
		return firework;
	}

	public static void drawShootableObject(Graphics2D g2, int x, int y, int radius, String name,
			boolean isPointerOver) {
		g2.setColor(Color.BLACK);
		g2.fillOval(x - radius - 2, y - radius - 2, radius * 2 + 4, radius * 2 + 4);
		if (name.equalsIgnoreCase("simple"))
			g2.setColor(Color.BLUE);
		if (name.equalsIgnoreCase("splitter"))
			g2.setColor(Color.RED);
		if (name.equalsIgnoreCase("small"))
			g2.setColor(Color.YELLOW);
		g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		if (isPointerOver) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
			g2.setComposite(opaque);
		}
	}

	/*
	 * public static void drawStatusBar(Graphics2D g2, int remainingSecond, int
	 * score, boolean pause) { fill code g2.setColor(Color.BLACK);
	 * g2.fillRect(0, 0, ConfigurableOption.screenWidth, 40);
	 * g2.setColor(Color.WHITE); g2.setFont(standardFont); String time =
	 * "TIME : " + remainingSecond; String scoretext = "SCORE : " + score;
	 * g2.drawString(time, 5, 35); g2.drawString(scoretext,
	 * ConfigurableOption.screenWidth / 2 + 40, 35);
	 * 
	 * if (gun != null) { gun.render(g2); }
	 * 
	 * fill code if (pause) { FontMetrics fontMetrics =
	 * g2.getFontMetrics(standardFont); Rectangle2D bound =
	 * fontMetrics.getStringBounds("PAUSE", g2); g2.setColor(Color.WHITE);
	 * g2.setFont(standardFont); int xPosition = (int)
	 * (ConfigurableOption.screenWidth - bound.getWidth()) / 2; int yPosition =
	 * (int) ((ConfigurableOption.screenHeight - bound.getHeight()) / 2 +
	 * bound.getHeight() - fontMetrics.getDescent()); g2.drawString("PAUSE",
	 * xPosition, yPosition); }
	 * 
	 * }
	 */
	public static GameAnimation createShootingAnimationAt(int x, int y) {
		GameAnimation explode = new GameAnimation(DrawingUtility.explosion, 5, 1);
		explode.centerAnimationAt(x, y);
		explode.play();
		return explode;
	}
	
	public static GameAnimation createFireworkAt(int x, int y) {
		GameAnimation firework = new GameAnimation(DrawingUtility.firework, 5, 1);
		firework.centerAnimationAt(x, y);
		firework.play();
		return firework;
	}
}
