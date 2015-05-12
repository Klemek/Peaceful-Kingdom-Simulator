package game;

import java.io.Serializable;

public class Map implements Serializable{

	private static final long serialVersionUID = 4845571570094010946L;
	
	private Terrain map0[][];
	private Construction map1[][];
	private int taille;
	private transient Base b;
	
	public Map(Base b){
		this.b = b;
	}

	public void init(Base b){
		this.b = b;
		for(Terrain[] t1:map0){
			for(Terrain t2:t1){
				t2.init(b);
			}
		}
		for(Construction[] c1:map1){
			for(Construction c2:c1){
				if(c2 != null)c2.init(b);;
			}
		}
	}
	
	public void generate(int taille){
		map0 = new MapGeneration().generate(taille,b);
		map1 = new Construction[taille][taille];
		this.taille = taille;
	}
	
	public void animation(){
		for(Terrain[] t1:map0){
			for(Terrain t2:t1){
				t2.animation();
			}
		}
		for(Construction[] c1:map1){
			for(Construction c2:c1){
				if(c2 != null)c2.animation();
			}
		}
	}
	
	public Terrain[][] getMap0() {
		return map0;
	}
	
	public Terrain getMap0(int i, int i2) {
		return map0[i][i2];
	}

	public void setMap0(Terrain[][] map0) {
		this.map0 = map0;
	}

	public void setMap0(Terrain t, int i, int i2) {
		this.map0[i][i2] = t;
	}
	
	
	public Construction[][] getMap1() {
		return map1;
	}
	
	public Construction getMap1(int i, int i2) {
		return map1[i][i2];
	}

	public void setMap1(Construction[][] map1) {
		this.map1 = map1;
	}
	
	public void setMap1(Construction c, int i, int i2) {
		this.map1[i][i2] = c;
	}
	

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	
}
