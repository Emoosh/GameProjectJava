package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_OldMan extends Entity
{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		direction = "down";
		speed = 1;
		
	    solidArea = new Rectangle(8,16,32,32);
	    collisionOn = true;
	    getImage();	
	    setDialogue();
	}
	
	 public void getImage() 
	  {
		  try {
			  
			  up1 = ImageIO.read(getClass().getResourceAsStream("/npc/cu1.png"));
			  up2 = ImageIO.read(getClass().getResourceAsStream("/npc/cu2.png"));
			  left1 = ImageIO.read(getClass().getResourceAsStream("/npc/cl1.png"));
			  left2 = ImageIO.read(getClass().getResourceAsStream("/npc/cl2.png"));
			  right1 = ImageIO.read(getClass().getResourceAsStream("/npc/cr1.png"));
			  right2 = ImageIO.read(getClass().getResourceAsStream("/npc/cr2.png"));
			  down1 = ImageIO.read(getClass().getResourceAsStream("/npc/cd1.png"));
			  down2 = ImageIO.read(getClass().getResourceAsStream("/npc/cd2.png"));
		  }
		  catch(IOException e) 
		  {
			  e.printStackTrace();
		  }
		  
}
	 public void setDialogue() {
			dialogue[0] = "meow";
			dialogue[1] = "Meoooowww :^3";
			

		}
	 public void speak() {

			if (dialogue[dialogueIndex] == null) {
				dialogueIndex = 0;
			}
			gp.ui.currentDialogue = dialogue[dialogueIndex];
			dialogueIndex++;

			switch (gp.player.direction) {
			case "up":
				direction = "down";
				break;
			case "down":
				direction = "up";
				break;
			case "left":
				direction = "right";
				break;
			case "right":
				direction = "left";
				break;
			}

		}
	 
	  public void setAction() 
	  {
		  actionLockCounter++;
		  
		  if(actionLockCounter == 150)
		  {
		  Random random = new Random();
		  int i = random.nextInt(100) + 1;
		  
		  if(i <= 25)
		  {
			  direction = "up";
		  }
		  if(i > 25 && i <= 50)
		  {
			  direction = "down";
		  }
		  if(i > 50 && i <= 75)
		  {
			  direction = "left";
		  }
		  if(i > 75 && i <= 100)
		  {
			  direction = "right";
		  }
		  actionLockCounter = 0;
	  }
	  }
}
