package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.HitSound;
import Graphic.TitleBackground;
import Logic.IGameLogic;
import Logic.MainLogic;

public class GameTitle extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton play, option, center;
	private TitleBackground background = new TitleBackground();
//	protected static BufferedImage bg = DrawingUtility.getImage("res/img/bg/title.jpg");
	public boolean isclick;
	IGameLogic logic = new MainLogic();
	GameScreen gc;

	public GameTitle() {
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		repaint();
		HitSound h = new HitSound();

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
				background.getTitleBG(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				background.getTitleBG(1);
				h.play(3);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// start game
				GameManager.newGame();
				h.play(5);
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
				background.getTitleBG(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				background.getTitleBG(2);
				h.play(3);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// option panel
				h.play(5);
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
				background.getTitleBG(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				background.getTitleBG(3);
				h.play(3);
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
		g.drawImage(TitleBackground.getBgImage(), 0, 0, null);
	}
}