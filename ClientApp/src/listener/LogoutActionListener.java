package listener;


import java.awt.event.ActionEvent;

import boundaries.Boundary;
import boundaries.LobbyBoundary;
import entity.ProcessID;




/**
 * ログアウトボタンのアクションリスナー
 * @author Kazuki0724
 *
 */
public class LogoutActionListener extends BoundaryActionListener{


	//private ClientControl control;
    private Boundary boundary;
    LobbyBoundary lobbyBoundary;


    /**
     * コンストラクタ
     * @param control
     * @param boundary
     */
    public LogoutActionListener(LobbyBoundary lobbyBoundary, Boundary boundary) {

    	//this.control = control;
    	this.boundary = boundary;
    	this.lobbyBoundary = lobbyBoundary;

    }

    /**
	 * イベント処理
	 */
    public void actionPerformed(ActionEvent e){

        String str = e.getActionCommand();
		System.out.println("[ LogoutActionListener ] actionPerFormed() : Log LogoutButton Clicked");

        //サーバ側にログアウトを通知
        boundary.getControl().communicate().sendData(ProcessID.LOGOUT,"blank");

    }


}