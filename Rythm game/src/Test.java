import Audio.NowPlaying;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NowPlaying now = new NowPlaying();
		now.play();
		while(true){
			System.out.println(now.getTime());
			now.update();
			
		}
			

	}

}
