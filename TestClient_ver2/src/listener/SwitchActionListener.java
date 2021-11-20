package listener;


import java.awt.event.ActionEvent;

import boundaries.AccountAuthentificationBoundary;
import boundaries.AccountRegistrationBoundary;
import boundaries.Boundary;
import control.ClientSystemControl;
import entity.Boundaries;




/**
 * ログインと新規登録の画面を行き来するリスナー
 * @author Kazuki0724
 *
 */

public class SwitchActionListener extends BoundaryActionListener{
	
	
	private AccountRegistrationBoundary arb;
	private AccountAuthentificationBoundary aab;
	private Boundary boundary;
	private ClientSystemControl control;
	
	
	public SwitchActionListener(AccountRegistrationBoundary arb, Boundary boundary ,ClientSystemControl control){
	        this.arb = arb;
	        this.boundary = boundary;
	        this.control = control;
	}
	
	
	public SwitchActionListener(AccountAuthentificationBoundary aab, Boundary boundary ,ClientSystemControl control){
        this.aab = aab;
        this.boundary = boundary;
        this.control = control;
	}
	


	public void actionPerformed(ActionEvent e){
		
		System.out.println("[Log] switch Button Clicked");
		String type = e.getActionCommand();
		
		System.out.println("[Log] "+type);
		
		if(type.equals("goLogin")) {
			control.getBoundary().changePanel(Boundaries.AccountAuthentificationBoudary);
			
		}else if(type.equals("goResist")) {
			control.getBoundary().changePanel(Boundaries.AccountRegistrationBoundary);
		
		}else {
			System.out.println("[Error] Switch action listener error");
		}
		
		
		
	}

}
