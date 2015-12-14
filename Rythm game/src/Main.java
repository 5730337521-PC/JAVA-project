import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.SliderUI;

import Audio.HitSound;
import Graphic.GameScreen;
import ui.GameTitle;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final HitSound h = new HitSound();
		GameScreen g = new GameScreen(null);
//		final HitSound h1 = new HitSound();
//		long starttime = System.currentTimeMillis();
//		long x;
//

		JFrame j = new JFrame("Rhythm Ta");
		j.setResizable(false);
		j.add(g);
		j.setVisible(true);
		j.pack();
		new Runnable() {
			public void run() {
				h.coinSound.play();
			}
		}.run();
		
//		while (!g.isclick) {
//			x = System.currentTimeMillis()-starttime;
//			System.out.println(x);
//			if(x >= 22422){
//				new Runnable() {
//					public void run() {
//						h1.acShoot.play();
//					}
//				}.run();
//			}
//			if(x >= 23720){
//				new Runnable() {
//					public void run() {
//						h1.acShoot.play();
//					}
//				}.run();
//			}
//			if(x >= 25017){
//				new Runnable() {
//					public void run() {
//						h1.acShoot.play();
//					}
//				}.run();
//			}
//			
//		}
//		
//
	}

}
