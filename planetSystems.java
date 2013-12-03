

import java.util.ArrayList;
import java.lang.Math;


public class planetSystems {

	// Number of objects in list currently.
	static int obNum = 0;

	public static int[][] list = new int[100][3];
	public static double[] listDist = new double[100];


// Create a new collection object withing the given range.
	public static void newRandom (int range) {

		double rand = Math.random()*range;

		list[obNum][0]=1;
		list[obNum][1]=(int)rand;
		list[obNum][2]=1;
		listDist[obNum]=1.0;
		obNum++;
	}


// Delete the first object in the list.
// This will always be the object closest to the user.
	static void removeObject() {

		obNum--;
		for(int ob=0;ob<=obNum;ob++) {
			for(int i=0;i<list[ob].length;i++) {
				list[ob][i]=list[ob+1][i];
				listDist[ob]=listDist[ob+1];
			}
		}
	}


// Move all the objects a unit closer to the user.
	public static void move () {

		for ( int i=0; i<obNum; i++) {

			listDist[i] += 1+listDist[i]/30;
			list[i][2]=(int)listDist[i];

		// Remove object when it hits the event horizon.
		// Give the user fuel if they hit it.
			if (list[i][2]>=500) {
				if (play.userPosition==list[i][1]) {
					play.score++;
					play.fuel+=30;
					play.fuelColour+=7;
					removeObject();
				}else{
					removeObject();
				}
			}

		}


	}



}


