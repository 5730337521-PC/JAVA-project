import Audio.HitSound;
import Audio.NowPlaying;

public class Test {

 public static void main(String[] args) throws InterruptedException {
  // TODO Auto-generated method stub
  NowPlaying now = new NowPlaying();
  HitSound h = new HitSound();
  h.play(4);
  now.play();
  while(true){}
 }
}