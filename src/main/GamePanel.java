package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;

import tile.TileManager;

public class GamePanel extends JPanel implements Runnable 
{

	final int OriginalTitleSize = 16;
	final int scale = 3;
	
    public final int tileSize = OriginalTitleSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; //768 pixels
    public int screenHight = tileSize * maxScreenRow; //576 pixels
    
    //FPS
    int FPS = 144;
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
   
    
    
    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    Sound soundse = new Sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    //ENTITY & OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();
    
   
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int examState1 = 4;
    public final int examState2 = 5;
    public final int examState3 = 6;
    public final int goodGameFinishState = 7;
    

    
    public GamePanel()
    {
    	this.setPreferredSize(new Dimension(screenWidth, screenHight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame()
    {
    	aSetter.setObject(); 
    	aSetter.setNPC();
    	aSetter.setMonster();
      	playMusic(6);
    	gameState = titleState;
    }

    public void startGameThread()
    {
    		gameThread = new Thread(this);
    		gameThread.start();
    }
    
	@Override
	public void run() {
		
		
		while(gameThread != null)
		{
		    double drawInterval = 1000000000/FPS; // 0.07 second to draw between lines
		    double nextDrawTime = System.nanoTime() + drawInterval;
 
			update();
					
			repaint();
			
			try 
			{
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0)
				{
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void update() 
	{
		if(gameState == playState)
		{
		player.update();
		tileM.update();
		//NPC
		for(int i = 0; i < npc.length; i++)
		{
			if(npc[i] != null)
			{
				npc[i].update();
			}
		}
		for(int i = 0; i < monster.length; i++)
		{
			if(monster[i] != null)
			{
				if(monster[i].alive == true)
				{
				 monster[i].update();
				}
				if(monster[i].alive == false)
				{
					monster[i].checkDrop();
					monster[i] = null;
				}
			}
		}
		}
		if(gameState == pauseState)
		{
			
		}
		if(player.life <= 0)
		{
			gameState = pauseState;
		}
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		
		Graphics2D g2 = (Graphics2D)g; 
		//DEBUG
		
		long drawStart = 0;
		drawStart = System.nanoTime();
		
		if(gameState == playState || gameState == dialogueState)
		{
			//TILE
			tileM.draw(g2);
			
			//ADD ENTITIES TO THE LIST
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++)
			{
				if(npc[i] != null)
				{
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length; i++)
			{
				if(obj[i] != null)
				{
					entityList.add(obj[i]);
				}
			}
			
			for(int i = 0; i < monster.length; i++)
			{
				if(monster[i] != null)
				{
					entityList.add(monster[i]);
				}
			}
			//SORT
			
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) 
				{
					// TODO Auto-generated method stub
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}}); 
			
			//DRAW ENTITIES
			
			for(int i = 0; i < entityList.size(); i++)
			{
				entityList.get(i).draw(g2);
			}
			//EMPTY ENTITY LIST 
			 entityList.clear();
			//MONSTER
			
			
			
			//PLAYER
			player.draw(g2);
			
		    //UI
			ui.draw(g2);
		}
		if(gameState == titleState)
		{
			ui.draw(g2);
		}
		if(gameState == examState1)
		{
			ui.draw(g2);
		}
		if(gameState == examState2)
		{
			ui.draw(g2);
		}
		if(gameState == examState3)
		{
			ui.draw(g2);
		}
		if(gameState == goodGameFinishState)
		{
			stopMusic();
			
			ui.draw(g2);
		}
		
		//DEBUG
		if(keyH.checkDrawTime == true)
		{
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw time: "+passed, 10, 400);
			System.out.println("Draw time: "+ passed);
		}
		
		g2.dispose();
		
	}
	
	public void playMusic(int i)
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
		
	}
	public void stopMusic()
	{
		sound.stop(); 
	}
	public void playSE(int i)
	{

		soundse.setFile(i);
        soundse.play();
	}
	
}
