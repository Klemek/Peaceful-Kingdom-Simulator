package game;

public class Herbe extends Detail {

	private static final long serialVersionUID = 5780071637036623878L;
	
	private static int NB = 2;
	
	public Herbe(double x, double y) {
		super(Util.randomInt(0,NB-1),0, NB, x, y);
	}

}
