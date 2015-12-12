package Beatmap;

import java.util.ArrayList;

public class Beatmap {
	private ArrayList<TargetObject> map;
	private int targetIndex;

	public Beatmap(ArrayList<TargetObject> map) {
		super();
		for (int i = 0; i < 5; i++) {
			ShortNote a = new ShortNote(300, 1, 50, i * 100);
			map.add(a);
		}
	}

	public TargetObject getNexttarget() {
		return map.get(targetIndex++);
	}

	public TargetObject gettarget() {
		return map.get(targetIndex);
	}

}
