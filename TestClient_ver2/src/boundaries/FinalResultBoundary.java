package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import listener.ToLobbyActionListener;


/**
 * 最終結果画面
 * @author Kazuki0724
 *
 */
public class FinalResultBoundary extends JPanel{


	//各パーツ
    private JLabel titleLabel;
    private JLabel winnerLabel;
    private JLabel messageLabel;
    private JLabel timerLabel;
    private JLabel[] playerLabel = new JLabel[4];
    private JButton toLobbyButton;

    //文字列
    final String TITLE = "最終結果発表";
    final String MESSAGE = "勝者  〇〇  xxpt";
    final String MESSAGE2 = "結果";
    final String PLAYER1 = "1st playerLabel   xxpt";
    final String PLAYER2 = "2nd playerLabel   xxpt";
    final String PLAYER3 = "3rd playerLabel   xxpt";
    final String PLAYER4 = "4th playerLabel   xxpt";
    final String TOLOBBY = "ロビーへ戻る";
    final String FONT_NAME = "MS ゴシック";



    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public FinalResultBoundary(){

        //this.boundary = boundary;
        //this.control = control;


        LineBorder border = new LineBorder(Color.RED, 2, true);


        //各パーツのインスタンス生成
        titleLabel = new JLabel(TITLE);
        winnerLabel = new JLabel(MESSAGE);
        messageLabel = new JLabel(MESSAGE2);
        //timerLabel = new JLabel("");
        playerLabel[0] = new JLabel(PLAYER1);
        playerLabel[1] = new JLabel(PLAYER2);
        playerLabel[2] = new JLabel(PLAYER3);
        playerLabel[3] = new JLabel(PLAYER4);
        toLobbyButton = new JButton(TOLOBBY);
        toLobbyButton.setActionCommand("BackToLobby");

      //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,52);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,36);
        Font winnerFont = new Font(FONT_NAME, Font.BOLD,36);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,45);
        Font messageFont2 = new Font(FONT_NAME, Font.BOLD,16);


        /*レイアウト***************************************/
        this.setLayout(null);

        titleLabel.setBounds(250,80,330,52);
        titleLabel.setBorder(border);
        titleLabel.setFont(titleFont);

        winnerLabel.setBounds(275,190,275,40);
        winnerLabel.setBorder(border);
        winnerLabel.setFont(winnerFont);

        messageLabel.setBounds(360,260,100,50);
        messageLabel.setBorder(border);
        messageLabel.setFont(messageFont);

        for(int i=0; i<4; i++) {
        	playerLabel[i].setBounds(220,320+50*i,390,44);
        	playerLabel[i].setBorder(border);
        	playerLabel[i].setFont(playerFont);
        }

        //timerLabel.setBounds(470,10,150,40);
        //timerLabel.setBorder(border);

        toLobbyButton.setBounds(675,535,120,35);

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(winnerLabel);
        this.add(messageLabel);
        //this.add(timerLabel);
        this.add(toLobbyButton);
        for(int i=0; i<4 ; i++) {
        	this.add(playerLabel[i]);
        }
        /************************/



        this.setSize(840, 630);


    }


    /**
     *時間遷移オンリーならいらない
     * @param toLobbyActionListener
     *
     */
    public void addNextButtonActionListener(ToLobbyActionListener toLobbyActionListener) {
    //	this.nextButton.addActionListener(toLobbyActionListener);
    }



    /**
     * テーマのラベルへの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {

    	System.out.println("お題は"+theme);
    	this.messageLabel.setText(String.format("お題：%s",theme ));

    }


    /**
     * カウントダウンタイマー
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }


}
