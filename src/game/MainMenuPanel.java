package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainMenuPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Base ba;
	private Panel but;
	private Button nouveau;
	private Button continuer;
	private Button options;
	private Button quitter;
	private JLabel version;
	private JPanel n = new JPanel();
	private Dimension b = new Dimension(160,30);
	private BufferedImage back;
	private BufferedImage title;
	private int[] dec = {0,0};
	private int[] pos = {0,0};
	private int[] lim = {0,0};
	private Timer t = new Timer(50,this);
	
	public MainMenuPanel(Base ba2){
		this.ba = ba2;
		
		back = ba2.getTl().getMenuImages()[Util.randomInt(0,ba2.getTl().getMenuImages().length-1)];
		
		dec = new int[]{Util.randomInt(-1,1),Util.randomInt(-1,1)};
		pos = new int[]{Util.randomInt(100,back.getWidth()-ba2.getOpt().size[0]-100),
				Util.randomInt(100,back.getHeight()-ba2.getOpt().size[1]-100)};
		lim = new int[]{dec[0]==-1?0:dec[0]==1?back.getWidth()-ba2.getOpt().size[0]:pos[0],
				dec[1]==-1?0:dec[1]==1?back.getHeight()-ba2.getOpt().size[1]:pos[1]};
		
		title = ba2.getTl().getConstantImage(8);
		
		but = new Panel(ba2);
		
		nouveau = new Button(ba2.getlang().getBouton().get(0),ba);
		nouveau.setPreferredSize(b);
		nouveau.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ba.openNewGamePanel();}});
		but.add(nouveau);
		
		continuer = new Button(ba2.getlang().getBouton().get(1),ba);
		continuer.setPreferredSize(b);
		continuer.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ba.continueGame();}});
		//continuer.enable(g.hasGame());
		but.add(continuer);
		
		options = new Button(ba2.getlang().getBouton().get(2),ba);
		options.setPreferredSize(b);
		options.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ba.openOptions();}});
		but.add(options);
		
		quitter = new Button(ba2.getlang().getBouton().get(3),ba);
		quitter.setPreferredSize(b);
		quitter.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ba.close();}});
		but.add(quitter);
		
		but.setBounds((ba.getSize().width-180)/2,(ba.getSize().height-150)*2/3,180,150);
		this.setLayout(new BorderLayout());
		this.add(but,null);
		
		version = new JLabel(ba2.getVersion());	
		version.setFont(ba2.getF().deriveFont(Font.PLAIN,30));
		version.setBounds(5,ba2.getOpt().TAILLES[ba2.getOpt().sizeSel][1]-20,100,20);
		version.setForeground(Color.BLACK);
		this.add(version,null);
		
		n.setVisible(false);
		this.add(n);
		
		t.start();
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
		g2.drawImage(back,-pos[0],-pos[1],this);
		g2.drawImage(title,((ba.getSize().width-356)/2)+8,((ba.getSize().height-144)/5)+5,this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		pos[0]+=dec[0];
		pos[1]+=dec[1];
		if(pos[0] == lim[0] && pos[1] == lim[1]){
			back = ba.getTl().getMenuImages()[Util.randomInt(0,ba.getTl().getMenuImages().length-1)];
			dec = new int[]{Util.randomInt(-1,1),Util.randomInt(-1,1)};
			pos = new int[]{Util.randomInt(0,back.getWidth()-ba.getOpt().size[0]),
					Util.randomInt(0,back.getHeight()-ba.getOpt().size[1])};
			lim = new int[]{dec[0]==-1?0:dec[0]==1?back.getWidth()-ba.getOpt().size[0]:pos[0],
					dec[1]==-1?0:dec[1]==1?back.getHeight()-ba.getOpt().size[1]:pos[1]};

		}
		this.repaint();
		
	}
	
}
