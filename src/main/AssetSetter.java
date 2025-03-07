package main;



import entity.NPC_OldMan;

import monster.MON_Skeleton;
import object.OBJ_Book;
import object.OBJ_Boots;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter
{
	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	
	public void setObject()
	{
		gp.obj[0] = new OBJ_Book(gp);
		gp.obj[0].worldX = gp.tileSize * 6;
		gp.obj[0].worldY = gp.tileSize * 3;
		
		gp.obj[1] = new OBJ_Book(gp);
		gp.obj[1].worldX = gp.tileSize * 20;
		gp.obj[1].worldY = gp.tileSize * 4;
		
		gp.obj[2] = new OBJ_Book(gp);
		gp.obj[2].worldX = gp.tileSize * 25;
		gp.obj[2].worldY = gp.tileSize * 3;
		
		gp.obj[3] = new OBJ_Book(gp);
		gp.obj[3].worldX = gp.tileSize * 35;
		gp.obj[3].worldY = gp.tileSize * 5;
		
		gp.obj[4] = new OBJ_Book(gp);
		gp.obj[4].worldX = gp.tileSize * 30;
		gp.obj[4].worldY = gp.tileSize * 4;
		
		gp.obj[5] = new OBJ_Book(gp);
		gp.obj[5].worldX = gp.tileSize * 40;
		gp.obj[5].worldY = gp.tileSize * 5;
	}
	
	public void setNPC()
	{
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;

		
	}
	
	public void setMonster()
	{
		gp.monster[0] = new MON_Skeleton(gp);
		gp.monster[0].worldX = gp.tileSize * 41;
		gp.monster[0].worldY = gp.tileSize * 4;
		
		gp.monster[1] = new MON_Skeleton(gp);
		gp.monster[1].worldX = gp.tileSize * 36;
		gp.monster[1].worldY = gp.tileSize * 3;
		
		gp.monster[2] = new MON_Skeleton(gp);
		gp.monster[2].worldX = gp.tileSize * 25;
		gp.monster[2].worldY = gp.tileSize * 4;
		
		gp.monster[3] = new MON_Skeleton(gp);
		gp.monster[3].worldX = gp.tileSize * 20;
		gp.monster[3].worldY = gp.tileSize * 5;
		
		gp.monster[4] = new MON_Skeleton(gp);
		gp.monster[4].worldX = gp.tileSize * 16;
		gp.monster[4].worldY = gp.tileSize * 4;
		
		gp.monster[5] = new MON_Skeleton(gp);
		gp.monster[5].worldX = gp.tileSize * 10;
		gp.monster[5].worldY = gp.tileSize * 9;
		
		gp.monster[6] = new MON_Skeleton(gp);
		gp.monster[6].worldX = gp.tileSize * 33;
		gp.monster[6].worldY = gp.tileSize * 4;
		
		gp.monster[7] = new MON_Skeleton(gp);
		gp.monster[7].worldX = gp.tileSize * 31;
		gp.monster[7].worldY = gp.tileSize * 5;
		
		gp.monster[8] = new MON_Skeleton(gp);
		gp.monster[8].worldX = gp.tileSize * 32;
		gp.monster[8].worldY = gp.tileSize * 6;
	}
	

}
