package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JLabel;

public class Ville extends Construction implements ActionListener{

	private static final long serialVersionUID = 5664275669241443636L;
	
	private transient JLabel pop;
	private transient Button bu;
	
	private int[] population = {12,24,36};
	
	private static int FRAMES = 1;
	private static int MAXSTATE = 3;
	
	public Ville(int x, int y, Base b){
		super(x,y,1,FRAMES,0,1,1,b.getlang().getNoms().get(b.getlang().VILLE),MAXSTATE,new int[]{15,30,45},new int[]{100,0,0},b);
		bu = new Button(b.getlang().getBouton().get(6),b);
		bu.addActionListener(this);
		bu.setPreferredSize(new Dimension(120,30));
		bu.enable(false);
	}
	
	public boolean estPlacable(int x, int y){
		if(ba.getG().getM().getMap1()[x][y] == null){
			if(ba.getG().getM().getMap0()[x][y].getType()==1){
				return true;
			}
		}
		return false;
	}

	@Override
	public void openGui() {
		pop.setForeground(Color.BLACK);
		pop.setFont(ba.getF().deriveFont(Font.PLAIN,30));
		ba.openInfo(ba.getlang().getNoms().get(ba.getlang().VILLE),new Component[]{pop,bu},120);
	}

	@Override
	public void placeCheck(){
		LinkedList<Detail> ded = ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails();
		for(int i = 0; i < ded.size(); i++){
			if((ded.get(i).getCoord()[0] > 0.25D && ded.get(i).getCoord()[0] < 0.75D) && (ded.get(i).getCoord()[1] > 0.25D && ded.get(i).getCoord()[1] < 0.75D)){
				if(ded.get(i).getClass() == Arbre.class)
					ba.getG().setRessources(0,ba.getG().getRessources()[0]+10);
				ded.remove(i);
			}
		}
		ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(ded);
		ba.getG().getP().change(population[0],ba.getG().getP().POPULATION);
		ba.getG().addValue(new InfoValue("+"+population[0]+" habitants",Color.GREEN,new int[]{coord[0],coord[1]}));
		pop = new JLabel(ba.getlang().getGui().get(ba.getlang().VILLEGUI).get(0)+" : "+Util.somme(population,0,state));
	}
	
	public Terrain fakeCheck(Terrain t){
		Terrain t1 = t;
		LinkedList<Detail> ded = t1.getDetails();
		for(int i = 0; i < ded.size(); i++){
			if((ded.get(i).getCoord()[0] > 0.25D && ded.get(i).getCoord()[0] < 0.75D) && (ded.get(i).getCoord()[1] > 0.25D && ded.get(i).getCoord()[1] < 0.75D)){
				ded.remove(i);
			}
		}
		t1.setDetails(ded);
		return t1;
	}
	
	@Override
	public void weekCheck() {
		if(!pourcent){
			if(this.state != this.maxstate-1){
				bu.enable(true);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == bu){
			if(!pourcent){
				if(this.state != this.maxstate-1){
					this.state++;
					
					ba.getG().getP().change(population[state],ba.getG().getP().POPULATION);
					ba.getG().addValue(new InfoValue("+"+population[state]+" habitants",Color.GREEN,new int[]{coord[0],coord[1]}));
					pop = new JLabel(ba.getlang().getGui().get(ba.getlang().VILLEGUI).get(0)+" : "+Util.somme(population,0,state));
					
					if(state == 1){
						LinkedList<Detail> ded = ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails();
						for(int i = 0; i < ded.size(); i++){
							if((ded.get(i).getCoord()[0] > 0.1D && ded.get(i).getCoord()[0] < 0.9D) && (ded.get(i).getCoord()[1] > 0.1D && ded.get(i).getCoord()[1] < 0.9D)){
								if(ded.get(i).getClass() == Arbre.class)
									ba.getG().setRessources(0,ba.getG().getRessources()[0]+10);
								ded.remove(i);
							}
						}
						ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(ded);
						prix = new int[]{300,100,0};
					}else{
						for(Detail d: ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails()){
							if(d.getClass() == Arbre.class){
								ba.getG().setRessources(0,ba.getG().getRessources()[0]+10);
							}
						}
						ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(null);
						prix = new int[]{1000,500,0};
					}
					nouveau = true;
					pourcent = true;
					reinitTemps();
					p = 0;
					disabled = true;
					bu.enable(false);
				}
				
			}
			
		}
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ba.getlang().getDesc().get(ba.getlang().VILLE).get(0),""};
		if(pourcent){s[1]="("+ba.getlang().getDesc().get(ba.getlang().VILLE).get(1)+" : "+p+"%)";}
		return s;
	}
	
	@Override
	public String getUtil() {
		return ba.getlang().getUtil().get(ba.getlang().VILLEGUI);
	}

	@Override
	public void deleteCheck() {
		ba.getG().getP().change(-Util.somme(population),ba.getG().getP().POPULATION);
		ba.getG().addValue(new InfoValue("-"+Util.somme(population)+" "+ba.getlang().getInfo().get(2),Color.RED,new int[]{coord[0],coord[1]}));

		
	}

	@Override
	public void internInit() {
		bu = new Button(ba.getlang().getBouton().get(6),ba);
		pop = new JLabel(ba.getlang().getGui().get(ba.getlang().VILLEGUI).get(0)+" : "+Util.somme(population,0,state));
	}

}
