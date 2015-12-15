package ui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel currentScene;

	public GameWindow(JPanel scene) {
		super("BEAT WAR!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		pack();
		setVisible(true);
		currentScene.requestFocus();
	}

	protected void switchScene(JPanel scene) {
		getContentPane().remove(currentScene);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		getContentPane().validate();
		pack();
		currentScene.requestFocus();
	}

	public JPanel getCurrentScene() {
		return currentScene;
	}
	
}