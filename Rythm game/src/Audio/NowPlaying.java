package Audio;

public class NowPlaying {
	public final int songduration = 210011;
	public int startingtime;
	public static int songposition;
	public Song music; // music

	public NowPlaying() {
		music = new Song();
		songposition = 0;
		startingtime = 0;
	}

	public void play() {
		music.play();
		startingtime = (int) System.currentTimeMillis();
	}

	public void update() { //thread
		songposition = (int) System.currentTimeMillis() - startingtime;
		System.out.println(songposition);
	}

	public int getTime() {
		return songposition;
	}
}