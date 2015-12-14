package Graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Logic.PlayerStatus;

public class GameBackground implements IRenderableObject{

	private BufferedImage bgImage,bgRed;
	private int currentX = 0;
	private int imageWidth;
	
	public GameBackground(PlayerStatus player){
		bgImage = DrawingUtility.bg;
		if(bgImage != null){
			imageWidth = bgImage.getWidth();
		}else{
			imageWidth = 0;
		}
	}
	
	public void updateBackground(){
		currentX++;
		if(currentX >= imageWidth){
			currentX = 0;
		}
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		if(bgImage == null) return;
		g2.drawImage(DrawingUtility.bg, 0, 0, null);
		}
	}