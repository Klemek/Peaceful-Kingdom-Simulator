package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage fond;
	
	public Panel(Base ba){
		fond = ba.getTl().getConstantImage(4);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		int i = fond.getHeight()/3;
		g2.drawImage(fond,0,0,i,i,0,0,i,i,this);
		g2.drawImage(fond,i,0,this.getWidth()-i,i,i,0,fond.getWidth()-i,i,this);
		g2.drawImage(fond,this.getWidth()-i,0,this.getWidth(),i,fond.getWidth()-i,0,fond.getWidth(),i,this);
		
		g2.drawImage(fond,0,i,i,this.getHeight()-i,0,i,i,fond.getHeight()-i,this);
		g2.drawImage(fond,i,i,this.getWidth()-i,this.getHeight()-i,i,i,fond.getWidth()-i,fond.getHeight()-i,this);
		g2.drawImage(fond,this.getWidth()-i,i,this.getWidth(),this.getHeight()-i,fond.getWidth()-i,i,fond.getWidth(),fond.getHeight()-i,this);
		
		g2.drawImage(fond,0,this.getHeight()-i,i,this.getHeight(),0,fond.getHeight()-i,i,fond.getHeight(),this);
		g2.drawImage(fond,i,this.getHeight()-i,this.getWidth()-i,this.getHeight(),i,fond.getHeight()-i,fond.getWidth()-i,fond.getHeight(),this);
		g2.drawImage(fond,this.getWidth()-i,this.getHeight()-i,this.getWidth(),this.getHeight(),fond.getWidth()-i,fond.getHeight()-i,fond.getWidth(),fond.getHeight(),this);
		
	}
	
}
