package Beatmap;

public class LongNote implements Note {
	protected float duration;// hold time
	protected float timeing; // when to hit

	public LongNote(int duration, int timeing) {
		super();
		this.duration = duration;
		this.timeing = timeing;
	}

	@Override
	public float getTime() {
		// TODO Auto-generated method stub
		return timeing;
	}

}
