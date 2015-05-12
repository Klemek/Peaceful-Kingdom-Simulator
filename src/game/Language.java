package game;

import java.util.ArrayList;

public class Language {
	
	
	private ArrayList<String> bouton = new ArrayList<String>();
	private ArrayList<String> menu = new ArrayList<String>();
	private ArrayList<ArrayList<String>> guimenu = new ArrayList<ArrayList<String>>();
	private ArrayList<String> noms = new ArrayList<String>();
	private ArrayList<ArrayList<String>> gui = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
	private ArrayList<String> metiers = new ArrayList<String>();
	private ArrayList<String> util = new ArrayList<String>();
	private ArrayList<String> usage = new ArrayList<String>();
	private ArrayList<String> info = new ArrayList<String>();
	private ArrayList<String> date = new ArrayList<String>();
	private ArrayList<String> warning = new ArrayList<String>();
	
	protected int PLAINE = 0;
	protected int FORET = PLAINE+1;
	protected int MONTAGNE = PLAINE+2;
	protected int MER = PLAINE+3;
	protected int PLAGE = PLAINE+4;
	
	protected int VILLE = PLAINE+5;
	protected int BUCHERON = VILLE+1;
	protected int MINE = VILLE+2;
	
	protected int VILLEGUI = 0;
	protected int BUCHERONGUI = 1;
	protected int MINEGUI = 2;
	
	public Language(ArrayList<String> s){
		
		int i;
		ArrayList<String> temp = new ArrayList<String>();
		for(String s2:s){
			System.out.println(s2);
		}
		//boutons
		i = 1;
		while(!s.get(i).startsWith("--")){
			bouton.add(s.get(i));
			i++;
		}
		//menu
		i++;
		while(!s.get(i).startsWith("--")){
			menu.add(s.get(i));
			i++;
		}
		
		//guimenu
		i++;
		while(!s.get(i).startsWith("--")){
			i++;
			temp = new ArrayList<String>();
			while(!s.get(i).startsWith("*") && !s.get(i).startsWith("--")){
				temp.add(s.get(i));
				i++;
			}
			guimenu.add(temp);
			
		}

		//noms
		i++;
		while(!s.get(i).startsWith("--")){
			noms.add(s.get(i));
			i++;
		}
		

		//gui
		i++;
		while(!s.get(i).startsWith("--")){
			i++;
			temp = new ArrayList<String>();
			while(!s.get(i).startsWith("*") && !s.get(i).startsWith("--")){
				temp.add(s.get(i));
				i++;
			}
			gui.add(temp);
			
		}
		
		//desc
		i++;
		while(!s.get(i).startsWith("--")){
			i++;
			temp = new ArrayList<String>();
			while(!s.get(i).startsWith("*") && !s.get(i).startsWith("--")){
				temp.add(s.get(i));
				i++;
			}
			desc.add(temp);
			
		}
		
		//metiers
		i++;
		while(!s.get(i).startsWith("--")){
			metiers.add(s.get(i));
			i++;
		}
		

		//util
		i++;
		while(!s.get(i).startsWith("--")){
			util.add(s.get(i));
			i++;
		}
		

		//usage
		i++;
		while(!s.get(i).startsWith("--")){
			usage.add(s.get(i));
			i++;
		}
		

		//info
		i++;
		while(!s.get(i).startsWith("--")){
			info.add(s.get(i));
			i++;
		}
		//date
		i++;
		while(!s.get(i).startsWith("--")){
			date.add(s.get(i));
			i++;
		}
		/*
		for(String s2:info){
			System.out.println(s2);
		}
		 */
		//warning
		i++;
		while(!s.get(i).startsWith("--")){
			warning.add(s.get(i));
			i++;
		}
		
	}
	
	public String getLangue(int i){
		switch(i){
		case 0:return "English";
		case 1:return "Français";
		}
		return "";
	}

	public ArrayList<String> getBouton() {
		return bouton;
	}

	public ArrayList<String> getMenu() {
		return menu;
	}

	public ArrayList<ArrayList<String>> getGuimenu() {
		return guimenu;
	}

	public ArrayList<String> getNoms() {
		return noms;
	}

	public ArrayList<ArrayList<String>> getGui() {
		return gui;
	}

	public ArrayList<ArrayList<String>> getDesc() {
		return desc;
	}

	public ArrayList<String> getMetiers() {
		return metiers;
	}

	public ArrayList<String> getUtil() {
		return util;
	}

	public ArrayList<String> getUsage() {
		return usage;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public ArrayList<String> getDate() {
		return date;
	}

	public ArrayList<String> getWarning() {
		return warning;
	}
	
	
}
