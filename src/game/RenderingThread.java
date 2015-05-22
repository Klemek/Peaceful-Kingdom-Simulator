package game;

import javax.swing.JPanel;

public class RenderingThread extends Thread{
	
	private JPanel p;
	public RenderingThread(JPanel gp){
		this.p = gp;
	}
	
	public void run(){
        while(true){
           try {
              p.repaint(); 
              sleep(10);
           } catch ( Exception e ) {} 
        }
    }

}
