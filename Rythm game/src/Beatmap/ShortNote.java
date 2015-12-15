package Beatmap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;

import Audio.HitSound;
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
		
		HitSound h = new HitSound();

		// TODO Auto-generated method stub
		float delta = Math.abs(now.getTime() - timing);
		System.out.print(delta+" delta");
		if (delta < 1500) { // hit
			this.isDestroy = true;
			this.isClicked = true;	
			h.play(1);
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
			h.play(0);
			player.addMiss();
		}
	}

	@Override
	public void move() { // 1 tick 1/60sec
		if (!isDestroy) {
			// TODO Auto-generated method stub
			System.out.println("speed = " + speedY +"R = "+(SPEEDRADIUS / TICKRATE));
			y -= (speedY / TICKRATE);
			speedY -= ((float) GRAVITY / TICKRATE);
			radius += ((float) SPEEDRADIUS / TICKRATE);
		}
		if(!isOnscreen()){
			isDestroy = true;
			System.out.println("isDestroy = "+isDestroy);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		DrawingUtility.drawShortNote(g2d, (int) x, (int) y, (int) radius, isPointerOver);
//		g2d.drawImage(note, (int) x, (int)y,(int)radius+100,(int)radius+100, null);


	}
}
