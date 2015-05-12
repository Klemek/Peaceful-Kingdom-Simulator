package game;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class Base extends JFrame implements ActionListener,MouseListener,MouseMotionListener, MouseWheelListener, WindowListener, WindowStateListener{

	private static final long serialVersionUID = 1L;

	private boolean INFO = true;
	
	private boolean DEV = true;
	private String version = "Alpha 1.3";
	
	private Options opt;
	
	private Font f;
	private ArrayList<ArrayList<String>> langs;
	private TextureLoader tl;
	private FileManager fm;
	
	private GraphicsDevice gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	private int state;
	
	private Timer tFps = new Timer(1000,this);
	private Timer t0 = new Timer(1,this); 
	
	private Language lang;
	
	private int mouseX = 0;
	private int mouseY = 0;
	private boolean click = false;
	private int fps = 0;

	
	private GamePanel gp;
	private InfoPanel ip;
	private Info2Panel i2p;
	private MenuPanel mp;
	private Info3Panel i3p;
	private MainMenuPanel mmp;
	private OptionsPanel op;
	private PopUpPanel pup;
	private ConstructionPanel cp;
	private NewGamePanel ngp;
	private CompetencesPanel cop;
	private PopulationPanel pp;
	
	private JPanel content = new JPanel();
	//InfoPanel

	private boolean wheelCheck = true;
	
	private boolean game = false;
	private Game g;
	
	private ActionMap am;
	private InputMap im;
	
	
	public Base(TextureLoader tl, FontLoader fl, LangLoader ll){

		this.setResizable(false);
		this.addWindowListener(this);
		this.addWindowStateListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Peaceful Kingdom Simulator");
		this.tl = tl;
		this.f = fl.getFont();	
		this.langs = ll.getLang();
		this.fm = new FileManager();
		if(fm.exist("options.opt")){
			if(INFO)System.out.println("[SYS]["+state+"]Chargement d'options");
			opt = fm.loadOptions();
		}else{
			if(INFO)System.out.println("[SYS]["+state+"]Chargement de nouvelles options");
			opt = new Options();
		}
		
		majLang();
		paintWindow();
		state = 0;
		
		setKeyBindings();
		
		openMainMenu();
		

	}
	
	public void majLang(){
		lang = new Language(this.langs.get(opt.langue));
	}

	public void paintWindow(){
		if(INFO)System.out.println("[SYS]["+state+"](Re)ouverture de la fenêtre");
		if(opt.fullscreen){
			if(gd.isFullScreenSupported()){
				for(int i = 0; i < Base.getWindows().length; i++){
					gd.setFullScreenWindow(Base.getWindows()[i]);
				}
			}
		}else{
			JFrame temp = new JFrame();
			temp.pack();
			Insets insets = temp.getInsets();
			temp=null;
			this.setSize(opt.size[0]+insets.right+insets.left,opt.size[1]+insets.bottom+insets.top);
			this.setLocationRelativeTo(null);
		}
		
		this.setVisible(true);
	}
	
	
	public void repaintPanels(){
		if(g.getSelected()[0] != -1 && g.getSelected()[1] != -1){
			ip.getB1().enable(g.hasSelected() && g.getM().getMap1()[g.getSelected()[0]][g.getSelected()[1]] != null);
		}
		ip.repaint();
	}
	
	public void openNewGamePanel(){
		state = 2;
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du menu nouveau jeu");				
		ngp = new NewGamePanel(this);
		content.removeAll(); 
		content.setLayout(new BorderLayout());
		content.add(ngp);
		this.getContentPane().add(content);
		this.setVisible(true);
		
	}
	
	public void openNewGame(int t){
		click = false;
		state = 1;
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture d'un nouveau jeu");
		gp = new GamePanel(this);
		ip = new InfoPanel(this);
		i2p = new Info2Panel(this);
		
		g = new Game(this,t);
		game = true;
		
		tFps.start();
		t0.start();
		openGame();
	}
	
	public void continueGame(){
		click = false;
		state = 1;
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture d'un jeu en cours");
		gp = new GamePanel(this);
		ip = new InfoPanel(this);
		i2p = new Info2Panel(this);
		if(fm.exist("save.pks")){
			g = fm.loadGame("save.pks");
		}else{
			if(INFO)System.out.println("[SYS]["+state+"]Aucun jeu en cours");
			if(INFO)System.out.println("[SYS]["+state+"]Ouverture d'un nouveau menu jeu");
			openNewGamePanel();
		}
		game = true;
		
		tFps.start();
		t0.start();
		openGame();
	}

	public void openMenu(){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du menu");
		if(game)g.getTick().stop();
		gp.removeAll();
		gp.setLayout(new BorderLayout());
		mp = new MenuPanel(this);
		gp.add(mp,null);
		JPanel gpn = new JPanel();
		gp.add(gpn,null);
		gpn.setVisible(false);
		gp.remove(gpn);
		fm.saveGame(g,"save.pks");
	}
	
	public void openPopulation(){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du menu population");
		gp.removeAll();
		gp.setLayout(new BorderLayout());
		pp = new PopulationPanel(this);
		gp.add(pp,null);
		g.getTick().stop();
		JPanel gpn = new JPanel();
		gp.add(gpn,null);
		gpn.setVisible(false);
		gp.remove(gpn);
	}
	
	public void popUp(String title, String[] texte, String sb1, String sb2,  ActionListener al1,   ActionListener al2){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture d'une pop-up");
		switch(state){
			case 1:
				if(game)g.getTick().stop();
				gp.removeAll();
				gp.setLayout(new BorderLayout());
				pup = new PopUpPanel(this,title,texte, sb1, sb2, al1, al2);
				gp.add(pup,null);
				g.getTick().stop();
				JPanel gpn = new JPanel();
				gp.add(gpn,null);
				gpn.setVisible(false);
				gp.remove(gpn);
				break;
			case 0:
				break;
		}
		
	}
	
	public void popUp(String title, String[] texte, String sb1,  ActionListener al1){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture d'une pop-up");
		switch(state){
			case 1:
				if(game)g.getTick().stop();
				gp.removeAll();
				gp.setLayout(new BorderLayout());
				pup = new PopUpPanel(this,title,texte, sb1, al1);
				gp.add(pup,null);
				JPanel gpn = new JPanel();
				gp.add(gpn,null);
				gpn.setVisible(false);
				gp.remove(gpn);
				break;
			case 0:
				//TODO
				break;
		}
		
	}
	
	public boolean GPisClean(){
		return i3p == null && pup == null && cp == null && mp == null;
	}
	
	public void cleanGP(){
		if(i3p != null){
			gp.remove(i3p);
			i3p = null;
		}
		if(pup != null){
			//pup.getParent().remove(pup);
			pup = null;
		}
		if(cp != null){
			//cp.getParent().remove(cp);
			cp = null;
		}
		if(mp != null){
			mp = null;
		}
	}
	
	public void newConstruction(){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du panneau de selection");
		gp.removeAll();
		gp.setLayout(new BorderLayout());
		cp = new ConstructionPanel(this);
		gp.add(cp,BorderLayout.CENTER);
		g.getTick().stop();
		JPanel gpn = new JPanel();
		gp.add(gpn,BorderLayout.CENTER);
		gpn.setVisible(false);
		gp.remove(gpn);
	}
	
	public void openInfo(String t,Component[] comps,int ty){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du panneau d'information");
		gp.removeAll();
		gp.setLayout(new BorderLayout());
		i3p = new Info3Panel(this,t,comps,ty);
		gp.add(i3p,null);
		JPanel gpn = new JPanel();
		gp.add(gpn,null);
		gpn.setVisible(false);
		gp.remove(gpn);
	}
	
	public void openMainMenu(){
		
		state = 0;
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du menu principal");
		if(game)g.getTick().stop();
		if(game)g.getTanim().stop();
		mmp = new MainMenuPanel(this);
		content.removeAll(); 
		content.setLayout(new BorderLayout());
		content.add(mmp);
		this.getContentPane().add(content);
		this.setVisible(true);
	}
	
	public void openOptions(){
		state = 2;
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du menu options");				
		op = new OptionsPanel(this);
		content.removeAll(); 
		content.setLayout(new BorderLayout());
		content.add(op);
		this.getContentPane().add(content);
		this.setVisible(true);
	}
	
	public void openCompetences(){
		if(INFO)System.out.println("[SYS]["+state+"]Ouverture du menu competences");				
		cop = new CompetencesPanel(this);
		content.removeAll(); 
		content.setLayout(new BorderLayout());
		content.add(cop);
		this.getContentPane().add(content);
		this.setVisible(true);
	}
	
	public void close(){
		System.exit(0);
	}
	
	public void removeO(Object o){
		if(INFO)System.out.println("[SYS]["+state+"]Suppression de "+o.toString());
		o = null;
	}
	
	public void openGame(){
		if(state != 1){
			state = 1;
			if(INFO)System.out.println("[SYS]["+state+"]Ouverture du jeu");
		}
		
		content.removeAll(); 
		content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridheight = 1;
	    gbc.gridwidth = 3;
	    
		gbc.gridx = 0;
	    gbc.gridy = 0;
		i2p.setPreferredSize(new Dimension(this.getContentPane().getWidth(),opt.interfSize[1]));	
	    content.add(i2p,gbc);
	    
	    gbc.gridy = 1;   
		gp.setPreferredSize(new Dimension(this.getContentPane().getWidth(),this.getContentPane().getHeight()-(opt.interfSize[1]+opt.interfSize[0])));
	    content.add(gp,gbc);
	    
	    gbc.gridy = 2; 
		ip.setPreferredSize(new Dimension(this.getContentPane().getWidth(),opt.interfSize[0]));	
	    content.add(ip,gbc);    
		
		this.getContentPane().add(content);
		this.setVisible(true);
		
		i2p.getBa().enable(false);
		
		g.open(this);
		
		
		
		}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}


	public Font getF() {
		return f;
	}

	public TextureLoader getTl() {
		return tl;
	}
	
	public Language getlang() {
		return lang;
	}

	public Info3Panel getI3p() {
		return i3p;
	}

	public int getState() {
		return state;
	}

	public boolean hasGame() {
		return game;
	}

	public Options getOpt() {
		return opt;
	}

	public void setOpt(Options opt) {
		this.opt = opt;
		this.paintWindow();
	}

	public Game getG() {
		return g;
	}

	public Info2Panel getI2p() {
		return i2p;
	}

	public GamePanel getGp() {
		return gp;
	}

	public InfoPanel getIp() {
		return ip;
	}

	public ConstructionPanel getCp() {
		return cp;
	}

	public NewGamePanel getNgp() {
		return ngp;
	}

	public String getVersion() {
		return version;
	}

	public FileManager getFm() {
		return fm;
	}

	public void setIp(InfoPanel ip) {
		this.ip = ip;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public void setI2p(Info2Panel i2p) {
		this.i2p = i2p;
	}

	public void setI3p(Info3Panel i3p) {
		this.i3p = i3p;
	}
	
	
	public boolean isClick() {
		return click;
	}

	public void setKeyBindings(){
		am = ((JComponent) this.getContentPane()).getActionMap();
		im = ((JComponent) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		for(int i = 0; i <= 127; i++){
			im.put(KeyStroke.getKeyStroke(i,0),""+KeyEvent.getKeyText(i)+"");
			am.put(""+KeyEvent.getKeyText(i)+"",new KeyAction(i,this));
		}
	}
	
	public void keyPressed(int keyCode){
		//TODO
		switch(state){
		case 0:
			break;
		case 1:
			switch(keyCode){
				case KeyEvent.VK_UP:
					g.setCamY(g.getCamY()+20);
					break;
				case KeyEvent.VK_DOWN:
					g.setCamY(g.getCamY()-20);
					break;
				case KeyEvent.VK_LEFT:
					g.setCamX(g.getCamX()+20);
					break;
				case KeyEvent.VK_RIGHT:
					g.setCamX(g.getCamX()-20);
					break;
				case KeyEvent.VK_ESCAPE:
						if(GPisClean()){
							if(mp == null){
								openMenu();
							}else{
								mp.close();
							}
						}else{
							cleanGP();
						}
					break;
				case KeyEvent.VK_F1:
					fm.saveImage(gp.printImage(),System.currentTimeMillis()+"");
				break;
				default:break;
			}
			break;
		}
		
	}
	
	//ActionListener
	public void actionPerformed(ActionEvent e){
		
		
		
		
		if(e.getSource()==t0){
			gp.repaint();
			if(DEV)this.setTitle("FPS:"+fps+" X:"+mouseX+" Y:"+mouseY+" Zoom:"+gp.getZoom()+" camX:"+g.getCamX()+" camY:"+g.getCamY());
			wheelCheck = true;
			/*
			int x = this.getWidth()/12;
		    int y=this.getHeight()/12;
			if(mouseX > 0 && mouseX < gp.getWidth() && mouseY > 0 && mouseY < gp.getHeight()){	
				if(mouseX < x){
					g.setCamX(g.getCamX()+(10-(mouseX*10/x)));
				}
				if(mouseX > gp.getWidth()-x){
					g.setCamX(g.getCamX()-(10-((gp.getWidth()-mouseX)*10/x)));
				}
				if(mouseY < y){
					g.setCamY(g.getCamY()+(10-(mouseY*10/y)));
				}
				if(mouseY > gp.getHeight()-y){
					g.setCamY(g.getCamY()-(10-((gp.getHeight()-mouseY)*10/y)));
				}
			}
			*/
		}

		if(e.getSource()==tFps)
			fps = gp.getFps();
		
		if(GPisClean()){
			if(e.getSource()== i2p.getBp()){
				g.getTick().stop();
				i2p.getBp().enable(false);
				i2p.getBa().enable(true);
				i2p.getBar().enable(true);
				i2p.getBsar().enable(true);
			}
		
			if(e.getSource()==i2p.getBa()){
					g.getTick().setDelay(g.getDELAY()[0]);
					g.getTick().start();
					i2p.getBp().enable(true);
					i2p.getBa().enable(false);
					i2p.getBar().enable(true);
					i2p.getBsar().enable(true);
			}
			
			if(e.getSource()==i2p.getBar()){
					g.getTick().setDelay(g.getDELAY()[1]);
					g.getTick().start();
					i2p.getBp().enable(true);
					i2p.getBa().enable(true);
					i2p.getBar().enable(false);
					i2p.getBsar().enable(true);
			}
			
			if(e.getSource()==i2p.getBsar()){
				g.getTick().setDelay(g.getDELAY()[2]);
				g.getTick().start();
				i2p.getBp().enable(true);
				i2p.getBa().enable(true);
				i2p.getBar().enable(true);
				i2p.getBsar().enable(false);
			}
			
			if(e.getSource()==i2p.getBm()){
				openMenu();
			}
			
			if(e.getSource()==i2p.getBc()){
				openCompetences();
			}
			
			if(e.getSource()==i2p.getBpo()){
				openPopulation();
			}
				
			if(e.getSource()==ip.getB1()){
				if(g.hasSelected()){
					g.getM().getMap1()[g.getSelected()[0]][g.getSelected()[1]].openGui();
				}
			}
				
			if(e.getSource()==ip.getB2()){
				newConstruction();
				g.setHasSelected(false);
			}
				
			if(e.getSource()==ip.getB3()){
				g.setSelstate(2);
				g.setHasSelected(false);
			}
		}
		
		if(e.getSource()== g.getTick()){
			i2p.repaint();	
		}
	}
	//MouseMotionListener
	public void mouseMoved(MouseEvent e){
		if(GPisClean() && state == 1){
			if(state == 1 && i3p == null && pup == null && cp == null){
				mouseX = e.getX();
				mouseY = e.getY();
				g.setOver(gp.getPos());
				if(g.getOver()[0] == -1)
					g.setHasOver(false);
				else
					g.setHasOver(true);
				
			}
		}
	}
	
	
	public void mouseClicked(MouseEvent e){
		if(GPisClean() && state == 1){
			mouseX = e.getX();
			mouseY = e.getY();
			g.setOver(gp.getPos());
			if(g.getOver()[0] == -1)
				g.setHasOver(false);
			else
				g.setHasOver(true);
			
			if(e.getButton() == MouseEvent.BUTTON1){
				switch(g.getSelstate()){
					case 0:
						g.setHasSelected(true);
						g.setSelected(g.getOver());
						break;
					case 1:
						if(g.getChoix().estPlacable(g.getOver()[0],g.getOver()[1])){
							for(int i = 0; i < g.getRessources().length; i++){
								g.setRessources(i,g.getRessources()[i]-g.getChoix().getPrix()[i]);
							}
							g.getChoix().setCoord(g.getOver()[0],g.getOver()[1]);
							g.getM().getMap1()[g.getOver()[0]][g.getOver()[1]] = g.getChoix();
							g.getM().getMap1()[g.getOver()[0]][g.getOver()[1]].placeCheck();
							g.setSelstate(0);
						}
						g.setHasSelected(false);
						break;
					case 2:
						if(g.getM().getMap1()[g.getOver()[0]][g.getOver()[1]] != null){
							popUp(lang.getWarning().get(0),new String[]{lang.getWarning().get(1)+" "+lang.getUsage().get(g.getM().getMap1()[g.getOver()[0]][g.getOver()[1]].getGente()+2).toLowerCase()+" "+g.getM().getMap1()[g.getOver()[0]][g.getOver()[1]].getInfo(1)+" ?",lang.getWarning().get(2)},lang.getBouton().get(9),lang.getBouton().get(10),new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									g.getM().getMap1(g.getOver()[0],g.getOver()[1]).deleteCheck();
									g.getM().setMap1(null,g.getOver()[0],g.getOver()[1]);
								}
							},null);
						}
						 
						g.setSelstate(0);
						g.setHasSelected(false);
						break;
				}
			}else{
				g.setSelstate(0);
				g.setHasSelected(false);
			}
			
			
			
				
			}
		}
		

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(wheelCheck){
			int w = (int) -e.getPreciseWheelRotation();
			if(gp.getZoom() != gp.getZOOMMAX() || w < 0){
				gp.setZoom((gp.getZoom()==2 && w < 0)?1:(gp.getZoom()==1 && w > 0)?2:gp.getZoom()+(w>0?2:-2));
				int dep = 50;
				if(w > 0){
					int x = e.getX();
					int y = e.getY();
					g.setCamX(x-opt.size[0]/2>0?g.getCamX()-dep:g.getCamX()+dep);
					g.setCamY((y-(opt.size[1]-opt.interfSize[0]-opt.interfSize[1])/2>0?g.getCamY()-dep:g.getCamY()+dep));
				}
				if(w < 0){
					g.setCamX(g.getCamX()>0?g.getCamX()-dep:g.getCamX()<0?g.getCamX()+dep:g.getCamX());
					g.setCamY(g.getCamY()>0?g.getCamY()-dep:g.getCamY()<0?g.getCamY()+dep:g.getCamY());
				}
				g.setOver(gp.getPos());
				if(g.getOver()[0] == -1)
					g.setHasOver(false);
				else
					g.setHasOver(true);
				gp.repaint();
				wheelCheck = false;
			}
			
		}
	}
	
	
	

	@Override public void windowActivated(WindowEvent arg0) {}
	@Override public void windowClosed(WindowEvent arg0) {}
	@Override public void windowClosing(WindowEvent arg0) {}
	
	@Override public void windowDeiconified(WindowEvent arg0) {}
	
	@Override public void windowOpened(WindowEvent arg0) {}
	@Override public void windowStateChanged(WindowEvent arg0) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent arg0) {}

	@Override 
	public void windowIconified(WindowEvent arg0) {
		if(state == 1){
			this.openMenu();
		}
	}
	
	@Override 
	public void windowDeactivated(WindowEvent arg0) {
		if(state == 1){
			this.openMenu();
		}
	}

}
