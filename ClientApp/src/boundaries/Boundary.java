package boundaries;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ClientControl;
import entity.BoundaryID;
import listener.AnswerSubmitActionListener;
import listener.LogoutActionListener;
import listener.StartGameActionListener;
import listener.SwitchActionListener;
import listener.ToLobbyActionListener;





/**
 * 基本となる画面(額縁役)
 * @author Kazuki0724
 *
 */
public class Boundary extends JFrame{


    // Control
    ClientControl control;

    // 現在のJPanel
    private JPanel currentPanel;

    // JPanel
    private AccountAuthentificationBoundary accountAuthentificationBoundary;
    private AccountRegistrationBoundary accountRegistrationBoundary;
    private LobbyBoundary lobbyBoundary;
    private PainterBoundary painterBoundary;
    private RespondentBoundary respondentBoundary;
    private ConfirmationBoundary confirmationBoundary;
    private WaitingTimeBoundary waitingTimeBoundary;
    private ResultBoundary resultBoundary;
    private GameStartBoundary gameStartBoundary;
    private FinalResultBoundary finalResultBoundary;






    //コンストラクタ
    public Boundary(){

    	//各インスタンス生成
        control = new ClientControl(this);


        //画面インスタンス生成
        accountAuthentificationBoundary  = new AccountAuthentificationBoundary();
        accountRegistrationBoundary = new AccountRegistrationBoundary();
        finalResultBoundary = new FinalResultBoundary();
        lobbyBoundary = new LobbyBoundary();
        painterBoundary = new PainterBoundary(this);
        respondentBoundary = new RespondentBoundary();
        confirmationBoundary = new ConfirmationBoundary();
        waitingTimeBoundary = new WaitingTimeBoundary();
        resultBoundary = new ResultBoundary();
        gameStartBoundary = new GameStartBoundary();


        //各リスナー生成
        ToLobbyActionListener toLobbyActionListener = new ToLobbyActionListener(accountAuthentificationBoundary, accountRegistrationBoundary, finalResultBoundary, this);
        SwitchActionListener switchActionListener = new SwitchActionListener(accountRegistrationBoundary, accountAuthentificationBoundary, this);
        StartGameActionListener startGameActionListener = new StartGameActionListener(lobbyBoundary, this);
        LogoutActionListener logoutActionListener = new LogoutActionListener(lobbyBoundary,this);
        AnswerSubmitActionListener answerSubmitActionListener = new AnswerSubmitActionListener(respondentBoundary, this);


        //listenerのadd
        accountAuthentificationBoundary.addLoginButtonListener(toLobbyActionListener);
        accountAuthentificationBoundary.addSwitchButtonListener(switchActionListener);
        accountRegistrationBoundary.addLoginButtonListener(toLobbyActionListener);
        accountRegistrationBoundary.addSwitchButtonListener(switchActionListener);
        lobbyBoundary.addStartGameButtonListener(startGameActionListener);
        lobbyBoundary.addLogoutButtonListener(logoutActionListener);
        respondentBoundary.addAnswerButtonListener(answerSubmitActionListener);
        finalResultBoundary.addToLobbyButtonActionListener(toLobbyActionListener);




        //JFrameに追加
        this.add(accountAuthentificationBoundary);
        accountAuthentificationBoundary.setVisible(true);

        this.add(accountRegistrationBoundary);
        accountRegistrationBoundary.setVisible(false);

        this.add(lobbyBoundary);
        lobbyBoundary.setVisible(false);

        this.add(painterBoundary);
        painterBoundary.setVisible(false);

        this.add(respondentBoundary);
        respondentBoundary.setVisible(false);

        this.add(confirmationBoundary);
        confirmationBoundary.setVisible(false);

        this.add(waitingTimeBoundary);
        waitingTimeBoundary.setVisible(false);

        this.add(resultBoundary);
        resultBoundary.setVisible(false);

        this.add(gameStartBoundary);
        gameStartBoundary.setVisible(false);

        this.add(finalResultBoundary);
        finalResultBoundary.setVisible(false);


        //初期画面
        this.currentPanel = accountAuthentificationBoundary;


    }




    public static void main(String[] args) {

        Boundary boundary = new Boundary();
        boundary.setDefaultCloseOperation(EXIT_ON_CLOSE);
        boundary.setVisible(true);
        boundary.setTitle("Client");
        boundary.setSize(840,630);
        boundary.setResizable(false);

    }




