package Beatmap;

public class ShortNote implements Note{
	protected int timeing;

	public ShortNote(int timeing) {
		super();
		this.timeing = timeing;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return timeing;
	}

}
