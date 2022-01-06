package boundaries;

import java.awt.Color;
import java.awt.Font;

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
         messageLabel.setFont(titleFont);

         noticeLabel.setBounds(40,49,250,36);
         noticeLabel.setBorder(border);
         noticeLabel.setFont(messageFont);

         timerLabel.setBounds(625,49,30,40);
         timerLabel.setBorder(border);

         timerBar.setBounds(650, 48, 150, 40);
         timerBar.setStringPainted(true);

         /*************************************/


         /*パネルに追加*********/
         this.add(messageLabel);
         this.add(noticeLabel);
         //this.add(timerLabel);
         this.add(timerBar);
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