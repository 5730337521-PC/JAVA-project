package Graphic;

import java.awt.Graphics2D;

import Audio.NowPlaying;

public interface IRenderableObject {
	public boolean isVisible();
	public int getZ();
	public void render(Graphics2D g2d);
}
