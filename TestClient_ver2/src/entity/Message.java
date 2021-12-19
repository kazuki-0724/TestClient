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

	//private String playerID;

	//private int numOfWin;

	//private int numOfLose;

	//private int numOfGame;

	private String[] rankingStrings;

	/**************************************/


	/*************************************/
	//通信番号7
	private String[] id_rateStrings;

	/************************************/


	/************************************/
	//通信番号10
	private String rommID;

	private List<GamePlayer> gamePlayerList;


	/************************************/


	/************************************/
	//通信番号17
	private String[] turnResultString;

	/************************************/


	/************************************/
	//通信番号18
	private List<Player> playerList;

	private List<Player> logoutList;
	/***********************************/




	//コンストラクタ
	public Message() {
			//全データの初期化があったほうがいい?
	}








	public List<GamePlayer> getGamePlayerList() {
		return gamePlayerList;
	}



	public void setGamePlayerList(List<GamePlayer> gamePlayerList) {
		this.gamePlayerList = gamePlayerList;
	}




	public String[] getTurnResultString() {
		return turnResultString;
	}



	public void setTurnResultString(String[] turnResultString) {
		this.turnResultString = turnResultString;
	}



	public List<Player> getPlayerList() {
		return playerList;
	}



	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}



	public List<Player> getLogoutList() {
		return logoutList;
	}



	public void setLogoutList(List<Player> logoutList) {
		this.logoutList = logoutList;
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



	public String getRommID() {
		return rommID;
	}



	public void setRommID(String rommID) {
		this.rommID = rommID;
	}




}