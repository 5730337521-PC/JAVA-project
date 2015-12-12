package Logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import Graphic.GameScreen;
import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;

public class GameManager {

	// private static GameTitle titleScene;
	private static GameScreen gameScreen;
	// private static GameWindow gameWindow;

	public static void runGame(IGameLogic gameLogic) {
		// titleScene = new GameTitle();

		if (gameLogic instanceof IRenderableHolder) {
			gameScreen = new GameScreen((IRenderableHolder) gameLogic);
		} else {
			gameScreen = new GameScreen(new IRenderableHolder() {
				private List<IRenderableObject> emptyList = new ArrayList<IRenderableObject>(0);

				@Override
				public List<IRenderableObject> getSortedRenderableObject() {
					return emptyList;
				}
			});
		}

		// gameWindow = new GameWindow(titleScene);

		// GameloopUtility.runGameLoop(gameLogic, gameWindow, titleScene,
		// gameScreen);
	}

	public static void goToTitle() {
		// GameloopUtility.goToTitle();
	}

	public static void newGame() {
		// GameloopUtility.newGame();
	}

	/*
	 * public static void resizeScreen(){ gameScreen.applyResize();
	 * gameWindow.setSize(ConfigurableOption.screenWidth,
	 * ConfigurableOption.screenHeight); titleScene.validate(); }
	 */
}