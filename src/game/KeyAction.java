package game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class KeyAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	
	int keyCode;
	Base b;
	
	public KeyAction(int keyCode, Base b){
		this.keyCode = keyCode;
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		b.keyPressed(keyCode);
	}

}
