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
	private Session CLMSession;
	private Session APSession;
	private Session currentSession;



	//実機運用ではここのlocalhostをサーバのIPアドレスにする
	//IPconfigで調べる
	private final String CLM_URI = "ws://172.30.26.161:8080/app/clm";
    private final String AP_URI = "ws://172.30.26.161:8081/app/ap";

    private final String REQUEST = "REQUEST";
    private final String REPLY = "REPLY";
    private final String POSITION = "POSITION";
    private final String CONNECT = "CONNECT";


    /**
     * コンストラクタ
     * @param control
     */
    public ClientCommunication(ClientControl control){
        this.control = control;
        isConnection = false;
        this.webSocketEndpoint = new WebSocketEndpoint(this);
    }




    /**
     * データ送信メソッド。送信前に一度送信形式に変換する。
     * @param dataFlag
     * @param data
     */
    public void sendData(ProcessID processID, String data){


    	String communicationFormat = handleSendData(processID,data);


    	System.out.println("[Log] send data [" + processID.toString() + "] "+ communicationFormat);

    	if(currentSession.isOpen()) {
    		webSocketEndpoint.sendMessage(currentSession,communicationFormat);

    	}else {
    		System.out.println("[Log] currentSession is closed");
    	}
    }




    /**
     * 送信データを通信形式に合わせる。実際の通信形式は未確定
     * @param processID
     * @param data
     * @return
     */
    public String handleSendData(ProcessID processID, String data) {

    	String communicationFormat = "";


    	System.out.println("[Log handleSendData() ] data = "+ data);

    	switch(processID) {

    		//サーバ接続時
    		case CONNECTCLM:
    			communicationFormat = encode(CONNECT, ProcessID.CONNECTCLM.toString());
    			break;

    		case CONNECTAP:
    			communicationFormat = encode(CONNECT, ProcessID.CONNECTAP.toString(),control.getMyPlayer().getId());
    			break;

    		case CONNECTAP_OK:
    			communicationFormat = encode(REPLY, ProcessID.CONNECTAP_OK.toString());
    			break;

    		//ログイン時
    		case LOGIN:
	    		communicationFormat = encode(REQUEST, "LOGIN", data);
	    		break;

	    	//新規登録時
	    	case REGISTER:
	    		communicationFormat = encode(REQUEST, "REGISTER", data);
	    		break;

	    	//ロビー遷移要求
	    	case MAKELOBBY:
	    		communicationFormat = encode(REQUEST, "MAKELOBBY", data);
	    		break;

	    	//マッチ参加要求
	    	case JOIN:
	    		communicationFormat = encode(REQUEST, "JOIN", control.getMyPlayer().getId());
	    		break;

	    	//座標
	    	case STROKE:
	    		communicationFormat = encode(POSITION, "COODINATE", data);
	    		break;

	    	//ゲームからロビーに戻る
	    	case BACKTOLOBBY:
	    		communicationFormat = encode(REQUEST,"");
	    		break;

	    	//時間経過を通知
	    	case TIMEOVER:
	    		communicationFormat = encode(REQUEST,"TIMEOVER",data);
	    		break;

	    	//画面遷移等の通知系
	    	case CHANGE:
	    		communicationFormat = encode(REPLY, "");
	    		break;

	    	case LOGOUT:
	    		communicationFormat = encode(REQUEST,"LOGOUT",control.getMyPlayer().getId());
	    		break;

	    	case STARTGAME_OK:
	    		communicationFormat = encode(REPLY, "STARTGAME_OK");
	    		break;

	    	case STARTTURN_OK:
	    		communicationFormat = encode(REPLY, "STARTTURN_OK");
	    		break;

	    	case QTIMESTART_OK:
	    		communicationFormat = encode(REPLY, "QTIMESTART_OK");
	    		break;

	    	case ANSWER:
	    		communicationFormat = encode(REQUEST, "ANSWERER",control.getGameInfo().getRoomID());
	    		break;

	    	case ANSWER_OK:
	    		communicationFormat = encode(REQUEST, "ANSWER_OK");
	    		break;

	    	case POSITION:
	    		communicationFormat = encode(POSITION,data);
	    		break;

	    	case TURNRESULT_OK:
	    		communicationFormat = encode(REPLY, "TURNRESULT_OK");
	    		break;

	    	case FINISHGAME_OK:
	    		communicationFormat = encode(REPLY, "FINISHGAME_OK");
	    		break;

	    	case TIMEOUT_OK:
	    		communicationFormat = encode(REPLY, "TIMEOUT_OK");
	    		break;

	    	default:
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
     * 受け取ったデータの解析
     * @param json
     */
    public void handleReceivedData(String message) {


    	String dataFlag;
    	String data;

    	String[] tmpString = message.split("#");

    	dataFlag = tmpString[0];
    	data = tmpString[1];

    	control.handleData(dataFlag, data);

    }







    /**
     * サーバに接続
     * CLMのSessionは張りっぱなし
     */
    public void connect(int type){


    	URI uri = null;
    	// 初期化のためWebSocketコンテナのオブジェクトを取得する
    	WebSocketContainer container = ContainerProvider.getWebSocketContainer();


    	if(type == 0) {

    		// CLMのURI
    		uri = URI.create(CLM_URI);

    		try {
    			CLMSession = container.connectToServer(webSocketEndpoint,uri);
    		} catch (DeploymentException | IOException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}

    		currentSession = CLMSession;



    		sendData(ProcessID.CONNECTCLM,"blank");


    	}else if(type == 1){
    		// APのURI
    		uri = URI.create(AP_URI);

    		try {
    			APSession = container.connectToServer(webSocketEndpoint,uri);
    		} catch (DeploymentException | IOException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}

    		currentSession = APSession;

    		sendData(ProcessID.CONNECTAP,"blank");



    	}else {
    		System.out.println("[Error connect()] type error");
    	}

    	if( currentSession.isOpen()) {
    		System.out.println("Connection Successed");
    	}else {
    		System.out.println("Connection failed");
    	}





    }




    /**
     * サーバから切断
     */
    public void disconnect(int type){

    	System.out.println("connection disconnected");

    	if(type == 0) {


    		try {
    			CLMSession.close();
    		} catch (IOException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}

    		currentSession = null;

    	}else if(type == 1) {

    		currentSession = CLMSession;

    		try {
    			APSession.close();
    		} catch (IOException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}
    	}



    }



    /**
     * 接続状態確認
     * @return
     */
    public boolean isConnected(){
        return this.currentSession.isOpen();
    }



    /**
     *
     * @param processID_1 REQUEST, REPLY, POSITIONのどれか
     * @param processID_2 具体的な処理内容
     * @param data
     * @return
     */
    public String encode(String processID_1, String processID_2, String data) {

    	String format = "";

    	format = String.format("%s#%s_%s", processID_1,processID_2,data);

    	return format;
    }


    /**
     *
     * @param processID_1 POSITION
     * @param data 座標データ
     * @return
     */
    public String encode(String processID_1, String data) {

    	String format = "";

    	format = String.format("%s#%s", processID_1,data);

    	return format;
    }



    /**************************************************/

}