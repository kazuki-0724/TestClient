package entity;

import java.util.HashMap;

import control.ClientSystemControl;

/**
 * ゲームに関する情報を扱うクラス
 * @author Kazuki0724
 *このクラスはだいぶ変更ありそう
 *
 *
 */
public class GameInfo {
	
	
	private ClientSystemControl css;
	//ゲームに参加しているユーザのデータ
	private HashMap<String,Player> userMap = new HashMap<>();
	
	private String theme;
	
	private String resultString;
	
	
	public GameInfo(ClientSystemControl csc) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.css = css;
	}
	
	
	public void setUsers(HashMap<String, Player> userMap) {
		this.userMap = userMap;
	}
	
	
	//適宜変化するユーザデータのアップデートを行う
	public void updateUsersInfo() {
		
	}
	
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
	public String getTheme() {
		return this.theme;
	}
	
	
	public void setResult(String resultString) {
		this.resultString = resultString;
	}
	
	
	public String getResult() {
		return resultString;
	}
	

}
