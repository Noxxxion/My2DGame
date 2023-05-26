package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;

	final int tileSize = originalTileSize * scale; // 48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixel
	final int screenHeight = tileSize * maxScreenRow; // 576 pixel

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	// Set player's default Position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		while (gameThread != null) {
			update();
			repaint();
		}
	}

	public void update() {
		if (keyH.upPressed == true) {
			playerY -= playerSpeed;
		} 
		else if (keyH.downPressed == true) {
			playerY += playerSpeed;
		} 
		else if (keyH.leftPressed == true) {
			playerX -= playerSpeed;
		} 
		else if (keyH.rightPressed == true) {
			playerX += playerSpeed;
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);

		g2.dispose();
	}
}
