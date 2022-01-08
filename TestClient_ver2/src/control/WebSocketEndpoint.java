package control;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

@ClientEndpoint
public class WebSocketEndpoint {

	private Session currentSession;
	private ClientCommunication cscc;
	Basic basic;


	public WebSocketEndpoint(ClientCommunication cscc) {
		super();
		this.cscc = cscc;
	}


	@OnOpen
	public void onOpen(Session session) {

		currentSession = session;

		/* セッション確立時の処理 */
		//System.out.println("WebSocketセッション確立");
		System.out.println("[ WebSocketEndpoint ] onOpen() : Log Connection established");
	}

	@OnMessage
	public void onMessage(String message) throws IOException {

		/* メッセージ受信時の処理 */
		//System.out.println("WebSocket受信："+message);
		System.out.println("[ WebSocketEndpoint ] onMessage() : Log message = "+message);
		cscc.receivedData(message);

	}

	@OnError
	public void onError(Throwable th) {
		/* エラー発生時の処理 */
		System.out.println("[ WebSocketEndpoint ] onError() : Error "+ th.getMessage());
	}



	@OnClose
	public void onClose(Session session) {
		/* セッション解放時の処理 */
		System.out.println("[ WebSocketEndpoint ] onClose() : Log session = "+ session.getId() + "disconnected");

	}


	public void sendMessage(Session session,String str) {

		try {
			session.getBasicRemote().sendText(str);
			//basic.sendText(str);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
