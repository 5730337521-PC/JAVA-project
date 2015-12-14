package Audio;

public class NowPlaying {
	public final int songduration = 210011;
	public int startingtime;
	public int songposition;
	public Song music; // music

	public NowPlaying(Song music) {
		music.play();
		startingtime = (int) System.currentTimeMillis();
		songposition = 0;
	}

	public void update() {
		songposition = (int) System.currentTimeMillis() - startingtime;

	}

	public int getTime() {
		return songposition;
	}
}