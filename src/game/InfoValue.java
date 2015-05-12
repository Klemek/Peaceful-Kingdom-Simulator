package game;

import java.awt.Color;

public class InfoValue extends Object{

	private String value;
	private Color color;
	private int i = 25;
	private int coords[];
	
	public InfoValue(String v, Color c, int[] co){
		value = v;
		color = c;
		coords = co;
	}

	public String getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}
	
	public void event(){
		if(i != 0){
			i--;
			if(i >= 0) color = new Color(color.getRed(),color.getGreen(),color.getBlue(),i*10);
		}
		
	}
	
}
