package entity;

import java.util.HashMap;

/**
 * ゲームに関する情報を扱うクラス
 * @author Kazuki0724
 *このクラスはだいぶ変更ありそう
 *
 *
 */
public class GameInfo {
	
	
	//ゲームに参加しているユーザのデータ
	private HashMap<String,Player> userMap = new HashMap<>();
	
	private String theme;
	
	private String resultString;
	
	
	/**
	 * コンストラクタ
	 * @param csc
	 */
	public GameInfo() {
		// TODO 自動生成されたコンストラクター・スタブ
	
	}
	
	/**
	 * 参加ユーザのデータをセットする
	 * @param userMap
	 */
	public void setUsers(HashMap<String, Player> userMap) {
		this.userMap = userMap;
	}
	
	
	/**
	 * 参加ユーザの情報のアップデート
	 */
	public void updateUsersInfo() {
		
	}
	
	/**
	 * テーマのセット
	 * @param theme
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	/**
	 * テーマのゲット。基本的にはテーマのデータをgameInfoからとってくる
	 * @return
	 */
	public String getTheme() {
		return this.theme;
	}
	
	/**
	 * 結果データのセット
	 * @param resultString
	 */
	public void setResult(String resultString) {
		this.resultString = resultString;
	}
	
	/**
	 * 結果データのゲット
	 * @return
	 */
	public String getResult() {
		return resultString;
	}
	

}
