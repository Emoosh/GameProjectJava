package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Book extends Entity 
{
	    GamePanel gp;
		int book = 0;
	
	public OBJ_Book(GamePanel gp)
	{
		super(gp);
		this.gp = gp;
		
		type = type_pickup;
		down1 = setup("/objects/waterbook",gp.tileSize,gp.tileSize);
		
		collision = true;
		solidArea = new Rectangle(3,18,42,30);
		
		
	}

	public void use(Entity entity)
	{
		gp.playSE(1);
		gp.player.books += book;
	}
}
