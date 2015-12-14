package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.HitSound;
import Graphic.DrawingUtility;
import Graphic.GameAnimation;
import Graphic.IRenderableHolder;
import Graphic.IRenderableObject;
import Logic.IGameLogic;
import Logic.MainLogic;
import Utility.InputUtility;

public class GameTitle extends JPanel {
	;
	private JButton play, option, center;
	protected static BufferedImage bg = DrawingUtility.getImage("res/img/bg/title.jpg");
	public boolean isclick;
	IGameLogic logic = new MainLogic();
	GameScreen gc;

	public GameTitle() {
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		repaint();

		/** add play button picture **/
		play = new JButton();
		play.setBorderPainted(false);
		play.setContentAreaFilled(false);
		play.setFocusPainted(false);
		play.setOpaque(false);
		play.setBounds(571, 168, 191, 191);
		play.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				bg = DrawingUtility.getImage("res/img/bg/title.jpg");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				bg = DrawingUtility.getImage("res/img/bg/play.jpg");
				// HitSound.playSound("shoot");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// start game
				if (logic instanceof IRenderableHolder) {
					gc = new GameScreen((IRenderableHolder) logic);
					System.out.println("this");
				} else {
					gc = new GameScreen(new IRenderableHolder() {
						private List<IRenderableObject> emptyList = new ArrayList<IRenderableObject>(0);

						@Override
						public List<IRenderableObject> getSortedRenderableObject() {
							System.out.println("this");
							return emptyList;
						}
					});
				}
				System.out.println("that");
				GameManager.frame.switchScene(gc);
			}
		});

		option = new JButton();
		option.setBorderPainted(false);
		option.setContentAreaFilled(false);
		option.setFocusPainted(false);
		option.setOpaque(false);
		option.setBounds(123, 339, 171, 171);
		option.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				bg = DrawingUtility.getImage("res/img/bg/title.jpg");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				bg = DrawingUtility.getImage("res/img/bg/option.jpg");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// option panel
			}
		});

		center = new JButton();
		center.setBorderPainted(false);
		center.setContentAreaFilled(false);
		center.setFocusPainted(false);
		center.setOpaque(false);
		center.setBounds(217, 101, 365, 365);
		center.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				bg = DrawingUtility.getImage("res/img/bg/Title.jpg");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				bg = DrawingUtility.getImage("res/img/bg/center.jpg");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// credit
				JOptionPane.showMessageDialog(null, "BEAT WAR!");

			}
		});

		this.setDoubleBuffered(true);
		this.add(option);
		this.add(play);
		this.add(center);

	}

	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
	}
}