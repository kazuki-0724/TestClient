package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import listener.LogoutActionListener;
import listener.StartGameActionListener;



/**
 * ロビー画面
 * @author Kazuki0724
 *
 */
public class LobbyBoundary extends JPanel{

	//各パーツ
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel rateLabel;
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel waitingMessageLabel;
    private JButton startGameButton;
    private JButton logoutButton;
    private JList<String> list;
    private JScrollPane sp;
    private JLabel[] playerLabel = new JLabel[5];
    //
    private DefaultTableModel tableModel;
    private JTable table;


    /*クラス図にはいらない気がする********/
    //final String[][] rankingData = {"1st:aaaa","2nd:bbbb","3rd:cccc","4th:dddd","5th:eeee"};
    final String[][] rankingData = {
    								{"1st","aaaa","100%"},
						    		{"2nd","bbbb","90%"},
						    		{"3rd","cccc","80%"},
						    		{"4th","dddd","70%"},
						    		{"5th","eeee","60%"}};

    final String GAME = "お絵描きゲーム";
    final String START = "START";
    final String LOGOUT = "ログアウト";
    final String FONT_NAME = "MS ゴシック";

    final String message = "お絵描きゲームへようこそ！";
    final String message2 = "現在のランキング";
    final String PLAYER = "playerLabel   xx%";
    final String RATE = "勝率：xx%";
    final String[] COLUMN_NAMES = {"RANK","ID","RATE"};
    /*************************************/



    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public LobbyBoundary(){


        //各パーツのインスタンス生成
        // title
        titleLabel = new JLabel(GAME);
        idLabel = new JLabel();
        setIdLabel("user");
        // message
        messageLabel_1 = new JLabel(message);
        messageLabel_2 = new JLabel(message2);
        // waitingMessageLabel
        waitingMessageLabel = new JLabel();
        waitingMessageLabel.setVisible(false);



        rateLabel = new JLabel(RATE);
        // start
        startGameButton = new JButton(START);
        startGameButton.setActionCommand(START);
        // logout
        logoutButton = new JButton(LOGOUT);
        logoutButton.setActionCommand(LOGOUT);
        // ranking
        for(int i=0; i<5; i++) {
        	playerLabel[i] = new JLabel(PLAYER);
        }


        //sp = new JScrollPane();
        //sp.getViewport().setView(list);



        tableModel = new DefaultTableModel(COLUMN_NAMES,0);
        for(int i=0;i<5;i++) {
        	tableModel.addRow(rankingData[i]);
        }

        table = new JTable(tableModel);

        sp = new JScrollPane(table);




        /*レイアウト視覚化のためのもの************************/
        Font titleFont = new Font(FONT_NAME, Font.BOLD,40);
        Font textFont = new Font(FONT_NAME, Font.BOLD,15);
        Font helloFont = new Font(FONT_NAME, Font.BOLD,30);
        Font rankingFont = new Font(FONT_NAME,Font.BOLD,24);
        Font playerFont = new Font(FONT_NAME, Font.PLAIN,14);

        // border
        LineBorder border = new LineBorder(Color.RED, 2, true);
        /*****************************************************/




        /*レイアウト***************************************/


        // init Layout
        this.setLayout(null);


        titleLabel.setBounds(200, 40, 400, 40);
        titleLabel.setBorder(border);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleFont);

        idLabel.setBounds(10, 40, 160, 25);
        idLabel.setFont(textFont);
        idLabel.setBorder(border);

        rateLabel.setBounds(10, 60, 160, 25);
        rateLabel.setFont(textFont);
        rateLabel.setBorder(border);

        messageLabel_1.setBounds(200, 180, 420, 40);
        messageLabel_1.setHorizontalAlignment(JLabel.CENTER);
        messageLabel_1.setBorder(border);
        messageLabel_1.setFont(helloFont);

        messageLabel_2.setBounds(280, 280, 240, 40);
        messageLabel_2.setHorizontalAlignment(JLabel.CENTER);
        messageLabel_2.setBorder(border);
        messageLabel_2.setFont(rankingFont);

        /*
        for(int i=0; i<5; i++) {
        	playerLabel[i].setBounds(340,355+20*i,120,20);
        	playerLabel[i].setBorder(border);
        	playerLabel[i].setFont(playerFont);
        }
        */



        //場所未確定
        //waitingMessageLabel.setBounds(400,400,200,50);

        startGameButton.setBounds(350, 500, 100, 30);

        logoutButton.setBounds(700, 50, 110, 30);

        sp.setBounds(300, 355, 240, 160);

        /***************************************************/



        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(idLabel);
        this.add(rateLabel);
        this.add(messageLabel_1);
        this.add(messageLabel_2);
        this.add(waitingMessageLabel);
        this.add(startGameButton);
        this.add(logoutButton);

        /*
        for(int i=0; i<5 ; i++) {
        	this.add(playerLabel[i]);
        }
        */

        this.add(sp);

        /************************/



        this.setSize(840, 630);


    }



    public void setRankingData(String[] rankingData) {

    	for(int i=0;i<rankingData.length;i++) {

    		String[] data = rankingData[i].split(":");
    		//String rank = data[0];
    		//String name = data[1];
    		//String rate = data[2];

    		for(int j=0;j<3;j++) {
    			//setValueAt(セルにセットするデータ,,n行,n列)
    			tableModel.setValueAt(data[j], i,j );
    		}
    	}



    }


    /**
     *
     * @param startGameActionListener
     */
    public void addStartGameButtonListener(StartGameActionListener startGameActionListener) {
    	this.startGameButton.addActionListener(startGameActionListener);
    }

    /**
     *
     * @param logoutActionListener
     */
    public void addLogoutButtonListener(LogoutActionListener logoutActionListener) {
    	this.logoutButton.addActionListener(logoutActionListener);
    }

    /**
     * idLabelのgetter
     * @return
     */
    public JLabel getIdLabel() {
        return idLabel;
    }

    /**
     * idLabelへのid埋め込み
     * @param text
     */
    public void setIdLabel(String text) {
        this.idLabel.setText(String.format("ID : %s", text));
    }

    /**
     * rateLabelのgetter
     * @return
     */
    public JLabel getRateLabel() {
        return rateLabel;
    }

    /**
     * rateLabelへの埋め込み
     * @param win
     * @param lose
     * @param games
     */
    public void setRateLabel(int win, int lose, int games) {
        this.rateLabel.setText(String.format("Rate : %d / %d / %d",win,lose,games ));
    }

    /**
     * messageLabelのgetter
     * @return
     */
    public JLabel getMessageLabel() {
        return messageLabel_1;
    }

    /**
     * messageLabelへのtext埋め込み
     * @param text
     */
    public void setMessageLabel(String text) {
        this.messageLabel_1.setText(text);
    }

    /**
     * ロビーのゲームスタートボタンのgetter
     * @return
     */
    public JButton getStartGameButton() {
		return startGameButton;
	}


    public JButton getLogoutButton() {
    	return this.logoutButton;
    }






}