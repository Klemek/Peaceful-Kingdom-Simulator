package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Mine extends Construction implements ActionListener {
	
	private static final long serialVersionUID = -8597772834882897872L;
	
	private transient JLabel trav;
	private transient Button b;
	
	private static int FRAMES = 1;
	private static int MAXSTATE = 3;

	private int[] travailleursMax = {4,8,16};
	private int travailleurs = 0;
	
	
	
	public Mine(int x, int y, Base ba){
		super(x,y,3,FRAMES,0,2,0,ba.getlang().getNoms().get(ba.getlang().MINE),MAXSTATE,new int[]{7,15,30},new int[]{80,0,0},ba);
		b = new Button(ba.getlang().getBouton().get(6),ba);
		b.addActionListener(this);
		b.setPreferredSize(new Dimension(120,30));
		b.enable(false);
	}
	
	public boolean estPlacable(int x, int y){
		if(ba.getG().getM().getMap1()[x][y] == null){
			if(ba.getG().getM().getMap0()[x][y].getType()==3){
				return true;
			}
		}
		return false;
	}

	@Override
	public void openGui() {
		//TODO
		trav = new JLabel(ba.getlang().getGui().get(ba.getlang().MINEGUI).get(0)+(travailleurs==1?"":"s")+" : "+travailleurs);
		trav.setForeground(Color.BLACK);
		trav.setFont(ba.getF().deriveFont(Font.PLAIN,30));
		ba.openInfo(ba.getlang().getNoms().get(ba.getlang().MINE),new Component[]{trav,b},120);
		
	}

	@Override
	public void placeCheck(){
		//TODO
		ba.getG().addValue(new InfoValue(ba.getlang().getInfo().get(1),Color.RED,new int[]{coord[0],coord[1]}));
		ba.getG().getP().changeDisponible(1,ba.getG().getP().MINEUR);
	}
	
	public Terrain fakeCheck(Terrain t){
		return t;
	}
	
	@Override
	public void weekCheck() {
			if(!pourcent){
				
				travailleurs = 0;
				if(ba.getG().getP().getInactifs(ba.getG().getP().MINEUR)<travailleursMax[state]){
					travailleurs+=ba.getG().getP().getInactifs(ba.getG().getP().MINEUR);
					ba.getG().getP().setInactifs(0,ba.getG().getP().MINEUR);
				}else{
					travailleurs=travailleursMax[state];
					ba.getG().getP().setInactifs(ba.getG().getP().getInactifs(ba.getG().getP().MINEUR)-travailleurs,ba.getG().getP().MINEUR);
				}
				int nbp = travailleurs*Util.randomInt(5,10);
				ba.getG().setRessources(1,ba.getG().getRessources()[1]+nbp);
				if(this.state != this.maxstate-1){
					b.enable(true);
				}
			}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == b){
			if(!pourcent){
				if(this.state != this.maxstate-1){
					this.state++;
					nouveau = true;
					pourcent = true;
					reinitTemps();
					p = 0;
					disabled = true;
					b.enable(false);
					switch(state){
						case 2:
							prix = new int[]{150,0,0};
							break;
						case 3:
							prix = new int[]{500,0,0};
							break;
					}
				}
				
			}
			
		}
	}

	@Override
	public String[] getInfo2() {
		String s[] = {ba.getlang().getDesc().get(ba.getlang().MINE).get(0),""};
		if(pourcent){s[1]="("+ba.getlang().getDesc().get(ba.getlang().MINE).get(1)+" : "+p+"%)";}
		return s;
	}
	
	@Override
	public String getUtil() {
		return ba.getlang().getUtil().get(ba.getlang().MINEGUI);
	}

	@Override
	public void deleteCheck() {
		//TODO
	}
	
	@Override
	public void internInit() {
		b = new Button(ba.getlang().getBouton().get(6),ba);
	}
}
