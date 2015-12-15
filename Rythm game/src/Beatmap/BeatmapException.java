package Beatmap;

public class BeatmapException extends Exception{
	private int errorType;

	public BeatmapException(int errorType) {
		this.errorType = errorType;
	}

	@Override
	public String getMessage() {
		if (errorType == 0)
			return "No Beatmap found";
		else if (errorType == 1)
			return "Wrong record format";
		else
			return "";
	}
}
