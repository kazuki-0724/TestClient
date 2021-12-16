package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
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

    //文字列
    final String message = "出題者がお題を確認中です...";
    final String NOTICE = "あなたは解答者です";
    final String TIMER = "timer";
    final String FONT_NAME = "MSゴシック";

    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public WaitingTimeBoundary() {
		// TODO 自動生成されたコンストラクター・スタブ

    	 LineBorder border = new LineBorder(Color.RED, 2, true);


         /*各インスタンス生成****************************************************/
         messageLabel = new JLabel(message);
         noticeLabel = new JLabel(NOTICE);
         timerLabel = new JLabel(TIMER);


         /***********************************************************************/


         //フォント
         Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
         Font messageFont = new Font(FONT_NAME, Font.BOLD ,26);


         /*レイアウト***************/
         this.setLayout(null);

         messageLabel.setBounds(150,270,540,45);
         messageLabel.setBorder(border);
         messageLabel.setFont(titleFont);

         noticeLabel.setBounds(40,40,260,36);
         noticeLabel.setBorder(border);
         noticeLabel.setFont(messageFont);

         timerLabel.setBounds(650,40,150,40);
         timerLabel.setBorder(border);

         /*************************************/


         /*パネルに追加*********/
         this.add(messageLabel);
         this.add(noticeLabel);
         this.add(timerLabel);
         /**********************/


         this.setSize(840, 630);


    }



    /**
     * タイマーカウントダウン
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }



}