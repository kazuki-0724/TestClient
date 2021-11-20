package control;

import java.util.Timer;
import java.util.TimerTask;

import boundaries.Boundary;
import entity.GameInfo;
import entity.Player;






/**
 *クライアントのコントロールクラス 
 * @author Kazuki0724
 *
 */
public class ClientSystemControl{
	
	
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
	private ClientSystemControlCommunication cscc;
	
	
	//3秒の制限時間
	private final int durationTime_3 = 3;
	//5秒の制限時間
	private final int durationTime_5 = 5;
	//10秒の制限時間
	private final int durationTime_10 = 10;
	//30秒の制限時間
	private final int durationTime_30 = 30;
	
	private int currentDurationTime = 0;
	
	
	
	/**
	 * コンストラクタ
	 * @param boundary
	 */
	public ClientSystemControl(Boundary boundary) {
		
		this.boundary = boundary;
		this.cscc = new ClientSystemControlCommunication(this); 
		this.gameInfo = new GameInfo(this);
			
	}
	

		
	/**
	 * getter
	 * @return
	 */
	public Boundary getBoundary() {
		return boundary;
	}
	
	
	
		
	/**
	 * タイマー計測開始
	 * @param type どの画面のカウントダウンなのか
	 * @param durationType カウントダウンの時間
	 */
	public void runTimer(int type, int durationType) {
		
		System.out.println("タイマーカウント");
		
		// どの制限時間なのか決定
		switch(durationType) {
			
			case 0:
				currentDurationTime = durationTime_3;
				break;
				
			case 1:
				currentDurationTime = durationTime_5;
				break;
			
			case 2:
				currentDurationTime = durationTime_10;
				break;
				
			case 3:
				currentDurationTime = durationTime_30;
				break;
				
			default:
				System.out.println("run time type error");
				break;
		}
		
		     
		//スケジューラー
		timer = new Timer();
		
		//タイマータスク
		timerTask= new MyTimeTask(this, type);
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
	public ClientSystemControlCommunication communicate() {
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
	 * タイマーのスレッド
	 * @author Kazuki0724
	 *
	 */
	private class MyTimeTask extends TimerTask {
		
		private ClientSystemControl control;
		private int type;
		
		
		public MyTimeTask(ClientSystemControl control, int type) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.control = control;
			this.type = type;
		}

		
		@Override
		public void run() {
			if (currentDurationTime >= 0) {
				
				control.getBoundary().updateCountDown(type, currentDurationTime+"");
				System.out.println(String.format("count : %2d", currentDurationTime) );
				currentDurationTime--;
				
				
			} else {
				timer.cancel();
				timer = null;
				control.getBoundary().updateCountDown(type, "Time Over!");
			}
		}

	}
    
}