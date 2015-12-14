package Beatmap;

import java.awt.Graphics2D;

import Audio.NowPlaying;
import Graphic.DrawingUtility;
import Logic.PlayerStatus;

public class LongNote extends TargetObject {
	private float timingOut;
	private int wiggcount;

	public LongNote(int x, int z, int hitDuration, float timing, float holdDuration) {
		super(x, z, hitDuration, timing);
		// TODO Auto-generated constructor stub
		this.timingOut = holdDuration;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawLongNote(g2d, x, y, radius, isPointerOver);
	}

	@Override
	public void hit(PlayerStatus player, NowPlaying now) {
		// TODO Auto-generated method stub
		float delta = Math.abs(now.getTime() - timing);
		if (delta < hitDuration / 2) { // hit
			player.addScore(100);
			player.addMaxhit();
		} else { // miss
			player.addMiss();
		}
	}

	@Override
	public void move() {
		if (onScreen && !isDestroy) {
			// TODO Auto-generated method stub
			y -= (int) (speedY / TICKRATE);
			speedY += (int) (GRAVITY / TICKRATE);
			radius += (int) (speedradius / TICKRATE);
		}
	}

	public void wiggle() {
		if (onScreen && !isDestroy) {
			if (wiggcount % 2 == 0) {
				x += 50;
			} else {
				x -= 100;
			}
		}
	}

	public float getHoldDuration() {
		return timingOut;
	}

}