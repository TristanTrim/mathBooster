

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
public class Displayifyer {


	/** The fonts to draw to the screen */
	private static TrueTypeFont font;
	private static TrueTypeFont bigfont;

	/** Boolean flag on whether AntiAliasing is enabled or not */
	private static boolean antiAlias = false;


// Returns false if the display has been shut down.
// (Usually this happens when the user closes the window.
// Also clears the screen for the next frame
	public static boolean displayLives() {

		boolean doesIt = true;
		if (Display.isCloseRequested()) {
			Display.destroy();
			doesIt = false;
		}else{
			Display.update();
			// Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		}
		return doesIt;
	} 


// Initial display setup.
	public static void makeDisplay() {


		try {
			Display.setDisplayMode(new DisplayMode(mainMenu.screenWidth,mainMenu.screenHeight));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}


		// init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, mainMenu.screenWidth, mainMenu.screenHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 

		//load font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 12);
		font = new TrueTypeFont(awtFont, antiAlias);

		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		bigfont = new TrueTypeFont(awtFont, antiAlias);

	}


	public static void drawQuads(int [][] quads) {

		// Clear the screen and depth buffer


		for (int i = 0; i < quads.length; i++) {
                        
			// set the color of the quad (R,G,B,A)
			if (quads[i][0]<10) {
				GL11.glColor3f((float)(quads[i][0]/10.0),0.0f,0.0f);
			}else if (quads[i][0]<20) {
				GL11.glColor3f(0.0f,(float)((quads[i][0]-10)/10.0),0.0f);
			}else{
				GL11.glColor3f(0.0f,0.0f,(float)((quads[i][0]-20)/10.0));
			}
                        
			// draw line
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(quads[i][1],quads[i][2]);
			GL11.glVertex2f(quads[i][3],quads[i][4]);
			GL11.glVertex2f(quads[i][5],quads[i][6]);
			GL11.glVertex2f(quads[i][7],quads[i][8]);

			GL11.glEnd();

		}
			
	}

	public static void drawLines(int [][] lines) {



		for (int i = 0; i < lines.length; i++) {
                        
			// set the color of the line (R,G,B,A)
			if (lines[i][0]<10) {
				GL11.glColor3f((float)(lines[i][0]/10.0),0.0f,0.0f);
			}else if (lines[i][0]<20) {
				GL11.glColor3f(0.0f,(float)((lines[i][0]-10)/10.0),0.0f);
			}else{
				GL11.glColor3f(0.0f,0.0f,(float)((lines[i][0]-20)/10.0));
			}
                        
			// draw line
			GL11.glBegin(GL11.GL_LINES);
			GL11.glVertex2f(lines[i][1],lines[i][2]);
			GL11.glVertex2f(lines[i][3],lines[i][4]);

			GL11.glEnd();

		}

	}

	public static void drawBigString(int x, int y, String txt) {

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                
		bigfont.drawString(x, y, txt, Color.blue);
                
		GL11.glDisable(GL11.GL_BLEND);
		
	}

	public static void drawString(int x, int y, String txt) {

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                
		font.drawString(x, y, txt, Color.green);
                
		GL11.glDisable(GL11.GL_BLEND);
		
	}


}

