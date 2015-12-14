package Beatmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Utility.Utility;

public class Beatmap {
	private ArrayList<TargetObject> map;
	private int targetIndex;

	private int hitduration;

	public Beatmap(String fileURL,int hitduration) {
		super();
		this.targetIndex = 0;
		this.hitduration = hitduration;
		load(fileURL);
	}



	public void load(String fileURL) {// res/map/
		File file = new File(fileURL);
		int type =0;
		int timing =0;
		int timingout=0;
		int z = Integer.MAX_VALUE-2; //MAX player, MAX-1 Animation
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				int index1 = line.indexOf(',');
				int index2 = line.indexOf(':');
				type = Integer.parseInt(line.substring(0, index1-1));
				if(type == 0){
					timing = Integer.parseInt(line.substring(index1+1,index2-1));
					timingout = timing;
					ShortNote sn = new ShortNote(Utility.random(100, 700), z, hitduration, timing);
					map.add(sn);
				}
				else{
					timing = Integer.parseInt(line.substring(index1+1,index2-1));
					timingout = Integer.parseInt(line.substring(index2+1));
					LongNote ln = new LongNote(Utility.random(100, 700), z, hitduration, timing, timingout);
					map.add(ln);
				}
				z--;
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.println("loadfail");
		}
	}
	
	public TargetObject getnote(){
		return map.get(targetIndex);
		
	}
	
	public void NextTargetIndex(){
		targetIndex++;
	}
	
	public int getTargetIndex() {
		return targetIndex;
	}

}
