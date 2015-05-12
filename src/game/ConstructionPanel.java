package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConstructionPanel extends Panel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private Base ga;
	private JLabel title;
	private Dimension b = new Dimension(120,30);
	private Dimension b2 = new Dimension(30,30);
	private Button construire;
	private Button retour;
	private Button droite;
	private Button gauche;
	private Font f;
	private int taillex = 578;
	private int tailley = 355;
	private int esp = 5;
	private int t = 186;
	private int t2 = 240;
	private int deb = 50;
	private BufferedImage fond;
	private BufferedImage preview;
	private Construction[] liste;
	private Terrain[] liste2;
	private BufferedImage[] terrain;
	private BufferedImage[] construction;
	private BufferedImage[] details;
	private BufferedImage ressources;
	private boolean sel = false;
	private int selection = 0;
	
	public ConstructionPanel(Base g) {
		super(g);
		this.ga = g;
		title = new JLabel(ga.getlang().getMenu().get(3));
		liste = g.getG().getListe();
		liste2 = g.getG().getListe2();
		fond = g.getTl().getConstantImage(4);
		preview = g.getTl().getConstantImage(5);
		ressources = g.getTl().getConstantImage(6);
		f = g.getF();
		this.terrain = ga.getTl().getLot(ga.getTl().TERRAIN)[0];
		this.construction = ga.getTl().getLot(ga.getTl().CONSTRUCTION1)[0];
		this.details = ga.getTl().getLot(ga.getTl().DETAILS)[0];
		this.setLayout(new BorderLayout());
		
		JPanel n = new JPanel();
		JPanel c = new JPanel();
		JPanel s = new JPanel();
		
		n.setBackground(new Color(0,0,0,0));
		c.setBackground(new Color(0,0,0,0));
		s.setBackground(new Color(0,0,0,0));
		
		title.setForeground(Color.BLACK);
		title.setFont(f.deriveFont(Font.PLAIN,40));	
		n.add(title);
		//TODO mutli page
		gauche = new Button(9,g);
		gauche.enable(false);
		gauche.setPreferredSize(b2);
		gauche.addActionListener(this);
		s.add(gauche);
		construire = new Button(ga.getlang().getBouton().get(8),g);
		construire.enable(false);
		construire.setPreferredSize(b);
		construire.addActionListener(this);
		s.add(construire);
		retour = new Button(ga.getlang().getBouton().get(7),g);
		retour.setPreferredSize(b);
		retour.addActionListener(this);
		s.add(retour);
		droite = new Button(10,g);
		droite.enable(false);
		droite.setPreferredSize(b2);
		droite.addActionListener(this);
		s.add(droite);
		
		this.setBounds((ga.getWidth()-taillex)/2,(ga.getGp().getHeight()-tailley)/2,taillex,tailley);
		
		this.add(n,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		this.add(s,BorderLayout.SOUTH);
		
		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retour){
			this.getParent().remove(this);
			ga.cleanGP();
			ga.getG().getTick().start();
		}
		if(e.getSource() == construire){
			ga.getG().setSelstate(1);
			ga.getG().setChoix(selection);
			this.getParent().remove(this);
			ga.cleanGP();
			ga.getG().getTick().start();
		}
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D)g;
		int i = fond.getHeight()/3;
		
		construire.enable((sel && liste[selection].hasRessources(ga.getG())));
		
		int lignes = liste.length/3;
		int fin = liste.length-lignes*3;
		
		g2.drawImage(fond,0,0,i,i,0,0,i,i,this);
		g2.drawImage(fond,i,0,this.getWidth()-i,i,i,0,fond.getWidth()-i,i,this);
		g2.drawImage(fond,this.getWidth()-i,0,this.getWidth(),i,fond.getWidth()-i,0,fond.getWidth(),i,this);
		
		g2.drawImage(fond,0,i,i,this.getHeight()-i,0,i,i,fond.getHeight()-i,this);
		g2.drawImage(fond,i,i,this.getWidth()-i,this.getHeight()-i,i,i,fond.getWidth()-i,fond.getHeight()-i,this);
		g2.drawImage(fond,this.getWidth()-i,i,this.getWidth(),this.getHeight()-i,fond.getWidth()-i,i,fond.getWidth(),fond.getHeight()-i,this);
		
		g2.drawImage(fond,0,this.getHeight()-i,i,this.getHeight(),0,fond.getHeight()-i,i,fond.getHeight(),this);
		g2.drawImage(fond,i,this.getHeight()-i,this.getWidth()-i,this.getHeight(),i,fond.getHeight()-i,fond.getWidth()-i,fond.getHeight(),this);
		g2.drawImage(fond,this.getWidth()-i,this.getHeight()-i,this.getWidth(),this.getHeight(),fond.getWidth()-i,fond.getHeight()-i,fond.getWidth(),fond.getHeight(),this);
	
		int s = 0;
		for(int j = 0; j <= lignes; j++){
			for(int j2 = 0; j2 < (j!=lignes?3:fin); j2++){
				if(sel && selection == s)g2.drawImage(preview,(esp*(j2+1))+(t*j2),deb+(esp*j)+(t2*j),(esp*(j2+1))+(t*(j2+1)),deb+(esp*j)+(t2*(j+1)),preview.getWidth()/2,0,preview.getWidth(),preview.getHeight(),this);
				else g2.drawImage(preview,(esp*(j2+1))+(t*j2),deb+(esp*j)+(t2*j),(esp*(j2+1))+(t*(j2+1)),deb+(esp*j)+(t2*(j+1)),0,0,preview.getWidth()/2,preview.getHeight(),this);
				s++;
			}
		}
		
		g2.setColor(Color.BLACK);
		g2.setFont(f.deriveFont(Font.PLAIN,20));
		int h;
		int w;
		for(int j = 0; j < liste.length; j++){
			g2.setColor(Color.BLACK);
			int x = j-(j/3)*3;
			int y = j/3;
			drawTerrain(liste2[j].getTexture(),0,liste2[j].getAnim(),(esp*(x+1))+(t*x)+13,deb+(esp*y)+(t2*y)+13,g2,160,0,1);
			if(liste2[j].getDetails() != null){
				for(Detail d: liste2[j].getDetails()){
					if(d.getCoord()[0]+d.getCoord()[1] < 1){
						drawDetail((esp*(x+1))+(t*x)+13,deb+(esp*y)+(t2*y)+13,d.getT(), d.getAp(), d.getNb(),d.getCoord()[0],d.getCoord()[1],16,g2);
					}
				}
			}
			drawTerrain(liste[j].getTexture(),1,liste[j].getAnim(),(esp*(x+1))+(t*x)+13,deb+(esp*y)+(t2*y)+13,g2,160,liste[j].getState(),liste[j].getMaxstate());			
			if(liste2[j].getDetails() != null){
				for(Detail d: liste2[j].getDetails()){
					if(d.getCoord()[0]+d.getCoord()[1] > 1){
						drawDetail((esp*(x+1))+(t*x),deb+(esp*y)+(t2*y)+13,d.getT(), d.getAp(), d.getNb(), d.getCoord()[0],d.getCoord()[1],16,g2);
					}
				}
			}
			
			w = (int) g2.getFontMetrics().getStringBounds(liste[j].getInfo(1), g2).getWidth();
			h = (int) g2.getFontMetrics().getStringBounds(liste[j].getInfo(1), g2).getHeight();
			g2.drawString(liste[j].getInfo(1),(esp*(x+1))+(t*x)+(t-w)/2,deb+(esp*y)+(t2*y)+t+2+h);
			
			g2.setColor(Color.DARK_GRAY);
			w = (int) g2.getFontMetrics().getStringBounds(liste[j].getUtil(), g2).getWidth();
			g2.drawString(liste[j].getUtil(),(esp*(x+1))+(t*x)+(t-w)/2,deb+(esp*y)+(t2*y)+t+4+h*2);
			
			h = (int) g2.getFontMetrics().getStringBounds("0", g2).getHeight();
			//TODO
			w = 0;
			int[] tw = new int[liste[j].getPrix().length];
			for(int k = 0; k < liste[j].getPrix().length; k++){
				if(liste[j].getPrix()[k] != 0){
					w = w + (int) g2.getFontMetrics().getStringBounds(""+liste[j].getPrix()[k], g2).getWidth()+25;
					tw[k] = (int) g2.getFontMetrics().getStringBounds(""+liste[j].getPrix()[k], g2).getWidth()+25;
				}
			}
			for(int k = 0; k < liste[j].getPrix().length; k++){
				if(liste[j].getPrix()[k] != 0){
					int w2 = 0;
					for(int l = 0; l < k; l++){
						w2 += tw[l];
					}
					g2.setColor(liste[j].hasRessources(ga.getG(),k)?Color.BLACK:Color.RED);
					g2.drawImage(ressources,(esp*(x+1))+(t*x)+(t-w)/2+5+w2,deb+(esp*y)+(t2*y)+preview.getHeight()-20,(esp*(x+1))+(t*x)+(t-w)/2+20+w2,deb+(esp*y)+(t2*y)+preview.getHeight()-5,k*ressources.getHeight(),0,k*ressources.getHeight()+ressources.getHeight(),ressources.getHeight(),this);
					g2.drawString(""+liste[j].getPrix()[k],(esp*(x+1))+(t*x)+(t-w)/2+25+w2,deb+(esp*y)+(t2*y)+preview.getHeight()-7);

				}
			}

		}

	}
	
	public void drawTerrain(int t, int img, int anim, int x, int y, Graphics2D g2,int taille, int state, int maxstate){
		BufferedImage[] i = null;
		switch(img){
			case 0:
				i = terrain;
				break;
			case 1:
				i = construction;					
		}
		int he = i[t].getHeight()/maxstate;
		g2.drawImage(i[t],x,y,x+taille,y+taille,anim*he,state*he,anim*he+he,state*he+he,this);
	}
	
	public void drawDetail(int x2, int y2, int t, int ap, int nb, double x, double y ,int taille,  Graphics2D g2){
		int x1 = Util.arrondi(x2+(((1-x)/2)+(y/2))*160);
		int y1 = Util.arrondi(y2+46+((x/2)+(y/2))*80);
		g2.drawImage(details[t],x1,y1,x1+taille,y1+taille,ap*(details[t].getWidth()/nb),0,(ap+1)*(details[t].getWidth()/nb),details[t].getHeight(),this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		sel = false;
		for(int j = 0; j < liste.length; j++){
			int x = j-(j/3)*3;
			int y = j/3;
			if(e.getX() >= ((esp*(x+1))+(t*x)) && e.getX() <= ((esp*(x+1))+(t*x))+t && e.getY() >= deb+(esp*y)+(t2*y) && e.getY() <= deb+(esp*y)+(t2*y)+t2){
				sel = true;
				selection = j;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
