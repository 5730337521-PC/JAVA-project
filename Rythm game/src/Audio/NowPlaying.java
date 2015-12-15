package Audio;

import java.util.ArrayList;
import java.util.Iterator;

public class NowPlaying {
	private final int songduration = 130011;
	private ArrayList<Integer> startingtime = new ArrayList<Integer>();
	private ArrayList<Integer> pausetime = new ArrayList<Integer>();
	private static int songposition;
	private Song music;; // music
	public boolean isPause = true;

	public NowPlaying() {
		songposition = 0;
		music = new Song("res/sound/music.wav");
		music.setPlay(false);
	}

	public void play() {
		music.play();
		startingtime.add((int) System.currentTimeMillis());
		isPause = false;
	}

	public void pause() {
		music.pause();
		pausetime.add((int) System.currentTimeMillis());
		isPause = true;

	}

	public void stop() {
		music.stop();

	}

	public void update() { // update song pos thread
		songposition = 0;
		if (isPause) {
			for (int i = 0; i < pausetime.size(); i++) {
				songposition += pausetime.get(i) - startingtime.get(i);
			}
		} else {
			for (int i = 0; i < pausetime.size(); i++) {
				songposition += pausetime.get(i) - startingtime.get(i);
			}
			songposition += ((int) System.currentTimeMillis() - startingtime.get(startingtime.size()-1));

		}
		System.out.println(songposition);

	}

	public int getTime() {
		return songposition;
	}

	public int getSongduration() {
		return songduration;
	}
}