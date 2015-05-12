package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Button extends JButton implements MouseListener{

	private static final long serialVersionUID = 6212904633336000528L;
	
	private String title;
	private Boolean e = true;
	private int state = 0;
	private Font f;
	private Image fond;
	private Image icons;
	private int nbicones;
	private boolean petit = false;
	private int ico;
	public Button(String t, Base ba){
		super(t);
		title = t;
		this.setBorderPainted(false);
		this.addMouseListener(this);
		this.f = ba.getF().deriveFont(Font.PLAIN,40);
		fond = ba.getTl().getConstantImage(1);
		state = 0;
	}
	
	public Button(int ico, Base ba){
		super(" ");
		this.ico = ico;
		petit = true;
		this.setBorderPainted(false);
		this.addMouseListener(this);
		this.f = ba.getF().deriveFont(Font.PLAIN,40);
		icons = ba.getTl().getConstantImage(2);
		nbicones = ba.getTl().getConstantsImagesTaille(2);
		fond = ba.getTl().getConstantImage(1);
	}
	
	public void paintComponent(Graphics g){
		this.setEnabled(e);
	    Graphics2D g2 = (Graphics2D)g;
	    if(petit){
	    	if(e){
	 		    switch(state){
	 		    case 0:
	 		    	g2.drawImage(fond,0,0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*3,0,fond.getWidth(this),fond.getHeight(this)/4,this);
	 		    	break;
	 		    case 1:
	 		    	g2.drawImage(fond,0,0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*3,fond.getHeight(this)/4,fond.getWidth(this),fond.getHeight(this)/4*2,this);
	 		    	break;
	 		    case 2:
	 		    	g2.drawImage(fond,0,0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*3,fond.getHeight(this)/4*2,fond.getWidth(this),fond.getHeight(this)/4*3,this);
	 		    	break;
	 		    }
	 		   if(state != 2){
		    		g2.drawImage(icons,0,0,this.getWidth(),this.getHeight(),icons.getWidth(this)/nbicones*ico,0,icons.getWidth(this)/nbicones*(ico+1),icons.getHeight(this),this);
		    	}else{
		    		g2.drawImage(icons,0+1,0+1,this.getWidth()+1,this.getHeight()+1,icons.getWidth(this)/nbicones*ico,0,icons.getWidth(this)/nbicones*(ico+1),icons.getHeight(this),this);

		    	}
	 	    }else{
	 	    	g2.drawImage(fond,0,0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*3,fond.getHeight(this)/4*3,fond.getWidth(this),fond.getHeight(this),this);
	    		g2.drawImage(icons,0,0,this.getWidth(),this.getHeight(),icons.getWidth(this)/nbicones*ico,0,icons.getWidth(this)/nbicones*(ico+1),icons.getHeight(this),this);
 	   
	 	    }
	    	
	    }else{
	    		g2.setFont(f);
	    		FontMetrics fm = g2.getFontMetrics();
	    		int height = fm.getHeight();
	    		int width = fm.stringWidth(title);
	    		if(e){
	 		    switch(state){
	 		    case 0:
	 		    	g2.drawImage(fond,0,0,this.getHeight(),this.getHeight(),0,0,fond.getWidth(this)/4,fond.getHeight(this)/4,this);
	 			    g2.drawImage(fond,this.getHeight(),0,this.getWidth()-this.getHeight(),this.getHeight(),fond.getWidth(this)/4,0,fond.getWidth(this)/4*2,fond.getHeight(this)/4,this);
	 			    g2.drawImage(fond,this.getWidth()-this.getHeight(),0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*2,0,fond.getWidth(this)/4*3,fond.getHeight(this)/4,this);
	 		    	break;
	 		    case 1:
	 		    	g2.drawImage(fond,0,0,this.getHeight(),this.getHeight(),0,fond.getHeight(this)/4,fond.getWidth(this)/4,fond.getHeight(this)/4*2,this);
	 			    g2.drawImage(fond,this.getHeight(),0,this.getWidth()-this.getHeight(),this.getHeight(),fond.getWidth(this)/4,fond.getHeight(this)/4,fond.getWidth(this)/4*2,fond.getHeight(this)/4*2,this);
	 			    g2.drawImage(fond,this.getWidth()-this.getHeight(),0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*2,fond.getHeight(this)/4,fond.getWidth(this)/4*3,fond.getHeight(this)/4*2,this);
	 		    	break;
	 		    case 2:
	 		    	g2.drawImage(fond,0,0,this.getHeight(),this.getHeight(),0,fond.getHeight(this)/4*2,fond.getWidth(this)/4,fond.getHeight(this)/4*3,this);
	 			    g2.drawImage(fond,this.getHeight(),0,this.getWidth()-this.getHeight(),this.getHeight(),fond.getWidth(this)/4,fond.getHeight(this)/4*2,fond.getWidth(this)/4*2,fond.getHeight(this)/4*3,this);
	 			    g2.drawImage(fond,this.getWidth()-this.getHeight(),0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*2,fond.getHeight(this)/4*2,fond.getWidth(this)/4*3,fond.getHeight(this)/4*3,this);
	 		    	break;
	 		    	
	 		    }
	 		   if(state != 2){
		 	    	g2.setColor(Color.GRAY);
		 	    	g2.drawString(title, this.getWidth() / 2 - (width / 2)+1, (this.getHeight() / 2) + (height / 4)+4);
		 	    	g2.setColor(Color.black);
			 	    g2.drawString(title, this.getWidth() / 2 - (width / 2), (this.getHeight() / 2) + (height / 4)+3); 
		 	    }else{
		 	    	g2.setColor(Color.black);
			 	    g2.drawString(title, this.getWidth() / 2 - (width / 2)+1, (this.getHeight() / 2) + (height / 4)+4); 
		 	    }
	 	    }else{
	 	    	g2.drawImage(fond,0,0,this.getHeight(),this.getHeight(),0,fond.getHeight(this)/4*3,fond.getWidth(this)/4,fond.getHeight(this),this);
	 		    g2.drawImage(fond,this.getHeight(),0,this.getWidth()-this.getHeight(),this.getHeight(),fond.getWidth(this)/4,fond.getHeight(this)/4*3,fond.getWidth(this)/4*2,fond.getHeight(this),this);
	 		    g2.drawImage(fond,this.getWidth()-this.getHeight(),0,this.getWidth(),this.getHeight(),fond.getWidth(this)/4*2,fond.getHeight(this)/4*3,fond.getWidth(this)/4*3,fond.getHeight(this),this);
		 	    g2.setColor(Color.GRAY);
		 	    g2.drawString(title, this.getWidth() / 2 - (width / 2)+1, (this.getHeight() / 2) + (height / 4)+4);
		 	    g2.setColor(Color.black);
			 	g2.drawString(title, this.getWidth() / 2 - (width / 2), (this.getHeight() / 2) + (height / 4)+3); 
	 	    }
	    	
	 	    
	 	    
	 	   
	 	    
	    }
	   
	       

	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		state = 1;
	}

	public void mouseExited(MouseEvent e) {
		state = 0;
	}

	public void mousePressed(MouseEvent e) {
		state = 2;
	}

	public void mouseReleased(MouseEvent e) {
		if((e.getY() > 0 && e.getY() < this.getHeight()) && (e.getX() > 0 && e.getX() < this.getWidth())){
			state = 1;
		    }
		    else{
		    	state = 0;
		    }   
	}
	
	public void setTitle(String s){
		this.title = s;
		this.setText(s);
	}
	
	public void setColor(Color c1){ 
	}

	public Color changeBrightness(Color arg1,int arg){
		return new Color((arg1.getRed()+arg)<0?0:(arg1.getRed()+arg)>250?250:arg1.getRed()+arg,
						(arg1.getGreen()+arg)<0?0:(arg1.getGreen()+arg)>250?250:arg1.getGreen()+arg,
						(arg1.getBlue()+arg<0)?0:(arg1.getBlue()+arg)>250?250:arg1.getBlue()+arg);
	}

	public boolean isEnable(){
		return e;
	}
	
	public void enable(boolean b){
		this.e = b;
	}
	
	
}
