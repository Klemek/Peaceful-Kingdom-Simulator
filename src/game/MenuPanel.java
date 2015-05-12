package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class MenuPanel extends Panel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Base ga;
	private JLabel title;
	private Dimension b = new Dimension(160,30);
	private Button continuer;
	private Button quitter;
	
	private int taillex = 180;
	private int tailley = 115;
	
	private boolean game = true;
	
	public MenuPanel(Base g) {
		super(g);
		this.ga = g;
		title = new JLabel(g.getlang().getMenu().get(0));
		title.setForeground(Color.BLACK);
		title.setFont(g.getF().deriveFont(Font.PLAIN,40));
		continuer = new Button(g.getlang().getBouton().get(1),g);
		quitter = new Button(g.getlang().getBouton().get(3),g);
		continuer.setPreferredSize(b);
		quitter.setPreferredSize(b);
		continuer.addActionListener(this);
		quitter.addActionListener(this);
		this.add(title);
		this.add(continuer);
		this.add(quitter);
		this.setBounds((ga.getWidth()-taillex)/2,(ga.getGp().getHeight()-tailley)/2,taillex,tailley);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == continuer){
			close();
		}
		if(e.getSource() == quitter){
			game = false;
			ga.openMainMenu();
			close();
		}
		
	}
	
	public void close(){
		if(game){
			ga.getG().getTick().setDelay(ga.getG().getDELAY()[0]);
			ga.getG().getTick().start();
		}
		this.getParent().remove(this);
		ga.cleanGP();
	}

		
	
}
