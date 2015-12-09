package Beatmap;

public class Beatmap {
	private int notecount;
	private int accuracy; //offset to perfect
	
	private Note thisNote;
	private Note []NoteList;

	public Beatmap(int accuracy) { //load txt life
		super();
		this.notecount = 0;
		this.accuracy = accuracy;
		// load all note
	}	
	
	public Note NextNote(){
		notecount++;
		return NoteList[notecount];
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public Note getThisNote() {
		return thisNote;
	}

	public void setThisNote(Note thisNote) {
		this.thisNote = thisNote;
	}

	public Note[] getNoteList() {
		return NoteList;
	}

	public void setNoteList(Note[] noteList) {
		NoteList = noteList;
	}
}
