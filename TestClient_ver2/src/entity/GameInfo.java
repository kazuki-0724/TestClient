package entity;

/**
 * ゲームに関する情報を扱うクラス
 * @author Kazuki0724
 *このクラスはだいぶ変更ありそう
 *
 *
 */
public class GameInfo {


	//ゲームに参加しているユーザのデータ
	//private HashMap<String,Player> playerMap = new HashMap<>();

	private String theme;

	private String resultString;

	private String roomID;


	private String[] rankingData;

	/**
	 * コンストラクタ
	 * @param csc
	 */
	public GameInfo() {
		// TODO 自動生成されたコンストラクター・スタブ

	}




	public String getResultString() {
		return resultString;
	}




	public void setResultString(String resultString) {
		this.resultString = resultString;
	}




	public String[] getRankingData() {
		return rankingData;
	}




	public void setRankingData(String[] rankingData) {
		this.rankingData = rankingData;
	}




	public String getResult() {
		return resultString;
	}




	public void setResult(String resultString) {
		this.resultString = resultString;
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




	public String getRoomID() {
		return roomID;
	}




	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}




}
