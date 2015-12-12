package Logic;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Beatmap.TargetObject;
import Graphic.GameAnimation;
import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Utility.InputUtility;

public class MainLogic implements IRenderableHolder, IGameLogic {

	// All renderable objects
//	private GameBackground background;
	private PlayerStatus player;
	private List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
//	private List<GameAnimation> onScreenAnimation = new ArrayList<GameAnimation>();

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
//		background = new GameBackground();
		player = new PlayerStatus();
		readyToRender = true;
	}

	// Called after exit the game loop
	public synchronized void onExit() {
		readyToRender = false;
		onScreenObject.clear();
	}

	public void logicUpdate() {
		// Paused
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
			player.setPause(!player.isPause());
		}

		if (player.isPause()) {
			return;
		}

		// Update moving background
//		background.updateBackground();

//		// Time up
//		if (player.getRemainingTime() == 0) {
//			HighScoreUtility.recordHighScore(player.getScore());
//			GameManager.goToTitle();
//			return;
//		}
//		player.decreaseRemainingTime(1);

		// Create random target
		createTarget();

		// Shoot and grab
		TargetObject target = null;
		TargetObject grabbedObject = null;
		if (!player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())) {

			boolean shoot = false;
			if ((InputUtility.isMouseLeftClicked() || InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))
					&& player.getCurrentGun() != null && player.getCurrentGun().canShoot()) {

				/* fill code2 */
				player.getCurrentGun().shoot();

				onScreenAnimation.add(
						DrawingUtility.createShootingAnimationAt(InputUtility.getMouseX(), InputUtility.getMouseY()));
				shoot = true;
			}

			target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY());
			if (target != null) {

				/* fill code3 */
				if (target instanceof CollectibleObject) {
					((CollectibleObject) target).grab(player);
				} else if (target instanceof ShootableObject) {
					if (shoot) {
						((ShootableObject) target).hit(player);
					}
				}
			}

			if (shoot && player.getCurrentGun() instanceof SpecialGun
					&& ((SpecialGun) player.getCurrentGun()).getBulletQuantity() <= 0) {

				/* fill code4 */
				player.setCurrentGun(new Gun(1));
			}
		}

		// Update target object
		for (TargetObject obj : onScreenObject) {
			if (obj instanceof CollectibleObject && grabbedObject != obj) {
				((CollectibleObject) obj).ungrab(); // Ungrab
			}
			obj.move();
		}

		// Update animation
		for (GameAnimation obj : onScreenAnimation) {
			obj.updateAnimation();
		}

		// Remove unused image
		for (int i = onScreenObject.size() - 1; i >= 0; i--) {
			if (onScreenObject.get(i).isDestroyed)
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

			// Random target type
			
			int rd = RandomUtility.random(1, 100);
			System.out.println(rd);
			TargetObject e = null;
			if(rd <= 15) {
				if(player.getCurrentGun() instanceof SpecialGun) e = new ItemBullet(movingDuration, zCounter);
				else e = new ItemSpecialGun(movingDuration, zCounter);
			}
			if(rd > 15 && rd <= 35) e = new SplitterTarget(40, movingDuration, zCounter, onScreenObject);
			if(rd>35 && rd <= 70) e = new SimpleTarget(30, movingDuration, zCounter);
			if(rd > 70 && rd<=100) e = new SmallTarget(15, movingDuration, zCounter);
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
