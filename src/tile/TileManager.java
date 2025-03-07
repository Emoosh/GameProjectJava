package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager
{
   GamePanel gp;
   public Tile[] tile;   
   public int mapTileNum[][];
   public int spriteCounter = 0;
   public int spriteNum = 1;
   BufferedImage image = null;
   
   public TileManager(GamePanel gp)
   {
	   this.gp = gp;
	   
	   tile = new Tile[100];
	   mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
	   
	   getTileImage();
	   loadMap("/maps/map01.txt");
   }
   
   public void getTileImage()
   {
	  
		   
		   setup(0, "grass4", false);
		   setup(1, "wall3", true);
		   setup(2, "water2", true);
		   setup(3, "floor2", false);
		   setup(4, "tree", true);
		   setup(5, "road", false);
		   setup(6, "appletree", true);
		   setup(7, "browntree", true);
		   setup(8, "mavi-çalı", true);
		   setup(9, "stone2",true);
		   setup(11, "Autumn_bush1", false);
		   setup(12, "Autumn_bush2", false);
		   setup(13, "Autumn_bush3", false);
		   setup(14, "palmiye", true);
		   setup(15, "sand",false);
		   setup(16, "iskele",false);
		   setup(18, "teacher1",true);
           setup(19, "teacher2",true);
		   setup(20, "book",true);
		   setup(22, "b1",true);
		   setup(23, "b2",true);
		   setup(24, "b3",true);
		   setup(25, "b4",true);
		   setup(26, "b5",true);
		   setup(27, "b6",true);
		   setup(28, "pano",true);
		   setup(29, "desk",true);
		   setup(30, "bear",true);
		   setup(31, "chair",true);
		   setup(32, "baloon1",false);
		   
		   setup(21, "demleme", true);
		   
	
   }
		  

   public void setup(int index, String imageName,boolean collision)
   {
 	  UtilityTool uTool = new UtilityTool();
 	
 	  
 	  try
 	  {
 		  tile[index] = new Tile();
 		  tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
 		  tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
 		  tile[index].collision = collision;
 		  
 	  }catch(IOException e)
 	  {
 		  e.printStackTrace();
 	  }
 	  
   }
   		public void animation(int i)
   {
   			if(spriteCounter < 600)
   	   		{
   	   		   image = tile[i].image;		
   	   		}
   	   		else if(spriteCounter < 1200)
   	   		{
   	   		   tile[i].image = tile[i+1].image;		
   	   		}
   	   		else if(spriteCounter < 1800)
   	   		{
   	   			tile[i].image = tile[i+2].image;	
   	   		}
   	   		else
   	   		{
   	   			tile[i].image = image;
   	   			spriteCounter = 0;
   	   		}
   	   		
   	   		// System.out.println(spriteCounter);	
   	   		spriteCounter++;
   }
   		public void update()
   {
   		animation(11);
   		
   }
   public void loadMap(String filePath)
   {
	   try
	   {
		   InputStream is = getClass().getResourceAsStream(filePath);
		   BufferedReader br = new BufferedReader(new InputStreamReader(is));
		  
		   int col = 0;
		   int row = 0;
		   
		   while((col < gp.maxWorldCol) && (row < gp.maxWorldRow))
		    {
		       String line = br.readLine(); 
		       
		       while(col < gp.maxWorldCol)
		       {
		    	   String numbers[] = line.split(" ");
		    	   
		    	   int num = Integer.parseInt(numbers[col]);
		    	   
		    	   mapTileNum[col][row] = num;
		    	   col++;
		       }
		       
		      if(col == gp.maxWorldCol)
		      {
		    	  col = 0;
		    	  row++;
		      }
		    }
		   br.close();
		   
	   }catch(Exception e) 
	   {
		   
	   }
   }
   
   public void draw(Graphics2D g2)
   {
	    int worldCol = 0;
	    int worldRow = 0;
	   
	    
	    
	    while((worldCol < gp.maxWorldCol) && (worldRow < gp.maxWorldRow))
	    {
	    	int tileNum = mapTileNum[worldCol][worldRow];
	    	
	    	
	    	int worldX = worldCol * gp.tileSize;
	    	int worldY = worldRow * gp.tileSize;
	    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    	int screenY = worldY - gp.player.worldY + gp.player.screenY;
	    	
	    	if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
	    	   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
	    	   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
	    	   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)	
	    	   
	    	{
	    		if(tileNum == 4 || tileNum == 6)
	    		{
	    			g2.drawImage(tile[0].image, screenX, screenY,  null);
	    		}
	    		// ÇİMLER
	    		if(tileNum == 7 || tileNum == 8 || tileNum == 9 || tileNum == 10 || tileNum==18 || tileNum==19)
	    		{
	    			g2.drawImage(tile[0].image, screenX, screenY,  null);
	    		}
	    		if(tileNum == 11)
	    		{
	    			g2.drawImage(tile[0].image, screenX, screenY,  null);
	    		}
	    		if( tileNum == 16 || tileNum == 17 || tileNum == 8 || tileNum == 9  )
	    		{
	    			g2.drawImage(tile[0].image, screenX, screenY,  null);
	    		}
	    		if(tileNum == 22 || tileNum == 23 || tileNum == 24 || tileNum == 25 || tileNum == 26 || tileNum == 27 || tileNum == 31 || tileNum == 29 || tileNum == 30)
	    		{
	    			g2.drawImage(tile[3].image, screenX, screenY,  null);
	    		}
	    		if(tileNum == 28  )
	    		{
	    			g2.drawImage(tile[1].image, screenX, screenY,  null);
	    		}
	    		if(tileNum == 32 || tileNum == 18 || tileNum == 19 )
	    		{
	    			g2.drawImage(tile[3].image, screenX, screenY,  null);
	    		}
	    		if(tileNum == 14  )
	    		{
	    			g2.drawImage(tile[15].image, screenX, screenY,  null);
	    		}
	    		if(tileNum == 16  )
	    		{
	    			g2.drawImage(tile[2].image, screenX, screenY,  null);
	    		}
	    		
	                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
	    		
	           
	           
	    	}
	       worldCol++;
	      
	       
	       if(worldCol == gp.maxWorldCol)
	       {
	    	   worldCol = 0;
	    	   worldRow++;
	       }
	    }
	    
   }
}
