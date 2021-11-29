package control;

import java.util.Timer;
import java.util.TimerTask;

import boundaries.Boundary;
import entity.Boundaries;
import entity.GameInfo;
import entity.Player;






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
	
	
	//3秒の制限時間
	private final int durationTime_3 = 3;
	//5秒の制限時間
	private final int durationTime_5 = 5;
	//10秒の制限時間
	private final int durationTime_10 = 10;
	//30秒の制限時間
	private final int durationTime_30 = 30;
	
	private int currentDurationTime = 0;
	
	
	
	private final int TIMER_TYPE_PAINTER = 0;
    private final int TIMER_TYPE_RESPONDER = 1;
    private final int TIMER_TYPE_CONFIRM = 2;
    private final int TIMER_TYPE_MATCH_MAKE = 3;
    
    
    
    private final int TIMER_DURATION_3 = 0;
    private final int TIMER_DURATION_5 = 1;
    private final int TIMER_DURATION_10 = 2;
    private final int TIMER_DURATION_30 = 3;
	
	
	
	/**
	 * コンストラクタ
	 * @param boundary
	 */
	public ClientControl(Boundary boundary) {
		
		this.boundary = boundary;
		this.cscc = new ClientCommunication(this); 
		this.gameInfo = new GameInfo();
			
	}
	
	
	
	/**
	 * 初期化処理実装予定
	 */
	public void init() {
		
	}
	
	
		
	/**
	 * タイマー計測開始
	 * @param type どの画面のカウントダウンなのか
	 * @param durationType カウントダウンの時間
	 */
	public void runTimer(int type, int durationTime) {
		
		System.out.println("タイマーカウント");
		
	
		
		     
		//スケジューラー
		timer = new Timer();
		
		//タイマータスク
		timerTask= new MyTimeTask(this, type, durationTime);
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
	 * データに基づいた処理
	 * @param dataFlag
	 * @param data
	 */
	public void handleData(String dataFlag, String data) {
		

    	switch(dataFlag) {
    	
    		
    		case "userData":	
    			//goLooby?とかにまとめる?
    			//本来はプレイヤーデータをサーバからもらったデータを基に復元
    			Player myPlayer = new Player("master1","1234",0,1,2);
    			setMyPlayer(myPlayer);
    			boundary.changePanel(Boundaries.LobbyBoundary);
    			break;
    			
    		case "match make":
    			boundary.changePanel(Boundaries.GameStartBoundary);
    			runTimer(TIMER_TYPE_MATCH_MAKE, TIMER_DURATION_5);
    			break;
    			   			
    		case "goConfirm":
    			System.out.println("theme is "+data);
    			getGameInfo().setTheme(data);
    			boundary.changePanel(Boundaries.ConfirmationBoundary);
    			runTimer(TIMER_TYPE_CONFIRM, TIMER_DURATION_5);
    			break;
    			
    		case "goPainter":
    			boundary.changePanel(Boundaries.PainterBoundary);
    			runTimer(TIMER_TYPE_PAINTER, TIMER_DURATION_30);
    			break;
    			
    		case "stroke":
    			boundary.updatePanel(Boundaries.RespondentBoundary, data);
    			break;
    		
    		case "goTurnResult":
    			getGameInfo().setResult(data);
    			boundary.changePanel(Boundaries.ResultBoundary);
    			break;
    			
    			
    		case "final result data":
    			boundary.changePanel(Boundaries.FinalResultBoundary);
    			break;
    			
    		case "back to lobby":
    			Player updateMyPlayer = new Player("master1","1234",0,1,2);
    			setMyPlayer(updateMyPlayer);
    			boundary.changePanel(Boundaries.LobbyBoundary);
    			break;		
    		
    	}
	}
	
	
	
	/**
	 * タイマーのスレッド
	 * @author Kazuki0724
	 *
	 */
	private class MyTimeTask extends TimerTask {
		
		private ClientControl control;
		private Boundaries panelType;
		private int durationTime;
		
		
		public MyTimeTask(ClientControl control, Boundaries panelType, int durationTime) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.control = control;
			this.panelType = panelType;
			this.durationTime = durationTime;
		}


		
		@Override
		public void run() {
			
			
			if (currentDurationTime >= 0) {
				
				boundary.updateCountDown(panelType, currentDurationTime+"");
					
				System.out.println(String.format("count : %2d", currentDurationTime) );
				currentDurationTime--;
				
				
			} else {
				timer.cancel();
				timer = null;
				boundary.updateCountDown(panelType, "time over");
			}
		}
		
		
	

	}
    
}