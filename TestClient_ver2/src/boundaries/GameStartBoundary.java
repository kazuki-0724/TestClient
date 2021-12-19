package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import entity.GamePlayer;


/**
 * ゲーム開始前画面
 * @author Kazuki0724
 *
 */
public class GameStartBoundary extends JPanel{


	//各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel timerLabel;


	//参加プレイヤーラベル
    private JLabel[] playerLabel = new JLabel[4];


    //文字列
    final String message1 = "参加プレイヤー確定";
    final String message2 = "ゲームを開始します";
    final String TIMER = "timer";
    final String PLAYER = "・playerLabel   xx%";
    final String FONT_NAME = "MS ゴシック";

    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public GameStartBoundary(){

    	LineBorder border = new LineBorder(Color.RED, 2, true);



        /*for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel(String.format("%s","player"));
        }*/

        //各パーツのインスタンス生成
        messageLabel_1 = new JLabel(message1);
        messageLabel_2 = new JLabel(message2);
        timerLabel = new JLabel(TIMER);
        for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel(PLAYER);
        }

        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,30);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,20);


        /*レイアウト***************************************/
        this.setLayout(null);

        messageLabel_1.setBounds(180,160,430,45);
        messageLabel_1.setBorder(border);
        messageLabel_1.setFont(titleFont);

        messageLabel_2.setBounds(600,30,200,30);
        messageLabel_2.setBorder(border);
        messageLabel_2.setFont(messageFont);


        timerLabel.setBounds(600,60,150,30);
        timerLabel.setBorder(border);

        for(int i=0; i<4; i++) {
        	playerLabel[i].setBounds(250,280+50*i,300,38);
        	playerLabel[i].setBorder(border);
        	playerLabel[i].setFont(playerFont);
        }

        /***************************************************/


        /*パネルに追加***********/
        this.add(messageLabel_1);
        this.add(messageLabel_2);
        this.add(timerLabel);
        for(int i=0; i<4 ; i++) {
        	this.add(playerLabel[i]);
        }
        /************************/



        this.setSize(840, 630);


    }




    /**
     * タイマーカウントダウン
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }



    public void setPlayersLabel(List<GamePlayer> list ) {

    	for(int i=0;i<list.size();i++) {
    		playerLabel[i].setText( String.format("[%s] %s", list.get(i).getId(), list.get(i).getRate()) );
    	}

    }




}
