package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewGamePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Base ba;
	private JLabel title;
	private Button taille;
	private Button nouveau;
	private Button retour;
	private Dimension b = new Dimension(160,30);
	
	private int[] TAILLES = {15,20,30,35,40};
	private int vTaille = 2;
	
	public NewGamePanel(Base ba) {
		this.ba = ba;
		
		title = new JLabel(ba.getlang().getMenu().get(6));
		
		this.setLayout(new BorderLayout());
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,10));
		c.setBackground(new Color(255,255,255,25));
		s.setBackground(new Color(0,0,0,10));
		
		title.setFont(ba.getF().deriveFont(Font.PLAIN,60));
		title.setForeground(Color.BLACK);
		n.add(title);
		
		JPanel jta = new JPanel();
		
		JLabel tai = new JLabel(ba.getlang().getGuimenu().get(2).get(0)+" :");
		tai.setForeground(Color.BLACK);
		tai.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		jta.add(tai);
		
		taille = new Button(""+TAILLES[vTaille],ba);
		taille.setPreferredSize(b);
		taille.addActionListener(this);
		jta.add(taille);

		jta.setBackground(new Color(0,0,0,0));
		jta.setPreferredSize(new Dimension(ba.getOpt().size[0],40));
		c.add(jta);
		
		
		nouveau = new Button(ba.getlang().getBouton().get(0),ba);
		nouveau.setPreferredSize(b);
		nouveau.addActionListener(this);
		s.add(nouveau);
		
		retour = new Button(ba.getlang().getBouton().get(7),ba);
		retour.setPreferredSize(b);
		retour.addActionListener(this);
		s.add(retour);
		
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == retour){
			ba.openMainMenu();
		}
		
		if(arg0.getSource() == nouveau){
			ba.openNewGame(TAILLES[vTaille]);
		}
		
		if(arg0.getSource() == taille){
			vTaille=vTaille+1>TAILLES.length-1?0:vTaille+1;
			taille.setTitle(""+TAILLES[vTaille]);
		}
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(150,0,0));
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
	}

	
}
