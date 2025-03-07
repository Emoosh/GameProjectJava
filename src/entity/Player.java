package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity
{

  KeyHandler keyH;
  
  public final int screenX;
  public final int screenY;
  
  

  
  public Player(GamePanel gp, KeyHandler keyH) 
  {
	  super(gp);

	  
	  this.keyH = keyH;
	  
	  screenX = gp.screenWidth / 2 - (gp.tileSize/2);
	  screenY = gp.screenHight / 2 - (gp.tileSize/2);
	  
	  solidArea = new Rectangle(8,16,32,32);
	  
	  // We want to record this basic values above, as we implemented it below.
	  
	  solidAreaDefaultX = solidArea.x;
	  solidAreaDefaultY = solidArea.y;
	  
	  attackArea.width = 36;
	  attackArea.height = 36;
	  
	  setDefaultValues();
	  getPlayerImage();
	  getAttackImagePlayer();
  }
  
  public void setDefaultValues()
  {
	  worldX = gp.tileSize * 23;
	  worldY = gp.tileSize * 21;
	  speed = 1;
	  direction = "up";
	  //STATS
	  
	  maxLife = 8;
	  life = maxLife;
  }
  public void getPlayerImage() 
  {
	
	  
	  up1 = setup("/player/girl_up_1",gp.tileSize,gp.tileSize);
	  up2 = setup("/player/girl_up_2",gp.tileSize,gp.tileSize);
	  down1 = setup("/player/girl_down_1",gp.tileSize,gp.tileSize);
	  down2 = setup("/player/girl_down_2",gp.tileSize,gp.tileSize);
	  left1 = setup("/player/girl_left_1",gp.tileSize,gp.tileSize);
	  left2 = setup("/player/girl_left_2",gp.tileSize,gp.tileSize);
	  right1 = setup("/player/girl_right_1",gp.tileSize,gp.tileSize);
	  right2 = setup("/player/girl_right_2",gp.tileSize,gp.tileSize);
	  
  }
  public void getAttackImagePlayer() {

	  attackUp1 = setup("/player/girl_attack_up_1",gp.tileSize,gp.tileSize*2);
	  attackUp2 = setup("/player/girl_attack_up_2",gp.tileSize,gp.tileSize*2);
	  attackDown1 = setup("/player/girl_attack_down_1",gp.tileSize,gp.tileSize*2);
	  attackDown2 = setup("/player/girl_attack_down_2",gp.tileSize,gp.tileSize*2);
	  attackLeft1 = setup("/player/girl_attack_left_1",gp.tileSize*2,gp.tileSize);
	  attackLeft2 = setup("/player/girl_attack_left_2",gp.tileSize*2,gp.tileSize);
	  attackRight1 = setup("/player/girl_attack_right_1",gp.tileSize*2,gp.tileSize);
	  attackRight2 = setup("/player/girl_attack_right_2",gp.tileSize*2,gp.tileSize);
	  
			 
					  
			  
  }
  
  public BufferedImage setup(String imageName)
  {
	  UtilityTool uTool = new UtilityTool();
	  BufferedImage image = null;
	  try 
	  {
		  image = ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
		  image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
	  }catch(IOException e)
	  {
		e.printStackTrace();  
	  }
	return image;
	  
  }
  
  
  public void update()
  {
	  
	  if(attacking == true) {
		   attacking();
	  }
	  else if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true || keyH.enterPressed == true)
	  {
	  if(keyH.upPressed == true)
		{
		    direction = "up";
		}
		else if(keyH.downPressed == true)
		{
			direction = "down";
		}
		else if(keyH.rightPressed == true)
		{
			direction = "right";
		}
		else if(keyH.leftPressed == true)
		{
			direction = "left";
		}
		
	  // CHECKING TILE FOR COLLISION
	  
	  collisionOn = false;
	  gp.cChecker.checkTile(this);
	  
	  // CHECK OBJECT COLLISION
	  
	  int objIndex = gp.cChecker.checkObject(this, true);
	  
	  PickObject(objIndex);
	  
	  //CHECK NPC COLLISION
	  
	  int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
	  interactNPC(npcIndex);
	  
	  //CHECK EVENT
	  
	  gp.eHandler.checkEvent();
	  gp.keyH.enterPressed = false;  
	  //CHECK MONSTER COLLISION
	 
	   
	  int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
	  contactMonster(monsterIndex);
	  if (collisionOn == false && keyH.enterPressed == false) {
			switch (direction) {
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
	  
	  gp.keyH.enterPressed = false;
	  
	  //CHECK EVENT
	  
	  gp.eHandler.checkEvent();
	  
	  if(collisionOn == false)
	  {
		switch(direction)
		{
		case "up":
			worldY -= speed;
			//System.out.println("y: "+worldY / 48);
			break;
		case "down":
			worldY += speed;
			//System.out.println("y: "+ worldY /48);
			break;
		case "right":
			worldX += speed;
			//System.out.println("x: "+ worldX /48);
			break;
		case "left":
			worldX -= speed;
			//System.out.println("x: "+ worldX/ 48);
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
	  }
	  
	  //INVINCIBLE
	  
	  if(invincible == true)
	  {
		  invinciblecounter++;
		  
		  if(invinciblecounter > 144)
		  {
			  invincible = false;
			  invinciblecounter = 0;
		  }
	  }
	  
  }
  
  public void attacking() {
	  
	  spriteCounter++;
	  
	  if(spriteCounter <= 5) {
		  spriteNum = 1;
	  }
	  if(spriteCounter > 5 && spriteCounter <= 25) {
		  spriteNum = 2;
		  
		  int currentWorldX = worldX;
		  int currentWorldY = worldY;
		  int solidAreaWidth = solidArea.width;
		  int solidAreaHeigth = solidArea.height;
		  
		  switch(direction) {
		  case "up" : worldY -= attackArea.height; break;
		  case "down": worldY += attackArea.height; break;
		  case "left": worldX -= attackArea.width; break;
		  case " right": worldX += attackArea.width; break;
		  }
		  
		  solidArea.width = attackArea.width;
		  solidArea.height = attackArea.height;
		  
		  int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		  damageMonster(monsterIndex);
		  
		  worldX = currentWorldX;
		  worldY = currentWorldY;
		  solidArea.width = solidAreaWidth;
		  solidArea.height = solidAreaHeigth;
		  
	  }
	  
	  if(spriteCounter > 25) {
		  spriteNum = 1;
		  spriteCounter = 0;
		  attacking = false;
	  }
  }
  
  public void interactNPC(int index)
  {

	  if(gp.keyH.enterPressed == true) {
		  if(index != 999) {
			  gp.gameState = gp.dialogueState;
			  gp.npc[index].speak();
			  
		  }
		  else {
			  attacking = true;
		  }
	  }

		  
  }
  public void contactMonster(int index)
  {
	  if(index != 999)
	  {
		  
		  if(invincible == false)
		  {
			  life -= 1;
			  invincible = true;
		  }
	
		
	  }
  }
  
  public void damageMonster(int i) {
	  
	  if(i != 999) {
		  
		if(gp.monster[i].invincible == false) {
			gp.monster[i].life -= 1;
			gp.monster[i].invincible = true;
			
			if(gp.monster[i].life <= 0) {
				gp.monster[i].alive = false;
				gp.monster[i] = null;
				
			}
		}
	}
  }
  
  
  		public void PickObject(int i) 
  {
	  if(i != 999)
	  {
		 if(gp.obj[i].type == type_pickup)
		 {
			 gp.obj[i].use(this);
			 gp.obj[i] = null;
		 }
		 
		  
	  }
  }
  public void draw(Graphics2D g2)
  {
	  BufferedImage image = null;
	  int tempScreenX = screenX;
	  int tempScreenY = screenY;
	  if(collisionOn == false)
	  {
	  switch(direction) 
	  {
	  case "up":
		  if(attacking == false) {
			  if(spriteNum == 1)
			  {
			  image = up1;
			  }
			  if(spriteNum == 2)
			  {
			  image = up2;
			  }
		  }
		  if(attacking == true) {
			  tempScreenY = screenY - gp.tileSize;
			  if(spriteNum == 1)
			  {
			  image = attackUp1;
			  }
			  if(spriteNum == 2)
			  {
			  image = attackUp2;
			  }
		  }
		  
		  break;
		  
	  case "down":
		  if(attacking == false) {
			  if(spriteNum == 1)
			  {
			  image = down1;
			  }
			  if(spriteNum == 2)
			  {
			  image = down2;
			  }
		  }
		  if(attacking == true) {
			  if(spriteNum == 1)
			  {
			  image = attackDown1;
			  }
			  if(spriteNum == 2)
			  {
			  image = attackDown2;
			  }
		  }
		  break;
		  
	  case "left":
		  if(attacking == false) {
			  if(spriteNum == 1)
			  {
			  image = left1;
			  }
			  if(spriteNum == 2)
			  {
			  image = left2;
			  }
		  }
		  if(attacking == true) {
			  tempScreenX = screenX - gp.tileSize;
			  if(spriteNum == 1)
			  {
			  image = attackLeft1;
			  }
			  if(spriteNum == 2)
			  {
			  image = attackLeft2;
			  }
		  }
		  break;
		  
	  case "right":
		  if(attacking == false) {
			  if(spriteNum == 1)
			  {
			  image = right1;
			  }
			  if(spriteNum == 2)
			  {
			  image = right2;
			  }
		  }
		  if(attacking == true) {
			  if(spriteNum == 1)
			  {
			  image = attackRight1;
			  }
			  if(spriteNum == 2)
			  {
			  image = attackRight2;
			  }
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
	  
	  if(invincible == true)
	  {
		  g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
	  }
		   g2.drawImage(image,tempScreenX ,tempScreenY ,  null);
	  
	     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	      //DEBUG
		   
	//	   g2.setFont(new Font("Arial", Font.ITALIC, 26));
		//   g2.setColor(Color.white);
		  // g2.drawString("IC: "+invinciblecounter, 5, 300);
	  }
  }
  

