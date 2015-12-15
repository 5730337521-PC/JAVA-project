package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import Audio.HitSound;
import Graphic.DrawingUtility;

public class Howto extends JPanel implements Runnable{
	private JButton back;
	private static int i;
	public Howto(){
	this.setPreferredSize(new Dimension(800, 600));
	this.setLayout(null);
	repaint();
	HitSound h = new HitSound();
	i=0;
	this.setVisible(true);


	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			while(i<6)
			{
				GameManager.frame.repaint();
				Thread.sleep(10);
				System.out.println("i: " + i);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Thread.interrupted();
		}
	}
	public void paintComponent(Graphics g){
		System.out.println(GameManager.thread.isAlive());
		if(GameManager.thread.isAlive()){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(DrawingUtility.getHowto(i), 0, 0, 800, 600,null);
			i++;
		}
	}
}