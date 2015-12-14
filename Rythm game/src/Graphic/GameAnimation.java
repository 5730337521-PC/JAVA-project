package Graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameAnimation implements IRenderableObject {

	private BufferedImage image = null;
	private int frameCount, frameDelay;
	private int currentFrame, frameDelayCount;
	private int x, y, frameWidth, frameHeight;
	private boolean visible = false, playing = false;

	public GameAnimation(BufferedImage image, int frameCount, int frameDelay) {
		this.image = image;
		this.frameCount = frameCount;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.frameDelayCount = 0;
		this.x = 0;
		this.y = 0;
		try {
			this.frameWidth = image.getWidth() / frameCount;
			this.frameHeight = image.getHeight();
		} catch (Exception e) {
			// TODO: handle exception
			this.frameWidth = 0;
			this.frameHeight = 0;
		}
	}

	protected void topLeftAnimationAt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public int getFrameDelay() {
		return frameDelay;
	}

	public void setFrameDelay(int frameDelay) {
		this.frameDelay = frameDelay;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		if (currentFrame <= 0)
			currentFrame = 0;
		this.currentFrame = currentFrame;
	}

	public int getFrameDelayCount() {
		return frameDelayCount;
	}

	public void setFrameDelayCount(int frameDelayCount) {
		if (frameDelayCount <= 0)
			frameDelayCount = 0;
		this.frameDelayCount = frameDelayCount;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFrameWidth() {

		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	protected void centerAnimationAt(int x, int y) {
		this.x = x - frameWidth / 2;
		this.y = y - frameHeight / 2;
	}

	public void play() {
		currentFrame = 0;
		playing = true;
		visible = true;
	}

	public void stop() {
		currentFrame = 0;
		playing = false;
		visible = false;
	}

	public void updateAnimation() {
		if (playing) {
			if (frameDelayCount > 0) {
				frameDelayCount--;
				return;
			}
			if (frameDelayCount == 0){frameDelayCount = frameDelay;
			currentFrame++;}
			if (currentFrame == frameCount)
				stop();
		}
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE - 1;
	}

	@Override
	public void render(Graphics2D g2) {
		if (isVisible() && image != null) {
			g2.drawImage(image.getSubimage(frameWidth * this.currentFrame, 0, frameWidth, frameHeight), null, x, y);
		}
	}

}
