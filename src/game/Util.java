package game;

import java.awt.Color;

public class Util {
	
	
	//NUMBERS
	
	public static int[] sortD(int[] i){
		if(i.length <= 1){
			return i;
		}else if(i.length == 2){
			if(i[0] < i[1]){
				int i2 = i[0];
				i[0] = i[1];
				i[1] = i2;		
			}
		}else{
			int k = 0;
			while(k != i.length-1){
				if(i[k] < i[k+1]){
					int i2 = i[k];
					i[k] = i[k+1];
					i[k+1] = i2;
					k = -1;
				}
				k++;
			}
		}
		return i;
	}
	
	public static int[] sortA(int[] i){
		int[] j = i;
		int k = 0;
		
		if(j.length <= 1){
			return j;
		}else if(j.length == 2){
			if(j[0] > j[1]){
				int i2 = j[0];
				j[0] = j[1];
				j[1] = i2;		
			}
		}else{
			while(k != j.length-1){
				if(j[k] > j[k+1]){
					int i2 = j[k];
					j[k] = j[k+1];
					j[k+1] = i2;
					k = -1;
				}
				k++;
			}
		}
		return j;
	}
	
	public static int[][] sortD(int[][] i,int i2){
		int[][] j = i;
		int k = 0;
		
		if(j.length <= 1){
			return j;
		}else if(j.length == 2){
			if(j[0][i2] < j[1][i2]){
				int[] i3 = j[0];
				j[0] = j[1];
				j[1] = i3;		
			}
		}else{
			while(k != j.length-1){
				if(j[k][i2] < j[k+1][i2]){
					int[] i3 = j[k];
					j[k] = j[k+1];
					j[k+1] = i3;
					k = -1;
				}
				k++;
			}
		}
		return j;
	}
	
	public static int[][] sortA(int[][] i,int i2){
		int[][] j = i;
		int k = 0;
		
		if(j.length <= 1){
			return j;
		}else if(j.length == 2){
			if(j[0][i2] > j[1][i2]){
				int[] i3 = j[0];
				j[0] = j[1];
				j[1] = i3;		
			}
		}else{
			while(k != j.length-1){
				if(j[k][i2] > j[k+1][i2]){
					int[] i3 = j[k];
					j[k] = j[k+1];
					j[k+1] = i3;
					k = -1;
				}
				k++;
			}
		}
		return j;
	}
	
	public static int randomInt(int min, int max){
		return (int)random(min,max);
	}

	public static double random(double min, double max){
		return Math.random()*(max+1-min)+min;
	}

public static int random(double[] d){
		
		double base = Math.random()*101;
		
		int sortie = 0;
		
		for(int i = 0; i < d.length; i++){
			int b = 0;
			for(int i2 = 0; i2 < i; i2++){
				b+=d[i2];
			}
			if(i == 0){
				
				if(base >= 0 && base < d[i])sortie = i; 
				
			}else if(i == d.length-1){
				
				if(base >= b && base < 100)sortie = i; 
				
			}else{
				
				if(base >= b && base < b+d[i])sortie = i; 
				
			}
		}
		return sortie;
	}
	
	public static int arrondi(double i){
		return (int)(i+0.5);
	}
	
	public static int somme(int[] i){
		int s = 0;
		for(int j = 0; j < i.length; j++){
			s+=i[j];
		}
		return s;
	}
	
	public static int somme(int[] i, int start, int stop){
		int s = 0;
		for(int j = start; j <= stop; j++){
			s+=i[j];
		}
		return s;
	}

	//STRINGS
	
	public static char randomChar(){
		return (char)randomInt(97,122);
	}
	
