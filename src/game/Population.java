package game;

import java.io.Serializable;

public class Population implements Serializable{

	private static final long serialVersionUID = -8577584858199980034L;
	
	public int POPULATION = 0;
	public int OUVRIERS = 1;
	public int BUCHERONS = 2;
	public int MINEUR = 3;
	
	private int minimum = 5;
	
	private int[] compte = {0,0,0,0};
	private int[] disponible = {0,0};
	private int[] inactifs = {0,0};
	
	public String[] getMetiers(Base b){
		String[] metiers = {b.getlang().getMetiers().get(0),b.getlang().getMetiers().get(1),b.getlang().getMetiers().get(2),b.getlang().getMetiers().get(3)};
		return metiers;
	}
	
	public int[] getCompte(){
		return compte;
	}
	
	public int getCompte(int i){
		return compte[i];
	}
	
	public void setCompte(int n, int i){
		compte[i] = n;
	}
	
	public int getDisponible(int i){
		return disponible[i-2];
	}
	
	public void setDisponible(int n, int i){
		disponible[i-2] = n;
	}
	
	public int getInactifs(int i){
		return inactifs[i-2];
	}
	
	public void setInactifs(int n, int i){
		inactifs[i-2] = n;
	}

	public void change(int n, int i){
		if(i == 0){
			compte[0]+=n;
			if(compte[0]<0)compte[0]=0;
			if(n > 0){
				int[][] j = new int[disponible.length+1][2];
				int c = 0;
				for(int k = -1; k < disponible.length; k++){
					if(k==-1){
						if(compte[1] < minimum){
							j[k+1] = new int[]{-1,minimum-compte[1]};
							c++;
						}
					}else{
						if(disponible[k] > 0){
							j[k+1] = new int[]{k,disponible[k]-compte[k+2]};
							c++;
						}
					}
					
					
				}
				int[][] l = new int[c][2];
				int p = 0;
				for(int k = 0; k < j.length; k++){
					if(j[k][1]!=0){
						l[p] = j[k];
						p++;
					}
					
				}
				l = Util.sortD(l,1);
				int m = n;
				int k = 0;
				while(k < l.length && m > 0){
					if(k != l.length-1){
						while(l[k][1] > l[k+1][1] && m > 0){
							for(int o = 0; o <= k && m > 0; o++){
								l[o][1]--;
								m--;
								compte[l[o][0]+2]++;
							}
						}
					}else{
						while(m > 0 && l[l.length-1][1] != 0){
							for(int o = 0; o < l.length; o++){
									l[o][1]--;
									m--;
									compte[l[o][0]+2]++;
									if(l[l.length-1][1] == 0 || m == 0)o = l.length;
									
							}
						}
					}
					k++;
				}
				if(m > 0){
					compte[1]+=m;
				}
			}else{
				if(compte[1]-minimum > Math.abs(n)){
					compte[1]+=n;
				}else{
					int m = Math.abs(n);
					m-= compte[1]-minimum;
					compte[1] = minimum; 
					boolean stop = false;
					while(m > 0 && !stop){
						stop = true;
						for(int j = 2; j < compte.length; j++){
							if(compte[j] != 0){
								compte[j]--;
								m--;
								if(m == 0)j = compte.length;
								stop = false;
							}
						}
					}
					if(stop){
						compte[1]-=m;
					}
				}
			}
		}else if(i != 1){
			if(n > 0){
				if(disponible[i-2] - compte[i] < n){
					if(compte[1]-minimum < disponible[i-2] - compte[i]){
						compte[i]+=compte[1]-minimum;
						compte[1]=minimum;
					}else{
						compte[1]-=disponible[i-2] - compte[i];
						compte[i]+=disponible[i-2] - compte[i];
					}
				}else{
					if(compte[1]-minimum < n){
						compte[i]+=compte[1]-minimum;
						compte[1]=minimum;
					}else{
						compte[1]-=n;
						compte[i]+=n;
					}
				}
			}else{
				compte[i]+=n;
				compte[1]-=n;
			}
		}
	}
	
	public void changeDisponible(int n, int i){
		if(i < 2)return;
		i-=2;
		disponible[i]+=n;
		if(disponible[i] < compte[i+2]){
			compte[1]+= compte[i+2]-disponible[i];
			compte[i+2] = disponible[i];
		}else if(disponible[i] > compte[i+2] && compte[1] > minimum){
			if(compte[1]-minimum < disponible[i]-compte[i+2]){
				compte[i+2]+=compte[1]-minimum;
				compte[1]=minimum;
			}else{
				compte[1]-=disponible[i]-compte[i+2];
				compte[i+2] = disponible[i];
			}
		}
	}
	
	public void tousInactifs(){
		for(int i = 0; i < inactifs.length; i++){
			inactifs[i] = compte[i+2];
		}
	}
	
	public void text(Base b){
		String[] metiers = {b.getlang().getMetiers().get(0),b.getlang().getMetiers().get(1),b.getlang().getMetiers().get(2),b.getlang().getMetiers().get(3)};

		System.out.println(metiers[0]+" : "+compte[0]);
		
		for(int i = 1; i < compte.length; i++){
			if(compte[i] != 0 || (i > 1 && disponible[i-2] != 0))System.out.println(metiers[i]+" : "+compte[i]+(i >= 2?"/"+disponible[i-2]:""));
		}
		
	}
}
