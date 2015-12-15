package Beatmap;

import java.awt.Graphics2D;

import Audio.NowPlaying;
import Graphic.DrawingUtility;
import Logic.PlayerStatus;

public class ShortNote extends TargetObject {

	public ShortNote(int x, int z, float hitDuration, float timing, int GRAVITY) {
		super(x, z, hitDuration, timing, GRAVITY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(PlayerStatus player, NowPlaying now) {
		// TODO Auto-generated method stub
		float delta = Math.abs(now.getTime() - timing);
		System.out.println(delta / (hitDuration / 2)+"!!!!!!!!");
		if (delta > hitDuration / 2) { // hit
			this.isDestroy = true;
			if (delta / (hitDuration / 2) <= 1200) { // maxhi
				player.addScore(100);
				player.addMaxhit();
			} else if (delta / (hitDuration / 1500) <= 2) { // hit50
				player.addScore(50);
				player.addHit50();
			} else { // hit10
				player.addScore(10);
				player.addHit10();
			}
		} else { // miss
			player.addMiss();
		}
	}

	@Override
	public void move() { // 1 tick 1/60sec
		if (!isDestroy) {
			// TODO Auto-generated method stub
			System.out.println("speed = " + speedY + "R = " + (SPEEDRADIUS / TICKRATE));
			y -= (speedY / TICKRATE);
			speedY -= ((float) GRAVITY / TICKRATE);
			radius += ((float) SPEEDRADIUS / TICKRATE);
		}
		if (!isOnscreen()) {
			isDestroy = true;
			System.out.println("isDestroy = " + isDestroy);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
			DrawingUtility.drawShortNote(g2d, (int) x, (int) y, (int) radius, isPointerOver);

	}


}
