

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


/**Shiny*/
public class userInput {

// The string that stores user number input
	public static String userNum = "";

// Input for during gameplay.
// Collects numbers from numpad and numbar.
// Uses arrow keys or numpad signs.
	public static String input () {

		int key = 0;

		if (Keyboard.next()) {
			key = (Keyboard.getEventKey());
		}

		if (Keyboard.getEventKeyState()) {

			if (key==82||key==11) {
				userNum += 0;
			}else if (key==79||key==2) {
				userNum += 1;
			}else if (key==80||key==3) {
				userNum += 2;
			}else if (key==81||key==4) {
				userNum += 3;
			}else if (key==75||key==5) {
				userNum += 4;
			}else if (key==76||key==6) {
				userNum += 5;
			}else if (key==77||key==7) {
				userNum += 6;
			}else if (key==71||key==8) {
				userNum += 7;
			}else if (key==72||key==9) {
				userNum += 8;
			}else if (key==73||key==10) {
				userNum += 9;
			}else if(!userNum.equals("")) {
				// left
				if (key==203||key==2) {
					
					play.userPosition -= Integer.parseInt(userNum);
					userNum="";
                
				// right
				}else if (key==205||key==2) {
                
					play.userPosition += Integer.parseInt(userNum);
					userNum="";
                
				// up
				}else if (key==200||key==2) {
                
					play.userPosition *= Integer.parseInt(userNum);
					userNum="";
                
				// down
				}else if (key==208||key==2) {
                
					play.userPosition /= Integer.parseInt(userNum);
					userNum = "";
				}
                
			}

		}

		return(userNum);
	}


/**Controls for the menu.*/
	public static void menuControl() {

		int key = 0;

		if (Keyboard.next()) {
			key = (Keyboard.getEventKey());
		}

		System.out.println("~.^: "+key);
		System.out.println("%%%: "+mainMenu.selection);

		if (Keyboard.getEventKeyState()) {

			if (key==28) {
				mainMenu.scene=mainMenu.selection;
				
			// left
			}else if (key==203||key==2) {
				
                
			// right
			}else if (key==205||key==2) {
                
                
			// up
			}else if ((key==200||key==2)&&
					mainMenu.selection>1) {
                
				mainMenu.selection--;
                
			// down
			}else if ((key==208||key==2)&&
					mainMenu.selection<2) {
                
				mainMenu.selection++;
			}
                
		}
	}


}

