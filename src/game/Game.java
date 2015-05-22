package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

//import javax.swing.Timer;



public class Game extends Thread implements ActionListener, Serializable{

	private static final long serialVersionUID = -2164338694582603232L;
	//private boolean INFO = true;
	//500 250 100
	public transient int[] DELAY;
	private transient int tid;
	private transient int camX;
	private transient int camY;
	private transient boolean cont;
	private transient boolean pause;
	private transient ArrayList<String> log;
	private transient ArrayList<Color> logColor;
	private transient int selstate;
	private transient int over[];
	private transient int selected[];
	private transient boolean hasOver;
	private transient boolean hasSelected;
	private transient Construction choix;

						     //bois pierre nourriture
	private int[] ressources = {1000,1000,1000};
	private Population p;
	//TODO Population détaillée
	
	private int[] date = new int[]{1,3,21};
	private int sem;
	private int saison = 0;
	//0 printemps
	//1 été
	//2 automne
	//3 hiver
	private transient int[][] MOIS;
	private transient int[][] SAIS;
	private boolean bis = false;
	
	private Map m;
	private transient InfoValue values[];
	private int taille = 30;
	
	private transient Construction[] liste;
	private transient Terrain[] liste2;
	
	private transient Base ba;
	
	public Game(Base ba, int t){
		m = new Map(ba);
		taille = t;
		m.generate(taille);
		p = new Population();
	}
	
