package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class TextureLoader {

	private boolean INFO = false;
	
	private String base = "img/";
	private String text = "textures/";
	private String[] urls = {"terrain/plaine","terrain/mer","terrain/montagne","terrain/plage",
							"construction/ville","construction/bucheron","construction/mine",
							"decoration/montagne","decoration/herbe","decoration/arbre","decoration/vague"};
	
	private Boolean[] disponiblesUrls = new Boolean[urls.length];
	
	public int PLAINE = 0;
	public int MER = PLAINE +1;
	public int MONTAGNE = PLAINE +2;
	public int PLAGE = PLAINE +3;
	
	public int VILLE = PLAINE +4;
	public int BUCHERON = VILLE + 1;
	public int MINE = VILLE + 2;
	
	public int DMONTAGNE = VILLE +3;
	public int DHERBE = DMONTAGNE + 1;
	public int DARBRE = DMONTAGNE + 2;
	public int DVAGUE = DMONTAGNE + 3;
	
	
									//0              1            2         3               4           5             6                7             8
	private String[] constantsUrls = {"gui/pourcent","gui/button","gui/ico","gui/interface","gui/panel","gui/preview","gui/ressources","gui/alertes","gui/title"};
	public int[] constantsImagesTaille = {0,0,11,0,0,0,3,3,0 };
	private BufferedImage[] constantsImages = new BufferedImage[constantsUrls.length];
	
	private String menuBase = "gui/menu/";
	private int MENUNB = 8;
	private BufferedImage[] menuImages = new BufferedImage[MENUNB]; 
	
	private Loader l;
	
	protected int TERRAIN = 0;
	protected int CONSTRUCTION1 = 1;
	protected int CONSTRUCTION2 = 2;
	protected int DETAILS = 3;
	
	public BufferedImage[][] getLot(int i){
		BufferedImage[][] out = new BufferedImage[4][];
		for(int j = 0; j < 4; j++){
			switch(i){
			case 0:
				out[j] = new BufferedImage[]{
						getImage(PLAINE,j),
						getImage(MER,j),
						getImage(MONTAGNE,j),
						getImage(PLAGE,j)
						};
				break;
			case 3:
				out[j] = new BufferedImage[]{
						getImage(DHERBE,j),
						getImage(DARBRE,j),
						getImage(DVAGUE,j)};
				break;
			}
			if(i >= 1 && i<=2){
				out[j] = new BufferedImage[]{
						null,
						getImage(VILLE,j),
						getImage(BUCHERON,j),
						getImage(MINE,j)};
			}
			if(i == 2){
				out[j][0] = getImage(DMONTAGNE,j);
			}
		}
		return out;
	}
	
	public BufferedImage getConstantImage(int i){
		return constantsImages[i];
	}
	
	public int getConstantsImagesTaille(int i) {
		return constantsImagesTaille[i];
	}
	
	public BufferedImage getImage(int i,int j){
		if(disponiblesUrls[i] == true){
			URL url = this.getClass().getResource(base+text+j+"/"+urls[i]+".png");
			if(INFO)System.out.println("[IMG]Lecture de "+base+text+j+"/"+urls[i]+".png");
			try {
				return ImageIO.read(url);
			} catch (IOException e2) {
				if(INFO)System.out.println("[IMG][!]Erreur de lecture de "+base+j+"/"+urls[i]+".png");
				return null;
			}
		}else{
			if(INFO)System.out.println("[IMG][!]"+base+text+j+"/"+urls[i]+".png est indisponible");
			return null;
		}
		
	}
	
	
	
	public BufferedImage[] getMenuImages() {
		return menuImages;
	}

	public TextureLoader(Loader l){
		this.l = l;
	}
	
	public boolean load(){
		int progress = 0;
		if(INFO)System.out.println("[IMG][VER]Verification des textures...");
		for(int k = 0; k < 4; k++){
			for(int i = 0; i < urls.length; i++){
				URL url = this.getClass().getResource(base+text+k+"/"+urls[i]+".png");
				if(INFO)System.out.print("[IMG][VER]Verification de "+base+text+k+"/"+urls[i]+".png");
				
				disponiblesUrls[i] = true;
				try {
					ImageIO.read(url);
					if(INFO)System.out.println("  OK");
				} catch (IOException e) {
					disponiblesUrls[i] = false;
					if(INFO)System.out.println("[IMG][VER][!]"+base+text+k+"/"+urls[i]+".png indisponible");
					return false;
				}
				
				progress = (i+k*(urls.length-1))*100/((urls.length-1)*4);
				l.setValue(("Verification des textures... ("+progress+"%)"),progress);	
			}
		}
		if(INFO)System.out.println("[IMG][VER]Verification des textures terminé.");
		if(INFO)System.out.println("[IMG][CHA]Chargement des textures...");
		for(int i = 0; i < constantsUrls.length; i++){
				URL url = this.getClass().getResource(base+constantsUrls[i]+".png");
				if(INFO)System.out.println("[IMG][CHA]Lecture de "+base+constantsUrls[i]+".png");
				try {
					constantsImages[i] = ImageIO.read(url);
				} catch (IOException e2) {
					if(INFO)System.out.println("[IMG][CHA][!]Erreur de lecture de "+base+constantsUrls[i]+".png");
					return false;
				}
			progress = i*100/(constantsUrls.length+MENUNB-1);
			l.setValue(("Chargement des textures... ("+progress+"%)"),progress);	
		}
		for(int i = 1; i <= MENUNB; i++){
				URL url = this.getClass().getResource(base+menuBase+i+".png");
				if(INFO)System.out.println("[IMG][CHA]Lecture de "+i+".png");
				try {
					menuImages[i-1] = ImageIO.read(url);
				} catch (IOException e2) {
					if(INFO)System.out.println("[IMG][CHA][!]Erreur de lecture de "+base+menuBase+i+".png");
					return false;
				}
				progress = (constantsUrls.length+i)*100/(constantsUrls.length+MENUNB-1);
				l.setValue(("Chargement des textures... ("+progress+"%)"),progress);	
		}
		
		if(INFO)System.out.println("[IMG][CHA]Chargement des textures terminé.");
		return true;
	}	
	
	public static BufferedImage[] extend(BufferedImage[] l1,BufferedImage[] l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		BufferedImage[] l3 = new BufferedImage[l1.length+l2.length];
		for(int i = 0; i < l1.length; i++){
			l3[i]=l1[i];
		}
		for(int i = 0; i < l2.length; i++){
			l3[i+l1.length]=l1[i];
		}
		return l3;	
	}
	
}
