package listener;

import java.awt.event.ActionEvent;

import boundaries.Boundary;
import boundaries.LobbyBoundary;
import control.ClientSystemControl;






/**
 * ロビーのゲーム開始ボタンのリスナー
 * @author Kazuki0724
 *
 */
public class StartGameActionListener extends BoundaryActionListener{
    

	private ClientSystemControl control;
    private Boundary boundary;
    private LobbyBoundary lobbyBoundary;
    
    
    /**
     * コンストラクタ
     * @param control
     * @param boundary
     * @param lobbyBoundary
     */
    public StartGameActionListener(ClientSystemControl control, Boundary boundary, LobbyBoundary lobbyBoundary) {
    	
    	this.control = control;
    	this.boundary = boundary;
    	this.lobbyBoundary = lobbyBoundary;
    }
    
    
    /**
     * イベント処理
     */
    public void actionPerformed(ActionEvent e){
        
        String str = e.getActionCommand();
        System.out.println("[Log] Start Game Button Clicked");
        
        
        //サーバ側にマッチメイクに参加することを通知
        control.communicate().sendData("match make","");
      
    
    }
    
}