package Logic;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

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
	private List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
	private List<GameAnimation> onScreenAnimation = new ArrayList<GameAnimation>();

	private boolean readyToRender = false; // For dealing with synchronization

	// Called before enter the game loop
	public synchronized void onStart() {
		background = new GameBackground();
		player = new PlayerStatus();
		map = new Beatmap("res/map/test.txt", 2000); // 2 sec
		readyToRender = true;
		now = new NowPlaying();
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
		/*
		 * // Paused if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
		 * player.setPause(!player.isPause()); }
		 * 
		 * if (player.isPause()) { return; }
		 */

		// Update background according to HP

		// update time
		now.update();

		background.updateBackground(player.getHp());

		// Time up
		if (now.getTime() >= now.songduration) {
			onExit();
			// HighScoreUtility.recordHighScore(player.getScore());

			return;
		}
		// System.out.println(map.getnote().getSpawntime());
		// System.out.println(now.getTime() - map.getnote().getSpawntime());
		// if it time to spawn

		if (now.getTime() - map.getnote().getSpawntime() >= 0) {
			onScreenObject.add(map.getnote()); // add note to screen
			map.NextTargetIndex(); // point to next note then repeat
			HitSound h = new HitSound();
			h.play(0);
			System.out.println("spawn");
		}

		// Shoot
		TargetObject target = null;
		if (player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())) { // mouse
			// System.out.println(InputUtility.getKeyPressed(KeyEvent.VK_SPACE));
			if ((InputUtility.isMouseLeftClicked() || InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))) {
				// shot

				/*
				 * manually create beat map
				 * 
				 * try { BufferedWriter out = new BufferedWriter(new
				 * FileWriter("res/file.txt"));
				 * 
				 * System.out.println("HERE!" + now.getTime());
				 * 
				 * } catch (IOException e) { }
				 */
				player.shoot();
				target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY());
				if (target != null) { // hit something
					if (target instanceof ShortNote) {
						target.hit(player, now);
						onScreenAnimation.add(
								DrawingUtility.createFireworkAt(InputUtility.getMouseX(), InputUtility.getMouseY()));
					}
					if (target instanceof LongNote) {
						// target.hit(player, now);

					}
				} else { // hit notting
					player.addMiss();
					// animation miss
				}
			}
		}

		// Update target object
		for (TargetObject obj : onScreenObject) {
			obj.move();
		}

		// Update animation
		for (GameAnimation obj : onScreenAnimation) {
			obj.updateAnimation();
		}

		player.update();

		// Remove unused image
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
//			System.out.println("ADD!!");
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
