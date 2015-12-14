import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.SliderUI;

import Audio.HitSound;
import ui.GameManager;
import ui.GameScreen;
import ui.GameTitle;
import ui.GameWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		final HitSound h = new HitSound();
//		GameWindow g = new GameWindow(new GameTitle());
////		final HitSound h1 = new HitSound();
////		long starttime = System.currentTimeMillis();
////		long x;
//
//		JFrame j = new JFrame("BEAT WAR!");
//		j.setResizable(false);
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.add(g);
//		j.setVisible(true);
//		j.pack();
		
		GameManager.rungame();
		/*new Runnable() {
			public void run() {
				HitSound.playSound("shoot");
			}
		}.run();*/
		
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
