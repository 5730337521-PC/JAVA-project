package Beatmap;

import java.awt.Graphics2D;

import Audio.NowPlaying;
import Graphic.DrawingUtility;
import Logic.PlayerStatus;

public class ShortNote extends TargetObject {
	private float delta;

	public ShortNote(float x, int z, float hitDuration, float timing, int GRAVITY) {
		super(x, z, hitDuration, timing, GRAVITY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hit(PlayerStatus player) {
		// TODO Auto-generated method stub
		if (delta < hitDuration * 1000 / 2) { // hit
			this.isDestroy = true;
			if (delta < hitDuration * 200 / 2) { // maxhit
				player.addScore(100);
				player.addMaxhit();
				return 3;
			} else if (delta < hitDuration * 500 / 2) { // hit50
				player.addScore(50);
				player.addHit50();
				return 2;
			} else { // hit10
				player.addScore(10);
				player.addHit10();
				return 1;
			}
		} else { // miss
			player.addMiss();
			return 0;
		}
	}

	@Override
	public void move(NowPlaying now, PlayerStatus player) { // 1 tick 1/60sec
		delta = Math.abs(now.getTime() - timing);
		if (!isDestroy) {
			// TODO Auto-generated method stub
			System.err.println("delta = " + delta);
			System.out.println("speed = " + speedY + "R = " + (SPEEDRADIUS / TICKRATE));
			y -= (speedY / TICKRATE);
			speedY -= ((float) GRAVITY / TICKRATE);
			radius += ((float) SPEEDRADIUS / TICKRATE);
		}
		if (!isOnscreen()) {
			isDestroy = true;
			System.out.println("isDestroy = " + isDestroy);
			player.addMiss();
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawShortNote(g2d, (int) x, (int) y, (int) radius, isPointerOver,delta);

	}
}
