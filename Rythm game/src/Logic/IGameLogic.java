package Logic;

import Beatmap.BeatmapException;

public interface IGameLogic {
	public void onStart() throws BeatmapException;
	public void logicUpdate();
	public void onExit();
}
