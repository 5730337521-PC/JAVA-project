import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.SliderUI;

import Audio.HitSound;
import Logic.IGameLogic;
import Logic.MainLogic;
import ui.GameManager;
import ui.GameScreen;
import ui.GameTitle;
import ui.GameWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGameLogic logic = new MainLogic();
		GameManager.rungame(logic);
		if(GameManager.frame.getCurrentScene() == GameManager.gc)
		while(true){
			GameManager.gl.logicUpdate();
		}
		
	}

}
