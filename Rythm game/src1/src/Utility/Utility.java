package Utility;

import java.util.Random;

public class Utility {
	public static int random(int min, int max) {
		Random rand = new Random();
		int num = rand.nextInt(max - min) + min;
		return num;
	}
}
