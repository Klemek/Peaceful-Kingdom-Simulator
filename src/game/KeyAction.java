package game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class KeyAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	
	int keyCode;
	Base ba;
	
	public KeyAction(int keyCode, Base b){
		this.keyCode = keyCode;
		this.ba = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ba.keyPressed(keyCode);
	}

}
