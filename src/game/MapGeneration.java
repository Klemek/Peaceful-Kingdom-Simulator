package game;

public class MapGeneration {
	
	private boolean INFO = false;
	
	private int GEN = 2;
	
	public Terrain[][] generate(int taille, Base b) {

		if(INFO)System.out.println("[MAP]Génération de la carte");
		
		Terrain[][] map0 = new Terrain[taille][taille];
		
		switch(GEN){
		case 0:
			for(int i = 0; i < taille; i++){for(int i2 = 0; i2 < taille; i2++){	
				int r = Util.randomInt(1,3);
				map0[i][i2] = r==1?new Plaine(i,i2,b):r==2?new Mer(i,i2,b):new Montagne(i,i2,b);
			}}
			break;
		case 1:
			int t1 = 0, t2 = 0,t3 = 0,t4 = 0,c2,c3,r;
			for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){	
						if(i!=0 && i2!=0)if(map0[i-1][i2-1] != null)t1 = map0[i-1][i2-1].getType();
						if(i!=0)if(map0[i-1][i2] != null)t2 = map0[i-1][i2].getType();
						if(i!=0 && i2!=(taille-1))if(map0[i-1][i2+1] != null)t3 = map0[i-1][i2+1].getType();
						if(i2!=0)if(map0[i][i2-1] != null)t4 = map0[i][i2-1].getType();
						
						c2=(t1!=3&&t2!=3&&t3!=3&&t4!=3)?((t1==2)?20:0)+((t2==2)?20:0)+((t3==2)?20:0)+((t4==2)?20:0):(t2!=3&&t4!=3)?30:0;
						c3=(t1!=2&&t2!=2&&t3!=2&&t4!=2)?((t1==3)?20:0)+((t2==3)?20:0)+((t3==3)?20:0)+((t4==3)?20:0):(t2!=2&&t4!=2)?30:0;
						r = Util.randomInt(0,100);
						if(r <= c2){
							map0[i][i2] = new Mer(i,i2,b);
						}else if(r > c2 && r <= (c2+c3)){
							map0[i][i2] = new Montagne(i,i2,b);
						}else{
							map0[i][i2] = new Plaine(i,i2,b);
						}	
			}}
			break;
		case 2:
			
			int nbmaxlacs = taille*4/15;
			int taillemaxlacs = taille*35/15;
			int nbmaxmonts = taille*4/15;
			int taillemaxmonts = taille*20/15;
			int nbmaxforets = (taille*6/15)+1;
			int taillemaxforets = taille*20/15;
		
