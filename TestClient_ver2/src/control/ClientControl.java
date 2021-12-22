package control;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

import boundaries.Boundary;
import entity.BoundaryID;
import entity.GameInfo;
import entity.GamePlayer;
import entity.Message;
import entity.Player;
import entity.ProcessID;






/**
 *クライアントのコントロールクラス
 * @author Kazuki0724
 *
 */
public class ClientControl{


	//ユーザデータ
	private Player myPlayer;

	private String id;

	//
	private GameInfo gameInfo;

	//メイン画面
	private Boundary boundary;

	//スケジューラー
	private Timer timer;

	//タイマーによる処理
	private TimerTask timerTask;

	//タイマー開始時間
	private Long startTime;

	//通信用
	private ClientCommunication cscc;



	private int currentDurationTime = 0;


	private final int CLM = 0;

	private final int AP = 1;

	//JSON用
	private Gson gson = new Gson();

	/**
	 * コンストラクタ
	 * @param boundary
	 */
	public ClientControl(Boundary boundary) {

		this.boundary = boundary;
		this.cscc = new ClientCommunication(this);
		this.gameInfo = new GameInfo();
		init();
	}


	/**
	 * 初期化処理
	 */
	public void init() {
		cscc.connect(CLM);
	}



	/**
	 * タイマー計測開始
	 * @param type どの画面のカウントダウンなのか
	 * @param durationType カウントダウンの時間
	 */
	public void runTimer(BoundaryID type, int time) {

		System.out.println("タイマーカウント");


		//スケジューラー
		timer = new Timer();

		//タイマータスク
		timerTask= new MyTimerTask(this,boundary, type, time);
		timer.schedule(timerTask, 0l, 1000l);


		System.out.println("タスクスタート");

	}




	/**
	 * タイマーの停止
	 */
	public void stopTimer() {

		if(timer != null) {
			timer.cancel();
			timer = null;
		}else {
			System.out.println("[Error] timer is not exsit");
		}
	}






	/**
	 * アクションリスナーから送信をできるようにするためのメソッド
	 * 実体はただのgetter
	 * @return
	 */
	public ClientCommunication communicate() {
		return this.cscc;
	}





