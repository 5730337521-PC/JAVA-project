
package Graphic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import ui.GameManager;

public class DrawingUtility {
	protected static final Font standardFont = new Font("Zapfino", Font.BOLD, 30);
	protected static final Font smallFont = new Font("Zapfino", Font.PLAIN, 9);

	private static BufferedImage getImage(String directory) {
		try {
			ClassLoader loader = GameManager.class.getClassLoader();
			BufferedImage image = ImageIO.read(loader.getResource(directory));
			return image;
		} catch (Exception e) {
			return null;
		}
	}

	protected static final BufferedImage title = getImage("res/img/bg/title.jpg");
	protected static final BufferedImage title_play = getImage("res/img/bg/play.jpg");
	protected static final BufferedImage title_option = getImage("res/img/bg/option.jpg");
	protected static final BufferedImage title_center = getImage("res/img/bg/center.jpg");
	protected static final BufferedImage bg = getImage("res/img/bg/blue.jpg");
	protected static final BufferedImage bgred[] = { getImage("res/img/bg/red+70.jpg"),
			getImage("res/img/bg/red+50.jpg"), getImage("res/img/bg/red+30.jpg"), getImage("res/img/bg/red.jpg") };

	protected static final BufferedImage explosion = getImage("res/img/sprite/Explosion-Sprite-Sheet.png");
	protected static final BufferedImage firework = getImage("res/img/sprite/firework.png");
	protected static final BufferedImage misshit = getImage("res/img/sprite/miss.png");

	protected static final BufferedImage hit10 = getImage("res/img/sprite/10.png");
	protected static final BufferedImage hit50 = getImage("res/img/sprite/50.png");
	protected static final BufferedImage hitmax = getImage("res/img/sprite/MAX.png");

	protected static final BufferedImage scorepic = getImage("res/img/sprite/score.png");
	protected static final BufferedImage combopic = getImage("res/img/sprite/combo.png");
	protected static final BufferedImage stopbar = getImage("res/img/sprite/stop.png");
	protected static final BufferedImage hpbar = getImage("res/img/sprite/hp.png");
	protected static final BufferedImage hp100 = getImage("res/img/sprite/hp/100.png");
	protected static final BufferedImage hp80 = getImage("res/img/sprite/hp/80.png");
	protected static final BufferedImage hp60 = getImage("res/img/sprite/hp/60.png");
	protected static final BufferedImage hp40 = getImage("res/img/sprite/hp/40.png");
	protected static final BufferedImage hp20 = getImage("res/img/sprite/hp/20.png");
	protected static final BufferedImage hp0 = getImage("res/img/sprite/hp/0.png");
	protected static final BufferedImage red = getImage("res/img/sprite/bomb/red.png");
	protected static final BufferedImage A = getImage("res/img/sprite/bomb/A.png");

	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);

	public static BufferedImage getExplosion() {
		return explosion;
	}

	public static BufferedImage getFirework() {
		return firework;
	}

	public static BufferedImage getMisshit() {
		return misshit;
	}

	public static BufferedImage getHit10() {
		return hit10;
	}

	public static BufferedImage getHit50() {
		return hit50;
	}

	public static BufferedImage getHitmax() {
		return hitmax;
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

	public static void drawStatusBar(Graphics2D g2, int score, int combo, int hp, int acc) {
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g2.setComposite(transcluentWhite);
		g2.setColor(Color.white);
		g2.fillRect(0, 570, 800, 30);
		g2.setComposite(opaque);
		g2.setColor(new Color(138, 7, 7));
		g2.setFont(standardFont);
		String scoretext = score + "";
		g2.drawImage(scorepic, 4, 554, null);
		g2.drawString(scoretext, 114, 595);
		String combotext = "" + combo;
		g2.drawImage(combopic, 200, 554, null);
		g2.drawString(combotext, 310, 595);
		g2.drawImage(hpbar, 500, 554, null);
		BufferedImage hppic = null;
		if (hp == 100)
			hppic = hp100;
		if (hp == 80)
			hppic = hp80;
		if (hp == 60)
			hppic = hp60;
		if (hp == 40)
			hppic = hp40;
		if (hp == 20)
			hppic = hp20;
		if (hp == 0)
			hppic = hp0;
		g2.drawImage(hppic, 580, 570, null);
		// g2.setComposite(transcluentWhite);
		// g2.setColor(Color.white);
		// g2.drawImage(stopbar, 0, 30, null);
		// g2.setComposite(opaque);
	}

	public static GameAnimation createHit(int x, int y, int type) {
		switch (type) {
		case 0:
			return createMissHit(x, y);
		case 1:
			return create10Hit(x, y);

		case 2:
			return create50Hit(x, y);

		case 3:
			return createMAXHit(x, y);

		default:
			return createMissHit(x, y);

		}
	}

	public static GameAnimation createMissHit(int x, int y) {
		GameAnimation Miss = new GameAnimation(DrawingUtility.misshit, 5, 1);
		Miss.centerAnimationAt(x, y);
		Miss.play();
		return Miss;
	}

	public static GameAnimation create10Hit(int x, int y) {
		GameAnimation hit10 = new GameAnimation(DrawingUtility.hit10, 5, 1);
		hit10.centerAnimationAt(x, y);
		hit10.play();
		return hit10;
	}

	public static GameAnimation create50Hit(int x, int y) {
		GameAnimation hit50 = new GameAnimation(DrawingUtility.hit50, 5, 1);
		hit50.centerAnimationAt(x, y);
		hit50.play();
		return hit50;
	}

	public static GameAnimation createMAXHit(int x, int y) {
		GameAnimation hitmax = new GameAnimation(DrawingUtility.hitmax, 5, 1);
		hitmax.centerAnimationAt(x, y);
		hitmax.play();
		return hitmax;
	}

	public static GameAnimation createExplosionAt(int x, int y) {
		GameAnimation explode = new GameAnimation(DrawingUtility.explosion, 5, 1);
		explode.centerAnimationAt(x, y);
		explode.play();
		return explode;
	}

	public static GameAnimation createFireworkAt(int x, int y) {
		GameAnimation firework = new GameAnimation(DrawingUtility.firework, 4, 1);
		firework.centerAnimationAt(x, y);
		firework.play();
		return firework;
	}

	public static void drawShortNote(Graphics2D g2, int x, int y, int radius, boolean isPointerOver, float delta) {
		g2.setColor(Color.BLACK);
		g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		g2.setColor(Color.RED);
		if (delta <= 200) {
			g2.setColor(Color.YELLOW);
		}
		g2.fillOval(x - radius + 2, y - radius + 2, (radius - 2) * 2, (radius - 2) * 2);

		if (isPointerOver) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
			g2.setComposite(opaque);
		}
	}

	public static void drawLongNote(Graphics2D g2, int x, int y, int radius, boolean isPointerOver, float deltain,
			float deltaout) {
		g2.setColor(Color.BLACK);
		g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);

		g2.setColor(Color.GREEN);
		if (deltain <= 200 || Math.abs(deltaout) <= 200) {
			if(Math.abs(deltaout) <= 200){
				g2.setColor(Color.BLACK);
				g2.fillOval(x - radius, y - radius, radius * 2+4, radius * 2+4);
			}
			g2.setColor(Color.CYAN);
		}
		g2.fillOval(x - radius + 2, y - radius + 2, (radius - 2) * 2, (radius - 2) * 2);

		if (isPointerOver) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
			g2.setComposite(opaque);
		}
	}
}