package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;




/**
 * 出題者のお題確認用
 * @author Kazuki0724
 *
 */
class ConfirmationBoundary extends JPanel{



	//各パーツ
    private JLabel messageLabel;
    private JLabel noticeLabel;
    private JLabel themeLabel;
    private JLabel timerLabel;
    private JProgressBar timerBar;

    //文字列
    final String message = "お題を確認してください";
    final String NOTICE = "あなたは出題者です";
    final String TIMER = "timer";
    final String THEME = "お題： 〇〇〇";
    final String FONT_NAME = "MS ゴシック";


    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public ConfirmationBoundary(){


    	LineBorder border = new LineBorder(Color.RED, 2, true);


        //各パーツのインスタンス生成
        messageLabel = new JLabel(message);
        noticeLabel = new JLabel(NOTICE);
        themeLabel = new JLabel(THEME);
        timerLabel = new JLabel(TIMER);
        timerBar = new JProgressBar();


        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font themeFont = new Font(FONT_NAME, Font.BOLD ,38);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,26);


        /*レイアウト***************************************/
        this.setLayout(null);

        messageLabel.setBounds(150,240,530,45);
        messageLabel.setBorder(border);
        messageLabel.setFont(titleFont);

        noticeLabel.setBounds(40,40,260,36);
        noticeLabel.setBorder(border);
        noticeLabel.setFont(messageFont);

        themeLabel.setBounds(270,400,300,40);
        themeLabel.setBorder(border);
        themeLabel.setFont(themeFont);

        timerLabel.setBounds(650,40,150,40);
        timerLabel.setBorder(border);

        timerBar.setBounds(650, 85, 150, 40);
        timerBar.setStringPainted(true);


        /***************************************************/


        /*パネルに追加***********/
        this.add(messageLabel);
        this.add(noticeLabel);
        this.add(themeLabel);
        this.add(timerLabel);
        this.add(timerBar);
        /************************/



        this.setSize(840, 630);



    }



    /**
     * テーマのラベルへの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {

    	System.out.println("お題は"+theme);
    	this.themeLabel.setText(String.format("お題：%s",theme ));

    }


    /**
     * タイマーカウントダウン
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");

    	int count;

    	try {
    		count = Integer.parseInt(time);
    	}catch(Exception e) {
    		return;
    	}


    	this.timerBar.setValue(count);
    	this.timerBar.setString(time);
    }



    public void setTimerBar(int num) {
    	timerBar.setMaximum(num);
    	timerBar.setMinimum(0);
    }





}