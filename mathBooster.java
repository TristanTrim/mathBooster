

import java.lang.Math;
import java.util.*;



public class mathBooster {


//Timer Stuff						##
	public static int time = 0;
	static class Task extends TimerTask {
		public void run() {

			try{
				userInput.menuControl();
			}catch(IllegalStateException e){
				System.out.println("The Keyboard input is dead!");
			}
			
		}
	}


//Useful Variables					##
	public static int highscore = 0;

	public static int mapWidth = 20;
	public static int screenWidth = 800;
	public static int screenHeight = 600;

	public static int scene = 0;
	public static int selection = 1;

	public static Displayifyer display = new Displayifyer();



//Main Method!						##
	public static void main (String[] args) {


	//Initial stuff					##


		display.makeDisplay();

		TimerTask task = new Task();
		Timer timer = new Timer();
        
		timer.scheduleAtFixedRate(task, 100, 100);


		int[][] selectionBar = new int [1][9];

	//Main game loop					##

		while (display.displayLives()&&scene!=2) {




		// Game Main menu loop.
			while (display.displayLives() && scene == 0) {


			// selection highlight
				selectionBar[0][0] = 13;
                
				selectionBar[0][1] = screenWidth*2/3-5;
				selectionBar[0][2] = selection*30+15;
                
				selectionBar[0][3] = screenWidth*2/3-5;
				selectionBar[0][4] = selection*30+35;
                
				selectionBar[0][5] = screenWidth*2/3-5+110;
				selectionBar[0][6] = selection*30+35;
                
				selectionBar[0][7] = screenWidth*2/3-5+110;
				selectionBar[0][8] = selection*30+15;

        
				display.drawQuads(selectionBar);
				display.drawString(screenWidth*1/3, 20, "MathBooster");
				display.drawString(screenWidth*2/3, 50, "Play");
				display.drawString(screenWidth*2/3, 80, "Quit");
				display.drawString(screenWidth*2/5, 50, ("Highscore: "+highscore));
        
        
			}
        
		//When the main loop ends; We go to a new scene.	##
        
			timer.cancel();
        
			if (scene==1){
				play.game();
			}

			if (scene!=2){
				task = new Task();
				timer = new Timer();
                        
				timer.scheduleAtFixedRate(task, 100, 100);
			}else{
				System.exit(0);
			}

		}
		

	}


}


