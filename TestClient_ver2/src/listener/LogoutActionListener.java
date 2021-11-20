package listener;


import java.awt.event.ActionEvent;

import boundaries.Boundary;
import control.ClientSystemControl;




/**
 * ログアウトボタンのアクションリスナー
 * @author Kazuki0724
 *
 */
public class LogoutActionListener extends BoundaryActionListener{
    
	
	private ClientSystemControl control;
    private Boundary boundary;
    
    
    public LogoutActionListener(ClientSystemControl control, Boundary boundary) {
    	
    	this.control = control;
    	this.boundary = boundary;
    	
    	
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        String str = e.getActionCommand();
        System.out.println("[Log] LogOut Button Clicked");
        
        //サーバ側にログアウトを通知
        control.communicate().sendData("logout","");
        
    }

    
}