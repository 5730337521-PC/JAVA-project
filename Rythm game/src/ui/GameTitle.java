package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Graphic.DrawingUtility;

public class GameTitle extends JPanel {;
	private JButton play,option;
	protected static final BufferedImage bg = DrawingUtility.getImage("res/img/bg/Title.jpg");
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
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Play");
			}
		});
		
		option = new JButton();
		  option.setBorderPainted(false); 
		  option.setContentAreaFilled(false);
		  option.setFocusPainted(false); 
		  option.setOpaque(false);
		option.setBounds(123, 339, 171, 171);
		option.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Option");
			}
		});
		this.add(option);
		this.add(play);
		

	}

	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
	}
}