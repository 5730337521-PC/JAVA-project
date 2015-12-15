package Beatmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Utility.Utility;

public class Beatmap {
	private ArrayList<TargetObject> map;
	private int targetIndex;

	private int hitduration; // sec

	public Beatmap(String fileURL, int hitduration) {
		super();
		map = new ArrayList<TargetObject>();
		this.targetIndex = 0;
		this.hitduration = hitduration;
		load(fileURL);
	}

	public void load(String fileURL) {// res/map/
		File file = new File(fileURL);
		int type = 0;
		int timing = 0;
		int timingout = 0;
		String line = "";
		int z = Integer.MAX_VALUE - 2; // MAX player, MAX-1 Animation
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			line = br.readLine();
			while (line != null) {
				int index1 = line.indexOf(',');
				int index2 = line.indexOf(':');
				System.out.println(line);
				type = Integer.parseInt(line.substring(0, index1));
				if (type == 1) {
					timing = Integer.parseInt(line.substring(index1 + 1));
					timingout = timing;
					ShortNote sn = new ShortNote(Utility.random(100, 700), z, hitduration, timing);
					map.add(sn);
				} else {
//					timing = Integer.parseInt(line.substring(index1 + 1, index2));
//					timingout = Integer.parseInt(line.substring(index2 + 1));
					// LongNote ln = new LongNote(Utility.random(100, 700), z,
					// hitduration, timing, timingout);
					// map.add(ln);
				}
				line = br.readLine();
				z--;
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("loadfail");
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
				System.out.println("Loading finish");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public TargetObject getnote() {
		if (targetIndex < map.size())
			return map.get(targetIndex);
		return null;

	}

	public void NextTargetIndex() {
		targetIndex++;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

}
