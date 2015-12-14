package Beatmap;

import Audio.NowPlaying;
import Graphic.IRenderableObject;
import Logic.PlayerStatus;

public abstract class TargetObject implements IRenderableObject {
	protected int x, y, z;
	protected final static int MAX_Radius = 200, MAX_Y = 50, MIN_Y = 640; // ค่อยคิดละกัน
	protected static int radius;
	private boolean isDestroy;
	protected boolean isPointerOver;
	protected boolean isHit;
	protected static int speedradius, speedY;
	protected float hitDuration, timing;
	

	public TargetObject(int x, int z, float hitDuration, float timing) {
		this.x = x;
		this.y = MIN_Y;
		this.z = z;
		this.setDestroy(false);
		this.isPointerOver = false;
		this.hitDuration = hitDuration;
		this.timing = timing;
		this.isHit = false;
	}

	public boolean isPointerOver() {
		return isPointerOver;
	}

	public void setPointerOver(boolean isPointerOver) {
		this.isPointerOver = isPointerOver;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public abstract void hit(PlayerStatus player, NowPlaying now);
	public abstract void move();

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}
	
	

}
