package Beatmap;

public class LongNote implements Note {
	protected int duration;// hold time
	protected int timeing; // when to hit

	public LongNote(int duration, int timeing) {
		super();
		this.duration = duration;
		this.timeing = timeing;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
