package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Info2Panel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Base ba;
	private Font f;
	private int[] date;
	private BufferedImage fond;
	private BufferedImage ressources;
	private int nbressources;
	private int[] ress;
	private Button bm; //menu
	private JPanel bmp = new JPanel();
	private Button bc; // competences
	private JPanel bcp = new JPanel();
	private Button bpo; //population
	private JPanel bpop = new JPanel();
	private Button bp; //pause
	private JPanel bpp = new JPanel();
	private Button ba2; //avance
	private JPanel bap = new JPanel();
	private Button bar; //avance rapide
	private JPanel barp = new JPanel();
	private Button bsar; //super avance rapide
	private JPanel bsarp = new JPanel();
	private JPanel ni2 = new JPanel();
	
	public Button getBm() {
		return bm;
	}

	public Button getBc() {
		return bc;
	}
	
	public Button getBpo() {
		return bpo;
	}

	public Button getBp() {
		return bp;
	}

	public Button getBa() {
		return ba2;
	}

	public Button getBar() {
		return bar;
	}
	
	public Button getBsar() {
		return bsar;
	}

	public Info2Panel(Base ba){
		this.ba = ba;
		this.f = ba.getF().deriveFont(Font.PLAIN,40);
		this.fond = ba.getTl().getConstantImage(3);
		this.ressources = ba.getTl().getConstantImage(6);
		nbressources = ba.getTl().getConstantsImagesTaille(6);
		this.setLayout(new BorderLayout());
		
		bm = new Button(0,ba);
		bm.addActionListener(this);
		bmp.setLayout(new BorderLayout());
		bmp.add(bm,BorderLayout.CENTER);
		this.add(bmp,null);
		bmp.setBounds(ba.getOpt().size[0]-33,3,30,30);
		
		bc = new Button(5,ba);
		bc.addActionListener(this);
		bcp.setLayout(new BorderLayout());
		bcp.add(bc,BorderLayout.CENTER);
		this.add(bcp,null);
		bcp.setBounds(ba.getOpt().size[0]-66,3,30,30);
		
		bpo = new Button(6,ba);
		bpo.addActionListener(this);
		bpop.setLayout(new BorderLayout());
		bpop.add(bpo,BorderLayout.CENTER);
		this.add(bpop,null);
		bpop.setBounds(ba.getOpt().size[0]-99,3,30,30);
		
		int x = 142;
		
		bp = new Button(1,ba);
		bp.addActionListener(this);
		bpp.setLayout(new BorderLayout());
		bpp.add(bp,BorderLayout.CENTER);
		this.add(bpp,null);
		bpp.setBounds(x,3,30,30);
		
		ba2 = new Button(2,ba);
		ba2.addActionListener(this);
		bap.setLayout(new BorderLayout());
		bap.add(ba2,BorderLayout.CENTER);
		this.add(bap,null);
		bap.setBounds(x+34,3,30,30);
		
		bar = new Button(3,ba);
		bar.addActionListener(this);
		barp.setLayout(new BorderLayout());
		barp.add(bar,BorderLayout.CENTER);
		this.add(barp,null);
		barp.setBounds(x+34*2,3,30,30);
		
		bsar = new Button(4,ba);
		bsar.addActionListener(this);
		bsarp.setLayout(new BorderLayout());
		bsarp.add(bsar,BorderLayout.CENTER);
		this.add(bsarp,null);
		bsarp.setBounds(x+34*3,3,30,30);
		
		this.add(ni2,null);
		ni2.setVisible(false);
		
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		ress = ba.getG().getRessources();
		Graphics2D g2 = (Graphics2D)g;
		//fond
		g2.drawImage(fond,0,0,this.getWidth(),this.getHeight(),0,0,fond.getWidth(),fond.getHeight()/37*7,this);
		//date
		g2.setColor(Color.BLACK);
		g2.setFont(f);
		date = ba.getG().getDate();
		String s = new String((date[2]<10?"0":"")+date[2]+" "+ba.getlang().getDate().get(date[1]-1)+" "+(date[0]<100?"0":"")+(date[0]<10?"0":"")+date[0]);
		//String s = new String( ga.getlang().getInfo()[3]+(date[0]<100?"0":"")+(date[0]<10?"0":"")+date[0]+" "+ga.getlang().getInfo()[4]+(date[1]<10?"0":"")+date[1]+" "+ga.getlang().getInfo()[5]+date[2]+" ");
		//int w = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
		int h = (int) g2.getFontMetrics().getStringBounds(s, g2).getHeight()-6;
		g2.drawString(s,5,(this.getHeight()+h)/2);
		//g2.drawRect(w,(this.getHeight()-h)/2,5,h);
		//g2.fillRect(w,((this.getHeight()+h)/2),5,-(date[3]*h/6));
		
		int dep = (this.getWidth()-((ress.length*78)-3))/2;
		h = (int) g2.getFontMetrics().getStringBounds("0", g2).getHeight()-6;
		
		for(int i = 0; i < ress.length; i++){
			drawico(i,dep+(i*78),3,30,g2);	
			g2.drawString(""+ress[i]+"",dep+(i*78)+33,(this.getHeight()+h)/2);
		}
	}
	
	public void drawico(int i,int x, int y, int t, Graphics2D g2){
		g2.drawImage(ressources,x,y,x+t,y+t,(ressources.getWidth()/nbressources)*(i),0,(ressources.getWidth()/nbressources)*(i+1),ressources.getHeight(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ba.actionPerformed(e);
	}

}
