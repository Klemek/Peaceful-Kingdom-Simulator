package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Base ba;
	private int VIEW = 1;
	private BufferedImage[][] terrain;
	private BufferedImage[][] terrainRouge;
	private BufferedImage[][] terrainVert;
	private BufferedImage[][] details;
	private BufferedImage[][] detailsRouge;
	private BufferedImage[][] detailsVert;
	private BufferedImage[][] construction;
	private BufferedImage[][] constructionRouge;
	private BufferedImage[][] constructionVert;
	private BufferedImage[][] constructionGris;
	
	private BufferedImage image;
	private Graphics2D buffer;

	private int fps = 0;
	private int fps2 = 0;
	private int zoom = 20;
	private int ZOOMMIN = 2;
	private int ZOOMMAX = 40;
	private int camX = 0;
	private int camY = 0;
	private Timer fpst = new Timer(1000,this);
	private Font f;
	private Font f2;

	private int nbalertes;
	private BufferedImage alertes;
	private BufferedImage pourcent;
	
	public GamePanel(Base ba){
		this.ba = ba;
		
		this.terrain = ba.getTl().getLot(ba.getTl().TERRAIN);
		Graphics gr,gv;
		int w,h;
		this.terrainRouge = new BufferedImage[this.terrain.length][];
		this.terrainVert = new BufferedImage[this.terrain.length][];
		for(int i = 0; i < this.terrain.length; i++){
			this.terrainRouge[i] = new BufferedImage[this.terrain[i].length];
			this.terrainVert[i] = new BufferedImage[this.terrain[i].length];
			for(int j = 0; j < terrain[i].length; j++){
				w = this.terrain[i][j].getWidth();
				h = this.terrain[i][j].getHeight();
				this.terrainRouge[i][j] = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				gr = this.terrainRouge[i][j].createGraphics();
				gr.drawImage(this.terrain[i][j],0,0,null);
				this.terrainVert[i][j] = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				gv = this.terrainVert[i][j].createGraphics();
				gv.drawImage(this.terrain[i][j],0,0,null);
			}
		}
		for(int j = 0; j < 4; j++){
			for(int i = 0; i < terrain[j].length; i++){
				terrainRouge[j][i] = change(terrainRouge[j][i],true,false,false);
				terrainVert[j][i] = change(terrainVert[j][i],false,true,false);
			}
		}
		this.details = ba.getTl().getLot(ba.getTl().DETAILS);
		this.detailsRouge = ba.getTl().getLot(ba.getTl().DETAILS);
		this.detailsVert = ba.getTl().getLot(ba.getTl().DETAILS);
		for(int j = 0; j < 4; j++){
			for(int i = 0; i < details[j].length; i++){
				detailsRouge[j][i] = change(detailsRouge[j][i],true,false,false);
				detailsVert[j][i] = change(detailsVert[j][i],false,true,false);
			}
		}
		this.construction = ba.getTl().getLot(ba.getTl().CONSTRUCTION2);
		this.constructionRouge = ba.getTl().getLot(ba.getTl().CONSTRUCTION2);
		this.constructionVert = ba.getTl().getLot(ba.getTl().CONSTRUCTION2);
		this.constructionGris = ba.getTl().getLot(ba.getTl().CONSTRUCTION1);
		for(int j = 0; j < 4; j++){
			for(int i = 0; i < construction[j].length; i++){
				constructionRouge[j][i] = change(constructionRouge[j][i],true,false,false);
				constructionVert[j][i] = change(constructionVert[j][i],false,true,false);
				constructionGris[j][i] = change(constructionGris[j][i],false,false,true);
			}
		}
		this.alertes = ba.getTl().getConstantImage(7);
		nbalertes = ba.getTl().getConstantsImagesTaille(7);
		this.pourcent = ba.getTl().getConstantImage(0);
		this.addMouseMotionListener(ba);
		this.addMouseListener(ba);
		this.addMouseWheelListener(ba);
		//this.t.start();
		this.fpst.start();
		this.f = ba.getF().deriveFont(Font.PLAIN,20);
		this.f2 = ba.getF().deriveFont(Font.PLAIN,40);
	}
	
	public void paintComponent(Graphics g){
		if(buffer==null){
			image = (BufferedImage) ba.createImage(this.getWidth(),this.getHeight());
			buffer = image.createGraphics();
		}
	    buffer.setColor(Color.BLACK);
	    buffer.fillRect(0,0,this.getWidth(),this.getHeight());
	    
	    switch(VIEW){
	    case 0:
	    	int t = ba.getG().getTaille();
		    for(int i = 0; i < t;i++){
		    	for(int i2 = 0; i2 < t;i2++){
			    	buffer.setColor(ba.getG().getM().getMap0()[i][i2].getType()==1?new Color(153,214,0):ba.getG().getM().getMap0()[i][i2].getType()==2?new Color(45,161,255):ba.getG().getM().getMap0()[i][i2].getType()==3?new Color(166,166,166):new Color(0,0,0));
			    	buffer.fillRect((this.getWidth()-this.getHeight())/2+(i*this.getHeight()/t), i2*this.getHeight()/t,this.getHeight()/t, this.getHeight()/t);
			    }
		    }
	    	break;
	    default:
	    	
	    	camX = (this.getWidth()/2)+ba.getG().getCamX();
	    	camY = (this.getHeight()/2)+ba.getG().getCamY();
	    	Terrain map0[][] = ba.getG().getM().getMap0();
	    	Construction map1[][] = ba.getG().getM().getMap1();
	    	
	    	if(ba.getG().hasOver()){
	    		for(int i2 = ba.getG().getTaille()-1; i2 >= 0; i2--){for(int i = 0; i < ba.getG().getTaille(); i++){	
	    			if(i == ba.getG().getOver()[0] && i2 == ba.getG().getOver()[1]){
	    				switch(ba.getG().getSelstate()){
	    				case 1:
	    					drawTerrain(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
	    					if(map0[i][i2].getDetails() != null){
				    			for(Detail d: map0[i][i2].getDetails()){
				    				if(d.getCoord()[0]+d.getCoord()[1] < 1){
				    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
				    				}
				    			}
				    		}
	    					if(ba.getG().getChoix() != null)drawTerrain(ba.getG().getChoix().getTexture(),1,ba.getG().getChoix().getAnim(),baseX(i,i2),baseY(i,i2),0,ba.getG().getChoix().getState(),ba.getG().getChoix().getMaxstate(),buffer,!(ba.getG().getChoix().estPlacable(i,i2)),ba.getG().getChoix().estPlacable(i,i2),false);
	    					else if(map0[i][i2].hasDecor())drawTerrain(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
	    					if(map0[i][i2].getDetails() != null){
				    			for(Detail d: map0[i][i2].getDetails()){
				    				if(d.getCoord()[0]+d.getCoord()[1] > 1){
				    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
				    				}
				    			}
				    		}
	    					break;
	    				case 2:
	    					drawTerrain(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer,true,false,false);
	    					if(map0[i][i2].getDetails() != null){
				    			for(Detail d: map0[i][i2].getDetails()){
				    				if(d.getCoord()[0]+d.getCoord()[1] < 1){
				    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer,true,false);
				    				}
				    			}
				    		}
	    					if(map1[i][i2] != null)drawTerrain(map1[i][i2].getTexture(),1,map1[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map1[i][i2].getH(),map1[i][i2].getState(),map1[i][i2].getMaxstate(),buffer,true,false,false);
	    					else if(map0[i][i2].hasDecor())drawTerrain(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer,true,false,false);
	    					if(map0[i][i2].getDetails() != null){
				    			for(Detail d: map0[i][i2].getDetails()){
				    				if(d.getCoord()[0]+d.getCoord()[1] > 1){
				    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer,true,false);
				    				}
				    			}
				    		}
	    					break;
	    				default:
	    					drawTerrain(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
	    					if(map0[i][i2].getDetails() != null){
				    			for(Detail d: map0[i][i2].getDetails()){
				    				if(d.getCoord()[0]+d.getCoord()[1] < 1){
				    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
				    				}
				    			}
				    		}
	    					if(map1[i][i2] != null){
				    			drawTerrain(map1[i][i2].getTexture(),1,map1[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map1[i][i2].getH(),map1[i][i2].getState(),map1[i][i2].getMaxstate(),buffer,false,false,map1[i][i2].isDisabled());
				    		}else if(map0[i][i2].hasDecor())drawTerrain(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
	    					if(map0[i][i2].getDetails() != null){
				    			for(Detail d: map0[i][i2].getDetails()){
				    				if(d.getCoord()[0]+d.getCoord()[1] > 1){
				    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
				    				}
				    			}
				    		}
			    			if(map1[i][i2] != null && map1[i][i2].hasPourcent())drawPourcent(map1[i][i2].getP(),baseX(i,i2),baseY(i,i2),buffer,map1[i][i2].getH());
			    			if(map1[i][i2] != null && map1[i][i2].hasAlerte())drawAlerte(map1[i][i2].getAlerteType(),baseX(i,i2),baseY(i,i2),buffer,map1[i][i2].getH());

			    			break;
	    				}
		    			
		    		}
		    		else{
		    			drawTerrain(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
		    			if(map0[i][i2].getDetails() != null){
			    			for(Detail d: map0[i][i2].getDetails()){
			    				if(d.getCoord()[0]+d.getCoord()[1] < 1){
			    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
			    				}
			    			}
			    		}
		    			if(map1[i][i2] != null){
		    				drawTerrain(map1[i][i2].getTexture(),1,map1[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map1[i][i2].getH(),map1[i][i2].getState(),map1[i][i2].getMaxstate(),buffer,false,false,map1[i][i2].isDisabled());
		    			}else if(map0[i][i2].hasDecor())drawTerrain(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
		    			if(map0[i][i2].getDetails() != null){
			    			for(Detail d: map0[i][i2].getDetails()){
			    				if(d.getCoord()[0]+d.getCoord()[1] > 1){
			    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
			    				}
			    			}
			    		}
	    				if(map1[i][i2] != null && map1[i][i2].hasPourcent())drawPourcent(map1[i][i2].getP(),baseX(i,i2),baseY(i,i2),buffer,map1[i][i2].getH());
		    			if(map1[i][i2] != null && map1[i][i2].hasAlerte())drawAlerte(map1[i][i2].getAlerteType(),baseX(i,i2),baseY(i,i2),buffer,map1[i][i2].getH());

		    		
		    		}
		    			
		    	}}
	    	}else{
	    		if(ba.getG().getSelstate()==1){
	    			for(int i2 = ba.getG().getTaille()-1; i2 >= 0; i2--){for(int i = 0; i < ba.getG().getTaille(); i++){	
			    		drawTerrain(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer,!(ba.getG().getChoix().estPlacable(i,i2)),ba.getG().getChoix().estPlacable(i,i2),false);
			    		if(map0[i][i2].getDetails() != null){
			    			for(Detail d: map0[i][i2].getDetails()){
			    				if(d.getCoord()[0]+d.getCoord()[1] < 1){
			    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer,!(ba.getG().getChoix().estPlacable(i,i2)),ba.getG().getChoix().estPlacable(i,i2));
			    				}
			    			}
			    		}
			    		if(map1[i][i2] != null)drawTerrain(map1[i][i2].getTexture(),1,map1[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map1[i][i2].getH(),map1[i][i2].getState(),map1[i][i2].getMaxstate(),buffer,!(ba.getG().getChoix().estPlacable(i,i2)),ba.getG().getChoix().estPlacable(i,i2),false);
			    		else if(map0[i][i2].hasDecor())drawTerrain(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer,!(ba.getG().getChoix().estPlacable(i,i2)),ba.getG().getChoix().estPlacable(i,i2),false);
			    		if(map0[i][i2].getDetails() != null){
			    			for(Detail d: map0[i][i2].getDetails()){
			    				if(d.getCoord()[0]+d.getCoord()[1] > 1){
			    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer,!(ba.getG().getChoix().estPlacable(i,i2)),ba.getG().getChoix().estPlacable(i,i2));
			    				}
			    			}
			    		}
	    			}}
	    		}else{
		    		for(int i2 = ba.getG().getTaille()-1; i2 >= 0; i2--){for(int i = 0; i < ba.getG().getTaille(); i++){	
			    		drawTerrain(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
			    		if(map0[i][i2].getDetails() != null){
			    			for(Detail d: map0[i][i2].getDetails()){
			    				if(d.getCoord()[0]+d.getCoord()[1] < 1){
			    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
			    				}
			    			}
			    		}
			    		if(map1[i][i2] != null){
			    			drawTerrain(map1[i][i2].getTexture(),1,map1[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),map1[i][i2].getH(),map1[i][i2].getState(),map1[i][i2].getMaxstate(),buffer,false,false,map1[i][i2].isDisabled());
			    		}else if(map0[i][i2].hasDecor())drawTerrain(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),map0[i][i2].getH(),0,1,buffer);
			    		if(map0[i][i2].getDetails() != null){
			    			for(Detail d: map0[i][i2].getDetails()){
			    				if(d.getCoord()[0]+d.getCoord()[1] > 1){
			    					drawDetail(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],map0[i][i2].getH(),buffer);
			    				}
			    			}
			    		}
		    			if(map1[i][i2] != null && map1[i][i2].hasPourcent())drawPourcent(map1[i][i2].getP(),baseX(i,i2),baseY(i,i2),buffer,map1[i][i2].getH());
		    			if(map1[i][i2] != null && map1[i][i2].hasAlerte())drawAlerte(map1[i][i2].getAlerteType(),baseX(i,i2),baseY(i,i2),buffer,map1[i][i2].getH());

	    				

		    		}}
	    		}
	    	}
	    	if(ba.getG().getValues() != null){
	    		for(int i = 0; i < ba.getG().getValues().length; i++){
	    	 		if(ba.getG().getValues()[i] != null){
	    	 			if(ba.getG().getValues()[i].getI() == 0)ba.getG().getValues()[i] = null;
	    	 			else drawInfoValue(ba.getG().getValues()[i],buffer);
	    	 		}
	    	 		
	    	 	}
	    	}
	    	buffer.setFont(f2);
	    	
	    	if(ba.getG().getLog()!=null){
	    		if(ba.getG().getLog().size()<5){
	    			buffer.setColor(new Color(0,0,0,128));
	    			buffer.fillRect(5,this.getHeight()-5,355,-(ba.getG().getLog().size()*25+5));
		    		for(int i = 0; i < ba.getG().getLog().size(); i++){
		    			buffer.setColor(Util.changeAlpha(ba.getG().getLogColor().get(ba.getG().getLog().size()-1-i),255-(i*(255/6))));
		    			buffer.drawString(ba.getG().getLog().get(ba.getG().getLog().size()-1-i),10,this.getHeight()-25*i-10);
		    		}
		    	}else{
			    		buffer.setColor(new Color(0,0,0,128));
		    			buffer.fillRect(5,this.getHeight()-5,355,-130);
		    		for(int i = 0; i < 5; i++){
		    			buffer.setColor(Util.changeAlpha(ba.getG().getLogColor().get(ba.getG().getLog().size()-1-i),255-(i*(255/6))));
		    			buffer.drawString(ba.getG().getLog().get(ba.getG().getLog().size()-1-i),10,this.getHeight()-25*i-10);
		    		}
		    	}
	    	}
	    	
	    	
    		break;
	    }
		if(ba.getOpt().fps){
			buffer.setFont(f2);
			buffer.setColor(Color.WHITE);
			buffer.drawString(""+fps2,2,f2.getSize()/2);
	    }
	    g.drawImage(image, 0, 0, this);
	    fps++;
	    ba.repaintPanels();
	    
	}
	
	public void drawDetail(int t,int ap, int nb, int x1, int y1,double x2,double y2,int h,  Graphics2D g2){
		int x = Util.arrondi((((1-x2)/2)+(y2/2))*zoom*8);
		int y = Util.arrondi(((x2/2)+(y2/2))*zoom*4);
		if(!(x1+x+Util.arrondi((double)zoom/1.1D) > this.getWidth() || Util.arrondi((double)zoom*2.2D)+y1+10-(h*zoom/10)+y+Util.arrondi((double)zoom/1.1D) > this.getHeight() || x1+x < 0 || Util.arrondi((double)zoom*2.2)+y1+10-(h*zoom/10)+y < 0))
		g2.drawImage(details[ba.getG().getSaison()][t],x1+x,Util.arrondi((double)zoom*2.2)+y1+10-(h*zoom/10)+y,x1+x+Util.arrondi((double)zoom/1.1D),Util.arrondi((double)zoom*2.2D)+y1+10-(h*zoom/10)+y+Util.arrondi((double)zoom/1.1D),ap*(details[ba.getG().getSaison()][t].getWidth()/nb),0,(ap+1)*(details[ba.getG().getSaison()][t].getWidth()/nb),details[ba.getG().getSaison()][t].getHeight(),this);
	}
	
	public void drawDetail2(int t,int ap, int nb, int x1, int y1,double x2,double y2,int h,  Graphics2D g2){
		int x = Util.arrondi((((1-x2)/2)+(y2/2))*zoom*8);
		int y = Util.arrondi(((x2/2)+(y2/2))*zoom*4);
		g2.drawImage(details[ba.getG().getSaison()][t],x1+x,Util.arrondi((double)zoom*2.2)+y1+10-(h*zoom/10)+y,x1+x+Util.arrondi((double)zoom/1.1D),Util.arrondi((double)zoom*2.2D)+y1+10-(h*zoom/10)+y+Util.arrondi((double)zoom/1.1D),ap*(details[ba.getG().getSaison()][t].getWidth()/nb),0,(ap+1)*(details[ba.getG().getSaison()][t].getWidth()/nb),details[ba.getG().getSaison()][t].getHeight(),this);
	}
	
	public void drawDetail(int t,int ap, int nb, int x1, int y1,double x2,double y2,int h,  Graphics2D g2, boolean r,boolean g){
		BufferedImage[] i = null;
		
		if(r) i = detailsRouge[ba.getG().getSaison()];
		else if(g) i = detailsVert[ba.getG().getSaison()];
		else i = details[ba.getG().getSaison()];
		
		int x = Util.arrondi((((1-x2)/2)+(y2/2))*zoom*8);
		int y = Util.arrondi(((x2/2)+(y2/2))*zoom*4);
		if(!(x1+x+Util.arrondi((double)zoom/1.1D) > this.getWidth() || Util.arrondi((double)zoom*2.2D)+y1+10-(h*zoom/10)+y+Util.arrondi((double)zoom/1.1D) > this.getHeight() || x1+x < 0 || Util.arrondi((double)zoom*2.2)+y1+10-(h*zoom/10)+y < 0))
		g2.drawImage(i[t],x1+x,Util.arrondi((double)zoom*2.2)+y1+10-(h*zoom/10)+y,x1+x+Util.arrondi((double)zoom/1.1D),Util.arrondi((double)zoom*2.2D)+y1+10-(h*zoom/10)+y+Util.arrondi((double)zoom/1.1D),ap*(details[ba.getG().getSaison()][t].getWidth()/nb),0,(ap+1)*(details[ba.getG().getSaison()][t].getWidth()/nb),details[ba.getG().getSaison()][t].getHeight(),this);
	}
	
	
	public void drawInfoValue(InfoValue iv, Graphics2D g2){
		g2.setFont(f);
		g2.setColor(iv.getColor());
		String v = iv.getValue();
		int w = (int) g2.getFontMetrics().getStringBounds(v, g2).getWidth();
		int h = (int) g2.getFontMetrics().getStringBounds(v, g2).getHeight();
		if(!(baseX(iv.getCoords()[0],iv.getCoords()[1])+((zoom*8-w)/2) > this.getWidth() || baseY(iv.getCoords()[0],iv.getCoords()[1])+iv.getI()+zoom*2 > this.getHeight() || baseX(iv.getCoords()[0],iv.getCoords()[1])+((zoom*8-w)/2)+w > 0 || baseY(iv.getCoords()[0],iv.getCoords()[1])+iv.getI()+zoom*2+h > 0))
		g2.drawString(v,baseX(iv.getCoords()[0],iv.getCoords()[1])+((zoom*8-w)/2),baseY(iv.getCoords()[0],iv.getCoords()[1])+iv.getI()+zoom*2);
	}
	
	public void drawPourcent(int p, int x, int y,Graphics2D g2, int h){
		Image i = pourcent;
		int tX = (int)(zoom*4.2);
		int tY = (int)(zoom*0.8);
		int xi = x+(int)(zoom*1.9);
		int yi = (int) (y+(zoom*3)+((zoom*4-tY)/2));
		if(!(xi+tX > this.getWidth() || yi+10+tY-(h*zoom/10) > this.getHeight() || xi < 0 || yi+10-(h*zoom/10) < 0))
		g2.drawImage(i,xi,yi+10-(h*zoom/10),xi+tX,yi+10+tY-(h*zoom/10),0,0,i.getWidth(this),i.getHeight(this)/2,this);
		g2.drawImage(i,xi,yi+10-(h*zoom/10),xi+(p*tX/100),yi+10+tY-(h*zoom/10),0,i.getHeight(this)/2,p*i.getWidth(this)/100,i.getHeight(this),this);
		
	}
	
	public void drawAlerte(int a, int x, int y, Graphics2D g2, int h){
		Image i = alertes;
		//TODO ?
		int tX = (int)(zoom*1.6);
		int tY = (int)(zoom*1.6);
		int xi = x+(int)(zoom*3.2);
		int yi = (int) (y+(zoom*3)+((zoom*3-tY)/2));
		if(!(xi+tX > this.getWidth() || yi+10+tY-(h*zoom/10) > this.getHeight() || xi < 0 || yi+10-(h*zoom/10) < 0))
		g2.drawImage(i,xi,yi+10-(h*zoom/10),xi+tX,yi+10+tY-(h*zoom/10),i.getWidth(this)/nbalertes*a,0,i.getWidth(this)/nbalertes*(a+1),i.getHeight(this),this);
	}
	
	public void drawTerrain(int t,int img, int anim, int x, int y,int h, int state, int maxstate,Graphics2D g2){
			BufferedImage[] i = null;
			switch(img){
				case 0:
					i = terrain[ba.getG().getSaison()];
					break;
				case 1:
					i = construction[ba.getG().getSaison()];					
			}
			int he = i[t].getHeight()/maxstate;
			if(!(x > this.getWidth() || y+10 > this.getHeight() || x+zoom*8 < 0 || y+zoom*8 < 0))
			g2.drawImage(i[t],x,y+10-(h*zoom/10),x+zoom*8,y+zoom*8+10-(h*zoom/10),anim*he,state*he,anim*he+he,state*he+he,this);
	}
	

	public void drawTerrain2(int t,int img, int anim, int x, int y,int h, int state, int maxstate,Graphics2D g2){
		BufferedImage[] i = null;
		switch(img){
			case 0:
				i = terrain[ba.getG().getSaison()];
				break;
			case 1:
				i = construction[ba.getG().getSaison()];					
		}
		int he = i[t].getHeight()/maxstate;
		g2.drawImage(i[t],x,y+10-(h*zoom/10),x+zoom*8,y+zoom*8+10-(h*zoom/10),anim*he,state*he,anim*he+he,state*he+he,this);

	}
	
	public void drawTerrain(int te,int img, int anim,  int x, int y,int h, int state, int maxstate,Graphics2D g2, boolean r,boolean g, boolean t){

		BufferedImage[] i = null;
		switch(img){
			case 0:
				if(r) i = terrainRouge[ba.getG().getSaison()];
				else if(g) i = terrainVert[ba.getG().getSaison()];
				else i = terrain[ba.getG().getSaison()];
				break;
			case 1:
				if(r) i = constructionRouge[ba.getG().getSaison()];
				else if(g) i = constructionVert[ba.getG().getSaison()];
				else if(t) i = constructionGris[ba.getG().getSaison()];
				else i = construction[ba.getG().getSaison()];				
		}
		int he = i[te].getHeight()/maxstate;
		if(!(x > this.getWidth() || y+10 > this.getHeight() || x+zoom*8 < 0 || y+zoom*8 < 0))
		g2.drawImage(i[te],x,y+10-(h*zoom/10),x+zoom*8,y+zoom*8+10-(h*zoom/10),anim*he,state*he,anim*he+he,state*he+he,this);
	}
	
	
	private BufferedImage change(BufferedImage imga,boolean rouge, boolean vert, boolean trans){
		if(imga == null)return null;
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
	public BufferedImage printImage(){
		BufferedImage img = new BufferedImage(160*ba.getG().getTaille(),80*ba.getG().getTaille()+80,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)img.getGraphics();
		
		int sz = zoom;
		zoom = 20;
		int scx = camX;
		camX = 80*ba.getG().getTaille();
		int scy = camY;
		camY = 40*ba.getG().getTaille()+40;
		
    	Terrain map0[][] = ba.getG().getM().getMap0();
    	Construction map1[][] = ba.getG().getM().getMap1();

	    for(int i2 = ba.getG().getTaille()-1; i2 >= 0; i2--){for(int i = 0; i < ba.getG().getTaille(); i++){	
	  		drawTerrain2(map0[i][i2].getTexture(),0,map0[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),0,0,1,g2);
	  		if(map0[i][i2].getDetails() != null){
	  			for(Detail d: map0[i][i2].getDetails()){
	  				if(d.getCoord()[0]+d.getCoord()[1] < 1){
	  					drawDetail2(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],0,g2);
	  				}
	  			}
	  		}
	  		if(map1[i][i2] != null){
	  			drawTerrain2(map1[i][i2].getTexture(),1,map1[i][i2].getAnim(),baseX(i,i2),baseY(i,i2),0,map1[i][i2].getState(),map1[i][i2].getMaxstate(),g2);
	  		}else if(map0[i][i2].hasDecor())drawTerrain2(map0[i][i2].getDTexture(),1,map0[i][i2].getDAnim(),baseX(i,i2),baseY(i,i2),0,0,1,g2);
	  		if(map0[i][i2].getDetails() != null){
	  			for(Detail d: map0[i][i2].getDetails()){
	  				if(d.getCoord()[0]+d.getCoord()[1] > 1){
	  					drawDetail2(d.getT(), d.getAp(), d.getNb(),baseX(i,i2),baseY(i,i2),d.getCoord()[0],d.getCoord()[1],0,g2);
	  				}
	  			}
	  		}
	    }}
    	zoom = sz;
    	camX = scx;
    	camY = scy;
    		
		return img;
	}
	
	public int getFps(){
		int f = fps;
		fps = 0;
		return f;
	}
	
	public int getZoom(){
		return zoom;
	}
	
	public int getZOOMMAX() {
		return ZOOMMAX;
	}

	public int[] getPos(){
		int pos[] = {-1,-1};
		for(int i = 0; i < ba.getG().getTaille(); i++){for(int i2 = 0; i2 < ba.getG().getTaille(); i2++){	
			int X = zoom*8;
			int Y = zoom*4;
			int oX = baseX(i,i2);
			int oY = baseY(i,i2)+zoom*4;
			int x = ba.getMouseX()-oX;
			int y = ba.getMouseY()-oY;
    		if(Y+(Y*2)/X*x >= y*2 && -Y+(Y*2)/X*x <= y*2 && Y-(Y*2)/X*x <= y*2 && 3*Y-(Y*2)/X*x >= y*2){
    			pos[0] = i;
    			pos[1] = i2;
    		}
    	}}
		return pos;
	}
	
	
	public int baseX(int i,int i2){
		return camX+(i*zoom*4)+(i2*zoom*4)-ba.getG().getTaille()*zoom*4;
	}
	public int baseY(int i,int i2){
		return camY-zoom*6+i*zoom*2-i2*zoom*2;
	}
	
	
	public void setZoom(int z){
		zoom = z;
		zoom = zoom<ZOOMMIN?ZOOMMIN:zoom>ZOOMMAX?ZOOMMAX:zoom;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==fpst){
			fps2 = fps;
			fps = 0;
		}
	}
}
