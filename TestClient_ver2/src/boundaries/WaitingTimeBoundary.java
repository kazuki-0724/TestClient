package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;




/**
 * 回答者の待機用画面
 * @author Kazuki0724
 *
 */
class WaitingTimeBoundary extends JPanel{

	//各パーツ
    private JLabel messageLabel;
    private JLabel noticeLabel;
    private JLabel timerLabel;
    private JProgressBar timerBar;

    //文字列
    final String message = "出題者がお題を確認中です...";
    final String NOTICE = "あなたは解答者です";
    final String TIMER = "00";
    final String FONT_NAME = "MSゴシック";

    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public WaitingTimeBoundary() {
		// TODO 自動生成されたコンストラクター・スタブ

    	 LineBorder border = new LineBorder(Color.BLACK, 2, true);


         /*各インスタンス生成****************************************************/
         messageLabel = new JLabel(message);
         noticeLabel = new JLabel(NOTICE);
         timerLabel = new JLabel(TIMER);
         timerBar = new JProgressBar();

         /***********************************************************************/


         //フォント
         Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
         Font timerFont = new Font(FONT_NAME,Font.BOLD,18);
         Font messageFont = new Font(FONT_NAME, Font.BOLD ,26);


         /*レイアウト***************/
         this.setLayout(null);

         messageLabel.setBounds(150,270,540,45);
         messageLabel.setOpaque(true);
         messageLabel.setBackground(new Color(255,255,255,240));
         messageLabel.setHorizontalAlignment(JLabel.CENTER);
         messageLabel.setFont(titleFont);

         noticeLabel.setBounds(40,49,248,36);
         noticeLabel.setOpaque(true);
         noticeLabel.setBackground(new Color(0,0,0,200));
         //noticeLabel.setBorder(border);
         noticeLabel.setForeground(new Color(255,255,255,255));
         noticeLabel.setHorizontalAlignment(JLabel.CENTER);
         noticeLabel.setFont(messageFont);

         timerLabel.setBounds(625,49,30,40);
         timerLabel.setBorder(border);

         timerBar.setBounds(638, 19, 174, 44);
         timerBar.setStringPainted(true);
         timerBar.setBackground(Color.LIGHT_GRAY);
         //timerBar.setForeground(Color.RED);
         //timerBar.setBorderPainted(false);
         timerBar.setBorder(border);

         //timerBar.setBorderPainted(false);

         //画像の表示
         ImageIcon icon = new ImageIcon("./image/question.png");
         JLabel backimage = new JLabel(icon);
         backimage.setIcon(icon);

         backimage.setBounds(0,0,840,630);

         /*************************************/


         /*パネルに追加*********/
         this.add(messageLabel);
         this.add(noticeLabel);
         this.add(timerBar);
         this.add(backimage);
         /**********************/


         this.setSize(840, 630);


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