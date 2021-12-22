package entity;


/**
 * ゲーム参加プレイヤー用のクラス
 * @author Kazuki0724
 *
 */
public class PlayerMessage {

	//ゲーム中でのプレイヤー番号
	private String playerNum;

	//プレイヤーID
	private String playerID;

	//プレイヤーレート(このレートはロビーでの表示でしか使わないからStringでもいい気はする)
	private String rate;

	//ターンポイント
	private int turnPoint;

	//トータルポイント
	private int totalPoint;



	public PlayerMessage(String playerNum, String playerID, String rate) {

		this.playerNum = playerNum;
		this.playerID = playerID;
		this.rate = rate;
	}



	public String getPlayerNum() {
		return playerNum;
	}



	public void setPlayerNum(String playerNum) {
		this.playerNum = playerNum;
	}



	public String getPlayerID() {
		return playerID;
	}



	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}



	public String getRate() {
		return rate;
	}



	public void setRate(String rate) {
		this.rate = rate;
	}



	public int getTurnPoint() {
		return turnPoint;
	}



	public void setTurnPoint(int turnPoint) {
		this.turnPoint = turnPoint;
	}



	public int getTotalPoint() {
		return totalPoint;
	}



	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}



}
