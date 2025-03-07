package monster;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Book;
import object.OBJ_Hearts;

public class MON_Skeleton extends Entity
{
    GamePanel gp;
	public MON_Skeleton(GamePanel gp) 
	{
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		name = "Skeleton";
		speed = 1;
		maxLife = 2;
		life = maxLife;
		type = 2;
		
		solidArea = new Rectangle(3,18,42,30);
	
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		 getImage();
	}
	
	public void getImage()
	{
		up1 = setup("/monster/mu1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/mu2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/md1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/md2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/ml1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/ml2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/mr1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/mr2",gp.tileSize,gp.tileSize);
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
	
	public void checkDrop()
	{
		int i = new Random().nextInt(100) + 1;
		
		
		//SET THE MONSTER DROP
			
		if(i < 50)
		{
			dropItem(new OBJ_Book(gp));
		}
		if(i >= 50 && i <75)
		{
			dropItem(new OBJ_Hearts(gp));
		}
		else
		{
			
		}
	}

	
	
	

}
