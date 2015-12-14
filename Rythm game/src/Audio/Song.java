package Audio;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;


public class Song {
	private static Music song;
	private boolean isPlay = false;

	static {
		TinySound.init();
		song = TinySound.loadMusic("res/sound/music.wav");

	}

	public void play() {
		song.play(true);
		setPlay(true);
	}

	public void pause() {
		song.pause();
		setPlay(false); 
	}

	public void resume() {
		song.resume();
		setPlay(true);
	}

	public void shutdown() {
		TinySound.shutdown();
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}

}