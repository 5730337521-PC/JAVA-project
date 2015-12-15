package Beatmap;

import Audio.NowPlaying;
import Graphic.IRenderableObject;
import Logic.PlayerStatus;

public abstract class TargetObject implements IRenderableObject {
	protected final static int YSTART = 500, INTRAD = 20;
	protected final static int TICKRATE = 60; // 1/60 per frame
	protected final static int SPEEDRADIUS = 30; // unit/s

	protected float x, y, radius;
	protected int z;
	protected float speedY; // unit/sec
	protected int GRAVITY = 500; // unit/s2 200-850 only

	protected boolean isDestroy, isPointerOver, isOnscreen;

	protected float hitDuration; // sec
	protected float timing, spawntime; // ms

	public TargetObject(int x, int z, float hitDuration, float timing, int GRAVITY) {
		super();
		this.x = x;
		this.y = YSTART;
		this.z = z;
		this.radius = INTRAD;
		this.speedY = GRAVITY * hitDuration / 2;
		this.isDestroy = false;
		this.isPointerOver = false;
		this.hitDuration = hitDuration;
		this.timing = timing;
		this.spawntime = this.timing - (this.hitDuration / 2);
		this.isOnscreen = true;
		this.GRAVITY = GRAVITY;
	}

	public boolean isOnscreen() {
		if (x + radius <= 0 || y + radius <= 0 || x - radius >= 800 || y - radius >= 600) {
			return false;
		} else {
			return true;
		}
	}

	public boolean contains(int x, int y) {
		return Math.hypot(x - this.x, y - this.y) <= radius + 6;
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
		return z;
	}

	public abstract void hit(PlayerStatus player, NowPlaying now);

	public abstract void move();

	public float getSpawntime() {
		return spawntime;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getRadius() {
		return radius;
	}

}
