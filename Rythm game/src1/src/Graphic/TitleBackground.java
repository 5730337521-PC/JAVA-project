package Graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TitleBackground implements IRenderableObject
{
	private static BufferedImage bgImage = null;

	public TitleBackground() {
		setBgImage(DrawingUtility.title);
	}
	
	public void getTitleBG(int choice) {
		switch (choice) {
		case 0:
			setBgImage(DrawingUtility.title);

			break;
		case 1:
			setBgImage(DrawingUtility.title_play);

			break;
		case 2:
			setBgImage(DrawingUtility.title_option);

			break;
		case 3:
			setBgImage(DrawingUtility.title_center);

			break;
			

		default:
			setBgImage(DrawingUtility.title);
			break;
		}
	}

	public static BufferedImage getBgImage() {
		return bgImage;
	}

	public static void setBgImage(BufferedImage bgImage) {
		TitleBackground.bgImage = bgImage;
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
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if (bgImage == null)
			return;
		g2d.drawImage(DrawingUtility.bg, 0, 0, null);		
	}

}
