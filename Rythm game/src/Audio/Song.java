package Audio;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;


public class Song {
	private static Music song;
	private boolean isPlay = false;

	
	
	public Song(String SongURL) {
		super();
		this.isPlay = false;
		TinySound.init();
		song = TinySound.loadMusic(SongURL);

	}

	public void play() {
		song.play(false); //no looping
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
	public void stop(){
		song.pause();
		song.rewind();
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