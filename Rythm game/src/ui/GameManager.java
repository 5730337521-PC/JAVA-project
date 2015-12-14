package ui;

import java.util.ArrayList;
import java.util.List;

import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Utility.InputUtility;
import Logic.IGameLogic;


public class GameManager{
	public static GameWindow frame;
	public static GameScreen gc;
	public static GameTitle gt;
	private static boolean Ingame = false;
	

	public static boolean isIngame() {
		return Ingame;
	}

	public static void setIngame(boolean ingame) {
		Ingame = ingame;
	}

	public static void rungame(IGameLogic gameLogic) {
	
		gt = new GameTitle();
		if(gameLogic instanceof IRenderableHolder){
			gc = new GameScreen((IRenderableHolder)gameLogic);
		}else{
			gc = new GameScreen(new IRenderableHolder() {
				private List<IRenderableObject> emptyList = new ArrayList<IRenderableObject>(0);
				@Override
				public List<IRenderableObject> getSortedRenderableObject() {
					return emptyList;
				}
			});
		}
		frame = new GameWindow(gt);

		while (true) {
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.getCurrentScene().repaint();

			if (frame.getCurrentScene() instanceof GameScreen) {
				if (!Ingame) {
//					AudioUtility.playSound("GameSound");
					Ingame = true;
				}
				gameLogic.logicUpdate();
				InputUtility.postUpdate();
			}
		}
	}

}
