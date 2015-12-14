package Audio;

//import java.applet.Applet;
//import java.applet.AudioClip;
//
////import org.newdawn.slick.openal.Audio;
////import org.newdawn.slick.openal.AudioLoader;
////import org.newdawn.slick.util.ResourceLoader;
//
//import Graphic.IRenderableHolder;
//
//public class HitSound {
////	private static Audio HitSound1;
//	public static AudioClip coinSound;
//	public static AudioClip acShoot;
////	private /*soundtype*/ HitSound2;
////	private /*soundtype*/ HitSound3;
////	private /*soundtype*/ HitSound4;
//	
//	static {
//		try {
////			HitSound1 = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/sound/test.ogg"));
////			acShoot = Applet.newAudioClip(arg0)
//			ClassLoader cloader = IRenderableHolder.class.getClassLoader();
//			coinSound = Applet.newAudioClip((cloader.getResource("res/sound/yyy.wav")).toURI().toURL());;		
//			acShoot = Applet.newAudioClip((cloader.getResource("res/sound/shoot.wav")).toURI().toURL());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}



import java.applet.Applet;
import java.applet.AudioClip;

public class HitSound {

	private static AudioClip acShoot;
	private static AudioClip acCollect;

	static {
		ClassLoader load = HitSound.class.getClassLoader();
		acShoot = Applet.newAudioClip(load.getResource("res/sound/2ne1.mp3"));
		acCollect = Applet.newAudioClip(load.getResource("res/se/collect.wav"));
	}

	public static void playSound(String identifier) {
		if (identifier.equalsIgnoreCase("shoot")) {
			acShoot.play();
		} else
			acCollect.play();
	}
}
