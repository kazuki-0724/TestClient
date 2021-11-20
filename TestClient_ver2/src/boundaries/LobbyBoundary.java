package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import control.ClientSystemControl;
import listener.LogoutActionListener;
import listener.StartGameActionListener;



/**
 * ロビー画面
 * @author Kazuki0724
 *
 */
public class LobbyBoundary extends JPanel{

	
    private Boundary boundary;
    private ClientSystemControl control;

    //各パーツ
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel rateLabel;
    private JLabel messageLabel;
    private JLabel waitingMessageLabel;
    private JButton startGameButton;
    private JButton logoutButton;
    private JList<String> list;
    private JScrollPane sp;


    /*クラス図にはいらない気がする********/
    final String[] data = {"1st:aaaa","2nd:bbbb","3rd:cccc","4th:dddd","5th:eeee","6th:ffff"};
    final String GAME = "Paint Quiz";
    final String START = "START";
    final String LOGOUT = "LOGOUT";
    final String FONT_NAME = "MS ゴシック";

    private String message = "message";
    /*************************************/

    
    

    public LobbyBoundary(Boundary boundary, ClientSystemControl control){

        this.boundary = boundary;
        this.control = control;
        
        
        //各パーツのインスタンス生成
        // title
        titleLabel = new JLabel(GAME);
        idLabel = new JLabel();
        setIdLabel("user");
        // message
        messageLabel = new JLabel(message);
        // waitingMessageLabel
        waitingMessageLabel = new JLabel();
        waitingMessageLabel.setVisible(false);
     
        
        
        rateLabel = new JLabel(message);
        // start
        startGameButton = new JButton(START);
        startGameButton.setActionCommand(START);
        // logout
        logoutButton = new JButton(LOGOUT);
        logoutButton.setActionCommand(LOGOUT);
        // ranking
        list = new JList<>(data);
        sp = new JScrollPane();
        sp.getViewport().setView(list);

        
        //スタートゲームボタンのリスナー
        StartGameActionListener startGameActionListener = new StartGameActionListener( control, boundary, this);
        startGameButton.addActionListener(startGameActionListener);
        
        //ログアウトボタンのリスナー
        LogoutActionListener logoutActionListener = new LogoutActionListener( control, boundary );        
        logoutButton.addActionListener(logoutActionListener);
        
        
        

        /*レイアウト視覚化のためのもの************************/
        Font titleFont = new Font(FONT_NAME, Font.BOLD,40);
        Font textFont = new Font(FONT_NAME, Font.BOLD,15);
        Font helloFont = new Font(FONT_NAME, Font.BOLD,30);

        // border
        LineBorder border = new LineBorder(Color.RED, 2, true);
        /*****************************************************/


        
        
        /*レイアウト***************************************/
       

        // init Layout
        this.setLayout(null);


        titleLabel.setBounds(200, 30, 400, 40);
        titleLabel.setFont(titleFont);

        idLabel.setBounds(10, 10, 160, 25);
        idLabel.setFont(textFont);
        idLabel.setBorder(border);

        rateLabel.setBounds(10, 40, 160, 25);
        rateLabel.setFont(textFont);
        rateLabel.setBorder(border);
        
        messageLabel.setBounds(150, 100, 350, 40);
        messageLabel.setFont(helloFont);
            
        waitingMessageLabel.setBounds(400,400,200,50);
        
        startGameButton.setBounds(250, 200, 100, 20);
       
        logoutButton.setBounds(500, 10, 90, 20);
        
        sp.setBounds(200, 300, 200, 80);
        
        /***************************************************/

        
        
        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(idLabel);
        this.add(rateLabel);
        this.add(messageLabel);
        this.add(waitingMessageLabel);
        this.add(startGameButton);
        this.add(logoutButton);
        this.add(sp);
        
        /************************/


        
        this.setSize(640, 480);

    }



    public JLabel getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(String text) {
        this.idLabel.setText(String.format("ID : %s", text));
    }

    public JLabel getRateLabel() {
        return rateLabel;
    }

    public void setRateLabel(int win, int lose, int games) {
        this.rateLabel.setText(String.format("Rate : %d / %d / %d",win,lose,games ));
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(String text) {
        this.messageLabel.setText(text);
    }
    
    
    public JLabel getWaitingMessageLabel() {
		return waitingMessageLabel;
	}
    
    //サーバからあと何人なのかの情報をもらうはず
    public void updateWaitingCount(int num) {
    	this.waitingMessageLabel.setVisible(true);
		this.waitingMessageLabel.setText(String.format("4人集まると開始します。現在%d人",num));
	}
    
    
    public JButton getStartGameButton() {
		return startGameButton;
	}
    
    public void changeButtonText() {
		this.startGameButton.setText("マッチング待機中");
		
	}
    
    
     

}