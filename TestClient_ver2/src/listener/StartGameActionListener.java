package listener;

import java.awt.event.ActionEvent;

import boundaries.Boundary;
import boundaries.LobbyBoundary;
import entity.ProcessID;






/**
 * ロビーのゲーム開始ボタンのリスナー
 * @author Kazuki0724
 *
 */
public class StartGameActionListener extends BoundaryActionListener{


	//private ClientControl control;
    private Boundary boundary;
    private LobbyBoundary lobbyBoundary;


    /**
     * コンストラクタ
     * @param control
     * @param boundary
     * @param lobbyBoundary
     */
    public StartGameActionListener(LobbyBoundary lobbyBoundary, Boundary boundary) {

    	this.boundary = boundary;
    	this.lobbyBoundary = lobbyBoundary;
    }


    /**
     * イベント処理
     */
    public void actionPerformed(ActionEvent e){

        String str = e.getActionCommand();
		System.out.println("[ StartGameActionListener ] actionPerFormed() : Log StartGameButton Clicked");


        //サーバ側にマッチメイクに参加することを通知
        boundary.getControl().communicate().sendData(ProcessID.JOIN,"blank");

        lobbyBoundary.setMessageLabel("マッチング待機中");

        //ボタンを押せなくさせる
        lobbyBoundary.getStartGameButton().setEnabled(false);
        lobbyBoundary.getLogoutButton().setEnabled(false);


    }

}