	/**
	 * 自分のユーザ情報のsetter
	 * @param myUser
	 */
	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}



	/**
	 * 自分のユーザ情報のgetter
	 * @return
	 */
	public Player getMyPlayer() {
		return this.myPlayer;
	}



	/**
	 * ゲームの状況のgetter
	 * @return
	 */
	public GameInfo getGameInfo() {
		return gameInfo;
	}



	/**
	 * ゲームの状況のsetter
	 */
	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}







	/**
	 *受信解析からの流れ
	 * @param dataFlag #前の第ヘッダ
	 * @param data #以降の部分
	 */
	public void handleData(String dataFlag, String data) {


    	switch(dataFlag) {


    		//第1ヘッダのswitch
    		case "REPLY":
    			System.out.println("[Log handleData() REPLY switch] data "+ data);

    			String[] dates = data.split("_");

    			//第2ヘッダのswitch
    			switch(dates[0]) {

    				case "LOGIN":
	    				if(dates[1].equals("OK")) {

	    					//Player myPlayer = new Player(dates[2],Integer.parseInt(dates[3]),Integer.parseInt(dates[4]));
	    					//setMyPlayer(myPlayer);
	    					id = dates[2];
	    					communicate().sendData(ProcessID.MAKELOBBY, dates[2]);

	    				}else if(dates[1].equals("IDERROR")) {
	    					boundary.updatePanel(BoundaryID.AccountAuthentificationBoudary,"IDエラー");

	    				}else if(dates[1].equals("PWERROR")) {
	    					boundary.updatePanel(BoundaryID.AccountAuthentificationBoudary,"パスワードエラー");

	    				}else if(dates[1].equals("DONEERROR")) {
	    					boundary.updatePanel(BoundaryID.AccountAuthentificationBoudary,"既にログイン済みです");
	    				}

	    				break;

    				case "LOGOUT":
    					if(dates[1].equals("OK")) {
    						//ログアウト処理
    						System.out.println("[Log] LOGOUT");
    						getMyPlayer().init();
    						boundary.changePanel(BoundaryID.AccountAuthentificationBoudary);

    					}
    					break;


    				case "REGISTER":
	    				if(dates[1].equals("OK")) {
	    					//新規登録が完了したらログイン画面に遷移
	    					boundary.changePanel(BoundaryID.AccountAuthentificationBoudary);

	    				}else if(dates[1].equals("IDNG")) {
	    					boundary.updatePanel(BoundaryID.AccountRegistrationBoundary, "既に登録済みのIDです");
	    				}

    					break;


    				case "MAKELOBBY":
    					if(dates[1].equals("OK")) {
    						//dates[2]をJSON処理
    						Message message = gson.fromJson(dates[2], Message.class);
    						String[] rankingData = message.getRankingStrings();
    						getGameInfo().setRankingData(rankingData);

    						Player myPlayer = new Player(id,message.getNumOfWin(),message.getNumOfGame());
    						setMyPlayer(myPlayer);


    						boundary.changePanel(BoundaryID.LobbyBoundary);
    					}

    					break;

    				case "JOIN":
    					if(dates[1].equals("OK")) {
    						//APに接続要求を飛ばす

    						//本当はここでAPへの接続処理をやるはず
    						//テスト段階では面倒くさいから同じサーバでやってるだけ
    						//communicate().connect(AP);
    						communicate().sendData(ProcessID.CONNECTAP,getMyPlayer().getId());


    					}
    					break;

    				case "CONNECTCLM":
    					//接続ok。本当は後ろにOKが続いてる
    					System.out.println("[Log ] Connect CLM complete");
    					break;

    				case "CONNECTAP":
    					//接続ok。本当は後ろにOKが続いてる
    					System.out.println("[Log ] Connect AP complete");
    					break;

	    			}
    			break;


    		case "POSITION":
    			//座標情報に基づいて描画する
    			boundary.updatePanel(BoundaryID.RespondentBoundary, data);
    			break;


    		case "REQUEST":
    			System.out.println("[Log handleData() REQUEST switch] data "+ data);

    			String[] dates2 = data.split("_");

    			switch(dates2[0]) {

    				case "STARTGAME":
	    				//dates2[1]についてJSON処理
    					Message message = gson.fromJson(dates2[1], Message.class);
						List<GamePlayer> gamePlayerList = message.getGamePlayerList();
						String roomId = message.getRoomID();

						getGameInfo().setGamePlayerList(gamePlayerList);
						getGameInfo().setRoomID(roomId);

						/**********************************************************/

						communicate().sendData(ProcessID.STARTGAME_OK, "blank");
						/*********************************************************/

						boundary.changePanel(BoundaryID.GameStartBoundary);
						runTimer(BoundaryID.GameStartBoundary,10);
	    				break;



    				case "STARTTURN":
    					//出題者プレイヤーNum
    					int painterPlayerNum = Integer.parseInt(dates2[1]);
    					//テーマ
    					String theme = dates2[2];

    					getGameInfo().setPainterPlayerNum(painterPlayerNum);
    					getGameInfo().setTheme(theme);

    					if(getGameInfo().getPlayerNum() == getGameInfo().getPainterPlayerNum()) {
    						boundary.changePanel(BoundaryID.ConfirmationBoundary);
    						runTimer(BoundaryID.ConfirmationBoundary,10);
    					}else {
    						boundary.changePanel(BoundaryID.WaitingTimeBoundary);
    						runTimer(BoundaryID.WaitingTimeBoundary,10);
    					}

    					communicate().sendData(ProcessID.STARTTURN_OK, "blank");
    					break;


    				case "QTIMESTART":
    					//制限時間
    					int limitTime = Integer.parseInt(dates2[1]);
    					if(getGameInfo().getPlayerNum() == getGameInfo().getPainterPlayerNum()) {
    						boundary.changePanel(BoundaryID.PainterBoundary);
    						runTimer(BoundaryID.PainterBoundary,limitTime);
    					}else {
    						boundary.changePanel(BoundaryID.RespondentBoundary);
    						runTimer(BoundaryID.RespondentBoundary,limitTime);
    					}


    					break;

    				case "ANSWERER":
    					//正解者のプレイヤーNum
    					int correctPlayerNum = Integer.parseInt(dates2[1]);
    					break;

    				case "TURNRESULT":
    					//結果情報をJSONで扱う
    					Message result = gson.fromJson(dates2[1], Message.class);
    					List<GamePlayer> resultGamePlayerList = result.getGamePlayerList();

    					getGameInfo().setGamePlayerList(resultGamePlayerList);


    					boundary.changePanel(BoundaryID.ResultBoundary);
    					runTimer(BoundaryID.ResultBoundary,10);
    					break;

    				case "FINISHGAME":
    					//勝者プレイヤー
    					String wonPlayer = dates2[1];
    					break;


    			}
    			break;

    		default:
    			System.out.println("[Log handleData()] type error");
    			break;




    	}
	}



	/**
	 * タイマーのスレッド
	 * @author Kazuki0724
	 *
	 */
	private class MyTimerTask extends TimerTask {

		private ClientControl control;
		private Boundary boundary;
		private BoundaryID type;
		private int time;
		private int limitTime;


		public MyTimerTask(ClientControl control,Boundary boundary, BoundaryID type, int time) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.control = control;
			this.boundary = boundary;
			this.type = type;
			this.time = time;
			this.limitTime = time;

		}


		@Override
		public void run() {
			if (time >= 0) {

				boundary.updateCountDown(type, time+"");
				System.out.println(String.format("count : %2d", time) );
				time--;


			} else {
				timer.cancel();
				timer = null;
				boundary.updateCountDown(type, "Time Over!");
				//時間制限が終了した画面の情報も送る
				control.communicate().sendData(ProcessID.TIMEOVER, type.toString());
			}
		}

	}

}