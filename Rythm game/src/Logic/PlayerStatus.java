package Logic;

import java.awt.Graphics2D;

import Audio.HitSound;

public class PlayerStatus implements Graphic.IRenderableObject {
	private static final int MAX_score = 200;
	private static final int MAX_HP = 100;
	private static final int HP_reduction = 20;

	private int score;
	private float accuracy;
	private int hp;
	private boolean pause = false;

	private int combocount;
	private int maxcombo;

	private int hitcount;
	private int maxhit; // 80+ MAX_score
	private int hit50; // MAX_score/2
	private int hit10; // MAX_score/10
	private int misscount; // 0

	public PlayerStatus() { //initialize when startplaying
		this.score = 0;
		this.accuracy = 100;
		this.hp = MAX_HP;
		this.combocount = 0;
		this.maxcombo = 0;
		this.maxhit = 0;
		this.hit50 = 0;
		this.hit10 = 0;
		this.misscount = 0;
	}

	public void update() { // update after each BPM
		try {
			this.accuracy = (maxhit * 100 + hit50 * 50 + hit10 * 10) / (maxhit + hit10 + hit50 + misscount);  //accupdate
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			accuracy = 100;
		}
		
		if (combocount > maxcombo) {  //maxcomboupdate
			maxcombo = combocount;
		}

	}

	public void addScore(int hittype) { // 100 50 10
		score += MAX_score * hittype * combocount / 100;
	}

	public void addMaxhit() {
		addScore(100);
		combocount++;
		maxhit++;
		hitcount++;
	}

	public void addHit50() {
		addScore(50);
		combocount++;
		hit50++;
		hitcount++;
	}

	public void addHit10() {
		addScore(10);
		combocount++;
		hit10++;
		hitcount++;
	}

	public void addMiss() { // when miss
		this.combocount = 0;
		misscount++;
		hp -= HP_reduction;
		hitcount++;
	}

	public int getMaxcombo() {
		return maxcombo;
	}

	public int getScore() {
		return score;
	}

	public int getHp() {
		return hp;
	}

	public int getHitcount() {
		return hitcount;
	}

	public int getCombocount() {
		return combocount;
	}

	public int getMaxhit() {
		return maxhit;
	}

	public int getHit50() {
		return hit50;
	}

	public int getHit10() {
		return hit10;
	}

	public int getMiss() {
		return misscount;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isDisplayingArea(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		return mouseX<=800 && mouseY <= 600 && mouseX >= 0 && mouseY >=0;
	}

	public void shoot() {
		// TODO Auto-generated method stub
		HitSound h = new HitSound();
		h.play(1);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	
}
