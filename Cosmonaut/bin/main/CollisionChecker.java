package main;

import Entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.TileSize;
		int entityRightCol = entityRightWorldX/gp.TileSize;
		int entityTopRow = entityTopWorldY/gp.TileSize;
		int entityBottomRow = entityBottomWorldY/gp.TileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "Up" :
			entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
			tileNum1 = gp.tileManager.MapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.MapTileNum[entityRightCol][entityTopRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "Down" :
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TileSize;
			tileNum1 = gp.tileManager.MapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileManager.MapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "Left" :
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TileSize;
			tileNum1 = gp.tileManager.MapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.MapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "Right" :
			entityRightCol = (entityRightWorldX + entity.speed)/gp.TileSize;
			tileNum1 = gp.tileManager.MapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileManager.MapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++ ) {
			
			if(gp.obj[i] != null) {
				
				//get entity solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get the object solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "Up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "Down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "Left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "Right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					break;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
}
