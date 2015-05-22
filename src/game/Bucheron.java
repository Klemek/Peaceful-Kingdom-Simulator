package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JLabel;

public class Bucheron extends Construction implements ActionListener {
	
	private static final long serialVersionUID = -7680272768086691915L;
	
	private transient JLabel trav;
	private transient Button b;
	
	private static int FRAMES = 1;
	private static int MAXSTATE = 3;

	private int[] travailleursMax = {2,4,8};
	private int travailleurs = 0;
	
	private boolean warning = false;
	
	public Bucheron(int x, int y, Base ba){
		//TODO
		super(x,y,2,FRAMES,0,1,0,ba.getlang().getNoms().get(ba.getlang().BUCHERON),MAXSTATE,new int[]{3,7,14},new int[]{20,0,0},ba);
		b = new Button(ba.getlang().getBouton().get(6),ba);
		b.addActionListener(this);
		b.setPreferredSize(new Dimension(120,30));
		b.enable(false);
	}
	
	public boolean estPlacable(int x, int y){
		if(ba.getG().getM().getMap1()[x][y] == null){
			if(ba.getG().getM().getMap0()[x][y].getType()==5){
				return true;
			}
		}
		return false;
	}

	@Override
	public void openGui() {
		//TODO
		trav = new JLabel(ba.getlang().getGui().get(ba.getlang().BUCHERONGUI).get(0)+(travailleurs==1?"":"s")+" : "+travailleurs);
		trav.setForeground(Color.BLACK);
		trav.setFont(ba.getF().deriveFont(Font.PLAIN,30));
		if(warning){
			JLabel jl = new JLabel(ba.getlang().getGui().get(ba.getlang().BUCHERONGUI).get(1));
			jl.setFont(ba.getF().deriveFont(Font.PLAIN,40));
			jl.setForeground(Color.RED);
			ba.openInfo(ba.getlang().getNoms().get(ba.getlang().BUCHERON),new Component[]{trav,jl,b},120);
		}else{
			ba.openInfo(ba.getlang().getNoms().get(ba.getlang().BUCHERON),new Component[]{trav,b},120);
		}
		
	}

	@Override
	public void placeCheck(){
		//TODO
		ba.getG().addValue(new InfoValue(ba.getlang().getInfo().get(1),Color.RED,new int[]{coord[0],coord[1]}));
		LinkedList<Detail> ded = ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails();
		for(int i = 0; i < ded.size(); i++){
			if((ded.get(i).getCoord()[0] > 0.40D && ded.get(i).getCoord()[0] < 0.60D) && (ded.get(i).getCoord()[1] > 0.40D && ded.get(i).getCoord()[1] < 0.60D)){
				if(ded.get(i).getClass() == Arbre.class)
					ba.getG().setRessources(0,ba.getG().getRessources()[0]+10);
				ded.remove(i);
			}
		}
		ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(ded);
		ba.getG().getP().changeDisponible(1,ba.getG().getP().BUCHERONS);
	}
	
	public Terrain fakeCheck(Terrain t){
		Terrain t1 = t;
		LinkedList<Detail> ded = t1.getDetails();
		for(int i = 0; i < ded.size(); i++){
			if((ded.get(i).getCoord()[0] > 0.40D && ded.get(i).getCoord()[0] < 0.60D) && (ded.get(i).getCoord()[1] > 0.40D && ded.get(i).getCoord()[1] < 0.60D))
				ded.remove(i);
		}
		t1.setDetails(ded);
		return t1;
	}
	
	@Override
	public void weekCheck() {
			if(!pourcent){
				if(this.state != this.maxstate-1){
					b.enable(true);
				}
				travailleurs = 0;
				if(ba.getG().getP().getInactifs(ba.getG().getP().BUCHERONS)<travailleursMax[state]){
					travailleurs+=ba.getG().getP().getInactifs(ba.getG().getP().BUCHERONS);
					ba.getG().getP().setInactifs(0,ba.getG().getP().BUCHERONS);
				}else{
					travailleurs=travailleursMax[state];
					ba.getG().getP().setInactifs(ba.getG().getP().getInactifs(ba.getG().getP().BUCHERONS)-travailleurs,ba.getG().getP().BUCHERONS);
				}
				
				if(warning){
					ba.getG().addValue(new InfoValue("!",Color.RED,new int[]{coord[0],coord[1]}));
					disabled = true;
				}else{
					int nba = Util.randomInt(5,10)*travailleurs;
					LinkedList<Detail> ded = ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails();
					for(int i = 0; i < (nba/10>ded.size()?ded.size():nba/10); i++){
						ded.remove(i);
					}
					if(ded.size() == 0){
						ba.getG().write(new String[]{ba.getlang().getGui().get(ba.getlang().BUCHERONGUI).get(1)+" ("+this.getCoord()[0]+";"+this.getCoord()[1]+")"},new Color[]{Color.RED});
						warning = true;
						alerte = true;
						ba.getG().getM().setMap0(new Plaine(this.coord[0],this.coord[1],false,ba), this.coord[0], this.coord[1]);
					}
					ba.getG().setRessources(0,ba.getG().getRessources()[0]+nba);
					ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(ded);
				}
				if(this.state != this.maxstate-1){
					b.enable(true);
				}
			}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == b){

			if(this.state != this.maxstate-1){
				this.state++;				
				if(state == 1){
					LinkedList<Detail> ded = ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails();
					for(int i = 0; i < ded.size(); i++){
						if((ded.get(i).getCoord()[0] > 0.35D && ded.get(i).getCoord()[0] < 0.65D) && (ded.get(i).getCoord()[1] > 0.35D && ded.get(i).getCoord()[1] < 0.65D)){
							if(ded.get(i).getClass() == Arbre.class)
								ba.getG().setRessources(0,ba.getG().getRessources()[0]+10);
							ded.remove(i);
						}
					}
					ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(ded);
					prix = new int[]{60,0,0};
				}else{
					LinkedList<Detail> ded = ba.getG().getM().getMap0()[coord[0]][coord[1]].getDetails();
					for(int i = 0; i < ded.size(); i++){
						if((ded.get(i).getCoord()[0] > 0.3D && ded.get(i).getCoord()[0] < 0.7D) && (ded.get(i).getCoord()[1] > 0.3D && ded.get(i).getCoord()[1] < 0.7D)){
							if(ded.get(i).getClass() == Arbre.class)
								ba.getG().setRessources(0,ba.getG().getRessources()[0]+10);
							ded.remove(i);
						}
					}
					ba.getG().getM().getMap0()[coord[0]][coord[1]].setDetails(ded);
					prix = new int[]{200,0,0};
				}
				nouveau = true;
				pourcent = true;
				reinitTemps();
				p = 0;
				disabled = true;
				b.enable(false);
			}
			
			
		}
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ba.getlang().getDesc().get(ba.getlang().BUCHERON).get(0),""};
		if(pourcent){s[1]="("+ba.getlang().getDesc().get(ba.getlang().BUCHERON).get(1)+" : "+p+"%)";}
		return s;
	}
	
	@Override
	public String getUtil() {
		return ba.getlang().getUtil().get(ba.getlang().BUCHERONGUI);
	}

	@Override
	public void deleteCheck() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void internInit() {
		b = new Button(ba.getlang().getBouton().get(6),ba);
	}
}
