import Beatmap.BeatmapException;
import Logic.IGameLogic;
import Logic.MainLogic;
import ui.GameManager;

public class Main {

	public static void main(String[] args) throws BeatmapException {
		// TODO Auto-generated method stub
		IGameLogic logic = new MainLogic();
		GameManager.rungame(logic);
	}

}
