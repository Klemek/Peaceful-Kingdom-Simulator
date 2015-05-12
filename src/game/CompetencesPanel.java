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

public class CompetencesPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Base ba;
	private JLabel title;
	private Button retour;
	private Dimension b = new Dimension(160,30);
	
	public CompetencesPanel(Base ba) {
		this.ba = ba;
		
		title = new JLabel(ba.getlang().getMenu().get(2));
		
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
			ba.openGame();
		}
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(150,0,0));
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
	}
	
}
