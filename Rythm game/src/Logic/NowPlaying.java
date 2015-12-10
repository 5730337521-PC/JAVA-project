package Logic;

import Audio.MusicControl;
import Beatmap.Beatmap;

public class NowPlaying {
	public float bmp;
	public Beatmap beatmap; //timning //duration //note number //acc
	public float songposition;
	public float timing;
	public MusicControl music; //music
	
	public NowPlaying(float bmp, Beatmap beatmap, float songposition, float timing, MusicControl music) {
		super();
		this.bmp = bmp;
		this.beatmap = beatmap;
		this.songposition = songposition;
		this.timing = timing;
		this.music = music;
	}

	public float getSongposition() {
		return songposition;
	}

	public float getTime() { //not fin
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
