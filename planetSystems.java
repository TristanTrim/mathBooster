

import java.util.ArrayList;
import java.lang.Math;


public class planetSystems {


	static int obNum = 0;

	public static int[][] list = new int[100][3];
	public static double[] listDist = new double[100];




	public static void newRandom (int range) {

		double rand = Math.random()*range;

		list[obNum][0]=1;
		list[obNum][1]=(int)rand;
		list[obNum][2]=1;
		listDist[obNum]=1.0;
		obNum++;

	}


	static void removeObject() {

		obNum--;
		for(int ob=0;ob<=obNum;ob++) {
			for(int i=0;i<list[ob].length;i++) {
				System.out.println("ob "+ob+"["+i+"]"+" is "+list[ob][i]);
				list[ob][i]=list[ob+1][i];
				listDist[ob]=listDist[ob+1];
				System.out.println("ob "+ob+"["+i+"]"+" is "+list[ob][i]);
			//quads[ob]=quads[ob+1];
			}
		}
	}
		


	public static void move () {

		//move the existing planet systems forwards.
		System.out.println(list[0][2]);

		for ( int i=0; i<obNum; i++) {

			listDist[i] += 1+listDist[i]/30;
			list[i][2]=(int)listDist[i];

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


