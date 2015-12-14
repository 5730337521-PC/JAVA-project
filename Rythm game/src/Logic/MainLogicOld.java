package Logic;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Beatmap.ShortNote;
import Beatmap.TargetObject;
import Graphic.DrawingUtility;
import Graphic.GameAnimation;
import Graphic.GameBackground;
import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Utility.InputUtility;

public class MainLogicOld implements IRenderableHolder, IGameLogic {

	// All renderable objects
	private GameBackground background;
	private PlayerStatus player;
	private List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
	private List<GameAnimation> onScreenAnimation = new ArrayList<GameAnimation>();

	/*
	 * Reserved z MIN_VALUE : background MAX_VALUE-1 : animation effect
	 * MAX_VALUE : player's status
	 */
	private int zCounter = Integer.MIN_VALUE + 1;
	private int nextObjectCreationDelay;
	private boolean readyToRender = false; // For dealing with synchronization
											// issue

	// Called before enter the game loop
	public synchronized void onStart() {
		// background = new GameBackground();
		player = new PlayerStatus();
		readyToRender = true;
	}

	// Called after exit the game loop
	public synchronized void onExit() {
		readyToRender = false;
		onScreenObject.clear();
	}

	public void logicUpdate() {
		/*
		 * // Paused if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
		 * player.setPause(!player.isPause()); }
		 * 
		 * if (player.isPause()) { return; }
		 */

		// Update moving background
		background.updateBackground();

		// // Time up
		// if (player.getRemainingTime() == 0) {
		// HighScoreUtility.recordHighScore(player.getScore());
		// GameManager.goToTitle();
		// return;
		// }

		// Create random target
		createTarget();

		// Shoot and grab
		TargetObject target = null;
		TargetObject grabbedObject = null;
		if (!player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())) {

			boolean shoot = false;
			if (((InputUtility.isMouseLeftClicked() || InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)))) {
				player.shoot();
				shoot = true;
			}

			target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY());
			if (target != null) {
				if (target instanceof ShortNote) {
					target.hit(player, now);
					onScreenAnimation
					.add(DrawingUtility.createFireworkAt(InputUtility.getMouseX(), InputUtility.getMouseY()));
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

	private void createTarget() {
		if (nextObjectCreationDelay > 0) {
			nextObjectCreationDelay--;
		} else {
			// Random next creation delay
			nextObjectCreationDelay = RandomUtility.random(ConfigurableOption.objectCreationMinDelay,
					ConfigurableOption.objectCreationMaxDelay);

			// Random moving duration
			int movingDuration = RandomUtility.random(ConfigurableOption.objectMinDuration,
					ConfigurableOption.objectMaxDuration);

			TargetObject e = null;
			e = new ShortNote(movingDuration, movingDuration, movingDuration, movingDuration);
			onScreenObject.add(e);
			// Increase z counter (so the next object will be created on top of
			// the previous one)
			zCounter++;
			if (zCounter == Integer.MAX_VALUE - 1) {
				zCounter = Integer.MIN_VALUE + 1;
			}
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
