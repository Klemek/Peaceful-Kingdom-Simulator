package game;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LangLoader {

	private boolean INFO = true;
	
	private String base = "lang/";
	
	private String[] urls = {"eng","fra"};

	private Loader l;
	
	private ArrayList<ArrayList<String>> lang;
	
	public LangLoader(Loader l){
		this.l = l;
	}
	
	public boolean load(){
		if(INFO)System.out.println("[LAN]Chargement des langues...");
		if(l != null)l.setValue(("Chargement de la langues... ("+0+"%)"),0);
		lang = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < urls.length;i++){	
			InputStream is = getClass().getResourceAsStream(base+urls[i]+".txt");
			try {
				temp = new ArrayList<String>();
				if(INFO)System.out.print("[LAN]Lecture de "+base+urls[i]+".txt");
				BufferedReader r = new BufferedReader(new InputStreamReader(new BufferedInputStream(is)));
				while(r.ready()){
					temp.add(r.readLine());
				}
				lang.add(temp);
				if(INFO)System.out.println(" OK");
				
			} catch (IOException e) {
				if(INFO)System.out.println("[LAN][!]Erreur de lecture de "+base+urls[i]+".txt");
				return false;
			}
			
		}
		if(l != null)l.setValue(("Chargement de la langues... ("+100+"%)"),0);
		if(INFO)System.out.println("[LAN]Chargement des langues terminé.");
		return true;
		
	}

	public ArrayList<ArrayList<String>> getLang() {
		return lang;
	}
	
}