    //パネルの切り替え用メソッド(nextPanelを文字列にしてるのめんどい)
    public void changePanel(BoundaryID nextBoundary){

        // close currentPanel
        this.currentPanel.setVisible(false);

        switch(nextBoundary){

            case AccountAuthentificationBoudary:
            	updatePanel(BoundaryID.AccountAuthentificationBoudary, "");
                accountAuthentificationBoundary.setVisible(true);
                //this.setTitle("Login");
                currentPanel = accountAuthentificationBoundary;
                break;

            case AccountRegistrationBoundary:
            	updatePanel(BoundaryID.AccountRegistrationBoundary, "");
                accountRegistrationBoundary.setVisible(true);
                //this.setTitle("new Account");
                currentPanel = accountRegistrationBoundary;
                break;

            case LobbyBoundary:
            	updatePanel(BoundaryID.LobbyBoundary, "");
                lobbyBoundary.setVisible(true);
                //this.setTitle("Lobby");
                currentPanel = lobbyBoundary;
                break;

            case WaitingTimeBoundary:
                updatePanel(BoundaryID.WaitingTimeBoundary, "");
            	waitingTimeBoundary.setVisible(true);
                //this.setTitle("Waiting");
                currentPanel = waitingTimeBoundary;
                break;

            case ConfirmationBoundary:
            	updatePanel(BoundaryID.ConfirmationBoundary, "");
                confirmationBoundary.setVisible(true);
                //this.setTitle("Confirmation");
                currentPanel = confirmationBoundary;
                break;

            case PainterBoundary:
            	updatePanel(BoundaryID.PainterBoundary, "");
                painterBoundary.setVisible(true);
                //this.setTitle("Paint");
                currentPanel = painterBoundary;
                break;

            case RespondentBoundary:
            	updatePanel(BoundaryID.RespondentBoundary, "blank");
                respondentBoundary.setVisible(true);
                respondentBoundary.getAnswerButton().setEnabled(true);
                respondentBoundary.getAnswerField().setEditable(true);
                //this.setTitle("Answer");
                currentPanel = respondentBoundary;
                break;

            case ResultBoundary:
            	updatePanel(BoundaryID.ResultBoundary, "");
                resultBoundary.setVisible(true);
                //this.setTitle("Result");
                currentPanel = resultBoundary;
                break;

            case FinalResultBoundary:
            	updatePanel(BoundaryID.FinalResultBoundary, "");
            	finalResultBoundary.setVisible(true);
            	//this.setTitle("FinalResult");
            	currentPanel = finalResultBoundary;
            	break;


            case GameStartBoundary:
            	updatePanel(BoundaryID.GameStartBoundary, "");
            	gameStartBoundary.setVisible(true);
            	//this.setTitle("PlayerList");
            	currentPanel = gameStartBoundary;
            	break;


            default:
                System.out.println("[ Boundary ] changePanel() : Error BoundaryID type eror");
                break;
        }


        //画面遷移が完了したことの通知(遷移先の画面IDを送る)
        //control.communicate().sendData(ProcessID.CHANGE,nextBoundary.toString() );

    }






