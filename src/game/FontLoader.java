package game;
import java.awt.Font;
import java.net.URL;
import java.net.URLConnection;


public class FontLoader {
	
	private boolean INFO = false;
	
	private Loader l;
	private Font f;
	
	public Font getFont(){
		if(f == null)load();
		return f;
	}

	public FontLoader(Loader l){
		this.l = l;
	}

	public boolean load(){
		if(INFO)System.out.println("[POL]Chargement de la police...");
		if(l != null)l.setValue(("Chargement de la police... ("+0+"%)"),0);
		try {
			URL u = this.getClass().getResource("font.ttf");
			URLConnection uc = u.openConnection();
			if(INFO)System.out.println("[POL]Lecture de font.ttf");
		      this.f = Font.createFont(Font.TRUETYPE_FONT,uc.getInputStream());
		      if(l != null)l.setValue(("Chargement de la police... ("+100+"%)"),100);
		    }catch (Exception ex) {
			    if(INFO)System.out.println("[POL]Erreur de lecture de font.ttf");
			    if(l != null)l.setValue(("Erreur de chargement de la police"),0);
			    return false;
		    }
		if(INFO)System.out.println("[POL]Chargement de la police terminée.");
		return true;
	}
	
}
