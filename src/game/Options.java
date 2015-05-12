package game;

import java.io.Serializable;

public class Options implements Serializable{

	//Affichage

	private static final long serialVersionUID = 3671871101164484711L;

								// 0          1          2            3          4          5          6
	public int[][] TAILLES = {{900,600},{1024,768},{1280,720},{1280,800},{1280,1024},{1360,768},{1440,900},};
	
	private int DEFAUTSIZESEL = 0;
	private int[] DEFAUTINTERFSIZE = {150,35};
	private boolean DEFAUTFULLSCREEN = false;
	private boolean DEFAUTDETAILS = true;
	private boolean DEFAUTANIMATIONS = true;
	
	private int DEFAUTLANGUE = 0;
	
	public int[] size;
	public int sizeSel;
	public int[] interfSize;
	public boolean fullscreen;
	public boolean details;
	public boolean animations;
	
	public int langue;
	
	//Audio
	
	//Jeu
	//TODO attribution automatique des metiers et minimum d'ouvriers
	
	public Options(){ 
		sizeSel = DEFAUTSIZESEL;
		size = TAILLES[sizeSel];
		interfSize = DEFAUTINTERFSIZE;
		fullscreen = DEFAUTFULLSCREEN;
		details = DEFAUTDETAILS;
		animations = DEFAUTANIMATIONS;
		langue = DEFAUTLANGUE;
	}
	
	public void setDefaut(){
		sizeSel = DEFAUTSIZESEL;
		size = TAILLES[sizeSel];
		interfSize = DEFAUTINTERFSIZE;
		fullscreen = DEFAUTFULLSCREEN;
		details = DEFAUTDETAILS;
		animations = DEFAUTANIMATIONS;
		langue = DEFAUTLANGUE;
	}
	
}
