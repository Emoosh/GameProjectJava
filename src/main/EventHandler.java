package main;

import java.awt.Rectangle;

public class EventHandler
{
		GamePanel gp;
		Rectangle eventRect;
		int eventRectDefaultX, eventRectDefaultY;
		String dialogue[]= new String[25];
		int dialogueIndex = 0;
		
		public EventHandler(GamePanel gp)
		{
			this.gp = gp;
			
			
			eventRect = new Rectangle();
			//THESE X AND Y VALES BECAUSE WE WANT TO APPLY OUR CODE WHEN PLAYER REALLY INTO A TILE.
			eventRect.x = 23;
			eventRect.y = 23;
			//This is because we want to this area is small.
			eventRect.height = 2;
			eventRect.width = 2;
			
			eventRectDefaultX = eventRect.x;
			eventRectDefaultY = eventRect.y;
			
		}
		
		
		public void checkEvent()
		{
		if(hit(26,14, "up") == true){Lesson(gp.dialogueState);}
		//if(hit(23,12, "up") == true){damagePit(gp.dialogueState);}	
			
		}
		
		public void damagePit(int gameState)
		{
			gp.gameState = gameState;
	     	gp.ui.currentDialogue = "Derse Hoşgeldin! :)";
			gp.player.life -= 1;
		}
		
		
		public void Lesson(int gameState)
		{
			if(gp.keyH.enterPressed == true)
			{
				if(gp.player.books == 6)
				{
					gp.gameState = gp.examState1;
				}
				else
				{
				gp.gameState = gameState;
				setDialogue();
				speak();
				}
			}
			
		}
		
		 public void setDialogue() {
			    
				dialogue[0] = "Merhaba Rapunzel /nBizim ilim kitaplarımızı çalanları/nBulacak kişi sen olmalısın.";
				dialogue[1] = "Kitapta yazılanları anlayamayacaksın /nO yüzden bana getir.";
				dialogue[2] = "Bu kitapta çok yakın gelecek zamanda/nKullanılacak bir dil yer alıyor./nNereden bildiğimi sorma!";
				dialogue[3] = "Bu kitapları etraftan elde edebilirsin.";
				dialogue[4] = "Fakat dikkatli ol! /nEtraftaki canavarlar tehlikelidir.";
				dialogue[5] = "Etrafımızda niye sen ve benden başka /nBirisi yok hiç merak etmedin mi?";
				dialogue[6] = "Her neyse şimdi sana bana getirdiğin /nKitapta yazanları okuyacağım.";	
				dialogue[7] = "Bu kitapta Java adlı bir programlama dilinin /nNasıl kullanılacağına dair bilgiler var.";
				dialogue[8] = "Bu dilde INPUT ve OUTPUT olarak iki önemli /nKavram var.";
				dialogue[9] = "INPUT bizim girdiğimiz, /nOUTPUT ise çıktı olarak aldığımız sonuçtur.";
             	dialogue[10] = "Girdiğimiz değerin çıktısını ise /nSystem.out.println(\"Hello World\") ile alırız.";
            	dialogue[11] = "Bu girdinin çıktısı 'Hello World' /nŞeklindedir.";
            	dialogue[12] = "Java hakkında bilmen gereken bir diğer /nÖnemli konu da VERİ TİPLERİ'dir.";
            	dialogue[13] = "En çok kullanılan veri tipleri INT, /nDOUBLE, CHAR, STRING, /nVe BOOLEAN'dır.";
            	dialogue[14] = "INT veri tipi tam sayılar için,";
            	dialogue[15] = "DOUBLE veri tipi ondalıklı sayılar için,";
            	dialogue[16] = "CHAR veri tipi karakterler için,";
            	dialogue[17] = "STRING veri tipi birden fazla karakter için";
            	dialogue[18] = "BOOLEAN veri tipi ise TRUE veya /nFALSE değerleri için kullanılır.";
            	dialogue[19] = "Sınava girmek için bana diğer kitapları da /ngetir!";
            	dialogue[20] = "Bu sınavdan başarılı olursan ilk işin 30k+";
			
			
		
			 

			}
		 public void speak() {

				if (dialogue[dialogueIndex] == null) {
					dialogueIndex = 0;
				}
				gp.ui.currentDialogue = dialogue[dialogueIndex];
				dialogueIndex++;

				}
		
		public boolean hit(int eventCol, int eventRow, String reqDirection)
		{
			boolean isHit = false;
			
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect.x = eventCol * gp.tileSize + eventRect.x;
			eventRect.y = eventRow * gp.tileSize + eventRect.y;

			
			if(gp.player.solidArea.intersects(eventRect))
			{
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
				{
					isHit = true;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect.x = eventRectDefaultX;
			eventRect.y = eventRectDefaultY;
			
			return isHit;
		}
}
