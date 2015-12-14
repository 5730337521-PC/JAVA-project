package Audio;

public class NowPlaying {
	public final int songduration = 210011;
	public int startingtime;
	public static int songposition;
	public Song music; // music

	public NowPlaying() {
		music = new Song();
		songposition = 0;
	}

	public void play(){	
		music.play();
		startingtime = (int) System.currentTimeMillis();
	}

	public void update() {
		songposition = (int) System.currentTimeMillis() - startingtime;
	}

	public int getTime() {
		return songposition;
	}
}