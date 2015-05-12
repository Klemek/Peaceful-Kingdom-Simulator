package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Minimap extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Map m;
	private Base b;
	private int mapType = 0;
	private int NBMAPTYPE = 2;
	private boolean map;
	private boolean selection = true;
	
	public Minimap(Base b){
		this.b = b;
		map = true;
		this.addMouseListener(this);
	}
	
	public Minimap(Base b, Map m){
		this.m = m;
		this.b = b;
		map = false;
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		if(map)m = b.getG().getM();
		g2.fillRect(0,0,this.getWidth()-1,this.getHeight());
		float t = (float) ((this.getHeight()*60/126)/m.getTaille());
		for(int i2 = b.getG().getTaille()-1; i2 >= 0; i2--){for(int i = 0; i < b.getG().getTaille(); i++){
			int x1 = (int)((this.getHeight()*3/63)+i*t*2+i2*t*2);
			int y1 = (int)((this.getHeight()/2)+i*t-i2*t);
			switch(mapType){
				case 0:
					g2.setColor(m.getMap0(i, i2).getPColor());
					break;
				case 1:
					if(m.getMap1(i, i2)==null){
						g2.setColor(Color.GRAY);
					}else{
						if(m.getMap1(i, i2).hasAlerte()){
							g2.setColor(Color.RED);
						}else{
							g2.setColor(Color.GREEN);
						}
					}
					break;
			}
			g2.fillPolygon(new int[]{x1,(int) (x1+t*2),(int) (x1+4*t),(int) (x1+2*t)}, new int[]{y1,(int) (y1+t),y1,(int) (y1-t)},4);
		}}
		if(selection){
			g2.setColor(Color.RED);
			int x = (int) (t*4.3*(b.getGp().getWidth()/(160*b.getGp().getZoom()/20)));
			int y = (int) (t*4.3*(b.getGp().getHeight()/(160*b.getGp().getZoom()/20)));
			int[] xy = {(int) (this.getWidth()/2-x/2-b.getG().getCamX()*t*4.3/(160*b.getGp().getZoom()/20)),
					(int) (this.getHeight()/2-y/2-b.getG().getCamY()*t*4.3/(160*b.getGp().getZoom()/20)),
					(int) (this.getWidth()/2-x/2-b.getG().getCamX()*t*4.3/(160*b.getGp().getZoom()/20)+x),
					(int) (this.getHeight()/2-y/2-b.getG().getCamY()*t*4.3/(160*b.getGp().getZoom()/20)+y)};
			xy[0]=xy[0]<0?0:xy[0]>this.getWidth()?this.getWidth():xy[0];
			xy[1]=xy[1]<0?0:xy[1]>this.getHeight()?this.getHeight():xy[1];
			xy[2]=xy[2]<0?0:xy[2]>this.getWidth()?this.getWidth():xy[2];
			xy[3]=xy[3]<0?0:xy[3]>this.getHeight()?this.getHeight():xy[3];
			g2.drawLine(xy[0],xy[1],xy[2],xy[1]);
			g2.drawLine(xy[2],xy[1],xy[2],xy[3]);
			g2.drawLine(xy[2],xy[3],xy[0],xy[3]);
			g2.drawLine(xy[0],xy[3],xy[0],xy[1]);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(selection){
			if(e.getButton() == MouseEvent.BUTTON1){
				int t = 60/b.getG().getTaille();
				int x = -(e.getX()-(this.getWidth()-141));
				int y = -(e.getY()-75);
				b.getG().setCamX((int) (x*(160*b.getGp().getZoom()/20)/(t*4.3)));
				b.getG().setCamY((int) (y*(160*b.getGp().getZoom()/20)/(t*4.3)));
			}
			if(e.getButton() == MouseEvent.BUTTON3){
				mapType++;
				if(mapType == NBMAPTYPE)mapType=0;
			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
	
}
