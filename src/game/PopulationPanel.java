package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopulationPanel extends Panel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Base ga;
	private JLabel title;
	private JLabel pop;
	private Dimension b = new Dimension(160,30);
	private Button retour;
	
	private int taillex = 360;
	private int tailley = 360;
	
	private int j;
	
	public PopulationPanel(final Base ba) {
		super(ba);
		this.ga = ba;
		this.setLayout(new BorderLayout());
		title = new JLabel(ga.getlang().getMenu().get(4));
		this.removeAll();
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,0));
		c.setBackground(new Color(0,0,0,0));
		s.setBackground(new Color(0,0,0,0));
		
		title.setForeground(Color.BLACK);
		title.setFont(ga.getF().deriveFont(Font.PLAIN,40));
		n.add(title);
		
		pop = new JLabel("                          "+ga.getlang().getGuimenu().get(1).get(0)+" : "+ga.getG().getP().getCompte()[ga.getG().getP().POPULATION]+"                          ");
		pop.setForeground(Color.BLACK);
		pop.setFont(ga.getF().deriveFont(Font.PLAIN,30));
		c.add(pop);
		
		for(int i = 1; i < ga.getG().getP().getCompte().length; i++){
			if(ga.getG().getP().getCompte()[i] != 0  || i==1 || ga.getG().getP().getDisponible(i) != 0){
				JLabel trav = new JLabel(ga.getG().getP().getMetiers(ba)[i]+" :");
				JLabel nb = new JLabel(ga.getG().getP().getCompte()[i]+(i!=1?"/"+ga.getG().getP().getDisponible(i):""));
				trav.setForeground(Color.BLACK);
				nb.setForeground(Color.BLACK);
				trav.setFont(ga.getF().deriveFont(Font.PLAIN,30));
				nb.setFont(ga.getF().deriveFont(Font.PLAIN,30));
				c.add(trav);
				if(i != 1){
					Button m = new Button(8,ga);
					m.setPreferredSize(new Dimension(30,30));
					j = i;
					m.addActionListener(new ActionListener(){
						int i = j;
						public void actionPerformed(ActionEvent e) {
							if(ga.getG().getP().getCompte()[i] != 0){
								ga.getG().getP().change(-1, i);
							}
						}
					});
					c.add(m);
				}
				c.add(nb);
				if(i != 1){
					Button p = new Button(7,ga);
					p.setPreferredSize(new Dimension(30,30));
					c.add(p);
				}
			}
		}
		
		
		
		
		retour = new Button(ga.getlang().getBouton().get(7),ga);
		retour.setPreferredSize(b);
		retour.addActionListener(this);
		
		s.add(retour);
		
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);
		
		this.setBounds((ga.getWidth()-taillex)/2,(ga.getGp().getHeight()-tailley)/2,taillex,tailley);
		
		for(int i = 0; i < this.getComponentCount(); i++){
			System.out.println(this.getComponent(i).toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retour){
			this.getParent().remove(this);

			ga.resumeG();
		}
		
	}

		
	
}
