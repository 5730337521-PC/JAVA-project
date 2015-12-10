package Logic;

public class PlayerStatus {
	private static final int MAX_score = 200;
	private static final int MAX_HP = 100;
	private static final int HP_reduction = 20;

	private int songpercent;
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

	public PlayerStatus() {
		this.songpercent = 0;
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

	public float getaccuracy() {
		float acc = maxhit * 100 + hit50 * 50 + hit10 * 10; // weight
		acc = acc / (maxhit + hit10 + hit50 + misscount); // /n
		return acc;
	}

	// public int getsongpercent() {
	// return songtime/songduration;
	// }

	public void update() { // update after each BPM
		this.accuracy = getaccuracy();
		if (combocount > maxcombo) {
			maxcombo = combocount;
		}

	}

	public int getCombocount() {
		return combocount;
	}

	public int getMaxcombo() {
		return maxcombo;
	}

	public void addScore(int hittype) { // 100 50 10
		score += MAX_score * hittype * combocount / 100;
	}

	public void addMaxhit() {
		combocount++;
		maxhit++;
		hitcount++;
	}

	public void addHit50() {
		combocount++;
		hit50++;
		hitcount++;
	}

	public void addHit10() {
		combocount++;
		hit10++;
		hitcount++;
	}

	public void addMiss() {
		this.combocount = 0;
		misscount++;
		hp -= HP_reduction;
		hitcount++;
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
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
}
