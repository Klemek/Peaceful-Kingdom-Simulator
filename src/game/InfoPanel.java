package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class InfoPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Base ba;
	private BufferedImage[][] terrain;
	private BufferedImage[][] details;
	private BufferedImage[][] construction;
	private BufferedImage[][] constructionGris;
	private BufferedImage pourcent;
	private BufferedImage alertes;
	private BufferedImage fond;
	private String info[] = {};
	private int nbalertes;
	private Font f;
	private Font f2;
	private Button b1;
	private Button b2; 
	private Button b3;
	private JPanel b1p = new JPanel();
	private JPanel b2p = new JPanel();
	private JPanel b3p = new JPanel();
	private Minimap mm;
	private JPanel ni = new JPanel();
	
	public Button getB1() {
		return b1;
	}
 
	public Button getB2() {
		return b2;
	}

	public Button getB3() {
		return b3;
	}

	public InfoPanel(Base ba){
		this.ba = ba;
		this.f = ba.getF().deriveFont(Font.PLAIN,20);
		this.f2 = ba.getF().deriveFont(Font.PLAIN,40);
		this.terrain = ba.getTl().getLot(ba.getTl().TERRAIN);
		this.details = ba.getTl().getLot(ba.getTl().DETAILS);
		this.construction = ba.getTl().getLot(ba.getTl().CONSTRUCTION2);
		this.constructionGris = ba.getTl().getLot(ba.getTl().CONSTRUCTION1);
		for(int j = 0; j < constructionGris.length; j++){
			for(int i = 0; i < constructionGris[j].length; i++){
				constructionGris[j][i] = change(constructionGris[j][i],false,false,true);
			}
		}
		
		
		this.pourcent = ba.getTl().getConstantImage(0);
		this.alertes = ba.getTl().getConstantImage(7);
		nbalertes = ba.getTl().getConstantsImagesTaille(7);
		this.fond = ba.getTl().getConstantImage(3);
		
		b1 = new Button(ba.getlang().getBouton().get(12),ba);
		b2 = new Button(ba.getlang().getBouton().get(8),ba);
		b3 = new Button(ba.getlang().getBouton().get(13),ba);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		//*
		this.setLayout(new BorderLayout());
		
		b1p.setLayout(new BorderLayout());
		b1p.add(b1,BorderLayout.CENTER);
		this.add(b1p,null);
		b1p.setBounds(ba.getOpt().size[0]-460,15,180,30);//430+(ga.getOpt().size[0]-880)/2
		
		
		b2p.setLayout(new BorderLayout());
		b2p.add(b2,BorderLayout.CENTER);
		this.add(b2p,null);
		b2p.setBounds(ba.getOpt().size[0]-460,60,180,30);
		
		b3p.setLayout(new BorderLayout());
		b3p.add(b3,BorderLayout.CENTER);
		this.add(b3p,null);
		b3p.setBounds(ba.getOpt().size[0]-460,105,180,30);
		
		mm = new Minimap(ba);
		this.add(mm,null);
		mm.setBounds(ba.getOpt().size[0]-258,12,257,126);
		
		this.add(ni,null);
		ni.setVisible(false);

	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		
		//fond
		/*
		g2.setColor(new Color(150,0,0));
		g2.fillRect(0, 0,this.getWidth(),this.getHeight());
		//Visualisation
		g2.setColor(Color.WHITE);
		g2.fillRect(10,10,this.getWidth()/6-20,this.getHeight()-20);
		g2.setColor(Color.BLACK);
		g2.fillRect(12,12,this.getWidth()/6-24,this.getHeight()-24);
		//Infos
		g2.setColor(Color.WHITE);
		g2.fillRect(this.getWidth()/6, 10,this.getWidth()/6-10, 30);
		g2.fillRect(this.getWidth()/6, 50,this.getWidth()/6*2-20, this.getHeight()-60);
		//map
		g2.setColor(Color.WHITE);
		g2.fillRect(this.getWidth()-270,10,this.getWidth()/6*2-40,this.getHeight()-20);
		g2.setColor(Color.BLACK);
		g2.fillRect(this.getWidth()-268,12,this.getWidth()/6*2-44,this.getHeight()-24);
		*/
		g2.drawImage(fond,0,0,fond.getWidth()/100*49,this.getHeight(),0,fond.getHeight()/37*7,fond.getWidth()/100*49,fond.getHeight(),this);
		g2.drawImage(fond,fond.getWidth()/100*49,0,this.getWidth()-(fond.getWidth()/100*31),this.getHeight(),fond.getWidth()/100*49,fond.getHeight()/37*7,fond.getWidth()-(fond.getWidth()/100*31),fond.getHeight(),this);
		g2.drawImage(fond,this.getWidth()-(fond.getWidth()/100*31),0,this.getWidth(),this.getHeight(),fond.getWidth()-(fond.getWidth()/100*31),fond.getHeight()/37*7,fond.getWidth(),fond.getHeight(),this);
		if(ba.getG().hasSelected()){
			//Visualisation
			if(ba.getG().getSelected()[0] != -1 && ba.getG().getSelected()[1] != -1){
				Terrain t = ba.getG().getM().getMap0()[ba.getG().getSelected()[0]][ba.getG().getSelected()[1]];
				Construction c = ba.getG().getM().getMap1()[ba.getG().getSelected()[0]][ba.getG().getSelected()[1]];
				drawTerrain(t.getTexture(),0,t.getAnim(),18,18,g2,112,0,1,false);
				if(t.getDetails() != null){
					for(Detail d: t.getDetails()){
						if(d.getCoord()[0]+d.getCoord()[1] < 1){
							drawDetail(d.getT(), d.getAp(), d.getNb(),d.getCoord()[0],d.getCoord()[1],11,g2);
						}
					}
				}
				if(c != null){
					drawTerrain(c.getTexture(),1,c.getAnim(),18,18,g2,112,c.getState(),c.getMaxstate(),c.isDisabled());
					if(c.hasPourcent())
						drawPourcent(c.getP(),g2);
				}else if(t.hasDecor()){
					drawTerrain(t.getDTexture(),1,t.getDAnim(),18,18,g2,112,0,1,false);
				}
				if(t.getDetails() != null){
					for(Detail d: t.getDetails()){
						if(d.getCoord()[0]+d.getCoord()[1] > 1){
							drawDetail(d.getT(), d.getAp(), d.getNb(), d.getCoord()[0],d.getCoord()[1],11,g2);
						}
					}
				}
				if(c != null){
					if(c.hasPourcent())
						drawPourcent(c.getP(),g2);
					if(c.hasAlerte())
						drawAlerte(c.getAlerteType(),g2);
				}
				//Infos
				g2.setColor(Color.BLACK);
				g2.setFont(f2);
				g2.drawString((c != null?c.getInfo(1):t.getInfo(1))+" "+(c != null?c.getInfo(2):t.getInfo(2)),155, 35);
				g2.setFont(f);
				info = c != null?c.getInfo2():t.getInfo2();
				if(info != null){
					for(int i = 0; i < info.length; i++){
						g2.drawString(info[i],155, 67+i*15);
					}
				}
			}
			
			
			

		}
	}
	
	public void drawDetail(int t, int ap, int nb, double x, double y ,int taille,  Graphics2D g2){
		int x1 = Util.arrondi(18+(((1-x)/2)+(y/2))*112);
		int y1 = Util.arrondi(50+((x/2)+(y/2))*56);
		g2.drawImage(details[ba.getG().getSaison()][t],x1,y1,x1+taille,y1+taille,ap*(details[ba.getG().getSaison()][t].getWidth()/nb),0,(ap+1)*(details[ba.getG().getSaison()][t].getWidth()/nb),details[ba.getG().getSaison()][t].getHeight(),this);
	}
	
	public void drawTerrain(int t, int img, int anim, int x, int y,Graphics2D g2,int taille, int state, int maxstate, boolean g){
		BufferedImage[] i = null;
		switch(img){
			case 0:
				i = terrain[ba.getG().getSaison()];
				break;
			case 1:
				if(g)i = constructionGris[ba.getG().getSaison()];
				else i = construction[ba.getG().getSaison()];					
		}
		int he = i[t].getHeight()/maxstate;
		g2.drawImage(i[t],x,y,x+taille,y+taille,anim*he,state*he,anim*he+he,state*he+he,this);
	}
	
	public void drawPourcent(int p,Graphics2D g2){
		Image i = pourcent;
		int x = 40;
		int y = 80;
		int tailleX = 69;
		int tailleY = 13;
		g2.drawImage(i,x,y,x+tailleX,y+tailleY,0,0,i.getWidth(this),i.getHeight(this)/2,this);
		g2.drawImage(i,x,y,x+(p*tailleX/100),y+tailleY,0,i.getHeight(this)/2,p*i.getWidth(this)/100,i.getHeight(this),this);
	}
	
	public void drawAlerte(int a, Graphics2D g2){
		Image i = alertes;
		int x = 60;
		int y = 70;
		int tailleX = 30;
		int tailleY = 30;
		g2.drawImage(i,x,y,x+tailleX,y+tailleY,i.getWidth(this)/nbalertes*a,0,i.getWidth(this)/nbalertes*(a+1),i.getHeight(this),this);		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ba.actionPerformed(arg0);
		
	}
	
	private BufferedImage change(BufferedImage imga,boolean rouge, boolean vert, boolean trans){
		if(imga== null)return null;
		BufferedImage img = imga;
		int w = img.getWidth();
	    int h = img.getHeight(); 
	    int x = 0;
	    int y = 0;
	    int count = 0;
	    for (x=0;x<w; x++) {
	    	for (y=0;y<h; y++) {
	    		if(img.getRGB(x, y) != 0) count++;
		    }
	    }
	    int[][] positions = new int[count][2];
	    count = 0;
	    for (x=0;x<w; x++) {
	    	for (y=0;y<h; y++) {
	    		if(img.getRGB(x, y) != 0){
	    			positions[count] = new int[]{x,y};
	    			count++;
	    		}
		    }
	    }
	    for(int[] i:positions){
	    	x=i[0];
	    	y=i[1];
	    	int rgb = 0;
        	Color co = new Color(img.getRGB(x, y),true);
        	int l = (co.getBlue()+co.getRed()+co.getGreen())/3;
	        if(rouge)rgb = new Color(co.getRed(),0,0,co.getAlpha()).getRGB();
	        if(vert)rgb = new Color(0,co.getGreen(),0,co.getAlpha()).getRGB();
	        if(trans)rgb = new Color(l,l,l,co.getAlpha()).getRGB();
        	img.setRGB(x,y,rgb);
	    }
		return img;
	}
}