			/*
			for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
				map0[i][i2] = new Mer(i,i2,b);
			}}
			*/
			for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
				map0[i][i2] = new Plaine(i,i2,b);
			}}
			int nblacs = Util.randomInt(1,nbmaxlacs);
			if(INFO)System.out.println("[MAP]"+nblacs+" lac(s)");
			for(int i = 0; i < nblacs; i++){
				int taillel = Util.randomInt(5,(int)taillemaxlacs/nblacs);
				int[] i1 = new int[taillel];
				int[] i2 = new int[taillel];
				//System.out.println("[MAP] "+(i+1)+"-"+taillel+" chunk(s)");
				boolean continuer = true;
				while(continuer){
					i1[0] = Util.randomInt(0,taille-1);
					i2[0] = Util.randomInt(0,taille-1);
					if(map0[i1[0]][i2[0]].getType() != 2){
						map0[i1[0]][i2[0]] = new Mer(i1[0],i2[0],b);
						continuer = false;
					}
				}
				
				for(int j = 1; j < taillel; j++){
					continuer = true;
					while(continuer){
						int ba = Util.randomInt(0,j-1);
						int d = Util.randomInt(0,1);
						int d2 = (Util.randomInt(0,1)==0?-1:1);
						if(d == 0){
							i1[j] = i1[ba] + d2;
							i1[j] = i1[j] < 0?0:i1[j] >= taille?taille-1:i1[j];
							i2[j]=i2[ba];
						}else{
							i2[j] = i2[ba] + d2;
							i2[j] = i2[j] < 0?0:i2[j] >= taille?taille-1:i2[j];
							i1[j]=i1[ba];
						}
						
						if(map0[i1[j]][i2[j]].getType() != 2){
							map0[i1[j]][i2[j]] = new Mer(i1[j],i2[j],b);
							continuer = false;
						}
						
					}
				
				}
			}
			int nbmonts = Util.randomInt(1,nbmaxmonts);
			if(INFO)System.out.println("[MAP]"+nbmonts+" montagnes(s)");
			for(int i = 0; i < nbmonts; i++){
				int taillem = Util.randomInt(5,(int)taillemaxmonts/nbmonts);
				int[] i1 = new int[taillem];
				int[] i2 = new int[taillem];
				//System.out.println("[MAP] "+(i+1)+"-"+taillem+" chunk(s)");
				boolean continuer = true;
				while(continuer){
					i1[0] = Util.randomInt(0,taille-1);
					i2[0] = Util.randomInt(0,taille-1);
					if(map0[i1[0]][i2[0]].getType() != 3 && map0[i1[0]][i2[0]].getType() != 2){
						map0[i1[0]][i2[0]] = new Montagne(i1[0],i2[0],b);
						continuer = false;
					}
				}
				
				for(int j = 1; j < taillem; j++){
					continuer = true;
					while(continuer){
						int ba = Util.randomInt(0,j-1);
						int d = Util.randomInt(0,1);
						int d2 = (Util.randomInt(0,1)==0?-1:1);
						if(d == 0){
							i1[j] = i1[ba] + d2;
							i1[j] = i1[j] < 0?0:i1[j] >= taille?taille-1:i1[j];
							i2[j]=i2[ba];
						}else{
							i2[j] = i2[ba] + d2;
							i2[j] = i2[j] < 0?0:i2[j] >= taille?taille-1:i2[j];
							i1[j]=i1[ba];
						}
						
						if(map0[i1[j]][i2[j]].getType() != 3 && map0[i1[j]][i2[j]].getType() != 2){
							map0[i1[j]][i2[j]] = new Montagne(i1[j],i2[j],b);
							continuer = false;
						}
					}
				}
			}
			for(int i2 = taille-1; i2 >= 0; i2--){for(int i = 0; i < taille; i++){
				if(map0[i][i2].getType()==2){
					if(i > 0){
						if(map0[i-1][i2].getType()==1){
							if(Util.random(new double[]{20,70})==1)
								map0[i-1][i2] = new Plage(i-1,i2,b);
						}
					}
					if(i < taille-1){
						if(map0[i+1][i2].getType()==1){
							if(Util.random(new double[]{20,70})==1)
								map0[i+1][i2] = new Plage(i+1,i2,b);
						}
					}
					if(i2 > 0){
						if(map0[i][i2-1].getType()==1){
							if(Util.random(new double[]{20,70})==1)
								map0[i][i2-1] = new Plage(i,i2-1,b);
						}
					}
					if(i2 < taille-1){
						if(map0[i][i2+1].getType()==1){
							if(Util.random(new double[]{20,70})==1)
								map0[i][i2+1] = new Plage(i,i2+1,b);
						}
					}
				}
			}}
			int nbforets = Util.randomInt(1,nbmaxforets);
			if(INFO)System.out.println("[MAP]"+nbforets+" forêts(s)");
			for(int i = 0; i < nbforets; i++){
				int taillem = Util.randomInt(5,(int)taillemaxforets/nbforets);
				int[] i1 = new int[taillem];
				int[] i2 = new int[taillem];
				//System.out.println("[MAP] "+(i+1)+"-"+taillem+" chunk(s)");
				boolean continuer = true;
				while(continuer){
					i1[0] = Util.randomInt(0,taille-1);
					i2[0] = Util.randomInt(0,taille-1);
					if(map0[i1[0]][i2[0]].getType() != 3 && map0[i1[0]][i2[0]].getType() != 2){
						map0[i1[0]][i2[0]] = new Foret(i1[0],i2[0],b);
						continuer = false;
					}
				}
				
				for(int j = 1; j < taillem; j++){
					continuer = true;
					while(continuer){
						int ba = Util.randomInt(0,j-1);
						int d = Util.randomInt(0,1);
						int d2 = (Util.randomInt(0,1)==0?-1:1);
						if(d == 0){
							i1[j] = i1[ba] + d2;
							i1[j] = i1[j] < 0?0:i1[j] >= taille?taille-1:i1[j];
							i2[j]=i2[ba];
						}else{
							i2[j] = i2[ba] + d2;
							i2[j] = i2[j] < 0?0:i2[j] >= taille?taille-1:i2[j];
							i1[j]=i1[ba];
						}
						
						if(map0[i1[j]][i2[j]].getType() != 3 && map0[i1[j]][i2[j]].getType() != 2){
							map0[i1[j]][i2[j]] = new Foret(i1[j],i2[j],b);
							continuer = false;
						}
					}
				}
			}
			break;
		}
		
		if(INFO){
			int plaine = 0;
			int mer = 0;
			int montagne = 0;
			int plage = 0;
			
			for(int i2 = taille-1; i2 >= 0; i2--){
				for(int i = 0; i < taille; i++){
					switch(map0[i][i2].getType()){
					case 1: plaine++; break;
					case 2: mer++; break;
					case 3: montagne++; break;
					case 4: plage++; break;
					}
				}
			}
			System.out.println("[MAP]Génération terminée:");
			System.out.println("[MAP]"+plaine+" chunk(s) de plaine ("+Util.arrondi((float)plaine*(float)100/(float)(taille*taille))+"%)");
			System.out.println("[MAP]"+mer+" chunk(s) de mer ("+Util.arrondi((float)mer*(float)100/(float)(taille*taille))+"%)");
			System.out.println("[MAP]"+plage+" chunk(s) de plage ("+Util.arrondi((float)plage*(float)100/(float)(taille*taille))+"%)");
			System.out.println("[MAP]"+montagne+" chunk(s) de montagne ("+Util.arrondi((float)montagne*(float)100/(float)(taille*taille))+"%)");

		}
		
		
		return map0;
	}
	
}
