package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Graphic.DrawingUtility;
import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Utility.InputUtility;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 7247841512480622650L;
	private IRenderableHolder renderableHolder;
	protected static final BufferedImage bg = DrawingUtility.getImage("res/img/bg/blue.jpg");

	public GameScreen(IRenderableHolder holder) {
		this.renderableHolder = holder;
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		repaint();
		this.addListener();
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
				InputUtility.setMouseLeftDown(true);
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
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;

		// render all the objects
		for (IRenderableObject renderable : renderableHolder.getSortedRenderableObject()) {
			renderable.render(g2);
		}
	}
	
//	public void paint(Graphics g) {
//		g.drawImage(bg, 0, 0, null);
//	}
}
