package Beatmap;

import java.awt.Graphics2D;

import Logic.NowPlaying;
import Logic.PlayerStatus;

public class LongNote extends TargetObject {
	private float holdDuration;
	public LongNote(int x, int z, int hitDuration, float timing,float holdDuration) {
		super(x, z, hitDuration, timing);
		// TODO Auto-generated constructor stub
		this.holdDuration=holdDuration;
	}
	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hit(PlayerStatus player, NowPlaying now) {
		// TODO Auto-generated method stub
		float delta = Math.abs(now.getTime() - timing);
		if(delta < hitDuration/2){ //hit
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
		}
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	
}