	public void init(Base ba){
		this.ba = ba;
		
		DELAY = new int[]{1000,500,20};

		tid = DELAY[0];
		
		camX = 0;
		camY = 0;
		
		selstate = 0;
		over = new int[]{0,0};
		selected = new int[]{0,0};
		hasOver = false;
		hasSelected = false;
		
		MOIS = new int[][]{{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};
		SAIS = new int[][]{{21,3},{21,6},{21,9},{21,12}};
		
		m.init(ba);
		values = new InfoValue[]{};
		
		liste = new Construction[]{new Ville(-1,-1,ba),new Bucheron(-1,-1,ba),new Mine(-1,-1,ba)};
		liste2 = new Terrain[]{new Plaine(-1,-1,ba),new Foret(-1,-1,ba),new Montagne(-1,-1,ba)};
		for(int i =0; i < liste.length; i++){
			liste2[i] = liste[i].fakeCheck(liste2[i]);
		}
	}
	
	public void open(Base ba){
		init(ba);

		tid = DELAY[0];

		new AnimationThread(this,ba.getOpt().animations).start();
	}
	
	public void addValue(InfoValue iv){
		if(values != null){
			for(int i = 0; i < values.length; i++){
				if(values[i]==null){
					values[i] = iv;
					return;
				}
			}
			InfoValue[] ivf = new InfoValue[values.length+1];
			for(int j = 0; j < values.length; j++){
				ivf[j] = values[j];
			}
			ivf[ivf.length-1] = iv;
			values = ivf;
		}else{
			values = new InfoValue[]{iv};
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*
		if(arg0.getSource()==tanim){
			if(m != null)m.animation();
		}
		
		if(arg0.getSource()== tick){
			date[2]++;
			sem++;
			for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
				if(m.getMap1()[i][i2] != null)m.getMap1()[i][i2].dayCheck();
			}}
			if(sem==8){
				p.tousInactifs();
				sem = 1;
				for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
					if(m.getMap1()[i][i2] != null)m.getMap1()[i][i2].weekCheck();
				}}
			}
			if(date[2]==MOIS[bis?1:0][date[1]-1]+1){
				date[2] = 1;
				date[1]++;
			}
			if(date[1]==13){
				date[1] = 1;
				date[0]++;
				bis=false;
				if(((double)date[0]/4D)==(int)(date[0]/4)){
					bis=true;
				}
			}
			if(date[1]==SAIS[saison==3?0:saison+1][1] && date[2]==SAIS[saison==3?0:saison+1][0]){
				saison=saison==3?0:saison+1;
				switch(saison){
				case 0:
					this.write(new String[]{b.getlang().getSeasons().get(0)},new Color[]{new Color(153,214,0)});
					break;
				case 1:
					this.write(new String[]{b.getlang().getSeasons().get(1)},new Color[]{Color.yellow});
					break;
				case 2:
					this.write(new String[]{b.getlang().getSeasons().get(2)},new Color[]{new Color(114,51,22)});
					break;
				case 3:
					this.write(new String[]{b.getlang().getSeasons().get(3)},new Color[]{new Color(113,204,255)});
					break;
				}
			}
		}
		b.actionPerformed(arg0);
		*/
	}
	
	public void stop2(){
		this.cont=false;
	}
	
	public boolean getCont() {
		return cont;
	}

	public void pause2(){
		this.pause = true;
	}
	
	public void resume2(){
		this.pause = false;
	}
	
	public boolean isPaused(){
		return pause;
	}
	
	public void run(){
		cont = true;
		while(cont){
			while(pause);
			date[2]++;
			sem++;
			for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
				if(m.getMap1()[i][i2] != null)m.getMap1()[i][i2].dayCheck();
			}}
			if(sem==8){
				p.tousInactifs();
				sem = 1;
				for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
					if(m.getMap1()[i][i2] != null)m.getMap1()[i][i2].weekCheck();
				}}
			}
			if(date[2]==MOIS[bis?1:0][date[1]-1]+1){
				date[2] = 1;
				date[1]++;
			}
			if(date[1]==13){
				date[1] = 1;
				date[0]++;
				bis=false;
				if(((double)date[0]/4D)==(int)(date[0]/4)){
					bis=true;
				}
			}
			if(date[1]==SAIS[saison==3?0:saison+1][1] && date[2]==SAIS[saison==3?0:saison+1][0]){
				saison=saison==3?0:saison+1;
				switch(saison){
				case 0:
					this.write(new String[]{ba.getlang().getSeasons().get(0)},new Color[]{new Color(153,214,0)});
					break;
				case 1:
					this.write(new String[]{ba.getlang().getSeasons().get(1)},new Color[]{Color.yellow});
					break;
				case 2:
					this.write(new String[]{ba.getlang().getSeasons().get(2)},new Color[]{new Color(114,51,22)});
					break;
				case 3:
					this.write(new String[]{ba.getlang().getSeasons().get(3)},new Color[]{new Color(113,204,255)});
					break;
				}
			}
			ba.i2prep();;
			try {
				sleep(tid);
			} catch (InterruptedException e) {}
		}
	}
	/*
	public Timer getTick() {
		return tick;
	}
	
	public Timer getTanim() {
		return tanim;
	}

	public void setTick(Timer tick) {
		this.tick = tick;
	}*/
	
	public void setTick(int tick) {
		this.tid = tick;
	}

	public int getCamX() {
		return camX;
	}

	public void setCamX(int camX) {
		this.camX = camX;
	}

	public int getCamY() {
		return camY;
	}

	public void setCamY(int camY) {
		this.camY = camY;
	}

	public int getSelstate() {
		return selstate;
	}

	public void setSelstate(int selstate) {
		this.selstate = selstate;
	}

	public int[] getOver() {
		return over;
	}

	public void setOver(int[] over) {
		this.over = over;
	}

	public int[] getSelected() {
		return selected;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public boolean hasOver() {
		return hasOver;
	}

	public void setHasOver(boolean hasOver) {
		this.hasOver = hasOver;
	}

	public boolean hasSelected() {
		return hasSelected;
	}

	public void setHasSelected(boolean hasSelected) {
		this.hasSelected = hasSelected;
	}

	public Construction getChoix() {
		return choix;
	}

	public void setChoix(int i) {
		Construction c = null;
		//TODO
		switch(i){
		case 0:
			c = new Ville(-1,-1,ba);
			break;
		case 1:
			c = new Bucheron(-1,-1,ba);
			break;
		case 2:
			c = new Mine(-1,-1,ba);
			break;
		}
		this.choix = c;
	}

	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}

	public InfoValue[] getValues() {
		return values;
	}

	public void setValues(InfoValue[] values) {
		this.values = values;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int[] getDELAY() {
		return DELAY;
	}

	public int[] getDate() {
		return date;
	}

	public int getSaison() {
		return saison;
	}

	public void setDate(int[] date) {
		this.date = date;
	}
	
	public int[] getRessources() {
		return ressources;
	}

	public void setRessources(int[] ressources) {
		this.ressources = ressources;
	}
	
	public void setRessources(int i, int ressources) {
		this.ressources[i] = ressources;
	}

	public Population getP() {
		return p;
	}

	public Construction[] getListe() {
		return liste;
	}

	public Terrain[] getListe2() {
		return liste2;
	}
	
	synchronized public void write(String[] lines){
		for(int k=0; k < lines.length; k++){
			log.add(lines[k]);
			logColor.add(Color.white);
		}
	}
	
	synchronized public void write(String[] lines, Color[] c){
		if(c.length!=lines.length)return;
		for(int k=0; k < lines.length; k++){
			//TODO
			log.add(lines[k]);
			logColor.add(c[k]);
		}
	}
	
	public ArrayList<String> getLog(){
		return log;
	}
	
	public ArrayList<Color> getLogColor(){
		return logColor;
	}
}
