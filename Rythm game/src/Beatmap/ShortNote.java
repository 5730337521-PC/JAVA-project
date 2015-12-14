package Beatmap;

import java.awt.Color;
import java.awt.Graphics2D;

import Audio.NowPlaying;
import Graphic.DrawingUtility;
import Logic.PlayerStatus;

public class ShortNote extends TargetObject {

	public ShortNote(int x, int z, float hitDuration, float timing) {
		super(x, z, hitDuration, timing);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(PlayerStatus player, NowPlaying now) {
		// TODO Auto-generated method stub
		float delta = Math.abs(now.getTime() - timing);
		if (delta < hitDuration / 2) { // hit
			this.isHit = true;
			this.isDestroy = true;
			if (delta / (hitDuration / 2) <= 0.1) { // maxhit
				player.addScore(100);
				player.addMaxhit();
			} else if (delta / (hitDuration / 2) <= 0.5) { // hit50
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
		if (onScreen && !isDestroy) {
			// TODO Auto-generated method stub
			y -= (int) (speedY / TICKRATE);
			speedY += (int) (GRAVITY / TICKRATE);
			radius += (int) (speedradius / TICKRATE);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawShortNote(g2d, x, y, radius, isPointerOver);
	
	}
}
