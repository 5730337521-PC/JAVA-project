package Logic;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;

import Audio.HitSound;
import Audio.NowPlaying;
import Beatmap.Beatmap;
import Beatmap.LongNote;
import Beatmap.ShortNote;
import Beatmap.TargetObject;
import Graphic.DrawingUtility;
import Graphic.GameAnimation;
import Graphic.GameBackground;
import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Utility.InputUtility;
import ui.GameManager;

public class MainLogic implements IRenderableHolder, IGameLogic {

	// All renderable objects
	private GameBackground background;
	private PlayerStatus player;
	private Beatmap map;
	private NowPlaying now;
	private HitSound h;
	private List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
	private List<GameAnimation> onScreenAnimation = new ArrayList<GameAnimation>();
	
	private int lastind = -1;

	private boolean readyToRender = false; // For dealing with synchronization

	// Called before enter the game loop
	public synchronized void onStart() {
		background = new GameBackground();
		player = new PlayerStatus();
		map = new Beatmap("res/map/test3.txt", 2, 1); // 2 sec
		readyToRender = true;
		now = new NowPlaying();
		h = new HitSound();
		now.play();
		System.out.println("onstart");
	}

	// Called after exit the game loop
	public synchronized void onExit() {
		readyToRender = false;
		onScreenObject.clear();
		GameManager.goToTitle();
	}

	public void logicUpdate() {
		System.err.println(now.getTime());

		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
			if (player.isPause()) {
				now.play();
			}
			player.setPause(!player.isPause());

		}
		if (player.isPause()) {
			now.pause();
			return;
		}

		// update time
		now.update();

		// Update background according to HP
		background.updateBackground(player.getHp());

		// Time up
		if (now.getTime() >= 130020) {
			now.stop();
			JOptionPane.showMessageDialog(null, "SCORE :" + player.getScore());
			onExit();
			// HighScoreUtility.recordHighScore(player.getScore());

			return;
		}
		if (player.getHp() <= 0) {
			now.stop();
			h.play(4);
			JOptionPane.showMessageDialog(null, "SCORE :" + player.getScore());
			onExit();
			// HighScoreUtility.recordHighScore(player.getScore());
			return;
		}

		// System.out.println(map.getnote().getSpawntime());
		// System.out.println(now.getTime() - map.getnote().getSpawntime());

		// if it time to spawn
		if (now.getTime() - map.getnote().getSpawntime() >= 0) {
			if (map.getTargetIndex() != lastind) {
				onScreenObject.add(map.getnote()); // add note to screen
				lastind = map.getTargetIndex();
				System.err.println("addnote");
			}

			System.out.println("X = " + map.getnote().getX() + " Y = " + map.getnote().getY() + " R = "
					+ map.getnote().getRadius() + "Speed = " + map.getnote().getSpeedY());

			if (map.getTargetIndex() <= map.getSize() - 2) {
				map.NextTargetIndex(); // point to next note then repeat
			}
		}

		// Shoot
		TargetObject target = null;
		if (player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())) { // mouse
			target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY());
			if (target != null) { // on something
				if ((InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))) {
					// on somthing & shoot
					player.shoot();
					if (target instanceof ShortNote) {
						onScreenAnimation.add(
								DrawingUtility.createHit((int) target.getX(), (int) target.getY(), target.hit(player)));
						onScreenAnimation
								.add(DrawingUtility.createFireworkAt((int) target.getX(), (int) target.getY()));
					} else if (target instanceof LongNote) {
						target.hit(player);
					}
				}
			} else { // on notihng
				if ((InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))) {
					if (!player.isLasthitismiss()) {
						player.addMiss();
						onScreenAnimation
								.add(DrawingUtility.createMissHit(InputUtility.getMouseX(), InputUtility.getMouseY()));
					}
				}
			}
		}

		// Update onScreenObject
		for (TargetObject obj : onScreenObject) {
			obj.move(now, player);
			if (obj instanceof LongNote) {
				if (((LongNote) obj).isPlayanimation()) {
					onScreenAnimation.add(DrawingUtility.createMAXHit((int) obj.getX(), (int) obj.getY()));
					onScreenAnimation.add(DrawingUtility.createExplosionAt((int) obj.getX(), (int) obj.getY()));
					((LongNote) obj).setPlayanimation(false);
				}
			}
			System.out.println("size = " + onScreenObject.size());
		}

		// Update animation
		for (GameAnimation obj : onScreenAnimation) {
			obj.updateAnimation();
		}

		player.update();

		// Remove Destroyed obj
		for (int i = onScreenObject.size() - 1; i >= 0; i--) {
			if (onScreenObject.get(i).isDestroy())
				onScreenObject.remove(i);
		}
		for (int i = onScreenAnimation.size() - 1; i >= 0; i--) {
			if (!onScreenAnimation.get(i).isVisible())
				onScreenAnimation.remove(i);
		}

	}

	private TargetObject getTopMostTargetAt(int x, int y) {
		TargetObject obj = null;
		for (TargetObject target : onScreenObject) {
			if (target.contains(x, y)) {
				if (obj != null) {
					if (target.getZ() > obj.getZ()) {
						obj.setPointerOver(false);
						obj = target;
						obj.setPointerOver(true);
					}
				} else {
					obj = target;
					obj.setPointerOver(true);
				}
			} else {
				target.setPointerOver(false);
			}
		}
		return obj;
	}

	@Override
	public synchronized List<IRenderableObject> getSortedRenderableObject() {
		List<IRenderableObject> sortedRenderable = new ArrayList<IRenderableObject>();
		if (!readyToRender)
			return sortedRenderable;
		for (TargetObject object : onScreenObject) {
			sortedRenderable.add(object);
		}
		for (GameAnimation object : onScreenAnimation) {
			sortedRenderable.add(object);
		}
		sortedRenderable.add(player);
		sortedRenderable.add(background);

		Collections.sort(sortedRenderable, new Comparator<IRenderableObject>() {
			@Override
			public int compare(IRenderableObject o1, IRenderableObject o2) {
				if (o1.getZ() > o2.getZ())
					return 1;
				else if (o1.getZ() < o2.getZ())
					return -1;
				else
					return 0;
			}
		});
		return sortedRenderable;
	}
}
