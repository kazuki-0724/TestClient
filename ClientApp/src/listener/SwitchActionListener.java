package listener;


import java.awt.event.ActionEvent;

import boundaries.AccountAuthentificationBoundary;
import boundaries.AccountRegistrationBoundary;
import boundaries.Boundary;
import entity.BoundaryID;




/**
 * ログインと新規登録の画面を行き来するリスナー
 * @author Kazuki0724
 *
 */

public class SwitchActionListener extends BoundaryActionListener{


	private AccountRegistrationBoundary arb;
	private AccountAuthentificationBoundary aab;
	private Boundary boundary;
	//private ClientControl control;



	/**
	 * 新規登録用のコンストラクタ
	 * @param arb
	 * @param boundary
	 * @param control
	 */
	public SwitchActionListener(AccountRegistrationBoundary arb, AccountAuthentificationBoundary aab,Boundary boundary){
	        this.arb = arb;
	        this.aab = aab;
	        this.boundary = boundary;
	        //this.control = control;
	}



	/**
	 * イベント処理
	 */
	public void actionPerformed(ActionEvent e){


		System.out.println("[ SwitchActionListener ] actionPerFormed() : Log SwitchButton Clicked");
		String type = e.getActionCommand();

		System.out.println("[ LogoutActionListener ] actionPerFormed() : Log Button type = "+ type);

		if(type.equals("goLogin")) {
			boundary.changePanel(BoundaryID.AccountAuthentificationBoudary);

		}else if(type.equals("goResist")) {
			boundary.changePanel(BoundaryID.AccountRegistrationBoundary);

		}else {
			System.out.println("[ LogoutActionListener ] actionPerFormed() : Eror Button type error");
		}



	}

}
