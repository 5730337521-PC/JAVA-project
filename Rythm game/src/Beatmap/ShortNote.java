package Beatmap;

import java.awt.Graphics2D;

import Audio.NowPlaying;
import Graphic.DrawingUtility;
import Logic.PlayerStatus;

public class ShortNote extends TargetObject{

	public ShortNote(int x, int z, float hitDuration, float timing) {
		super(x, z, hitDuration, timing);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit(PlayerStatus player, NowPlaying now) {
		// TODO Auto-generated method stub
		if(isDestroy()) return;
		float delta = Math.abs(now.getTime() - timing);
		if(delta < hitDuration/2){ //hit
			this.isHit = true;
			this.setDestroy(true);
			if(delta/(hitDuration/2)<=0.1){ //maxhit
				player.addScore(100);
				player.addMaxhit();			
			}else if(delta/(hitDuration/2)<=0.5){ //hit50
				player.addScore(50);
				player.addHit50();
			}else{ //hit10
				player.addScore(10);
				player.addHit10();
			}
		}
		else { //miss
			player.addMiss();
			DrawingUtility.createExplosionAt(this.x, this.y);
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	


}
