package Audio;

public class Music {
	private /*soundtype*/ music;
	
	static {
		ClassLoader loader = GameManager.class.getClassLoader();
//		try {
//			acCollect = Applet.newAudioClip((loader.getResource("res/se/collect.wav")).toURI().toURL());
//			acShoot = Applet.newAudioClip((loader.getResource("res/se/shoot.wav")).toURI().toURL());
//		} catch (Exception e) {
//		}

	}

	public static void playSound(String identifier) {
//		if (identifier.equalsIgnoreCase("shoot")) {
//			acShoot.play();
//
//		} else {
//			acCollect.play();
//		}
	}

}
