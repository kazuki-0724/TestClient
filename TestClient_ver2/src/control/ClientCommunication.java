package control;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import entity.ProcessID;






/**
 * 通信用のクラス
 * @author Kazuki0724
 *
 */
public class ClientCommunication{

	
    private ClientControl control;
    private boolean isConnection;
    
    private WebSocketEndpoint webSocketEndpoint;
	private Session session; 
    
	private static String CLM_URI = "ws://localhost:8080/TestServer/WebSocketServer";
    private static String AP_URI = "";
    
    
    
    /**
     * コンストラクタ
     * @param control
     */
    public ClientCommunication(ClientControl control){
        this.control = control;
        isConnection = false;
        this.webSocketEndpoint = new WebSocketEndpoint(this);
        
        //connect();
    }


    
    
    /**
     * データ送信メソッド。送信前に一度送信形式に変換する。
     * @param dataFlag
     * @param data
     */
    public void sendData(ProcessID processID, String data){

    	
    	String communicationFormat = handleSendData(processID,data);

    	
    	System.out.println("[Log] send data [" + processID.toString() + "] "+ communicationFormat);
    	
    	webSocketEndpoint.sendMessage(communicationFormat);
    	
    }


    

    /**
     * 送信データを通信形式に合わせる。実際の通信形式は未確定
     * @param processID
     * @param data
     * @return
     */
    public String handleSendData(ProcessID processID, String data) {
    	
    	String communicationFormat = "";
    	
    	
    	switch(processID) {
	    	
    		case LOGIN:
	    		communicationFormat = encode(ProcessID.REQUEST, ProcessID.LOGIN, data);
	    		break;
	    	
	    	case REGIST:
	    		communicationFormat = encode(ProcessID.REQUEST, ProcessID.REGISTER, data);
	    		break;
	    	
	    	case GOLOBBY:
	    		communicationFormat = encode(ProcessID.REQUEST, ProcessID.MAKELOBBY, data);
	    		break;
	    		
	    	case MATCHMAKE:
	    		communicationFormat = encode(ProcessID.REQUEST, ProcessID.JOIN, data);
	    		break;
	    	
	    	case CONFIRM:
	    		communicationFormat = encode(ProcessID.REQUEST, processID, data);
	    		break;
	    	
	    	case STARTGAME:
	    		communicationFormat = String.format("%s#%s", ProcessID.REQUEST,ProcessID.LOGIN);
	    		break;
	    	
	    	case STROKE:
	    		communicationFormat = encode(ProcessID.POSITION, ProcessID.COODINATE, data);
	    		break;
	    	
	    	case TURNRESULT:
	    		communicationFormat = String.format("%s#%s", ProcessID.REQUEST,ProcessID.LOGIN);
	    		break;
	    	
	    	case FINALRESULT:
	    		communicationFormat = String.format("%s#%s", ProcessID.REQUEST,ProcessID.REGISTER);
	    		break;
	    	
	    	case BACKTOLOBBY:
	    		communicationFormat = String.format("%s#%s", ProcessID.REQUEST,ProcessID.REGISTER);
	    		break;
	    	
	    	case TIMEOVER:
	    		communicationFormat = String.format("%s#%s", ProcessID.REQUEST,ProcessID.LOGIN);
	    		break;
	    		
	    	case CHANGE:
	    		communicationFormat = String.format("%s#%s", ProcessID.CHANGE, data);
	    		break;
	    		
	    	case MAKELOBBY:
	    		communicationFormat = encode(ProcessID.REQUEST, ProcessID.MAKELOBBY, data);
	    		break;
	    		
    	}
    	
    	   	
    	
    	return communicationFormat; 
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
    	
    	String[] tmpString = json.split("_");
    	
    	dataFlag = tmpString[0];
    	data = tmpString[1];
    	
    	control.handleData(dataFlag, data);
    	
    }
    
    
    
    
    


    /**
     * サーバに接続
     * 引数でCLMかAPの判別が必要な気がする
     */
    public void connect(){
    	
    	// 初期化のためWebSocketコンテナのオブジェクトを取得する
    	WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    			
   		// サーバー・エンドポイントのURI
    	URI uri = URI.create(CLM_URI);
    		
    	
    	// サーバー・エンドポイントとのセッションを確立する
    	try {
			session = container.connectToServer(webSocketEndpoint,uri);
		} catch (DeploymentException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	
    	
    	//メッセージをサーバへ送る
    	try {
			session.getBasicRemote().sendText(String.format("%s#%s", ProcessID.REQUEST.toString(),ProcessID.HELLOSERVER));
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
    	
    	try {
			session.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	
    }
    
    

    /**
     * 接続状態確認
     * @return
     */
    public boolean isConnected(){
        return this.session.isOpen();
    }
    
    
    
    
    public String encode(ProcessID processID_1, ProcessID processID_2, String data) {
    	
    	String format = "";
    	
    	format = String.format("%s#%s_%s", processID_1.toString(),processID_2.toString(),data);
    		
    	return format;
    }
    
    
    
    
    /**************************************************/
      
}