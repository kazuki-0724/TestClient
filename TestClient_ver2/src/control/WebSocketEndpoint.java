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

		basic = session.getBasicRemote();

		/* セッション確立時の処理 */
		System.out.println("WebSocketセッション確立");
	}

	@OnMessage
	public void onMessage(String message) throws IOException {

		/* メッセージ受信時の処理 */
		System.out.println("WebSocket受信："+message);
		cscc.receivedData(message);

	}

	@OnError
	public void onError(Throwable th) {
		/* エラー発生時の処理 */
		System.out.println("WebSocketエラー：" + th.getMessage());
	}



	@OnClose
	public void onClose(Session session) {
		/* セッション解放時の処理 */
		System.out.println("["+ session.getId()+ "]"+"disconnected");
		System.out.println("[Log] disconnect");
	}


	public void sendMessage(String str) {

		try {
			basic.sendText(str);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
