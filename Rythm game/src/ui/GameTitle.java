package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.HitSound;
import Graphic.DrawingUtility;
import Graphic.GameAnimation;
import Utility.InputUtility;

public class GameTitle extends JPanel {;
	private JButton play,option,center;
	protected static BufferedImage bg = DrawingUtility.getImage("res/img/bg/title.jpg");
	public boolean isclick;

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
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//start game
				GameManager.frame.switchScene(new GameScreen(null));
				
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
				//option panel
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
				//credit
				JOptionPane.showMessageDialog(null,
					    "BEAT WAR!");

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