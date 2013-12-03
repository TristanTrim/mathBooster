

import java.lang.Math;
import java.util.*;



public class play {


//Useful Variables
	public static int level = 0;
	public static int score = 0;
	public static int fuel = 200;
	public static int fuelColour = 0;

	public static int userPosition = 0;

	public static int time = 0;

// Grab info from mainMenu for convienience
	public static int mapWidth = mainMenu.mapWidth;
	public static int screenWidth = mainMenu.screenWidth;
	public static int screenHeight = mainMenu.screenHeight;




//Main Method!
	public static void game () {


	//Initial stuff

		// Make timer:
		//	This is for physics and user input.
		//	See the method below.
		TimerTask task = new Task();
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(task, 100, 100);


		// Set up arrays for holding visual data.

		int [] map = new int[100];

		int [][] userShip = new int[1][9];

		int [][] numbers = new int[100][3];

		int [][] healthbar = new int[1][9];


		// reset conditions before begining the game.
		fuel = 200;
		score = 0;
		level = 0;
		time = 0;
		userPosition = 0;
		while (planetSystems.list[0][1]!=0){
			planetSystems.removeObject();
		}



	//Main game loop					##
		//you lose the game when you run out of fuel or close the window.
		while (mainMenu.display.displayLives()&&fuel>0) {


		// Set up visual data in arrays.

			// position of lines*10 on the map
			for (int i = 0;i<map.length;i++) {
        
				map[i] = i*10-100-userPosition;

				numbers[i][0] = (i*10-userPosition)
						*(screenWidth/mapWidth);
        
				numbers[i][1] = screenHeight-50;
				numbers[i][2] = i*10;
        
			}

			// position of lines*1 on the map
			for (int i = 0;i<10;i++) {
        
				map[i] = i+10-userPosition;

				numbers[i][0] = (i+10-userPosition)
						*(screenWidth/mapWidth);
        
				numbers[i][1] = screenHeight-30;
				numbers[i][2] = i;
        
			}
        
        		// healthbar
			for (int i = 0;i<healthbar.length;i++) {
				healthbar[i][0] = fuelColour+23;
        
				healthbar[i][1] = 20;
				healthbar[i][2] = screenHeight-20;
        
				healthbar[i][3] = 20;
				healthbar[i][4] = screenHeight-40;
        
				healthbar[i][5] = 20+fuel;
				healthbar[i][6] = screenHeight-40;
        
				healthbar[i][7] = 20+fuel;
				healthbar[i][8] = screenHeight-20;
			}

			// User
			for (int i = 0;i<userShip.length;i++) {
				userShip[i][0] = 27;
        
				userShip[i][1] = screenWidth/2-5;
				userShip[i][2] = screenHeight-20;
        
				userShip[i][3] = screenWidth/2-5;
				userShip[i][4] = screenHeight-10;
        
				userShip[i][5] = screenWidth/2+5;
				userShip[i][6] = screenHeight-10;
        
				userShip[i][7] = screenWidth/2+5;
				userShip[i][8] = screenHeight-20;
			}
        
        	// Draw to screen using the display started in the mainMenu.

			mainMenu.display.drawQuads(healthbar);
			mainMenu.display.drawQuads(userShip);
			mainMenu.display.drawLines(drawHorizon(map));

			mainMenu.display.drawString(10,30,("Level: "+level));
			mainMenu.display.drawString(10,50,("Score: "+score));

			mainMenu.display.drawBigString(screenWidth/2,screenHeight/2,(""+userInput.userNum));

			int[][] planets = makePlanetObs(planetSystems.list);
			mainMenu.display.drawQuads(planets);

			for (int num = 0; num < numbers.length; num++) {
				mainMenu.display.drawString(numbers[num][0], numbers[num][1], (String)(""+numbers[num][2]));
				mainMenu.display.drawString(planets[num][1], planets[num][2], (String)(""+planets[num][9]));


			}


		}


	/**When the main loop ends; It's game is over.	*/

		//close gamephysics/user timer.
		timer.cancel();

		//check/set the score in main menu.
		if(score>mainMenu.highscore){
			mainMenu.highscore=score;
		}

		//set scene back to 0: Main Menu.
		mainMenu.scene=0;

	}



// Timer for physics and user input.
	static class Task extends TimerTask {
		public void run() {

			time++;
			fuel--;
			fuelColour=fuelColour*4/5;

			planetSystems.move();

			if (time%300 == 0) {
				level++;
			}

			if ( time%(30-level) == 0 ) {
				planetSystems.newRandom(11+(level*5));
			}


			try{
				System.out.println("user input is " + userInput.input());
			}catch(IllegalStateException e){
				System.out.println("The Keyboard input is dead!");
			}
			
		}
	}


// Draw quads based on a set of points. (collection objects/tokens.)
	static int[][] makePlanetObs(int[][] list) {

		int[][] quads = new int[100][10];

		for (int quad=0;quad<planetSystems.obNum;quad++) {

			int yoffset = (int)((1-list[quad][2]/500.0)*(screenHeight*2/3));

			int xoffset = (int)(
					//proximity to horizon draws objects to vanishing point
					(1.0-(double)yoffset/(screenHeight*2/3))*
					//objects position on map; map offset by users position
					((double)list[quad][1]-userPosition)
					//objects on screen multiplyed by screen width, and divided by amount of map/screen
					*screenWidth/mapWidth
					//plus half the screen to center
					+screenWidth/2);
			

			quads[quad][0] = time%7+3;

			quads[quad][1] = xoffset;
			quads[quad][2] = screenHeight-45-yoffset;

			quads[quad][3] = xoffset;
			quads[quad][4] = screenHeight-55-yoffset;

			quads[quad][5] = 10+xoffset;
			quads[quad][6] = screenHeight-55-yoffset;

			quads[quad][7] = 10+xoffset;
			quads[quad][8] = screenHeight-45-yoffset;


			quads[quad][9] = list[quad][1];


		}

		return(quads);

	}


// Take array of positions and draws lines from them to the vanishing point.
	public static int[][] drawHorizon(int[] linePosition) {

		int [][] lines = new int[linePosition.length][5];
        
		for (int i=0;i<linePosition.length;i++) {
        
        
			lines[i][0] = (int)(15);
                        lines[i][1] = screenWidth/2;
                        lines[i][2] = screenHeight*1/3-50;

			lines[i][4] = screenHeight-50;
			lines[i][3] = (linePosition[i]*screenWidth)/mapWidth;
		}

		return(lines);

	}


}


