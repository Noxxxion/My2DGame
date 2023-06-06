package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		// FIRST KEY
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		// SECOND KEY
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 39 * gp.tileSize;
		// THIRD KEY
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 9 * gp.tileSize;
		// FIRST DOOR
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 12 * gp.tileSize;
		gp.obj[3].worldY = 22 * gp.tileSize;
		// SECOND DOOR
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
		// THIRD DOOR
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 10 * gp.tileSize;
		gp.obj[5].worldY = 11 * gp.tileSize;
		// FINAL CHEST
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 10 * gp.tileSize;
		gp.obj[6].worldY = 8 * gp.tileSize;
		// FIRST BOOTS
		gp.obj[7] = new OBJ_Boots();
		gp.obj[7].worldX = 36 * gp.tileSize;
		gp.obj[7].worldY = 40 * gp.tileSize;
	}

}
