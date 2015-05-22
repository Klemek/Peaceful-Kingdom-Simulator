package game;

import java.awt.Color;

public class Plage extends Terrain{

	private static final long serialVersionUID = 1212087338817081291L;
	
	private static int FRAMES = 1;
	
	public Plage(int x, int y, Base ba){
		super(x,y,3,FRAMES,0,4,ba.getlang().getNoms().get(ba.getlang().PLAGE),ba);
	}
	
	public Plage(int x, int y, int h, Base ba){
		super(x,y,3,FRAMES,h,4,ba.getlang().getNoms().get(ba.getlang().PLAGE),ba);
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ba.getlang().getDesc().get(ba.getlang().PLAGE).get(0)};
		return s;
	}

	@Override
	public Color getPColor() {
		return new Color(255,232,119);
	}
}
