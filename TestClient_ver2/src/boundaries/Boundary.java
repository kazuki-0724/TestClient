package boundaries;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ClientControl;
import entity.Boundaries;





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
        

        accountAuthentificationBoundary  = new AccountAuthentificationBoundary(this,control);
        accountRegistrationBoundary = new AccountRegistrationBoundary(this,control);
        lobbyBoundary = new LobbyBoundary(this,control);
        painterBoundary = new PainterBoundary(this,control);
        respondentBoundary = new RespondentBoundary(this,control);
        confirmationBoundary = new ConfirmationBoundary(this,control);
        waitingTimeBoundary = new WaitingTimeBoundary(this,control);
        resultBoundary = new ResultBoundary(this,control);
        gameStartBoundary = new GameStartBoundary(this, control);
        finalResultBoundary = new FinalResultBoundary(this, control);


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
        boundary.setSize(640,480);
    
    }

    

   
    //パネルの切り替え用メソッド(nextPanelを文字列にしてるのめんどい)
    public void changePanel(Boundaries nextBoundary){

        // close currentPanel
        this.currentPanel.setVisible(false);

        switch(nextBoundary){

            case AccountAuthentificationBoudary:
            	updatePanel(Boundaries.AccountAuthentificationBoudary, "");
                accountAuthentificationBoundary.setVisible(true);
                this.setTitle("Login");
                currentPanel = accountAuthentificationBoundary;
                break;

            case AccountRegistrationBoundary:
            	updatePanel(Boundaries.AccountRegistrationBoundary, "");
                accountRegistrationBoundary.setVisible(true);
                this.setTitle("new Account");
                currentPanel = accountRegistrationBoundary;
                break;

            case LobbyBoundary:
            	updatePanel(Boundaries.LobbyBoundary, "");    
                lobbyBoundary.setVisible(true);
                this.setTitle("Lobby");
                currentPanel = lobbyBoundary;
                break;

            case WaitingTimeBoundary:
                updatePanel(Boundaries.WaitingTimeBoundary, "");
            	waitingTimeBoundary.setVisible(true);
                this.setTitle("Waiting");
                currentPanel = waitingTimeBoundary;   
                break;

            case ConfirmationBoundary:
            	updatePanel(Boundaries.ConfirmationBoundary, "");
                confirmationBoundary.setVisible(true);
                this.setTitle("Confirmation");
                currentPanel = confirmationBoundary;
                break;
                
            case PainterBoundary:
            	updatePanel(Boundaries.PainterBoundary, "");
                painterBoundary.setVisible(true);
                this.setTitle("Paint");
                currentPanel = painterBoundary;
                break;

            case RespondentBoundary:
            	updatePanel(Boundaries.RespondentBoundary, "");
                respondentBoundary.setVisible(true);
                this.setTitle("Answer");
                currentPanel = respondentBoundary;
                break;

            case ResultBoundary:
            	updatePanel(Boundaries.ResultBoundary, "");
                resultBoundary.setVisible(true);
                this.setTitle("Result");
                currentPanel = resultBoundary;
                break;

            case FinalResultBoundary:
            	updatePanel(Boundaries.FinalResultBoundary, "");
            	finalResultBoundary.setVisible(true);
            	this.setTitle("FinalResult");
            	currentPanel = finalResultBoundary;
            	break;
            	
            	
            case GameStartBoundary:
            	updatePanel(Boundaries.GameStartBoundary, "");
            	gameStartBoundary.setVisible(true);
            	this.setTitle("PlayerList");
            	currentPanel = gameStartBoundary;
            	break;
                
                
            default:
                System.out.println("error");
                break;
        }
        
        
        //何かしら画面遷移が完了したことの通知をサーバに送る予定
        //control.communicate().sendData("panelChange complete","blank" );

    }
    
    
    
    
    
    
    /**
     * 
     * @param boundary どの画面を更新するのか
     * @param text 更新内容(からの場合もある)
     */
     public void updatePanel(Boundaries boundary, String text){

    	
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
                //ランキング情報もセットするはず
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
                break;

            //(text使う)受け手側の画面
            case RespondentBoundary:
            	System.out.println("update");
            	respondentBoundary.drawStroke(text);
                break;

            //ターン結果画面
            case ResultBoundary:
            	resultBoundary.setTheme(control.getGameInfo().getTheme());
                //画面に結果をセット(gameInfoから)
            	resultBoundary.setResult(control.getGameInfo().getResult());
            	break;

            //最終結果画面
            case FinalResultBoundary:
            	//画面に最終結果をセット(gameInfoから)
            	break;
            	
            //ゲーム待機画面	
            case GameStartBoundary:
            	//画面に参加者データをセット(gameInfoから)
            	break;
                                
            default:
                System.out.println("[Error] Boundary.updatePanel()");
                break;
        }

    }
    
    
    
    
    
    
    
    //それぞれの画面のカウントダウン制御 
    public void updateCountDown(Boundaries type, String time) {
    	
    	
    	
    	switch(type) {
    	
	    	case GameStartBoundary:
	    		gameStartBoundary.updateTimer(time);
	    		break;
	    		
	    	case ConfirmationBoundary:
				confirmationBoundary.updateTimer(time);	
				break;
			
			case WaitingTimeBoundary:
				gameStartBoundary.updateTimer(time);
				break;
			
    		case PainterBoundary:
    			painterBoundary.updateTimer(time);	
    			break;
    			
    		case RespondentBoundary:
    			respondentBoundary.updateTimer(time);
    			break;
    			
    		case ResultBoundary:
    			break;
    			
    		
    			
    			
    		default:
    			System.out.println("[Error] Boundary updateCountDown type Error");
    	
    	}
    	
    }
    
    
    
    
    

}