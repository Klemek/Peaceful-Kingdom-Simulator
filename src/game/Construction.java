package game;

import java.awt.Color;
import java.io.Serializable;

public abstract class Construction implements Serializable{

	private static final long serialVersionUID = 316646068020902104L;
	
	protected int t;
	protected int coord[];
	protected int h;
	protected int anim = 0;
	protected int frames;
	//info
	protected String types;
	protected int type;
	protected int[] prix;
	protected transient Base ba;
	protected int p = 0;
	protected boolean pourcent = true;
	protected boolean disabled = true;
	protected boolean nouveau = true;
	
	protected boolean alerte = false;
	protected int alerteType = 0;
	
	private int gente;
	
	protected int state = 0;
	protected int maxstate;
	
	protected int[] temps;
	protected int tr;
	
	public abstract boolean estPlacable(int x, int y);
	public abstract void openGui();
	public abstract void weekCheck();
	public abstract void placeCheck();
	public abstract void deleteCheck();
	public abstract Terrain fakeCheck(Terrain t);
	public abstract String[] getInfo2();
	public abstract String getUtil();
	public abstract void internInit();

	
	public Construction(int x, int y, int t, int frames, int h, int type, int gente, String types, int maxstate,int[] temps ,int[] prix, Base b){
		this.coord = new int[]{x,y};
		this.h = h;
		this.type = type;
		this.types = types;
		this.ba = b;
		this.t = t;
		this.frames = frames;
		this.maxstate = maxstate;
		this.gente = gente;
		this.prix = prix;
		this.temps = temps;
		tr = temps[0];
	}
	
	public void init(Base b){
		this.ba = b;
		internInit();
	}
	
	public int getType() {
		return type;
	}
	public int getTexture() {
		return t;
	}
	public int[] getCoord() {
		return coord;
	}
	public void setCoord(int coordX, int coordY) {
		this.coord = new int[]{coordX, coordY};
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

	public boolean isDisabled() {
		return disabled;
	}
	public int getP() {
		return p;
	}
	public boolean hasPourcent() {
		return pourcent;
	}
	
	public void animation(){
		anim++;
		if(anim == frames)
			anim = 0;
	}

	public int getAnim() {
		return anim;
	}
	public int getState() {
		return state;
	}
	public int getMaxstate() {
		return maxstate;
	}
	public int getGente() {
		return gente;
	}
	
	public boolean hasRessources(Game g) {
		for(int i = 0; i < prix.length; i++){
			if(g.getRessources()[i] < prix[i])return false;
		}
		return true;
	}
	
	public boolean hasRessources(Game g,int i) {
		return g.getRessources()[i] >= prix[i];
	}
	
	public int[] getPrix() {
		return prix;
	}
	
	public void dayCheck() {
		if(nouveau){
			nouveau = false;
		}else{
			if(pourcent){
				if(tr != 0){
					tr--;
					p = (temps[state]-tr)*100/temps[state];
				}else{
					ba.getG().addValue(new InfoValue(ba.getlang().getInfo().get(0),Color.GREEN,new int[]{coord[0],coord[1]}));
					disabled = false;
					pourcent = false;
				}
			}
		}
	}
	
	protected void reinitTemps(){
		tr = temps[state];
	}
	public boolean hasAlerte() {
		return alerte;
	}
	public void setAlerte(boolean alerte) {
		this.alerte = alerte;
	}
	public int getAlerteType() {
		return alerteType;
	}
	public void setAlerteType(int alerteType) {
		this.alerteType = alerteType;
	}
}
