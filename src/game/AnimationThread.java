package game;

public class AnimationThread extends Thread{

	private Game g;
	private boolean anim;
	private static final transient int HA = 1;
	private static final transient int MAXH = 8;
	private static final transient int DELAY = 30;
	private int t = 0;
	
	
	public AnimationThread(Game g, boolean anim){
		this.g = g;
		this.anim = anim;
		
	}
	
	
	public void run(){
		while(true){
			for(int i2 = g.getTaille()-1; i2 >= 0; i2--){for(int i = 0; i < g.getTaille(); i++){	
				if(g.hasSelected() && i == g.getSelected()[0] && i2 == g.getSelected()[1] && g.getSelstate() == 0){
					if(g.getM().getMap0()[i][i2].getH() < MAXH){
						g.getM().getMap0()[i][i2].setH(g.getM().getMap0()[i][i2].getH()+HA);
						if(g.getM().getMap1()[i][i2] != null)g.getM().getMap1()[i][i2].setH(g.getM().getMap1()[i][i2].getH()+HA);
					}
				}else if(g.hasOver() && i == g.getOver()[0] && i2 == g.getOver()[1] && g.getSelstate() == 0){
					if(g.getM().getMap0()[i][i2].getH() < MAXH){
						g.getM().getMap0()[i][i2].setH(g.getM().getMap0()[i][i2].getH()+HA);
						if(g.getM().getMap1()[i][i2] != null)g.getM().getMap1()[i][i2].setH(g.getM().getMap1()[i][i2].getH()+HA);
					}
				}else if(g.getM().getMap0()[i][i2].getH() > 0){
					g.getM().getMap0()[i][i2].setH(g.getM().getMap0()[i][i2].getH()-HA);
					if(g.getM().getMap1()[i][i2] != null)g.getM().getMap1()[i][i2].setH(g.getM().getMap1()[i][i2].getH()-HA);
				}
			}}
			if(g.getValues() != null){
	    		for(int i = 0; i < g.getValues().length; i++){
	    	 		if(g.getValues()[i] != null){
	    	 			g.getValues()[i].event();
	    	 		}
	    		}
			}
			t++;
			if(t == 10){
				if(g.getM() != null && anim)g.getM().animation();
				t=0;
			}
			try {
				sleep(DELAY);
			} catch (InterruptedException e) {}
		}
	}
	
}
