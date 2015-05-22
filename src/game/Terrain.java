package game;

import java.awt.Color;
import java.io.Serializable;
import java.util.LinkedList;

public abstract class Terrain implements Serializable{

	private static final long serialVersionUID = 479633741036970028L;
	
	//jeu
	protected transient Base ba;
	protected int coord[];
	//visuel
	protected int h;
	protected int t;
	protected int anim = 0;
	protected int frames;
	protected boolean decor = false;
	protected int t2;
	protected int danim = 0;
	protected int dframes;
	protected LinkedList<Detail> details = null;
	//info
	protected String types;
	protected int type;
	
	protected int bois = 0;
	protected int pierre = 0;
	
	public abstract String[] getInfo2();
	public abstract Color getPColor();
	
	public Terrain(int x, int y, int t, int frames, int h, int type, String types, Base b){
		this.coord = new int[]{x,y};
		this.h = h;
		this.type = type;
		this.types = types;
		this.ba = b;
		this.t = t;
		this.frames = frames;
		
	}
	
	public void init(Base b){
		this.ba = b;
	}
	
	public Terrain(int x, int y, int t, int frames, int t2, int dframes, int h, int type, String types, Base b){
		this.coord = new int[]{x,y};
		this.h = h;
		this.type = type;
		this.types = types;
		this.ba = b;
		this.t = t;
		this.frames = frames;
		this.t2 = t2;
		this.dframes = dframes;
		this.decor = true;
	}

	public int getType() {
		return type;
	}
	public int getTexture() {
		return t;
	}
	public int getDTexture() {
		return t2;
	}
	public int[] getCoord() {
		return coord;
	}
	
	public int getBois() {
		return bois;
	}
	
	public void setBois(int bois) {
		this.bois = bois;
	}

	public int getPierre() {
		return pierre;
	}

	public void setPierre(int pierre) {
		this.pierre = pierre;
	}

	public String getInfo(int par0) {
		switch(par0){
			case 0: return types+"["+coord[0]+";"+coord[1]+"]";
			case 1: return types;
			case 2: return "["+coord[0]+","+coord[1]+"]";
			default: return "no info "+par0;
		}
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	
	public void animation(){
		anim++;
		if(anim == frames)
			anim = 0;
		if(decor){
			danim++;
			if(danim == dframes)
				danim = 0;
		}
	}

	public int getAnim() {
		return anim;
	}
	public int getDAnim() {
		return danim;
	}

	public boolean hasDecor() {
		return decor;
	}
	
	public LinkedList<Detail> getDetails() {
		return details;
	}

	public void setDetails(LinkedList<Detail> details) {
		this.details = details;
	}

	
	
}