	public static String nbfra(int i){
		String s = "";
		String s2 = "";
		int i4 = i;
		if(i >= 100){
			int i3 = i/100;
			i4 = i-i3*100;
			if(i3 == 1){
				s = s.concat("Cent");
			}else{
				switch(i3){
				case 2:s = s.concat("Deux");break;
				case 3:s = s.concat("Trois");break;
				case 4:s = s.concat("Quatre");break;
				case 5:s = s.concat("Cinq");break;
				case 6:s = s.concat("Six");break;
				case 7:s = s.concat("Sept");break;
				case 8:s = s.concat("Huit");break;
				case 9:s = s.concat("Neuf");break;
				}
				s = s.concat(" cents");
			}
		}
			if(i4 < 10){
				switch(i4){
				case 0:s2 = s2.concat("Zéro");break;
				case 1:s2 = s2.concat("Un");break;
				case 2:s2 = s2.concat("Deux");break;
				case 3:s2 = s2.concat("Trois");break;
				case 4:s2 = s2.concat("Quatre");break;
				case 5:s2 = s2.concat("Cinq");break;
				case 6:s2 = s2.concat("Six");break;
				case 7:s2 = s2.concat("Sept");break;
				case 8:s2 = s2.concat("Huit");break;
				case 9:s2 = s2.concat("Neuf");break;
				}
			}else{
				int i2 = i4/10;
				int i3 = i4-i2*10;
				if(i4 == 10 || i4 > 16){
					switch(i2){
					case 1:s2 = s2.concat("Dix");break;
					case 2:s2 = s2.concat("Vingt");break;
					case 3:s2 = s2.concat("Trente");break;
					case 4:s2 = s2.concat("Quarante");break;
					case 5:s2 = s2.concat("Cinquante");break;
					case 6:s2 = s2.concat("Soixante");break;
					case 7:s2 = s2.concat("Soixante");break;
					case 8:s2 = i3==0?s2.concat("Quatre-vingts"):s2.concat("Quatre-vingt");break;
					case 9:s2 = s2.concat("Quatre-vingt");break;
					}
					if(i2 == 7 || i2 == 9){
						switch(i3){
						case 0:s2=s2.concat("-dix");break;
						case 1:if(i2 == 7)s2=s.concat(" et onze");else s2=s2.concat("-onze");break;
						case 2:s2=s2.concat("-douze");break;
						case 3:s2=s2.concat("-treize");break;
						case 4:s2=s2.concat("-quatorze");break;
						case 5:s2=s2.concat("-quinze");break;
						case 6:s2=s2.concat("-seize");break;
						case 7:s2=s2.concat("-dix-sept");break;
						case 8:s2=s2.concat("-dix-huit");break;
						case 9:s2=s2.concat("-dix-neuf");break;
						}
					}else{
						if(i2 != 8 && i3 == 1){
							s2 = s2.concat(" et ");
						}else{
							if(i3 != 0)
								s2 = s2.concat("-");
						}
						switch(i3){
						case 1:s2=s2.concat("un");break;
						case 2:s2=s2.concat("deux");break;
						case 3:s2=s2.concat("trois");break;
						case 4:s2=s2.concat("quatre");break;
						case 5:s2=s2.concat("cinq");break;
						case 6:s2=s2.concat("six");break;
						case 7:s2=s2.concat("sept");break;
						case 8:s2=s2.concat("huit");break;
						case 9:s2=s2.concat("neuf");break;
						}
					}
					
				}else{
					switch(i3){
					case 1:s2 = s2.concat("Onze");break;
					case 2:s2 = s2.concat("Douze");break;
					case 3:s2 = s2.concat("Treize");break;
					case 4:s2 = s2.concat("Quatorze");break;
					case 5:s2 = s2.concat("Quinze");break;
					case 6:s2 = s2.concat("Seize");break;
					}
				}
				
				
				
			}
			if(i >= 100){
				if((int)(i/100)!=(double)(i/100D))s = s.concat(" "+s2.toLowerCase());
			}else{
				s = s2;
			}
		return s;
		
	}
	
	public static String nbeng(int i){
		String s = "";
		String s2 = "";
		int i4 = i;
		if(i >= 100){
			int i5 = i/100;
			i4 = i-i5*100;
			switch(i5){
			case 1:s2 = "One";break;
			case 2:s2 = "Two";break;
			case 3:s2 = "Three";break;
			case 4:s2 = "Four";break;
			case 5:s2 = "Five";break;
			case 6:s2 = "Six";break;
			case 7:s2 = "Seven";break;
			case 8:s2 = "Eight";break;
			case 9:s2 = "Nine";break;
			}
			if(i5 == 1){
				s2 = s2.concat(" hundred");
			}else{
				s2 = s2.concat(" hundreds");
			}
			
		}
		if(i4 < 20){
			switch(i4){
			case 0:s = i>=100?"":"Zero";break;
			case 1:s = "One";break;
			case 2:s = "Two";break;
			case 3:s = "Three";break;
			case 4:s = "Four";break;
			case 5:s = "Five";break;
			case 6:s = "Six";break;
			case 7:s = "Seven";break;
			case 8:s = "Eight";break;
			case 9:s = "Nine";break;
			case 10:s = "Ten";break;
			case 11:s = "Eleven";break;
			case 12:s = "Twelve";break;
			case 13:s = "Thirteen";break;
			case 14:s = "Fourteen";break;
			case 15:s = "Fifteen";break;
			case 16:s = "Sixteen";break;
			case 17:s = "Seventeen";break;
			case 18:s = "Eighteen";break;
			case 19:s = "Nineteen";break;
			}
		}else{
			int i2 = i4/10;
			int i3 = i4-i2*10;
			switch(i2){
			case 2:s = "Twenty";break;
			case 3:s = "Thirty";break;
			case 4:s = "Forty";break;
			case 5:s = "Fifty";break;
			case 6:s = "Sixty";break;
			case 7:s = "Seventy";break;
			case 8:s = "Eighty";break;
			case 9:s = "Ninety";break;
			}
			
			switch(i3){
			case 1:s = s.concat("-one");break;
			case 2:s = s.concat("-two");break;
			case 3:s = s.concat("-three");break;
			case 4:s = s.concat("-four");break;
			case 5:s = s.concat("-five");break;
			case 6:s = s.concat("-six");break;
			case 7:s = s.concat("-seven");break;
			case 8:s = s.concat("-eight");break;
			case 9:s = s.concat("-nine");break;
			}
		}
		if(i >= 100){
			return s2.concat(" "+s.toLowerCase());
		}else{
			return s;
		}
		
	}
	
	//COLORS
	
	public static Color changeAlpha(Color c,int a){
		return new Color(c.getRed(),c.getGreen(),c.getBlue(),a);
	}
}
