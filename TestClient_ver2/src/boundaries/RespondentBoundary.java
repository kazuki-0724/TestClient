package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import listener.AnswerSubmitActionListener;



/**
 * 回答者用画面
 * @author Kazuki0724
 *
 */
public class RespondentBoundary extends JPanel{



    //各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel messageLabel_3;

    private JLabel timerLabel;
    private JLabel pointLabel;

    private JList<String> list;
    private JScrollPane sp;

    private AnswerCanvas ac;

    private JTextField answerField;
    private JButton answerButton;



    //参加プレイヤーラベル
    private JLabel[] playerLabel = new JLabel[4];
    /**********************************/


    /*クラス図にはいらない気がする********/
    final String FONT_NAME = "MS ゴシック";
    final String THEME = "お題 : ";
    final String TIMEKEEP = "制限時間以内に回答してください";
    String[] sampleData = {"1st:aaaa","2nd:bbbb","3rd:cccc","4th:dddd","5th:eeee","6th:ffff"};
    /*******************************************/


    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public RespondentBoundary(){



        //各パーツのインスタンス生成
        messageLabel_1 = new JLabel(TIMEKEEP);

        timerLabel = new JLabel("timer layout");

        // ranking
        list = new JList<>(sampleData);
        sp = new JScrollPane();
        sp.getViewport().setView(list);

        // answer input field
        answerField = new JTextField(10);

        // submit button
        answerButton = new JButton("submit");
        answerButton.setActionCommand("answer");

        ac = new AnswerCanvas();

        for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel("playerLabel");
        }


        /**************************************************/





        //レイアウト可視化のため
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        this.setLayout(null);
        /****************************************************/



        /*レイアウト*******************************/
        this.setLayout(null);

        messageLabel_1.setBounds(150,10,300,40);
        messageLabel_1.setBorder(border);
        messageLabel_1.setFont(themeFont);

        timerLabel.setBounds(470,10,150,40);
        timerLabel.setBorder(border);

        sp.setBounds(540,50,80,200);

        answerField.setBounds(380,410,200,30);

        answerButton.setBounds(580,410,50,30);

        ac.setBounds(20,50,500,350);

        /******************************************/




        /*パネルに追加*****************/
        this.add(ac);
        this.add(messageLabel_1);
        this.add(timerLabel);
        this.add(sp);
        this.add(answerField);
        this.add(answerButton);
        /******************************/



        this.setSize(640, 480);

    }


    /**
     *
     * @param answerSubmitActionListener
     */
    public void addAnswerButtonListener(AnswerSubmitActionListener answerSubmitActionListener) {
    	this.answerButton.addActionListener(answerSubmitActionListener);
    }



    /**
     * カウントダウンタイマー
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }


    /**
     * 送られてきた座標データによって描画
     * @param stroke
     */
    public void drawStroke(String stroke) {

    	int type,s_x,s_y,e_x,e_y;

    	String[] tmpString = stroke.split("_");

    	type = Integer.parseInt(tmpString[0]);
    	s_x = Integer.parseInt(tmpString[1]);
    	s_y = Integer.parseInt(tmpString[2]);
    	e_x = Integer.parseInt(tmpString[3]);
    	e_y = Integer.parseInt(tmpString[4]);


    	ac.setLine(type,s_x,s_y,e_x,e_y);

    }

    /**
     * answerFieldのgetter
     * @return
     */
    public JTextField getAnswerField() {
		return answerField;
	}


    public void setPlayersLabel(String[] data ) {

    	//(name_rate )* 4
    	/**
    	 * split(_)
    	 */


    	for(int i=0; i<4;i++) {
    		playerLabel[i].setText(String.format("%s : %s", "player"+i,"rate"));
    	}

    }


}