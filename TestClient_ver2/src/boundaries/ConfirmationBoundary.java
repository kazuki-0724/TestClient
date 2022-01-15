package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
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
    final String TIMER = "00";
    final String THEME = "お題： 〇〇〇";
    final String FONT_NAME = "MS ゴシック";


    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public ConfirmationBoundary(){


    	LineBorder border = new LineBorder(Color.BLACK, 2, true);


        //各パーツのインスタンス生成
        messageLabel = new JLabel(message);
        noticeLabel = new JLabel(NOTICE);
        themeLabel = new JLabel(THEME);
        timerLabel = new JLabel(TIMER);
        timerBar = new JProgressBar();


        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font themeFont = new Font(FONT_NAME, Font.BOLD ,38);
        Font timerFont = new Font(FONT_NAME,Font.BOLD,18);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,26);


        /*レイアウト***************************************/
        this.setLayout(null);

        messageLabel.setBounds(150,240,540,45);
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(255,255,255,240));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(titleFont);

        noticeLabel.setBounds(40,40,248,36);
        noticeLabel.setOpaque(true);
        noticeLabel.setBackground(new Color(0,0,0,200));
        //noticeLabel.setBorder(border);
        noticeLabel.setForeground(new Color(255,255,255,255));
        noticeLabel.setHorizontalAlignment(JLabel.CENTER);
        noticeLabel.setFont(messageFont);

        themeLabel.setBounds(140,400,560,40);
        themeLabel.setHorizontalAlignment(JLabel.CENTER);
        themeLabel.setOpaque(true);
        themeLabel.setBackground(new Color(255,255,255,240));
        themeLabel.setFont(themeFont);

        timerLabel.setBounds(625,39,30,40);

        timerBar.setBounds(638, 19, 174, 44);
        timerBar.setStringPainted(true);
        timerBar.setBackground(Color.LIGHT_GRAY);
        //timerBar.setForeground(Color.RED);
        //timerBar.setBorderPainted(false);
        timerBar.setBorder(border);

        //画像の表示
        ImageIcon icon = new ImageIcon("./image/question.png");
        JLabel backimage = new JLabel(icon);
        backimage.setIcon(icon);

        backimage.setBounds(0,0,840,630);


        /***************************************************/


        /*パネルに追加***********/
        this.add(messageLabel);
        this.add(noticeLabel);
        this.add(themeLabel);
        this.add(timerBar);
        this.add(backimage);
        /************************/



        this.setSize(840, 630);



    }



    /**
     * テーマのラベルへの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {

    	//System.out.println("お題は"+theme);
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