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
	private HashMap<String,Player> playerMap = new HashMap<>();
	
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
	public void setPlayers(HashMap<String, Player> playerMap) {
		this.playerMap = playerMap;
	}
	
	
	/**
	 * 参加ユーザの情報のアップデート
	 */
	public void updatePlayerInfo() {
		
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
