package listener;


import java.awt.event.ActionEvent;

import boundaries.Boundary;
import control.ClientControl;




/**
 * ログアウトボタンのアクションリスナー
 * @author Kazuki0724
 *
 */
public class LogoutActionListener extends BoundaryActionListener{
    
	
	private ClientControl control;
    private Boundary boundary;
    
    
    /**
     * コンストラクタ
     * @param control
     * @param boundary
     */
    public LogoutActionListener(ClientControl control, Boundary boundary) {
    	
    	this.control = control;
    	this.boundary = boundary;
    	
    	
    }
    
    /**
	 * イベント処理
	 */
    public void actionPerformed(ActionEvent e){
        
        String str = e.getActionCommand();
        System.out.println("[Log] LogOut Button Clicked");
        
        //サーバ側にログアウトを通知
        control.communicate().sendData("logout","");
        
    }

    
}