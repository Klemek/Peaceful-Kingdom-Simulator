package game;

import java.awt.Color;

public class Montagne extends Terrain{

	private static final long serialVersionUID = 8496107521487881213L;
	
	private static int FRAMES = 1;
	private static int DFRAMES = 1;
	
	public Montagne(int x, int y, Base g){
		super(x,y,2,FRAMES,0,DFRAMES,0,3,g.getlang().getNoms().get(g.getlang().MONTAGNE),g);
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ga.getlang().getDesc().get(ga.getlang().MONTAGNE).get(0)};
		return s;
	}

	@Override
	public Color getPColor() {
		return new Color(158,158,158);
	}
}
