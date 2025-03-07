package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity 
{
  GamePanel gp;
  public int worldX,worldY;
  public int speed;
  public boolean isSkeleton;
  
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead;
  public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
  public String direction = "down";
  
  public int spriteCounter = 0;
  public int spriteNum = 1;
  
  public Rectangle solidArea;
  public Rectangle attackArea = new Rectangle(0,0,0,0);
  public int solidAreaDefaultX;
  public int solidAreaDefaultY;
  public boolean collisionOn = false;
  public int actionLockCounter;
  public boolean invincible = false;
  public int invinciblecounter = 0;
  public boolean attacking = false;
  public boolean alive = true;
  
  String dialogue[]= new String[20];
  int dialogueIndex = 0;
  public BufferedImage image, image2, image3;
  public String name;
  public boolean collision = false;
  public int type; // 0 = player , 1 = npc, 2= monster
  public final int type_pickup = 7;
  public int books = 0;
  
  //CHAR STAT
  
  public int maxLife;
  public int life;
  
  
  public Entity(GamePanel gp)
  {
	  this.gp = gp;
  } 
  public void setAction() 
  {
	  
  }
  
public void speak() 
  {
	  
  }
public void use(Entity entity)
    {
	}
public void checkDrop() 
    {
	
	}
public void dropItem(Entity droppedItem)
    {	
			for(int i = 0; i < gp.monster.length; i++)
			{
				if(gp.monster[i] == null)
				{
					gp.obj[i] = droppedItem;
					gp.obj[i].worldX = worldX;
					gp.obj[i].worldY = worldY;
					break;
				}
			}
    }
  public void update()
  {
	  setAction();
	  
	  collisionOn = false;
	  gp.cChecker.checkTile(this);
	  gp.cChecker.checkObject(this, false);
	  gp.cChecker.checkEntity(this, gp.monster);
	  boolean contactPlayer = gp.cChecker.checkPlayer(this); 
	  //I Let npc go inside of player because they can be stuck each other.
	  
	  
	  if(this.type == 2 && contactPlayer == true)
	  {
		  if(gp.player.invincible == false)
		  {
			  gp.player.life -= 1;
			  gp.player.invincible = true;
		  }
	  }
	  if(collisionOn == false)
	  {
		  if(isSkeleton == true)
		  {
		switch(direction)
		{
		case "up":
			if(worldY >= 366 && worldY <= 528)
			{
			worldY -= speed;
			}
			break;
		case "down":
			if(this.worldY < gp.tileSize * 13)
			{
				
			}
			else
			{
				 worldY += speed;
			}
			break;
		case "right":
			if(worldX >= 1680 && worldX <= 1912)
			{
			   worldX += speed;
			}
			break;
		case "left":
			if(worldX >= 1685 && worldX <= 1912)
			{
				worldX -= speed;
			}
			break;
	   	}
		  }
		  else
		  {
			  switch(direction)
				{
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "right":
					worldX += speed;
					break;
				case "left":
					worldX -= speed;
					break;
		  }
	  }
	  
	  spriteCounter++;
	  if(spriteCounter > 10)
	  {
		  if(spriteNum == 1)
		  {
			  spriteNum = 2;
		  }
		  else if(spriteNum == 2)
		  {
			  spriteNum = 1;
		  }
		  spriteCounter = 0;
	  }
	  if(invincible == true)
	  {
		  invinciblecounter++;
		  
		  if(invinciblecounter > 40)
		  {
			  invincible = false;
			  invinciblecounter = 0;
		  }
	  }
	  }
  }
  public void draw(Graphics2D g2)
  {
	  BufferedImage image = null;
	  
	  int screenX = worldX - gp.player.worldX + gp.player.screenX;
  	  int screenY = worldY - gp.player.worldY + gp.player.screenY;
  	
  	if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
  	   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
  	   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
  	   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
  	{
  		 if(collisionOn == false)
  		  {
  		  switch(direction) 
  		  {
  		  case "up":
  			  if(spriteNum == 1)
  			  {
  			  image = up1;
  			  }
  			  if(spriteNum == 2)
  			  {
  			  image = up2;
  			  }
  			  break;
  			  
  		  case "down":
  			  if(spriteNum == 1)
  			  {
  			  image = down1;
  			  }
  			  if(spriteNum == 2)
  			  {
  			  image = down2;
  			  }
  			  break;
  			  
  		  case "left":
  			  if(spriteNum == 1)
  			  {
  			  image = left1;
  			  }
  			  if(spriteNum == 2)
  			  {
  			  image = left2;
  			  }
  			  break;
  			  
  		  case "right":
  			  if(spriteNum == 1)
  			  {
  			  image = right1;
  			  }
  			  if(spriteNum == 2)
  			  {
  			  image = right2;
  			  }
  			  break;
  		  }
  		  }
  		  else
  		  {
  			 switch(direction) 
  			  {
  			  case "up":	
  				  
  				  image = up1;
  		 
  				  break;
  				  
  			  case "down":
  				
  				  image = down1;
  			  
  				  break;
  				  
  			  case "left":
  				
  				  image = left1;
  				 		
  				  break;
  				  
  			  case "right":
  				
  				  image = right1;
  				 
  				  break;
  			  } 
  		  }
  		 
     g2.drawImage(image, screenX, screenY, gp.tileSize , gp.tileSize, null);
  	}
  }
  
  public BufferedImage setup(String imagePath, int width, int height)
  {
	  UtilityTool uTool = new UtilityTool();
	  BufferedImage image = null;
	  try 
	  {
		  image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
		  image = uTool.scaleImage(image, width, height);
	  }catch(IOException e)
	  {
		e.printStackTrace();  
	  }
	return image;
	  
  }
  
}
