package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpPanel extends Panel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Base ba;
	private Dimension b = new Dimension(90,30);
	
	private int taillex = 360;
	private int tailley = 140;
	
	public PopUpPanel(Base ba,String title, String[] texte, String sb1, String sb2, ActionListener al, ActionListener al2) {
		super(ba);
		this.ba = ba;
		this.setLayout(new BorderLayout());
		
		JLabel jtitle = new JLabel(title);
		jtitle.setForeground(Color.BLACK);
		jtitle.setFont(ba.getF().deriveFont(Font.PLAIN,40));

		Button b1 = new Button(sb1,ba);
		b1.setPreferredSize(b);
		b1.addActionListener(this);
		b1.addActionListener(al);
		
		Button b2 = new Button(sb2,ba);	
		b2.setPreferredSize(b);
		b2.addActionListener(this);
		b2.addActionListener(al2);
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,0));
		c.setBackground(new Color(0,0,0,0));
		s.setBackground(new Color(0,0,0,0));
		
		n.add(jtitle);
		for(String s1: texte){
			JLabel jl = new JLabel(s1);
			jl.setForeground(Color.BLACK);
			jl.setFont(ba.getF().deriveFont(Font.PLAIN,20));
			c.add(jl);
		}
		
		s.add(b1);
		s.add(b2);
		
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);

		this.setBounds((ba.getWidth()-taillex)/2,(ba.getGp().getHeight()-tailley)/2,taillex,tailley);
	}
	
	public PopUpPanel(Base ba,String title, String[] texte, String sb1, ActionListener al) {
		super(ba);
		this.ba = ba;
		this.setLayout(new BorderLayout());
		
		JLabel jtitle = new JLabel(title);
		jtitle.setForeground(Color.BLACK);
		jtitle.setFont(ba.getF().deriveFont(Font.PLAIN,40));

		Button b1 = new Button(sb1,ba);
		b1.setPreferredSize(b);
		b1.addActionListener(this);
		b1.addActionListener(al);
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,0));
		c.setBackground(new Color(0,0,0,0));
		s.setBackground(new Color(0,0,0,0));
		
		n.add(jtitle);
		for(String s1: texte){
			JLabel jl = new JLabel(s1);
			jl.setForeground(Color.BLACK);
			jl.setFont(ba.getF().deriveFont(Font.PLAIN,20));
			c.add(jl);
		}
		
		s.add(b1);
		
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);

		this.setBounds((ba.getWidth()-360)/2,(ba.getHeight()-(ba.getOpt().interfSize[1]+ba.getOpt().interfSize[0])-140)/2,360,140);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.getParent().remove(this);
		if(ba.hasGame()){
			//ga.getG().getTick().start();
			ba.resumeG();
			ba.cleanGP();
		}
		
	}

		
	
}
