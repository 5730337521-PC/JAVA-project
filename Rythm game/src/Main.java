import Logic.IGameLogic;
import Logic.MainLogic;
import ui.GameManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGameLogic logic = new MainLogic();
		GameManager.rungame(logic);
		if(GameManager.frame.getCurrentScene() == GameManager.gc)
		while(true){
			logic.logicUpdate();
		}
		
	}

}
