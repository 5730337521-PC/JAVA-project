package Logic;

public class PlayerStatus {
	private static final int MAX_score = 200;

	private int songpercent;
	private int score;
	private float accuracy;

	private int combocount;
	private int maxcombo;
	
//	private boolean  = false;
	private int lasthit; //miss,10,50,MAX 0,1,2,3
	private int hitscore[] = {0,MAX_score/10,MAX_score/2,MAX_score}; //last hittype miss,10,50,MAX

	private int hitcount;
	private int maxhit; // 80+ MAX_score
	private int hit50; // MAX_score/2
	private int hit10; // MAX_score/10
	private int misscount; // 0

	public PlayerStatus() {
		this.songpercent = 0;
		this.score = 0;
		this.accuracy = 100;
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

	public void update(){
		this.accuracy = getaccuracy();
		this.
		scoreupdate();
		
	}

	public void scoreupdate() {
		score += hitscore[lasthit]*combocount;
	}

	public int getCombocount() {
		return combocount;
	}

	public void addCombocount() {
		combocount++;
	}

	public int getMaxcombo() {
		return maxcombo;
	}

	public void addMaxhit() {
		maxhit++;
	}

	public void addHit50() {
		hit50++;
	}

	public void addHit10() {
		hit10++;
	}

	public void addMiss() {
		misscount++;
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
}
