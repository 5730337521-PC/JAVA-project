import javax.swing.plaf.SliderUI;

import Audio.HitSound;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final HitSound h = new HitSound();
		final HitSound h1 = new HitSound();
		long starttime = System.currentTimeMillis();
		long x;

		new Runnable() {
			public void run() {
				h.coinSound.play();
			}
		}.run();
		while (true) {
			x = System.currentTimeMillis()-starttime;
			System.out.println(x);
			if(x >= 22422){
				new Runnable() {
					public void run() {
						h1.acShoot.play();
					}
				}.run();
			}
			if(x >= 23720){
				new Runnable() {
					public void run() {
						h1.acShoot.play();
					}
				}.run();
			}
			if(x >= 25017){
				new Runnable() {
					public void run() {
						h1.acShoot.play();
					}
				}.run();
			}
			
		}
		

	}

}
