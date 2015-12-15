package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.xml.stream.events.StartDocument;

import Audio.HitSound;
import Graphic.IRenderableHolder;
import Utility.InputUtility;
import Logic.IGameLogic;

public class GameManager {
	private static final int REFRESH_DELAY = 16;

	public static GameWindow frame;
	public static GameScreen gc;
	public static GameTitle gt;
	private static boolean Ingame = false;
	private static JPanel nextScene = null;
	private static Howto howto;
	public static Thread thread;

	public static boolean isIngame() {
		return Ingame;
	}

	public static void setIngame(boolean ingame) {
		Ingame = ingame;
	}

	public static void rungame(IGameLogic gameLogic) {
		gt = new GameTitle();
		gc = new GameScreen((IRenderableHolder) gameLogic);
		frame = new GameWindow(gt);

		while (true) {
			try {
				Thread.sleep(REFRESH_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("running");
			System.out.println(frame.getCurrentScene());

			frame.getCurrentScene().repaint();
			if (frame.getCurrentScene() instanceof GameScreen) {
				System.out.println("updating logic");
				gameLogic.logicUpdate();
				InputUtility.postUpdate();
			}
			if (nextScene != null) {
				if (frame.getCurrentScene() instanceof GameScreen)
					gameLogic.onExit();
				Ingame = false;
				frame.switchScene(nextScene);
			}
			if (nextScene instanceof GameScreen && !Ingame) {
				System.out.println("onstart");
				gameLogic.onStart();
				Ingame = true;
				frame.switchScene(nextScene);
			}
			nextScene = null;
		}

	}

	public static void goToTitle() {
		nextScene = gt;
	}

	public static void newGame() {
		nextScene = gc;
	}

	public static void startThread() {
		thread = new Thread(howto);
		thread.start();
	}

	public static void runHowto() {
		howto = new Howto();
		frame.switchScene(howto);
		//howto.repaint();
		startThread();
		while (thread.isAlive()) {
			//frame.repaint();
		}
		if (!thread.isAlive()) {
			JButton back = new JButton();
			back.setBorderPainted(false);
			back.setContentAreaFilled(false);
			back.setFocusPainted(false);
			back.setOpaque(false);
			back.setBounds(640, 446, 132, 132);
			back.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					HitSound h = new HitSound();
					h.play(3);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					GameManager.goToTitle();
				}
			});
		}
	}

}
