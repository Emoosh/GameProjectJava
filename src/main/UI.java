package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entity.Entity;
import object.OBJ_Hearts;
import object.OBJ_Key;


public class UI 
{
	GamePanel gp;
	Font arial_40, arial_80B;
	Graphics2D g2;
	//BufferedImage KeyImage;
	BufferedImage fullHeart, halfHeart, blankHeart;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false; 
	public String currentDialogue = "";
	
	double PlayTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    int command = 0;
    int examCommand = 0;
    
	public UI(GamePanel gp)
	{
	this.gp = gp;	
	
	arial_40 = new Font("Arial", Font.ITALIC, 40);
	arial_80B = new Font("Arial", Font.BOLD, 80);
	//OBJ_Key key = new OBJ_Key();
    //	KeyImage = key.image;
	
	
	Entity heart = new OBJ_Hearts(gp);
	fullHeart = heart.image;
	halfHeart = heart.image2;
	blankHeart = heart.image3;
	}
	
	public void message(String text)
	{
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2)
	{
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.black);
		
		if(gp.gameState == gp.titleState)
		{
			drawTitle();
		}
		if(gp.gameState == gp.goodGameFinishState)
		{
			drawCongratulations();
		}
		if(gp.gameState == gp.examState1)
		{
			drawExamScreen1();
		}
		if(gp.gameState == gp.examState2)
		{
			drawExamScreen2();
		}
		if(gp.gameState == gp.examState3)
		{
			drawExamScreen3();
		}
		if(gp.gameState == gp.playState)
		{
			drawPlayerLife();
		}
		else if(gp.gameState == gp.pauseState)
		{
			drawPauseScreen();
		}
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		
	}
	
	public void drawTitle() {
        g2.setColor(new Color(00,00,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Java Genies";
        int x = getTextCentered(text);
        int y = gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "START GAME";
        x = getTextCentered(text);
        y = y + gp.tileSize*5;
        g2.drawString(text, x, y);
        if(command == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }


        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "QUIT";
        x = getTextCentered(text);
        y = y + gp.tileSize;
        g2.drawString(text, x, y);
        if(command == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
	public void drawPlayerLife()
	{
		
		
		
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		
		int i = 0;
		
		while(i < gp.player.maxLife / 2)
		{
			g2.drawImage(blankHeart, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		
		i = 0;
		
		while(i < gp.player.life)
		{
		g2.drawImage(halfHeart, x, y, null);
		i++;
		if(i < gp.player.life)
		{
			g2.drawImage(fullHeart, x, y, null);
		}
		
		i++;
		x += gp.tileSize;
		}
		
	}
	public void drawPauseScreen()
	{
		g2.setFont(g2.getFont().deriveFont(Font.ITALIC,80F));
		String text = "PAUSED";
		int x = getTextCentered(text);
		int y = gp.screenHight / 2;
		g2.drawString(text, x, y);
	}
	private void drawDialogueScreen() {
		
		// window
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 3;
		drawSubWindow(x, y, width, height);
		
		Color c = new Color(255, 255, 255, 200);
		g2.setColor(c);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,26F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("/n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
		
	}
	
	private void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(255, 255, 255, 250);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(76,0,153, 150);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.fillRoundRect(x+5, y+5, width-10, height-10, 25,25);
	}

public void drawExamScreen1() {
		
		g2.setColor(new Color(00,00,0));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHight);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
		String text = "Ekrana Hello World nasıl yazdırılır?";
		int x = getTextCentered(text);
		int y = gp.tileSize*3;
		
		
		
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		text = "A) System.output.printline(\"Hello World\");";
		x = getTextCentered(text);
		y = y + gp.tileSize*5;
		g2.drawString(text, x, y);
		if(examCommand == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		text = "B) System.out.println(\"Hello World\");";
		x = getTextCentered(text);
		y = y + gp.tileSize;
		g2.drawString(text, x, y);
		if(examCommand == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		}

	public void drawExamScreen2() {
	
	g2.setColor(new Color(00,00,0));
	g2.fillRect(0,0,gp.screenWidth,gp.screenHight);
	
	
	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
	String text = "System.out.println(60%5) bunun çıktısı nedir?";
	int x = getTextCentered(text);
	int y = gp.tileSize*3;
	
	
	
	
	g2.setColor(Color.white);
	g2.drawString(text, x, y);
	
	//MENU
	g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
	text = "A) 0";
	x = getTextCentered(text);
	y = y + gp.tileSize*5;
	g2.drawString(text, x, y);
	if(examCommand == 0) {
		g2.drawString(">", x-gp.tileSize, y);
	}
	
	
	g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
	text = "B) 12";
	x = getTextCentered(text);
	y = y + gp.tileSize;
	g2.drawString(text, x, y);
	if(examCommand == 1) {
		g2.drawString(">", x-gp.tileSize, y);
	}
	}
	
	public void drawExamScreen3() {
	
	g2.setColor(new Color(00,00,0));
	g2.fillRect(0,0,gp.screenWidth,gp.screenHight);
	
	
	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
	String text = "Kullanıcıdan veri almak için hangisi kullanılır?";
	int x = getTextCentered(text);
	int y = gp.tileSize*3;
	
	
	
	
	g2.setColor(Color.white);
	g2.drawString(text, x, y);
	
	//MENU
	g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
	text = "A) BufferedImage";
	x = getTextCentered(text);
	y = y + gp.tileSize*5;
	g2.drawString(text, x, y);
	if(examCommand == 0) {
		g2.drawString(">", x-gp.tileSize, y);
	}
	
	
	g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
	text = "B) Scanner";
	x = getTextCentered(text);
	y = y + gp.tileSize;
	g2.drawString(text, x, y);
	if(examCommand == 1) {
		g2.drawString(">", x-gp.tileSize, y);
	}
	}
	
	public void drawCongratulations() {
		g2.setColor(new Color(00,00,0));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHight);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
		String text = "CONGRATULATIONS";
		int x = getTextCentered(text);
		int y = gp.tileSize*3;
		
		
		g2.setColor(Color.yellow);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,22F));
		text = "Müslüm Türker Kırtız, Merve Nair, Emirhan Yılmaz, Zeynep Çorumluoğlu";
		x = getTextCentered(text);
		y = y + 5*gp.tileSize;
		g2.drawString(text, x, y);
		if(examCommand == 1) {
			g2.drawString(">", x-gp.tileSize, y);	
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		text = "RyiSnow Youtube kanalına özel teşekkürler.";
		x = getTextCentered(text);
		y = y + 2*gp.tileSize;
		g2.drawString(text, x, y);
		if(examCommand == 1) {
			g2.drawString(">", x-gp.tileSize, y);	
		}
	}
	public int getTextCentered(String x)
	{
		int a;
		
		int length = (int)g2.getFontMetrics().getStringBounds(x, g2).getWidth();
		a = gp.screenWidth / 2 - length / 2;
		
		return a;
	}
}

