package entity;
import java.util.List;

/**
 *
 * @author Kazuki0724
 *
 */
public class Message {


	/***************************************/
	//通信番号4

	//勝ち数
	private int numOfWin;
	//試合数
	private int numOfGame;
	//ランキングの文字配列
	private String[] rankingStrings;

	/**************************************/




	/*************************************/
	//通信番号7

	private List<PlayerMessage> joinPlayerList;
	/************************************/




	/************************************/
	//通信番号10

	//roomID
	private String roomID;
	//参加プレイヤーのリスト
	private List<PlayerMessage> gamePlayerList;

	/************************************/




	/************************************/
	//通信番号17

	//結果情報はgamePlayerListに埋め込んである

	/************************************/




	/************************************/
	//通信番号18
	//ここのプレイヤーは型を合わせておかないとだめじゃね?

	private List<PlayerMessage> playerList;

	private List<PlayerMessage> logoutList;
	/***********************************/








	//コンストラクタ
	public Message() {
			//全データの初期化があったほうがいい?


	}


	public int getNumOfWin() {
		return numOfWin;
	}
	public void setNumOfWin(int numOfWin) {
		this.numOfWin = numOfWin;
	}
	public int getNumOfGame() {
		return numOfGame;
	}
	public void setNumOfGame(int numOfGame) {
		this.numOfGame = numOfGame;
	}
	public String[] getRankingStrings() {
		return rankingStrings;
	}
	public void setRankingStrings(String[] rankingStrings) {
		this.rankingStrings = rankingStrings;
	}
	public List<PlayerMessage> getJoinPlayerList() {
		return joinPlayerList;
	}
	public void setJoinPlayerList(List<PlayerMessage> joinPlayerList) {
		this.joinPlayerList = joinPlayerList;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public List<PlayerMessage> getGamePlayerList() {
		return gamePlayerList;
	}
	public void setGamePlayerList(List<PlayerMessage> gamePlayerList) {
		this.gamePlayerList = gamePlayerList;
	}
	public List<PlayerMessage> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<PlayerMessage> playerList) {
		this.playerList = playerList;
	}
	public List<PlayerMessage> getLogoutList() {
		return logoutList;
	}
	public void setLogoutList(List<PlayerMessage> logoutList) {
		this.logoutList = logoutList;
	}





}
