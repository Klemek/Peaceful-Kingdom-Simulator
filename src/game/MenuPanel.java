package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class MenuPanel extends Panel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Base ba;
	private JLabel title;
	private Dimension b = new Dimension(160,30);
	private Button continuer;
	private Button quitter;
	
	private int taillex = 180;
	private int tailley = 115;
	
	private boolean game = true;
	
	public MenuPanel(Base g) {
		super(g);
		this.ba = g;
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
		this.setBounds((ba.getWidth()-taillex)/2,(ba.getGp().getHeight()-tailley)/2,taillex,tailley);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == continuer){
			close();
		}
		if(e.getSource() == quitter){
			game = false;
			ba.openMainMenu();
			close();
		}
		
	}
	
	public void close(){
		if(game){

			ba.getG().setTick(ba.getG().getDELAY()[0]);
			ba.resumeG();
		}
		this.getParent().remove(this);
		ba.cleanGP();
	}

		
	
}
