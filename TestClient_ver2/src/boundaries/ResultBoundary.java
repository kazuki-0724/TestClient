package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;




/**
 * ターン結果用画面
 * @author Kazuki0724
 *
 */
class ResultBoundary extends JPanel{


	//各パーツ
    private JLabel titleLabel;
    private JLabel themeLabel;
    private JLabel timerLabel;
    private JLabel messageLabel;
    private JLabel messageLabel_2;
    private JLabel[] playerLabel = new JLabel[4];

    //文字列
    final String TITLE = "ターン結果発表";
    final String THEME = "このターンのお題：〇〇〇";
    final String MESSAGE = "現在の獲得ポイント";
    final String MESSAGE2 = "次のターンまで";
    final String TIMER = "timer";
    final String PLAYER = "・playerLabel   xxpt";
    final String FONT_NAME = "MS ゴシック";


    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public ResultBoundary(){

    	LineBorder border = new LineBorder(Color.RED, 2, true);


        //各パーツのインスタンス生成
        titleLabel = new JLabel(TITLE);
        themeLabel = new JLabel(THEME);
        messageLabel = new JLabel(MESSAGE);
        messageLabel_2 = new JLabel(MESSAGE2);
        timerLabel = new JLabel(TIMER);
        for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel(PLAYER);
        }

        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,36);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,20);
        Font messageFont2 = new Font(FONT_NAME, Font.BOLD,16);


        /*レイアウト***************************************/
        this.setLayout(null);

        titleLabel.setBounds(240,80,340,45);
        titleLabel.setBorder(border);
        titleLabel.setFont(titleFont);

        themeLabel.setBounds(210,170,400,40);
        themeLabel.setBorder(border);
        themeLabel.setFont(themeFont);

        messageLabel.setBounds(310,270,200,40);
        messageLabel.setBorder(border);
        messageLabel.setFont(messageFont);

        messageLabel_2.setBounds(640,10,150,30);
        messageLabel_2.setBorder(border);
        messageLabel_2.setFont(messageFont2);

        timerLabel.setBounds(640,40,150,30);
        timerLabel.setBorder(border);

        for(int i=0; i<4; i++) {
        	playerLabel[i].setBounds(230,320+50*i,360,44);
        	playerLabel[i].setBorder(border);
        	playerLabel[i].setFont(playerFont);
        }

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(messageLabel);
        this.add(themeLabel);
        this.add(messageLabel_2);
        this.add(timerLabel);
        for(int i=0; i<4 ; i++) {
        	this.add(playerLabel[i]);
        }
        /************************/



        this.setSize(840, 630);


    }

    /**
     * テーマの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {

    	System.out.println("お題は"+theme);
    	this.themeLabel.setText(String.format("お題：%s",theme ));

    }


    /**
     * 結果の埋め込み
     * @param text
     */
    public void setResult(String text) {
    	this.messageLabel.setText(String.format("この問題の結果%s", text));
    }



    /**
     * タイマーカウントダウン
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }

}