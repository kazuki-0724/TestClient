package control;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import entity.Boundaries;
import entity.Player;






/**
 * 通信用のクラス
 * @author Kazuki0724
 *
 */
public class ClientSystemControlCommunication{

	
    private ClientSystemControl control;
    private boolean isConnection;
    
    private WebSocketClient webSocketClient;
	private Session session; 
    
    
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
     * @param control
     */
    public ClientSystemControlCommunication(ClientSystemControl control){
        this.control = control;
        isConnection = false;
        this.webSocketClient = new WebSocketClient(this);
        
        connect();
    }


    
    
    /**
     * データ送信メソッド。送信前に一度送信形式に変換する。
     * @param dataFlag
     * @param data
     */
    public void sendData(String dataFlag, String data){

    	
    	String communicationFormat = handleSendData(dataFlag,data);

    	
    	System.out.println("[Log] send data [" + dataFlag + "] "+ communicationFormat);
    	
    	webSocketClient.sendMessage(communicationFormat);
    	
    }


    

    /**
     * 送信データを通信形式に合わせる。実際の通信形式は未確定
     * @param dataFlag
     * @param data
     * @return
     */
    public String handleSendData(String dataFlag, String data) {
    	
    	String json = "";
    	
    	switch(dataFlag) {
    		
    		case "login":
    			json = String.format("%s#%s",dataFlag,data);
    			break;
    			
    		case "regist":
    			json = String.format("%s#%s",dataFlag,data);
    			break;
    			
    		case "match make":
    			json = String.format("%s#%s",dataFlag,"blank");
    			break;
    			
    		case "confirm":
    			json = String.format("%s#%s",dataFlag,"blank");
    			break;
    		
    		case "game start":
    			json = String.format("%s#%s",dataFlag,"blank");
    			break;
    			
    		case "stroke":
    			json = String.format("%s#%s",dataFlag,data);
    			break;
    			
    		case "turn result":
    			json = String.format("%s#%s",dataFlag,"blank");
    			break;
    			
    			
    		case "final result":
    			json = String.format("%s#%s",dataFlag,"blank");
    			break;
    			
    		case "back to lobby":
    			json = String.format("%s#%s",dataFlag,"blank");
    			break;
    			
    		default:
    			System.out.println("[Error] handleSendData dataFlag error");
    			break;
    	
    	}
    	
    	
    	
    	return json; 
    }
    
    
    
    
    
    /**
     * WebSocketServerのOnMessageで受信したらこのメソッド
     * @param message
     */
    public void receivedData(String message){
    	
    	System.out.println("[Log] received Data "+message);
    	//イメージ的にはこの感じ
    	handleReceivedData(message);
    	
    }
    
    
    
    
    /**
     * 受け取ったデータの解析。実際のデータの形の未定だからこれも要変更
     * @param json
     */
    public void handleReceivedData(String json) {
    	
    	
    	String dataFlag;
    	String data;
    	
    	String[] tmpString = json.split("#");
    	
    	dataFlag = tmpString[0];
    	data = tmpString[1];
    	
    	
    	switch(dataFlag) {
    	
    		case "userData":			
    			Player myPlayer = new Player("master1","1234",0,1,2);
    			control.setMyPlayer(myPlayer);
    			control.getBoundary().changePanel(Boundaries.LobbyBoundary);
    			break;
    			
    		case "match make":
    			control.getBoundary().changePanel(Boundaries.PlayerListBoundary);
    			control.runTimer(TIMER_TYPE_MATCH_MAKE, TIMER_DURATION_5);
    			break;
    			   			
    		case "goConfirm":
    			System.out.println("theme is "+data);
    			control.getGameInfo().setTheme(data);
    			control.getBoundary().changePanel(Boundaries.ConfirmationBoundary);
    			control.runTimer(TIMER_TYPE_CONFIRM, TIMER_DURATION_5);
    			
    			break;
    			
    		case "goPainter":
    			control.getBoundary().changePanel(Boundaries.PainterBoundary);
    			control.runTimer(TIMER_TYPE_PAINTER, TIMER_DURATION_30);
    			break;
    			
    		case "stroke":
    			control.getBoundary().updatePanel(Boundaries.RespondentBoundary, data);
    			break;
    		
    		case "goTurnResult":
    			control.getGameInfo().setResult(data);
    			control.getBoundary().changePanel(Boundaries.ResultBoundary);
    			break;
    			
    			
    		case "final result data":
    			control.getBoundary().changePanel(Boundaries.FinalResultBoundary);
    			break;
    			
    		case "back to lobby":
    			Player updateMyPlayer = new Player("master1","1234",0,1,2);
    			control.setMyPlayer(updateMyPlayer);
    			control.getBoundary().changePanel(Boundaries.LobbyBoundary);
    			break;		
    		
    	}
    	
    	
    }
    
    
    
    
    


    /**
     * サーバに接続
     */
    public void connect(){
    	
    	// 初期化のためWebSocketコンテナのオブジェクトを取得する
    	WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    			
   		// サーバー・エンドポイントのURI
    	URI uri = URI.create("ws://localhost:8080/TestServer/WebSocketServer");
    		
    	
    	// サーバー・エンドポイントとのセッションを確立する
    	try {
			session = container.connectToServer(webSocketClient,uri);
		} catch (DeploymentException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	
    	
    	//メッセージをサーバへ送る
    	try {
			session.getBasicRemote().sendText("Hello World!!#blank");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	
    	
    	
    	
    	System.out.println("connection successed");
    }
    
    
    

    /**
     * サーバから切断
     */
    public void disconnect(){
    	
    	System.out.println("connection disconnected");
    	control.setMyPlayer(null);
    	
    }
    
    

    /**
     * 接続状態確認
     * @return
     */
    public boolean isConnected(){
        return this.isConnection;
    }

    
    
    
    
    /**************************************************/
      
}