package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity
{
	public OBJ_Door(GamePanel gp)
	{
		super(gp);
		name = "Door";
  	     
		down1 = setup("/objects/door.png",gp.tileSize,gp.tileSize);
 
  	    collision = true;
	}
}
