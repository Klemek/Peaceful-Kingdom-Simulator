package game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Loader extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private TextureLoader tl = new TextureLoader(this);
	private FontLoader fl = new FontLoader(this);
	private LangLoader ll = new LangLoader(this);
	
	private JLabel title;
	private JPanel titlep = new JPanel();
	
	private JProgressBar bar = new JProgressBar();
	
	public Loader(){
		this.setSize(400,70);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setFocusable(false);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		gbc.gridheight = 1;
	    gbc.gridwidth = 2;
	    
	    title = new JLabel("Chargement des textures...");
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    titlep.setPreferredSize(new Dimension(400,30));
	    this.add(titlep,gbc);
	    titlep.add(title);
	    
	    gbc.gridy = 1;
		bar.setPreferredSize(new Dimension(400,40));
		this.add(bar,gbc);
		this.setVisible(true);
		System.out.println(this.getClass().getResource(""));
		if(!tl.load())return;
		if(!fl.load())return;
		if(!ll.load())return;
		
		new Base(tl,fl,ll);
		this.setVisible(false);
		this.setEnabled(false);
		
		
		
	}
	
	public void setValue(String s,int i){
		bar.setValue(i);
		title.setText(s);
	}
	
}
