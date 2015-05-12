package game;

public class Arbre extends Detail {

	private static final long serialVersionUID = -8983825346648291685L;
	
	private static int NB = 3;
	
	public Arbre(double x, double y) {
		super(Util.random(new double[]{50,45,5}),1, NB, x, y);
	}
	
}
