package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		// Set Player Camera position to the Center
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		// Set Player Starting position on World Map
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		// Set Player Speed
		speed = 4;
		// Set Player initial Direction
		direction = "down";
	}

	public void getPlayerImage() {
		// setup(String imageName)
		up1 = setup("boy_up_1");
		up2 = setup("boy_up_2");
		down1 = setup("boy_down_1");
		down2 = setup("boy_down_2");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
	}

	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void update() {

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// CHECK TILE COLLISON
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;

				default:
					break;
				}
			}

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				gp.playSE(1);
				gp.ui.showMessage("You got a Key!");
				break;
			case "Door":
				if (hasKey > 0) {
					gp.obj[i] = null;
					gp.playSE(4);
					gp.ui.showMessage("You opened the door!");
					hasKey--;
				} else {
					gp.ui.showMessage("You need a Key!");
				}
				break;
			case "Boots":
				gp.ui.showMessage("Speed UP!");
				speed += 2;
				gp.playSE(3);
				gp.obj[i] = null;
				break;
			case "Chest":
				gp.obj[i] = null;
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.ui.showMessage("You found the Chest!");
				gp.playSE(2);
				break;
			}
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		default:
			break;
		}
		g2.drawImage(image, screenX, screenY,null);
	}
}
