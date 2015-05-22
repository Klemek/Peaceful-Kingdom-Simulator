package game;

import java.awt.Color;
import java.util.LinkedList;

public class Plaine extends Terrain{
	
	private static final long serialVersionUID = -6623728555582786453L;
	
	private static int FRAMES = 1;
	private int nba;
	
	public Plaine(int x, int y, Base ba){
		super(x,y,0,FRAMES,0,1,ba.getlang().getNoms().get(ba.getlang().PLAINE),ba);
		
		details = new LinkedList<Detail>();
		for(int i = 0; i < Util.randomInt(5,15); i++){
			boolean c = true;
			while(c){
				c = false;
				double x1 = Math.random();
				if(x1 > 0.90D || x1 < 0.10D)c = true;
				double y1 = Math.random();
				if(y1 > 0.90D || y1 < 0.10D)c = true;
				if(!c)details.add(new Herbe(x1,y1));
			}
		}
		nba = Util.random(new double[]{70,20,8,2});
		for(int i = 0; i < nba; i++){
			boolean c = true;
			while(c){
				c = false;
				double x1 = Math.random();
				if(x1 > 0.90D || x1 < 0.10D)c = true;
				double y1 = Math.random();
				if(y1 > 0.90D || y1 < 0.10D)c = true;
				if(!c)details.add(new Arbre(x1,y1));
			}
		}
	}
	
	public Plaine(int x, int y, boolean b,  Base ba){
		super(x,y,0,FRAMES,0,1,ba.getlang().getNoms().get(ba.getlang().PLAINE),ba);
		if(b){
			details = new LinkedList<Detail>();
			for(int i = 0; i < Util.randomInt(5,15); i++){
				boolean c = true;
				while(c){
					c = false;
					double x1 = Math.random();
					if(x1 > 0.90D || x1 < 0.10D)c = true;
					double y1 = Math.random();
					if(y1 > 0.90D || y1 < 0.10D)c = true;
					if(!c)details.add(new Herbe(x1,y1));
				}
			}
			nba = Util.random(new double[]{70,20,8,2});
			for(int i = 0; i < nba; i++){
				boolean c = true;
				while(c){
					c = false;
					double x1 = Math.random();
					if(x1 > 0.90D || x1 < 0.10D)c = true;
					double y1 = Math.random();
					if(y1 > 0.90D || y1 < 0.10D)c = true;
					if(!c)details.add(new Arbre(x1,y1));
				}
			}
		}

	}

	@Override
	public String[] getInfo2() {
		return new String[]{ba.getlang().getDesc().get(ba.getlang().PLAINE).get(0)+(nba>0?" "+ba.getlang().getDesc().get(ba.getlang().PLAINE).get(1)+" "+(ba.getOpt().langue==0?Util.nbeng(nba):Util.nbfra(nba)).toLowerCase()+" "+ba.getlang().getDesc().get(ba.getlang().PLAINE).get(2)+(nba == 1?".":"."):".")};
	}

	@Override
	public Color getPColor() {
		return new Color(153,214,0);
	}
}
