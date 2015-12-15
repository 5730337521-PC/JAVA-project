package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Utility.InputUtility;



public class GameScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private IRenderableHolder renderableHolder;

	protected GameScreen(IRenderableHolder holder) {
		this.renderableHolder = holder;
		this.addListener();
		this.setPreferredSize(new Dimension(800, 600));
		setDoubleBuffered(true);
	}

	private void addListener() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setMouseLeftDown(false);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == 1) {
					InputUtility.setMouseLeftDown(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(true);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
				}

			}
		});
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(e.getKeyCode(), false);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				InputUtility.setKeyTriggered(e.getKeyCode(), true);

			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;

		// assign the background color to be "black"
		g2.setBackground(Color.BLACK);
		
		

		// clear all the objects
		Dimension dim = getSize();
		g2.clearRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

//		 reder all the objects
		for (IRenderableObject renderable : renderableHolder.getSortedRenderableObject()) {
			renderable.render(g2);
			System.out.println(renderable.getClass());
			System.out.println("render");
		}
	}}