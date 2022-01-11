package control;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

import boundaries.Boundary;
import entity.BoundaryID;
import entity.GameInfo;
import entity.Message;
import entity.Player;
import entity.PlayerMessage;
import entity.ProcessID;






/**
 *クライアントのコントロールクラス
 * @author Kazuki0724
 * 2022年1月11日
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

	//通信用
	private ClientCommunication cscc;

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
		this.gameInfo.init();
		init();
		//cscc.connect(CLM);

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

		stopTimer();//

		//スケジューラー
		timer = new Timer();

		//タイマータスク
		timerTask= new ClientTimerTask(this,boundary, type, time);
		timer.schedule(timerTask, 0l, 1000l);


		System.out.println("[ ClientControl ] runTimer() : Log タイマー開始");

	}




	/**
	 * タイマーの停止
	 */
	public void stopTimer() {

		if(timer != null) {
			timer.cancel();
			timer = null;
			System.out.println("[ ClientControl ] stopTimer() : Log タイマー停止");

		}else {
			System.out.println("[ ClientControl ] stopTimer() : Error 起動しているタイマーが存在しない");
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


		//第二ヘッダとデータ部のString型データのsplit用
		String[] dates = {"blank"};
		String secDataFlag = "blank";


		if(data.contains("FINISHGAME")) {
			//REQUEST#FINISHGAMEはsplitErrorになってしまうから
			System.out.println("[ ClientControl ] handleData() : Log FINISHGAME受信");
			secDataFlag = "FINISHGAME";

		}else if(data.contains("TIMEOUT")) {
			//REQUEST#FINISHGAMEはsplitErrorになってしまうから
			System.out.println("[ ClientControl ] handleData() : Log TIMEOUT受信");
			secDataFlag = "TIMEOUT";

		}else {

			try {

				dates = data.split("_");
				secDataFlag = dates[0];

			}catch(Exception e) {

				//スプリットエラーが出た場合の処理
				e.printStackTrace();
				System.out.println("[ ClientControl ] handleData() : Error スプリットエラー");

				//(拡張用)スプリットエラーが出たからもう一回遅れの指示を送るかも
				//communicate().sendData(null, data);

				return;

			}
		}





    	switch(dataFlag) {


    		//第1ヘッダのswitch
    		case "REPLY":
    			System.out.println("[ ClientControl ] handleData() : Log REPLY case");


    			//第2ヘッダのswitch
    			switch(secDataFlag) {

    				case "LOGIN":
	    				if(dates[1].equals("OK")) {

	    					//idの一時保存
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
    						getGameInfo().init();
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

    						//JSONをmessageクラスに復元
    						Message message = gson.fromJson(dates[2], Message.class);

    						//String[]型のランキングデータの取り出し、gameInfoにセットする
    						String[] rankingData = message.getRankingStrings();
    						getGameInfo().setRankingData(rankingData);

    						//Playerデータの復元、Player情報のセット
    						Player myPlayer = new Player(id,message.getNumOfWin(),message.getNumOfGame());
    						setMyPlayer(myPlayer);


    						boundary.changePanel(BoundaryID.LobbyBoundary);
    					}

    					break;


    				case "JOIN":
    					if(dates[1].equals("OK")) {

    						//communicate().connect(AP);
    						//communicate().sendData(ProcessID.CONNECTAP,getMyPlayer().getId());

    					}
    					break;

    				case "CONNECTCLM":
    					//接続ok。本当は後ろにOKが続いてる
    					System.out.println("[ ClientControl ] handleData() : Log Connect CLM complete");

    					break;

    				case "CONNECTAP":
    					//接続ok。本当は後ろにOKが続いてる
    					System.out.println("[ ClientControl ] handleData() : Log Connect AP complete");

    					break;

    				case "DISCONNECT_CLM":
    					communicate().disconnect(CLM);
    					break;

    				case "DISCONNECT_AP":
    					communicate().disconnect(AP);

    					break;


    				default:
        				System.out.println("[ ClientControl ] handleData() : Error REPLY/2ndDataFlag type Error");
        				break;

	    			}


    			break;



    		case "POSITION":
    			//座標情報に基づいて描画する
    			//POSITION#0_0_100_100_200_200
    			//POSITION#(ペン/消しゴム)_(開始座標x)_(開始座標y)_(終了座標x)_(終了座標y)

    			boundary.updatePanel(BoundaryID.RespondentBoundary, data);
    			break;




    		case "REQUEST":
    			System.out.println("[ ClientControl ] handleData() : Log REQUEST case");

    			int limitTime = 0;

    			switch(secDataFlag) {


    				case "CONNECTTOAP":
    					//CLMからAPに接続城っていう指令が来る
    					communicate().connect(AP);
    					//communicate().sendData(ProcessID.CONNECTAP,getMyPlayer().getId());
    					break;


    				case "STARTGAME":

    					//REQUEST#STARTGAME_(制限時間)_JSON
    					limitTime = Integer.parseInt(dates[1]);

	    				//JSONからの復元
    					Message message = gson.fromJson(dates[2], Message.class);

    					//gamePlayerListの取り出し、rommIDの取り出しとgameInfoへのセット
    					List<PlayerMessage> gamePlayerList = message.getGamePlayerList();
						String roomId = message.getRoomID();


						for(PlayerMessage tmp:gamePlayerList) {
							if(myPlayer.getId().equals(tmp.getPlayerID())) {
								getGameInfo().setPlayerNum(tmp.getPlayerNum());
								System.out.println(tmp.getPlayerID() +" "+ tmp.getPlayerNum());

							}
						}


						getGameInfo().setGamePlayerList(gamePlayerList);
						getGameInfo().setRoomID(roomId);


						//ゲーム開始前処理が正常に行えたことを通知する
						communicate().sendData(ProcessID.STARTGAME_OK, "blank");

						//画面遷移
						boundary.changePanel(BoundaryID.GameStartBoundary);

						//タイマバーの幅を調整
						boundary.setTimerBar(BoundaryID.GameStartBoundary, limitTime);

						//タイマーの起動
						runTimer(BoundaryID.GameStartBoundary,limitTime);

						break;



    				case "STARTTURN":

    					//REQUEST#STARTTURN_(制限時間)_(出題者プレイヤーNum)_(お題)

    					limitTime = Integer.parseInt(dates[1]);

    					//出題者プレイヤーのゲーム内番号とテーマの取り出し
    					//int painterPlayerNum = Integer.parseInt(dates[2]);
    					String theme = dates[3];


    					//それらのgameInfoへのセット
    					getGameInfo().setPainterPlayerNum(dates[2]);
    					getGameInfo().setTheme(theme);

    					System.out.println("[ ClientControl ] handleData() : Log painterNum/theme/myplayerNum "+ dates[2] + "/" + theme + " "+ getGameInfo().getPlayerNum());


    					if(getGameInfo().getPlayerNum().equals(getGameInfo().getPainterPlayerNum()) ) {
    						//自分が出題者だった場合
    						boundary.changePanel(BoundaryID.ConfirmationBoundary);
    						boundary.setTimerBar(BoundaryID.ConfirmationBoundary, limitTime);
    						runTimer(BoundaryID.ConfirmationBoundary,limitTime);

    					}else {
    						//自分が解答者だった場合
    						boundary.changePanel(BoundaryID.WaitingTimeBoundary);
    						boundary.setTimerBar(BoundaryID.WaitingTimeBoundary, limitTime);
    						runTimer(BoundaryID.WaitingTimeBoundary,limitTime);
    					}

    					communicate().sendData(ProcessID.STARTTURN_OK, "blank");
    					break;



    				case "QTIMESTART":

    					//制限時間の取り出し
    					limitTime = Integer.parseInt(dates[1]);

    					if(getGameInfo().getPlayerNum().equals(getGameInfo().getPainterPlayerNum()) ) {
    						//自分が出題者だった場合
    						boundary.changePanel(BoundaryID.PainterBoundary);
    						boundary.setTimerBar(BoundaryID.PainterBoundary, limitTime);
    						runTimer(BoundaryID.PainterBoundary,limitTime);

    					}else {
    						//自分が解答者だった場合
    						boundary.changePanel(BoundaryID.RespondentBoundary);
    						boundary.setTimerBar(BoundaryID.RespondentBoundary, limitTime);
    						runTimer(BoundaryID.RespondentBoundary,limitTime);
    					}

    					communicate().sendData(ProcessID.QTIMESTART_OK, "");

    					break;


    				case "ANSWERER":

    					//REQUEST#ANSWERER_(プレイヤーNum)

    					/*******************************************************/
    					//正解者のプレイヤーNum
    					int correctPlayerNum = Integer.parseInt(dates[1]);

    					//correctPlayerNumを使ってPaintrer/RespondenrBoundaryの表示を変える
    					boundary.setCorrectPlayer(correctPlayerNum);

    					communicate().sendData(ProcessID.ANSWER_OK, "blank");

    					/*****************************************************/

    					break;


    				case "TURNRESULT":

    					//REQUEST#TURNRESULT_(制限時間)_JSON

    					//制限時間の取り出し
    					limitTime = Integer.parseInt(dates[1]);

    					//JSONからMessageに復元
    					Message result = gson.fromJson(dates[2], Message.class);

    					//結果が入ったプレイヤーリストの取り出し
    					List<PlayerMessage> resultGamePlayerList = result.getGamePlayerList();
    					getGameInfo().setGamePlayerList(resultGamePlayerList);


    					boundary.changePanel(BoundaryID.ResultBoundary);
    					boundary.setTimerBar(BoundaryID.ResultBoundary, limitTime);
    					runTimer(BoundaryID.ResultBoundary,limitTime);

    					communicate().sendData(ProcessID.TURNRESULT_OK,"blank");
    					break;



    				case "FINISHGAME":
    					//finalResultへの遷移
    					boundary.changePanel(BoundaryID.FinalResultBoundary);
    					//boundary.setTimerBar(BoundaryID.FinalResultBoundary, 60);

    					//裏で動かすタイマー60秒
    					runTimer(BoundaryID.FinalResultBoundary,60);
    					communicate().sendData(ProcessID.FINISHGAME_OK, secDataFlag);



    					/*非常に怪しい*******************/
    					communicate().disconnect(AP);
    					/********************************/

    					break;


    				case "TIMEOUT":
    					//ログアウト処理を入れる
    					//ログアウト処理
						System.out.println("[ ClientControl ] handleData() : Log REQUEST/TIMEOUT case");
						getMyPlayer().init();
						getGameInfo().init();

						communicate().sendData(ProcessID.TIMEOUT_OK, "blank");
						boundary.changePanel(BoundaryID.AccountAuthentificationBoudary);

    					break;







    				default:
        				System.out.println("[ ClientControl ] handleData() : Error REQUEST/2ndDataFlag type Error");
        				break;
    			}


    			break;

    		default:
    			System.out.println("[ ClientControl ] handleData() : Error dataFlag type Error");
    			break;




    	}
	}



	/**
	 * タイマーのスレッド
	 * @author Kazuki0724
	 *
	 */
	private class ClientTimerTask extends TimerTask {

		private ClientControl control;
		private Boundary boundary;
		private BoundaryID type;
		private int time;
		private int limitTime;


		public ClientTimerTask(ClientControl control,Boundary boundary, BoundaryID type, int time) {
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
				//control.communicate().sendData(ProcessID.TIMEOVER, type.toString());

				//最終結果画面にいるときは裏で60秒カウントする
				if(type == BoundaryID.FinalResultBoundary) {

					control.communicate().sendData(ProcessID.MAKELOBBY, getMyPlayer().getId());
				}

			}
		}

	}

}