package game;

import java.awt.Color;

public class Plage extends Terrain{

	private static final long serialVersionUID = 1212087338817081291L;
	
	private static int FRAMES = 1;
	
	public Plage(int x, int y, Base g){
		super(x,y,3,FRAMES,0,4,g.getlang().getNoms().get(g.getlang().PLAGE),g);
	}
	
	public Plage(int x, int y, int h, Base g){
		super(x,y,3,FRAMES,h,4,g.getlang().getNoms().get(g.getlang().PLAGE),g);
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ga.getlang().getDesc().get(ga.getlang().PLAGE).get(0)};
		return s;
	}

	@Override
	public Color getPColor() {
		return new Color(255,232,119);
	}
}
