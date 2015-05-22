package game;

import java.awt.Color;

public class Montagne extends Terrain{

	private static final long serialVersionUID = 8496107521487881213L;
	
	private static int FRAMES = 1;
	private static int DFRAMES = 1;
	
	public Montagne(int x, int y, Base ba){
		super(x,y,2,FRAMES,0,DFRAMES,0,3,ba.getlang().getNoms().get(ba.getlang().MONTAGNE),ba);
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ba.getlang().getDesc().get(ba.getlang().MONTAGNE).get(0)};
		return s;
	}

	@Override
	public Color getPColor() {
		return new Color(158,158,158);
	}
}
