package control;

import java.util.Timer;
import java.util.TimerTask;

import boundaries.Boundary;
import entity.BoundaryID;
import entity.GameInfo;
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
		cscc.connect();
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
	
	
	
	public void handleData(String dataFlag, String data) {
		

    	switch(dataFlag) {
    	
    		case "userData":			
    			Player myPlayer = new Player("master1","1234",0,1,2);
    			setMyPlayer(myPlayer);
    			boundary.changePanel(BoundaryID.LobbyBoundary);
    			break;
    			
    		case "match make":
    			boundary.changePanel(BoundaryID.GameStartBoundary);
    			runTimer(BoundaryID.GameStartBoundary, 5);
    			break;
    			   			
    		case "goConfirm":
    			System.out.println("theme is "+data);
    			getGameInfo().setTheme(data);
    			boundary.changePanel(BoundaryID.ConfirmationBoundary);
    			runTimer(BoundaryID.ConfirmationBoundary, 5);
    			break;
    			
    		case "goPainter":
    			boundary.changePanel(BoundaryID.PainterBoundary);
    			runTimer(BoundaryID.PainterBoundary, 30);
    			break;
    			
    		case "stroke":
    			boundary.updatePanel(BoundaryID.RespondentBoundary, data);
    			break;
    		
    		case "goTurnResult":
    			getGameInfo().setResult(data);
    			boundary.changePanel(BoundaryID.ResultBoundary);
    			break;
    			
    			
    		case "final result data":
    			boundary.changePanel(BoundaryID.FinalResultBoundary);
    			break;
    			
    		case "back to lobby":
    			Player updateMyPlayer = new Player("master1","1234",0,1,2);
    			setMyPlayer(updateMyPlayer);
    			boundary.changePanel(BoundaryID.LobbyBoundary);
    			break;		
    			
    		case "LOGIN":
    			if(data.equals("OK")) {
    				communicate().sendData(ProcessID.MAKELOBBY, "master1");
    			}else {
    				
    			}
    			break;
    			
    		case "LOBBY":
    			Player player = new Player("master1", "1234", 5, 5, 10);
    			setMyPlayer(player);
    			boundary.changePanel(BoundaryID.LobbyBoundary);
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
		
		
		public MyTimerTask(ClientControl control,Boundary boundary, BoundaryID type, int time) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.control = control;
			this.boundary = boundary;
			this.type = type;
			this.time = time;
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
				control.communicate().sendData(ProcessID.TIMEOVER, "blank");
			}
		}

	}
    
}