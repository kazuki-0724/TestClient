package entity;


/**
 * ゲーム参加プレイヤー用のクラス
 * @author Kazuki0724
 *
 */
public class GamePlayer {

	//ゲーム中でのプレイヤー番号
	private int playerNum;

	//プレイヤーID
	private String id;

	//プレイヤーレート(このレートはロビーでの表示でしか使わないからStringでもいい気はする)
	private double rate;

	//ターンポイント
	private int turnPoint;

	//トータルポイント
	private int totalPoint;



	public GamePlayer(int playerNum, String id, double rate) {

		this.playerNum = playerNum;
		this.id = id;
		this.rate = rate;
	}



	public int getPlayerNum() {
		return playerNum;
	}



	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public double getRate() {
		return rate;
	}



	public void setRate(double rate) {
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
