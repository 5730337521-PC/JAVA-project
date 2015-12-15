package ui;

import javax.swing.JPanel;

import Beatmap.BeatmapException;
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

	public static boolean isIngame() {
		return Ingame;
	}

	public static void setIngame(boolean ingame) {
		Ingame = ingame;
	}

	public static void rungame(IGameLogic gameLogic) throws BeatmapException {
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
			if (nextScene instanceof GameScreen && !Ingame){
				System.out.println("onstart");
				gameLogic.onStart();
				Ingame = true;
				frame.switchScene(nextScene);
			}
			nextScene = null;
		}
		
		
	}
	public static void goToTitle(){
		nextScene = gt;
	}
	
	public static void newGame(){
		nextScene = gc;
	}
	
	
}
