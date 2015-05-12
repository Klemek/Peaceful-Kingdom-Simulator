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

public class OptionsPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Base ga;
	
	private Button taille;
	private Button pe;
	private Button details;
	private Button anim;
	private Button lang;
	
	private Button appliquer;
	private Button defaut;
	private Button retour;
	private Options opt;
	private Dimension b = new Dimension(160,30);
	
	
	public OptionsPanel(Base g){
		this.ga = g;
		opt = ga.getOpt();
		
		this.setLayout(new BorderLayout());
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,10));
		c.setBackground(new Color(255,255,255,25));
		s.setBackground(new Color(0,0,0,10));
		
		//titre
		JLabel title = new JLabel(g.getlang().getMenu().get(1));
		title.setForeground(Color.BLACK);
		title.setFont(g.getF().deriveFont(Font.PLAIN,60));
		n.add(title);
		
		//Taille
		JPanel jpt = new JPanel();
		
		JLabel ta = new JLabel(g.getlang().getGuimenu().get(0).get(0)+" :");
		ta.setForeground(Color.BLACK);
		ta.setFont(g.getF().deriveFont(Font.PLAIN,40));
		jpt.add(ta);
		
		taille = new Button(opt.size[0]+"x"+opt.size[1],g);
		taille.setPreferredSize(b);
		taille.addActionListener(this);
		jpt.add(taille);
		
		jpt.setBackground(new Color(0,0,0,0));
		jpt.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jpt);
		
		//Plein écran
		JPanel jpe = new JPanel();
		
		JLabel ple = new JLabel(g.getlang().getGuimenu().get(0).get(1)+" :");
		ple.setForeground(Color.BLACK);
		ple.setFont(g.getF().deriveFont(Font.PLAIN,40));
		jpe.add(ple);
		
		pe = new Button(opt.fullscreen?g.getlang().getUsage().get(0):g.getlang().getUsage().get(1),g);
		pe.setPreferredSize(b);
		pe.addActionListener(this);
		jpe.add(pe);
		
		jpe.setBackground(new Color(0,0,0,0));
		jpe.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jpe);
		
		//Details
		JPanel jd = new JPanel();
		
		JLabel det = new JLabel(g.getlang().getGuimenu().get(0).get(2)+" :");
		det.setForeground(Color.BLACK);
		det.setFont(g.getF().deriveFont(Font.PLAIN,40));
		jd.add(det);
		
		details = new Button(opt.details?g.getlang().getUsage().get(0):g.getlang().getUsage().get(1),g);
		details.setPreferredSize(b);
		details.addActionListener(this);
		jd.add(details);
		
		jd.setBackground(new Color(0,0,0,0));
		jd.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jd);
		
		//animations
		JPanel ja = new JPanel();
		
		JLabel ani = new JLabel(g.getlang().getGuimenu().get(0).get(3)+" :");
		ani.setForeground(Color.BLACK);
		ani.setFont(g.getF().deriveFont(Font.PLAIN,40));
		ja.add(ani);
		
		anim = new Button(opt.animations?g.getlang().getUsage().get(0):g.getlang().getUsage().get(1),g);
		anim.setPreferredSize(b);
		anim.addActionListener(this);
		ja.add(anim);
		
		ja.setBackground(new Color(0,0,0,0));
		ja.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(ja);
		
		//Langue
		JPanel jl = new JPanel();
		
		JLabel lan = new JLabel(g.getlang().getGuimenu().get(0).get(4)+" :");
		lan.setForeground(Color.BLACK);
		lan.setFont(g.getF().deriveFont(Font.PLAIN,40));
		jl.add(lan);
		
		lang = new Button(g.getlang().getLangue(opt.langue),g);
		lang.setPreferredSize(b);
		lang.addActionListener(this);
		jl.add(lang);
		
		jl.setBackground(new Color(0,0,0,0));
		jl.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jl);
		
		//boutons
		appliquer = new Button(g.getlang().getBouton().get(4),g);
		appliquer.setPreferredSize(b);
		appliquer.addActionListener(this);
		s.add(appliquer);
		
		defaut = new Button(g.getlang().getBouton().get(5),g);
		defaut.setPreferredSize(b);
		defaut.addActionListener(this);
		s.add(defaut);
		
		retour = new Button(g.getlang().getBouton().get(7),g);
		retour.setPreferredSize(b);
		retour.addActionListener(this);
		s.add(retour);
		
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(150,0,0));
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == taille){
			opt.sizeSel++;
			if(opt.sizeSel == opt.TAILLES.length)opt.sizeSel = 0;
			opt.size = opt.TAILLES[opt.sizeSel];
			taille.setTitle(opt.size[0]+"x"+opt.size[1]);
		}
		
		if(arg0.getSource() == pe){
			opt.fullscreen = !opt.fullscreen;
			pe.setTitle(opt.fullscreen?ga.getlang().getUsage().get(0):ga.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == details){
			opt.details = !opt.details;
			details.setTitle(opt.details?ga.getlang().getUsage().get(0):ga.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == anim){
			opt.animations = !opt.animations;
			anim.setTitle(opt.animations?ga.getlang().getUsage().get(0):ga.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == lang){
			opt.langue++;
			if(opt.langue==2){
				opt.langue=0;
			}
			lang.setTitle(ga.getlang().getLangue(opt.langue));
		}
		
		if(arg0.getSource() == defaut){
			opt.setDefaut();
			taille.setTitle(opt.size[0]+"x"+opt.size[1]);
			pe.setTitle(opt.fullscreen?ga.getlang().getUsage().get(0):ga.getlang().getUsage().get(1));
			details.setTitle(opt.details?ga.getlang().getUsage().get(0):ga.getlang().getUsage().get(1));
			anim.setTitle(opt.animations?ga.getlang().getUsage().get(0):ga.getlang().getUsage().get(1));
			lang.setTitle(ga.getlang().getLangue(opt.langue));
		}
		if(arg0.getSource() == appliquer){
			ga.setOpt(opt);
			ga.majLang();
			ga.openMainMenu();
			ga.openOptions();
			ga.getFm().saveOptions(opt);
		}
		if(arg0.getSource() == retour){
			ga.openMainMenu();
		}
		
	}
	
}
