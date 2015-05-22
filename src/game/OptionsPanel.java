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

	private Base ba;
	
	private Button taille;
	private Button pe;
	private Button details;
	private Button anim;
	private Button fps;
	private Button lang;
	
	private Button appliquer;
	private Button defaut;
	private Button retour;
	private Options opt;
	private Dimension b = new Dimension(160,30);
	
	
	public OptionsPanel(Base ba){
		this.ba = ba;
		opt = ba.getOpt();
		
		this.setLayout(new BorderLayout());
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,10));
		c.setBackground(new Color(255,255,255,25));
		s.setBackground(new Color(0,0,0,10));
		
		//titre
		JLabel title = new JLabel(ba.getlang().getMenu().get(1));
		title.setForeground(Color.BLACK);
		title.setFont(ba.getF().deriveFont(Font.PLAIN,60));
		n.add(title);
		
		//Taille
		JPanel jpt = new JPanel();
		
		JLabel ta = new JLabel(ba.getlang().getGuimenu().get(0).get(0)+" :");
		ta.setForeground(Color.BLACK);
		ta.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		jpt.add(ta);
		
		taille = new Button(opt.size[0]+"x"+opt.size[1],ba);
		taille.setPreferredSize(b);
		taille.addActionListener(this);
		jpt.add(taille);
		
		jpt.setBackground(new Color(0,0,0,0));
		jpt.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jpt);
		
		//Plein écran
		JPanel jpe = new JPanel();
		
		JLabel ple = new JLabel(ba.getlang().getGuimenu().get(0).get(1)+" :");
		ple.setForeground(Color.BLACK);
		ple.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		jpe.add(ple);
		
		pe = new Button(opt.fullscreen?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1),ba);
		pe.setPreferredSize(b);
		pe.addActionListener(this);
		jpe.add(pe);
		
		jpe.setBackground(new Color(0,0,0,0));
		jpe.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jpe);
		
		//Details
		JPanel jd = new JPanel();
		
		JLabel det = new JLabel(ba.getlang().getGuimenu().get(0).get(2)+" :");
		det.setForeground(Color.BLACK);
		det.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		jd.add(det);
		
		details = new Button(opt.details?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1),ba);
		details.setPreferredSize(b);
		details.addActionListener(this);
		jd.add(details);
		
		jd.setBackground(new Color(0,0,0,0));
		jd.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jd);
		
		//animations
		JPanel ja = new JPanel();
		
		JLabel ani = new JLabel(ba.getlang().getGuimenu().get(0).get(3)+" :");
		ani.setForeground(Color.BLACK);
		ani.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		ja.add(ani);
		
		anim = new Button(opt.animations?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1),ba);
		anim.setPreferredSize(b);
		anim.addActionListener(this);
		ja.add(anim);
		
		JPanel jf = new JPanel();
		
		JLabel fpsl = new JLabel(ba.getlang().getGuimenu().get(0).get(4)+" :");
		fpsl.setForeground(Color.BLACK);
		fpsl.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		jf.add(fpsl);
		
		fps = new Button(opt.fps?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1),ba);
		fps.setPreferredSize(b);
		fps.addActionListener(this);
		jf.add(fps);
		
		jf.setBackground(new Color(0,0,0,0));
		jf.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jf);
		
		//Langue
		JPanel jl = new JPanel();
		
		JLabel lan = new JLabel(ba.getlang().getGuimenu().get(0).get(5)+" :");
		lan.setForeground(Color.BLACK);
		lan.setFont(ba.getF().deriveFont(Font.PLAIN,40));
		jl.add(lan);
		
		lang = new Button(ba.getlang().getLangue(opt.langue),ba);
		lang.setPreferredSize(b);
		lang.addActionListener(this);
		jl.add(lang);
		
		jl.setBackground(new Color(0,0,0,0));
		jl.setPreferredSize(new Dimension(opt.size[0],40));
		c.add(jl);
		
		//boutons
		appliquer = new Button(ba.getlang().getBouton().get(4),ba);
		appliquer.setPreferredSize(b);
		appliquer.addActionListener(this);
		s.add(appliquer);
		
		defaut = new Button(ba.getlang().getBouton().get(5),ba);
		defaut.setPreferredSize(b);
		defaut.addActionListener(this);
		s.add(defaut);
		
		retour = new Button(ba.getlang().getBouton().get(7),ba);
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
			pe.setTitle(opt.fullscreen?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == details){
			opt.details = !opt.details;
			details.setTitle(opt.details?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == anim){
			opt.animations = !opt.animations;
			anim.setTitle(opt.animations?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == fps){
			opt.fps = !opt.fps;
			fps.setTitle(opt.fps?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
		}
		
		if(arg0.getSource() == lang){
			opt.langue++;
			if(opt.langue==2){
				opt.langue=0;
			}
			lang.setTitle(ba.getlang().getLangue(opt.langue));
		}
		
		if(arg0.getSource() == defaut){
			opt.setDefaut();
			taille.setTitle(opt.size[0]+"x"+opt.size[1]);
			pe.setTitle(opt.fullscreen?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
			details.setTitle(opt.details?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
			anim.setTitle(opt.animations?ba.getlang().getUsage().get(0):ba.getlang().getUsage().get(1));
			lang.setTitle(ba.getlang().getLangue(opt.langue));
		}
		if(arg0.getSource() == appliquer){
			ba.setOpt(opt);
			ba.majLang();
			ba.openMainMenu();
			ba.openOptions();
			ba.getFm().saveOptions(opt);
		}
		if(arg0.getSource() == retour){
			ba.openMainMenu();
		}
		
	}
	
}
