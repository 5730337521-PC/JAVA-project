package Graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameBackground implements IRenderableObject {

	private static BufferedImage bgImage = null;

	public GameBackground() {
		bgImage = DrawingUtility.bg;
	}

	public void updateBackground(int HP) {
		if (HP <= 50) {
			bgImage = DrawingUtility.bgred;
		}
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
		g2.drawImage(DrawingUtility.bg, 0, 0, null);
	}
}