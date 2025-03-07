package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	GamePanel gp;
	public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;
	boolean checkDrawTime = false;
	
	
	public KeyHandler(GamePanel gp)
	{
		this.gp = gp;
	}
	

	@Override
	
	
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
	 int code = e.getKeyCode();
	 
	// play state

			if (gp.gameState == gp.playState) {
				if (code == KeyEvent.VK_W) {
					upPressed = true;
				}

				if (code == KeyEvent.VK_S) {
					downPressed = true;
				}
				if (code == KeyEvent.VK_A) {
					leftPressed = true;
				}
				if (code == KeyEvent.VK_D) {
					rightPressed = true;
				}

				if (code == KeyEvent.VK_P) {
					gp.gameState = gp.pauseState;

				}
				
				if (code == KeyEvent.VK_ENTER) {
					enterPressed = true;

				}
				
				// DEBUG
				if (code == KeyEvent.VK_T) {
					if (checkDrawTime == false) {
						checkDrawTime = true;
					} else if (checkDrawTime == true) {
						checkDrawTime = false;
					}
				}
			}

			
			// pause state
			else if (gp.gameState == gp.pauseState) {
				if (code == KeyEvent.VK_P) {
					gp.gameState = gp.playState;

				}
			}

			// dialog state
			else if (gp.gameState == gp.dialogueState) {
				if(code == KeyEvent.VK_ENTER) {
					gp.gameState = gp.playState;
				}
			}
			if (gp.gameState == gp.examState1) {
				 if(code == KeyEvent.VK_W)
				 {
					 gp.ui.examCommand = 0;
				 }
				 
				 if(code == KeyEvent.VK_S)
				 {
					 gp.ui.examCommand = 1;
				 }
				 if(code == KeyEvent.VK_ENTER) {
					 if(gp.ui.examCommand == 0) {
						 System.exit(0);
					 }
					 if(gp.ui.examCommand ==1) {
						 gp.gameState = gp.examState2;
						 gp.ui.examCommand = 3;
					 }
				 }
				 }
			if (gp.gameState == gp.examState2) {
				 if(code == KeyEvent.VK_W)
				 {
					 gp.ui.examCommand = 0;
				 }
				 
				 if(code == KeyEvent.VK_S)
				 {
					 gp.ui.examCommand = 1;
				 }
				 if(code == KeyEvent.VK_ENTER) {
					 if(gp.ui.examCommand == 0) {
						 gp.gameState = gp.examState3;
						 gp.ui.examCommand = 3;
					 }
					 if(gp.ui.examCommand ==1) {
						 System.out.println("");
						 System.exit(0);
					 }
				 }
				 }
			if (gp.gameState == gp.examState3) {
				 if(code == KeyEvent.VK_W)
				 {
					 gp.ui.examCommand = 0;
				 }
				 
				 if(code == KeyEvent.VK_S)
				 {
					 gp.ui.examCommand = 1;
				 }
				 if(code == KeyEvent.VK_ENTER) {
					 if(gp.ui.examCommand == 0) {
						 System.exit(0);
					 }
					 if(gp.ui.examCommand ==1) {
						
						 gp.gameState = gp.goodGameFinishState;
					 }
				 }
				 }
			if (gp.gameState == gp.titleState) {
				 if(code == KeyEvent.VK_W)
				 {
					 gp.ui.command = 0;
				 }
				 
				 if(code == KeyEvent.VK_S)
				 {
					 gp.ui.command = 1;
				 }
				 if(code == KeyEvent.VK_ENTER) {
					 if(gp.ui.command == 0) {
						 gp.gameState = gp.playState;
					 }
					 if(gp.ui.command ==1) {
						 System.exit(0);
					 }
				 }
				 }
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		 {
			 upPressed = false;
		 }
		 
		 if(code == KeyEvent.VK_S)
		 {
			 downPressed = false;
		 }
		 if(code == KeyEvent.VK_A)
		 {
			 leftPressed = false;
		 }
		 if(code == KeyEvent.VK_D)
		 {
			 rightPressed = false;
		 }
	}

}
