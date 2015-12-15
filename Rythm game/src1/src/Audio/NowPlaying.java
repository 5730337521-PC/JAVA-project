package Audio;

import java.util.ArrayList;

public class NowPlaying {
	private final int songduration = 130011;
	private ArrayList<Long> startingtime = new ArrayList<Long>();
	private ArrayList<Long> pausetime = new ArrayList<Long>();
	private static int songposition;
	private Song music;; // music
	public boolean isPause = true;

	public NowPlaying() {
		songposition = 0;
		music = new Song("res/sound/music.wav");
		music.setPlay(false);
	}

	public void play() {
		if (!isPause) {
			return;
		}
		music.play();
		startingtime.add(System.currentTimeMillis());
		isPause = false;
		// System.err.println("startingtime = "+ startingtime );
	}

	public void pause() {
		if (isPause) {
			return;
		}
		music.pause();
		pausetime.add(System.currentTimeMillis());
		isPause = true;
		// System.err.println("pausetime = "+ pausetime );

	}

	public void stop() {
		music.stop();

	}

	public void update() { // update song pos thread
		songposition = 0;
		try {
			if (isPause) {
				for (int i = 0; i < pausetime.size() - 1; i++) {
					songposition += pausetime.get(i) - startingtime.get(i);
					// System.out.println("pausetime= "+ pausetime.get(i)+ "-"
					// +" startingtime= "+startingtime.get(i));
				}
			} else {
				for (int i = 0; i < startingtime.size() - 1; i++) {
					songposition += pausetime.get(i) - startingtime.get(i);
					// System.out.println("pausetime= "+ pausetime.get(i)+ "-"
					// +" startingtime= "+startingtime.get(i));
				}
				songposition += (int) (System.currentTimeMillis() - startingtime.get(startingtime.size() - 1));

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getTime() {
		return songposition;
	}

	public int getSongduration() {
		return songduration;
	}
}