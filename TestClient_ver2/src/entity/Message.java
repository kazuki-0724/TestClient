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
	private String[] id_rateStrings;

	/************************************/




	/************************************/
	//通信番号10

	//roomID
	private String roomID;
	//参加プレイヤーのリスト
	private List<GamePlayer> gamePlayerList;

	/************************************/




	/************************************/
	//通信番号17

	//結果情報はgamePlayerListに埋め込んである

	/************************************/




	/************************************/
	//通信番号18
	//ここのプレイヤーは型を合わせておかないとだめじゃね?

	//private List<Player> playerList;

	//private List<Player> logoutList;
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

	public String[] getId_rateStrings() {
		return id_rateStrings;
	}

	public void setId_rateStrings(String[] id_rateStrings) {
		this.id_rateStrings = id_rateStrings;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public List<GamePlayer> getGamePlayerList() {
		return gamePlayerList;
	}

	public void setGamePlayerList(List<GamePlayer> gamePlayerList) {
		this.gamePlayerList = gamePlayerList;
	}


}
