package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[]tile;
	public int MapTileNum[][];
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		MapTileNum = new int[gp.MaxWorldCol][gp.MaxWorldRow];
		
		
		GetTileImage();
		LoadMap("/maps/planet1.txt");
	}
	
	public void GetTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand2.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand3.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand4.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[7].collision = true;

			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));
			tile[8].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void LoadMap(String Filepath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(Filepath);
			BufferedReader buffredReader = new BufferedReader(new InputStreamReader(is));
			
			
			int Col = 0; 
			int Row = 0;
			
			while (Col < gp.MaxWorldCol && Row < gp.MaxWorldRow) {
				
				String Line = buffredReader.readLine();
				
				while ( Col < gp.MaxWorldCol) {
					
					String numbers [] = Line.split(" ");
					
					int num = Integer.parseInt(numbers[Col]);
					
					MapTileNum[Col][Row] = num;
					Col++;
				}
				if (Col == gp.MaxWorldCol) {
					Col = 0;
					Row++;
				}
			}
			buffredReader.close();
				
				
		}catch (Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int WorldCol = 0;
		int WorldRow = 0;
		
		
		while(WorldCol < gp.MaxWorldCol && WorldRow < gp.MaxWorldRow) {
			
			int TileNum = MapTileNum [WorldCol][WorldRow];
			
			int worldX = WorldCol * gp.TileSize;
			int worldY = WorldRow * gp.TileSize;
			int ScreenX = worldX - gp.player.worldX + gp.player.ScreenX;
			int ScreenY = worldY - gp.player.worldY + gp.player.ScreenY;
			
			if(worldX + gp.TileSize > gp.player.worldX - gp.player.ScreenX &&
			   worldX - gp.TileSize < gp.player.worldX + gp.player.ScreenX &&
			   worldY + gp.TileSize > gp.player.worldY - gp.player.ScreenY &&
			   worldY - gp.TileSize< gp.player.worldY + gp.player.ScreenY) {
				
				g2.drawImage(tile[TileNum].image, ScreenX, ScreenY,gp.TileSize, gp.TileSize, null );	
			}
		
			WorldCol++;
			
			  
			if (WorldCol == gp.MaxWorldCol) {
				WorldCol = 0;
				WorldRow++;
				
			}
		}
	}
	
}
