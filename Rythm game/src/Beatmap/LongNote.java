package Beatmap;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.HitSound;
import Audio.NowPlaying;
import Logic.PlayerStatus;
import Utility.InputUtility;

public class LongNote extends TargetObject {
	private static final int WSPEED = 50;
	private float deltain;
	private float deltaout;
	private float timingOut;
	private boolean hitted;
	private boolean hold;
	private boolean playanimation;
	private int wiggcount;
	private HitSound h = new HitSound();

	public LongNote(int x, int z, int hitDuration, float timing, float holdDuration, int GRAVITY) {
		super(x, z, hitDuration, timing, GRAVITY);
		// TODO Auto-generated constructor stub
		this.timingOut = holdDuration;
		wiggcount = 0;
		hitted = false;
		setPlayanimation(false);
	}

	public void wiggle() {
		if (isOnscreen && !isDestroy) {
			if (wiggcount % 8 == 0) {
				x += WSPEED;
			} else if (wiggcount % 8 == 1) {
				x -= WSPEED;
			} else if (wiggcount % 8 == 2) {
				y -= WSPEED;
			} else if (wiggcount % 8 == 3) {
				y += WSPEED;
			} else if (wiggcount % 8 == 4) {
				x -= WSPEED;
			} else if (wiggcount % 8 == 5) {
				x += WSPEED;
			} else if (wiggcount % 8 == 6) {
				y -= WSPEED;
			} else if (wiggcount % 8 == 7) {
				y += WSPEED;
			}
		}
		wiggcount++;
	}

	public float getHoldDuration() {
		return timingOut;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public int hit(PlayerStatus player) {
		// TODO Auto-generated method stub
		if (deltain < hitDuration * 1000 / 2) { // hit
			player.addScore(100);
			player.addMaxhit();
			hitted = true;
			return 3;

		} else

		{ // miss
			player.addMiss();
			return 0;
		}

	}

	@Override
	public void move(NowPlaying now, PlayerStatus player) {
		// TODO Auto-generated method stub
		hold = InputUtility.getKeyPressed(KeyEvent.VK_SPACE);
		deltain = Math.abs(now.getTime() - timing);
		deltaout = now.getTime() - timingOut;
		if (!isDestroy && !hitted) {
			// TODO Auto-generated method stub
			System.err.println("deltain = " + deltain);
			System.err.println("deltaout = " + deltaout);
			System.out.println("speed = " + speedY + "R = " + (SPEEDRADIUS / TICKRATE));
			y -= (speedY / TICKRATE);
			speedY -= ((float) GRAVITY / TICKRATE);
			radius += ((float) SPEEDRADIUS / TICKRATE);
		}
		if (hitted && hold && deltaout <= 0) {
			wiggle();
			if (wiggcount%30 == 29){
				player.addScore(100);
				player.addMaxhit();
			}
		}
		if (hitted && !hold && Math.abs(deltaout) > 500) {
			player.addMiss();
			isDestroy = true;
		}
		if (hitted && !hold && Math.abs(deltaout) <= 500) {
			player.addMaxhit();
			h.play(2);
			isDestroy = true;
			setPlayanimation(true);
		}

		if (!isOnscreen()) {
			isDestroy = true;
			System.out.println("isDestroy = " + isDestroy);
		}

	}

	public boolean isPlayanimation() {
		return playanimation;
	}

	public void setPlayanimation(boolean playanimation) {
		this.playanimation = playanimation;
	}

}