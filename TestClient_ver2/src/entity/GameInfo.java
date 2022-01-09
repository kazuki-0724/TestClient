package entity;

import java.util.ArrayList;
import java.util.List;



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

	//ゲーム中に使う番号
	private String playerNum;

	private String painterPlayerNum;

	private String theme;

	private String[] resultString;

	private String roomID;

	private String[] rankingData;

	private List<PlayerMessage> gamePlayerList = new ArrayList<>();

	//private int painterNum;

	/**
	 * コンストラクタ
	 * @param csc
	 */
	public GameInfo() {
		// TODO 自動生成されたコンストラクター・スタブ

	}


	public void init() {
		this.gamePlayerList = null;
		this.painterPlayerNum = null;
		this.playerNum = null;
		this.rankingData = null;
		this.resultString = null;
		this.roomID = null;
		this.gamePlayerList = null;
	}



/*
	public int getPainterNum() {
		return painterNum;
	}


	public void setPainterNum(int painterNum) {
		this.painterNum = painterNum;
	}
*/

	public List<PlayerMessage> getGamePlayerList() {
		return gamePlayerList;
	}


	public void setGamePlayerList(List<PlayerMessage> gamePlayerList) {
		this.gamePlayerList = gamePlayerList;
	}


	public String getPainterPlayerNum() {
		return painterPlayerNum;
	}




	public void setPainterPlayerNum(String dates) {
		this.painterPlayerNum = dates;
	}




	public String getPlayerNum() {
		return playerNum;
	}




	public void setPlayerNum(String string) {
		this.playerNum = string;
	}




	public String[] getResultString() {
		return resultString;
	}




	public void setResultString(String[] resultString) {
		this.resultString = resultString;

		for(int i=0;i<resultString.length;i++) {

			String[] tmp = resultString[i].split(":");
			int playerNum = Integer.parseInt(tmp[0]);

		}


	}




	public String[] getRankingData() {
		return rankingData;
	}




	public void setRankingData(String[] rankingData) {
		this.rankingData = rankingData;
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
