package game;

import java.io.Serializable;

public class Detail implements Serializable{

	private static final long serialVersionUID = 2986528303793001850L;
	
	protected double coord[];
	protected int t;
	protected int nb;
	protected int ap;
	
	public Detail(int ap, int t, int nb, double x, double y){
		this.t = t;
		this.coord = new double[]{x,y};
		this.nb = nb;
		this.ap = ap;
	}

	public int getAp() {
		return ap;
	}

	public int getNb() {
		return nb;
	}

	public double[] getCoord() {
		return coord;
	}

	public int getT() {
		return t;
	}
	
}
