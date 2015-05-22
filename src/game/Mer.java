package game;

import java.awt.Color;
import java.util.LinkedList;

public class Mer extends Terrain{

	private static final long serialVersionUID = -8725523446393722012L;
	
	private static int FRAMES = 1;
	
	public Mer(int x, int y, Base ba){
		super(x,y,1,FRAMES,0,2,ba.getlang().getNoms().get(ba.getlang().MER),ba);
		details = new LinkedList<Detail>();
		for(int i = 0; i < Util.randomInt(2,6); i++){
			boolean c = true;
			while(c){
				c = false;
				double x1 = Math.random();
				if(x1 > 0.85D || x1 < 0.2D)c = true;
				double y1 = Math.random();
				if(y1 > 0.85D || y1 < 0.2D)c = true;
				if(!c)details.add(new Vague(x1,y1));
			}
			
		}
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ba.getlang().getDesc().get(ba.getlang().MER).get(0)};
		return s;
	}

	@Override
	public Color getPColor() {
		return new Color(14,168,255);
	}
}
