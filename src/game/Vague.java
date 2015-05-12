package game;

public class Vague extends Detail {

	private static final long serialVersionUID = 7223166249355195882L;
	
	private static int NB = 2;
	
	public Vague(double x, double y) {
		super(Util.randomInt(0,NB-1),2, NB, x, y);
	}

}
