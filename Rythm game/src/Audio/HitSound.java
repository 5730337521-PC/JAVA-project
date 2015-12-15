package Audio;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class HitSound {
	private static Sound HitSound0;
	private static Sound HitSound1;
	private static Sound HitSound2;
	private static Sound HitSound3;
	private static Sound HitSound4;

	static {
		try {
			TinySound.init();
			HitSound0 = TinySound.loadSound("res/sound/miss.wav"); //miss
			HitSound1 = TinySound.loadSound("res/sound/hit1.wav"); //hit
			HitSound2 = TinySound.loadSound("res/sound/hit2.wav"); //hit2
			HitSound3 = TinySound.loadSound("res/sound/hit3.wav"); //click
			HitSound4 = TinySound.loadSound("res/sound/hit4.wav"); //fail

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play(int type) {
		switch (type) {
		case 0:
			HitSound0.play();
			break;
		case 1:
			HitSound1.play();
			break;
		case 2:
			HitSound2.play();
			break;
		case 3:
			HitSound3.play();
			break;
		case 4:
			HitSound4.play();
			break;

		default:
			HitSound1.play();
			break;
		}
	}
	public void shutdown(){
		TinySound.shutdown();
	}
}
