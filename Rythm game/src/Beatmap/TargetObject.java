package Beatmap;

import Audio.NowPlaying;
import Graphic.IRenderableObject;
import Logic.PlayerStatus;

public abstract class TargetObject implements IRenderableObject {
	protected int x, y, z;
	protected final static int MAX_Radius = 200, Y_MAX = 50,Y_MIN = 640, GRAVITY = 150;
	protected int speedY;
	protected final static int speedradius = 100;
	protected final static int TICKRATE = 60; // 1/60 per frame
	protected static int radius;
	protected boolean isDestroy, isPointerOver, isHit, onScreen;
	protected float hitDuration, timing, spawntime; //ms

	public TargetObject(int x, int z, float hitDuration, float timing) {
		this.speedY = (int) (GRAVITY*hitDuration/2);
		this.x = x;
		this.y = Y_MIN;
		this.z = z;
		this.isDestroy = false;
		this.isPointerOver = false;
		this.hitDuration = hitDuration;
		this.timing = timing;
		this.isHit = false;
		this.spawntime = timing - hitDuration / 2;
		this.onScreen = false;
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
		return 0;
	}

	public abstract void hit(PlayerStatus player, NowPlaying now);

	public abstract void move();

	public float getSpawntime() {
		return spawntime;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

}
