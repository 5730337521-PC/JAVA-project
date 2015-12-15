package Graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameBackground implements IRenderableObject {
	private static BufferedImage bgImage = null;
	private int count = 0;

	public GameBackground() {
		bgImage = DrawingUtility.bg;
	}

	public void updateBackground(int HP) {
		if (HP <= 50) {
			bgImage = DrawingUtility.bgred[count];
			if (count < 3)
				count++;
		} else {
			bgImage = DrawingUtility.bg;
		}
	}

	public static BufferedImage getBgImage() {
		return bgImage;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		if (bgImage == null)
			return;
		g2.drawImage(getBgImage(), 0, 0, null);
	}
}