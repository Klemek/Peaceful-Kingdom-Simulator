package game;

import java.awt.Color;
import java.util.LinkedList;

public class Foret extends Terrain {

	private static final long serialVersionUID = -3428045509913832686L;
	
	private static int FRAMES = 1;
	private int nba;
	
	public Foret(int x, int y, Base ba){
		super(x,y,0,FRAMES,0,5,ba.getlang().getNoms().get(ba.getlang().FORET),ba);
		nba = Util.randomInt(400,700);
		details = new LinkedList<Detail>();
		for(int i = 0; i < nba/10; i++){
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
	

	@Override
	public String[] getInfo2() {
		return new String[]{ba.getlang().getDesc().get(ba.getlang().FORET).get(0)+" "+(ba.getOpt().langue==0?Util.nbeng(nba):Util.nbfra(nba)).toLowerCase()+" "+ba.getlang().getDesc().get(ba.getlang().FORET).get(1)+"."};
	}


	@Override
	public Color getPColor() {
		return new Color(55,145,0);
	}

}
