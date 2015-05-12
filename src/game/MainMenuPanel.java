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

	private Base ga;
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
	
	public MainMenuPanel(Base g){
		this.ga = g;
		
		back = g.getTl().getMenuImages()[Util.randomInt(0,g.getTl().getMenuImages().length-1)];
		
		dec = new int[]{Util.randomInt(-1,1),Util.randomInt(-1,1)};
		pos = new int[]{Util.randomInt(100,back.getWidth()-g.getOpt().size[0]-100),
				Util.randomInt(100,back.getHeight()-g.getOpt().size[1]-100)};
		lim = new int[]{dec[0]==-1?0:dec[0]==1?back.getWidth()-g.getOpt().size[0]:pos[0],
				dec[1]==-1?0:dec[1]==1?back.getHeight()-g.getOpt().size[1]:pos[1]};
		
		title = g.getTl().getConstantImage(8);
		
		but = new Panel(g);
		
		nouveau = new Button(g.getlang().getBouton().get(0),ga);
		nouveau.setPreferredSize(b);
		nouveau.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ga.openNewGamePanel();}});
		but.add(nouveau);
		
		continuer = new Button(g.getlang().getBouton().get(1),ga);
		continuer.setPreferredSize(b);
		continuer.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ga.continueGame();}});
		//continuer.enable(g.hasGame());
		but.add(continuer);
		
		options = new Button(g.getlang().getBouton().get(2),ga);
		options.setPreferredSize(b);
		options.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ga.openOptions();}});
		but.add(options);
		
		quitter = new Button(g.getlang().getBouton().get(3),ga);
		quitter.setPreferredSize(b);
		quitter.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {ga.close();}});
		but.add(quitter);
		
		but.setBounds((ga.getSize().width-180)/2,(ga.getSize().height-150)*2/3,180,150);
		this.setLayout(new BorderLayout());
		this.add(but,null);
		
		version = new JLabel(g.getVersion());	
		version.setFont(g.getF().deriveFont(Font.PLAIN,30));
		version.setBounds(5,g.getOpt().TAILLES[g.getOpt().sizeSel][1]-20,100,20);
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
		g2.drawImage(title,((ga.getSize().width-356)/2)+8,((ga.getSize().height-144)/5)+5,this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		pos[0]+=dec[0];
		pos[1]+=dec[1];
		if(pos[0] == lim[0] && pos[1] == lim[1]){
			back = ga.getTl().getMenuImages()[Util.randomInt(0,ga.getTl().getMenuImages().length-1)];
			dec = new int[]{Util.randomInt(-1,1),Util.randomInt(-1,1)};
			pos = new int[]{Util.randomInt(0,back.getWidth()-ga.getOpt().size[0]),
					Util.randomInt(0,back.getHeight()-ga.getOpt().size[1])};
			lim = new int[]{dec[0]==-1?0:dec[0]==1?back.getWidth()-ga.getOpt().size[0]:pos[0],
					dec[1]==-1?0:dec[1]==1?back.getHeight()-ga.getOpt().size[1]:pos[1]};

		}
		this.repaint();
		
	}
	
}
