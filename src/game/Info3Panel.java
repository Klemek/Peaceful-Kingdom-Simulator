package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info3Panel extends Panel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Base ga;
	private JLabel title;
	private Dimension b = new Dimension(120,30);
	private Button fermer;
	private int taillex = 360;
	
	public Info3Panel(Base g,String t, Component[] comps, int tailley) {
		super(g);
		this.ga = g;
		title = new JLabel("  "+g.getlang().getMenu().get(5)+"  ");
		this.setLayout(new BorderLayout());
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,0));
		c.setBackground(new Color(0,0,0,0));
		s.setBackground(new Color(0,0,0,0));
		
		title.setText(t);
		title.setForeground(Color.BLACK);
		title.setFont(g.getF().deriveFont(Font.PLAIN,40));	
		fermer = new Button(g.getlang().getBouton().get(11),g);
		fermer.setPreferredSize(b);
		fermer.addActionListener(this);
		n.add(title);
		for(int i = 0; i < comps.length; i++){
			c.add(comps[i]);
		}
		s.add(fermer);
		this.setBounds((ga.getWidth()-taillex)/2,(ga.getGp().getHeight()-tailley)/2,taillex,tailley);
	
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fermer){
			this.getParent().remove(this);
			ga.cleanGP();
		}
	}

		
	
}
