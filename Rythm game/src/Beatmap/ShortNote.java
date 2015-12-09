package Beatmap;

public class ShortNote implements Note{
	protected float timeing;

	public ShortNote(int timeing) {
		super();
		this.timeing = timeing;
	}

	@Override
	public float getTime() {
		// TODO Auto-generated method stub
		return timeing;
	}

}