    /**
     *
     * @param boundary どの画面を更新するのか
     * @param text 更新内容(からの場合もある)
     */
     public void updatePanel(BoundaryID boundary, String text){


    	//textを使う場面は画面遷移を伴わない画面の更新があるとき

        switch(boundary){

        	//(text使う)認証エラーメッセージとか
            case AccountAuthentificationBoudary:
            	accountAuthentificationBoundary.updateMessageLabel(text);
                break;

            //(text使う)認証エラーメッセージとか
            case AccountRegistrationBoundary:
            	accountRegistrationBoundary.updateMessageLabel(text);
                break;

            //ロビーに遷移してきたとき
            case LobbyBoundary:
            	lobbyBoundary.setIdLabel(control.getMyPlayer().getId());
                lobbyBoundary.setRateLabel(control.getMyPlayer().getWin(),control.getMyPlayer().getLose(),control.getMyPlayer().getGames());
                lobbyBoundary.setMessageLabel("こんにちは"+control.getMyPlayer().getId()+"さん");
                //ランキング情報もセットするはず
                lobbyBoundary.setRankingData(control.getGameInfo().getRankingData());
                lobbyBoundary.getStartGameButton().setEnabled(true);
                lobbyBoundary.getLogoutButton().setEnabled(true);
                break;

            //回答者側の確認画面
            case WaitingTimeBoundary:
                break;

            //出題者側の確認画面
            case ConfirmationBoundary:
            	confirmationBoundary.setTheme(control.getGameInfo().getTheme());
                break;

            //書き手側の画面
            case PainterBoundary:
            	painterBoundary.setTheme(control.getGameInfo().getTheme());
            	painterBoundary.setTable(control.getGameInfo().getGamePlayerList());
                break;

            //(text使う)受け手側の画面
            case RespondentBoundary:
            	System.out.println("[ Boundary ] updatePanel() : Log call drawStroke() text = "+text);

            	//blankの時は初期化処理のためのupdate
            	if(!text.equals("blank"))
            		respondentBoundary.drawStroke(text);

            	break;

            //ターン結果画面
            case ResultBoundary:
            	resultBoundary.setTheme(control.getGameInfo().getTheme());
                //画面に結果をセット(gameInfoから)
            	resultBoundary.setTable(control.getGameInfo().getGamePlayerList(), control.getGameInfo().getPainterPlayerNum());
            	break;

            //最終結果画面
            case FinalResultBoundary:
            	//画面に最終結果をセット(gameInfoから)
            	finalResultBoundary.setTable(control.getGameInfo().getGamePlayerList());
            	break;

            //ゲーム待機画面
            case GameStartBoundary:
            	//画面に参加者データをセット(gameInfoから)
            	gameStartBoundary.setTable(control.getGameInfo().getGamePlayerList());
            	break;

            default:
                System.out.println("[ Boundary ] updatePanel() : Error BoundaryID type eror");
                break;
        }

    }




    /**
     * それぞれの画面のカウントダウン制御
     * @param type
     * @param time
     */
    public void updateCountDown(BoundaryID type, String time) {


    	switch(type) {

	    	case GameStartBoundary:
	    		gameStartBoundary.updateTimer(time);
	    		break;

	    	case ConfirmationBoundary:
				confirmationBoundary.updateTimer(time);
				break;

			case WaitingTimeBoundary:
				waitingTimeBoundary.updateTimer(time);
				break;

    		case PainterBoundary:
    			painterBoundary.updateTimer(time);
    			break;

    		case RespondentBoundary:
    			respondentBoundary.updateTimer(time);
    			break;

    		case ResultBoundary:
    			resultBoundary.updateTimer(time);
    			break;


    		case FinalResultBoundary:
    			//画面にタイマーの表示はいらない
    			break;



    		default:
    			System.out.println("[ Boundary ] updateCountDown() : Error BoundaryID type eror");
    			break;

    	}

    }




    /**
     * 画面のカウントダウンバーを制御
     * @param type
     * @param time
     */
    public void setTimerBar(BoundaryID type, int time) {


    	switch(type) {

	    	case GameStartBoundary:
	    		gameStartBoundary.setTimerBar(time);
	    		break;

	    	case ConfirmationBoundary:
				confirmationBoundary.setTimerBar(time);
				break;

			case WaitingTimeBoundary:
				waitingTimeBoundary.setTimerBar(time);
				break;

    		case PainterBoundary:
    			painterBoundary.setTimerBar(time);
    			break;

    		case RespondentBoundary:
    			respondentBoundary.setTimerBar(time);
    			break;

    		case ResultBoundary:
    			resultBoundary.setTimerBar(time);
    			break;




    		default:
    			System.out.println("[ Boundary ] setTimerBar() : Error BoundaryID type eror");
    			break;

    	}

    }





    /**
     * 正解者の情報を画面に反映
     * @param correctPlayerNum
     */
    public void setCorrectPlayer(int correctPlayerNum) {

    	if( currentPanel == painterBoundary ) {

			painterBoundary.setCorrectPlayer(control.getGameInfo().getGamePlayerList(), correctPlayerNum);

    	}else if( currentPanel == respondentBoundary) {

    		respondentBoundary.setCorrectPlayer(control.getGameInfo().getGamePlayerList(), correctPlayerNum);
    	}
    }



    public ClientControl getControl() {
    	return this.control;
    }







}