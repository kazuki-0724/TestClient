package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



/**
 * 出題者用画面
 * @author Kazuki0724
 *
 */
class PainterBoundary extends JPanel{


    private Boundary boundary;

    //各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel messageLabel_3;

    private JLabel themeLabel;
    private JLabel timerLabel;
    private JLabel pointLabel;

    //idと点数を紐づけられる何かに変更*****************
    //private JList<String> list;
    //private JScrollPane sp;

    private GameCanvas gc;

    //参加プレイヤーラベル
    private JLabel[] playerLabel = new JLabel[4];


    /*クラス図にはいらない気がする********/
    final String FONT_NAME = "MS ゴシック";
    final String THEME = "お題：〇〇〇";
    final String TIMEKEEP = "※制限時間内に絵を描いてください";
    final String LEFTCLICK = "	左クリック：線を描く";
    final String RIGHTCLICK = "右クリック：消しゴム";
    final String PLAYER = "・playerLabel   xxpt";
    final String TIMER = "timer";
    final String POINT = "point";


    //private String[] sampleData = {"1st:aaaa","2nd:bbbb","3rd:cccc","4th:dddd","5th:eeee","6th:ffff"};
    /*************************************/




    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public PainterBoundary(Boundary boundary){


        this.boundary = boundary;

      //各パーツのインスタンス生成
        themeLabel = new JLabel(THEME);

        messageLabel_1 = new JLabel(TIMEKEEP);
        messageLabel_2 = new JLabel(LEFTCLICK);
        messageLabel_3 = new JLabel(RIGHTCLICK);

        timerLabel = new JLabel(TIMER);

        pointLabel = new JLabel(POINT);

        //list = new JList<>(sampleData);
        //sp = new JScrollPane();

        gc = new GameCanvas(boundary.getControl());


        for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel(PLAYER);
        }

        /********************************/



        /*レイアウト視覚化のためのもの*/
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        Font messageFont = new Font(FONT_NAME, Font.BOLD,30);
        Font playerFont = new Font(FONT_NAME, Font.PLAIN,16);
        /*****************************/




        /*レイアウト***********************************/
        this.setLayout(null);

        themeLabel.setBounds(150,10,300,40);
        themeLabel.setBorder(border);
        themeLabel.setFont(themeFont);

        messageLabel_1.setBounds(260,537,500,36);
        messageLabel_1.setBorder(border);
        messageLabel_1.setFont(messageFont);

        messageLabel_2.setBounds(20,525,150,30);
        messageLabel_2.setBorder(border);

        messageLabel_3.setBounds(20,555,150,30);
        messageLabel_3.setBorder(border);

        timerLabel.setBounds(640,10,150,40);
        timerLabel.setBorder(border);

        for(int i=0; i<4; i++) {
        	playerLabel[i].setBounds(640,220+30*i,160,30);
        	playerLabel[i].setBorder(border);
        	playerLabel[i].setFont(playerFont);
        }

        //sp.getViewport().setView(list);
        //sp.setBounds(540,200,80,200);

        gc.setBounds(20,70,600,450);

        /*********************************************/


        /*パネルに追加**********/
        this.add(gc);
        this.add(themeLabel);
        this.add(messageLabel_1);
        this.add(messageLabel_2);
        this.add(messageLabel_3);
        //this.add(sp);
        this.add(timerLabel);
        for(int i=0; i<4 ; i++) {
        	this.add(playerLabel[i]);
        }
        /***********************/



        this.setSize(840, 630);

    }


    /**
     * pointLabelのgetter
     * @return
     */
    public JLabel getPointLabel() {
        return pointLabel;
    }

    /**
     * pointLabelのsetter
     * @param pointLabel
     */
    public void setPointLabel(JLabel pointLabel) {
        this.pointLabel = pointLabel;
    }

    /**
     * timerLabelのgetter
     * @return
     */
    public JLabel getTimerLabel() {
        return timerLabel;
    }

    /**
     * timerLabelのsetter
     * @param timerLabel
     */
    public void setTimerLabel(JLabel timerLabel) {
        this.timerLabel = timerLabel;
    }

    /**
     * タイマーカウントダウン
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }

    /**
     * テーマの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {
    	this.themeLabel.setText(String.format("お題 : %s", theme));
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