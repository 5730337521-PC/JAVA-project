package ui;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logic.MainLogic;


public class GameManager{
	public static GameWindow frame;
	public static GameScreen gc;
	public static GameTitle gt;
	public static MainLogic gl;
	private static boolean Ingame = false;
	

	public static boolean isIngame() {
		return Ingame;
	}

	public static void setIngame(boolean ingame) {
		Ingame = ingame;
	}

	public static void rungame() {
	
		gt = new GameTitle();
		gc = new GameScreen(null);
		gl = new MainLogic();
		frame = new GameWindow(gt);

		while (true) {
			try {
				Thread.sleep(35);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.getCurrentScene().repaint();

			if (frame.getCurrentScene() instanceof GameScreen) {
				if (!Ingame) {
//					AudioUtility.playSound("GameSound");
					Ingame = true;
				}
				gl.logicUpdate();
			}
		}
	}

}
